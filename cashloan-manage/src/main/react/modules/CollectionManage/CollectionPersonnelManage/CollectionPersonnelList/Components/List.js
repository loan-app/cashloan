import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddCollectionPersonnel from './AddCollectionPersonnel';
import Reset from "../../../../SystemMng/UserMang/Components/Reset";
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
      visibleAdd: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      visibleAdd: false
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    if (title == '新增') {
        record = null
    }
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record
    }/*, () => {
      this.refs.CustomerWin.setFieldsValue(record);
    }*/);
  },
  //新增
  addModal(title, record, canEdit) {
    this.setState({
      visibleAdd: true,
      title: title,
    })

  },
  rowKey(record) {
    return record.userId;
  },

  //分页
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
    this.refreshList();
  },
  fetch(params = {}) {
    this.setState({
      loading: true
    });
    if (!params.pageSize) {
      var params = {};
      params = {
        pageSize: 10,
        current: 1,
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/borrow/repay/urge/collection/member.htm',
      data: params,
      method: 'get',
      callback: (result) => {
        //console.log(result.data);
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        this.setState({
          loading: false,
          data: result.data,
          pagination
        });
      }
    });
  },
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  refreshList() {
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
    });
    this.fetch(params);
  },
  componentDidMount() {
    this.fetch();
  },

  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id],
      selectedrecord: record
    });
  },

  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      selectedRowKeys,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '真实姓名',
      dataIndex: 'name',
    }, {
      title: '用户名',
      dataIndex: "userName",
    }, {
      title: '工号',
      dataIndex: 'jobNumber'
    }, {
      title: '催收订单总数',
      dataIndex: 'count',
    }, {
      title: '待催收订单数',
      dataIndex: "waitCount",
    }, {
      title: '催收成功订单数',
      dataIndex: "successCount",
    }, {
      title: '昨日催收次数',
      dataIndex: 'yesterdayCount',
    }];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
            <button className="ant-btn" onClick={this.showModal.bind(this,'新增催收专员',null,true)}>
                新增
            </button>
        </div>
        <Table columns={columns} rowKey={this.rowKey}
          onRowClick={this.onRowClick}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange} />
          <AddCollectionPersonnel ref="AddCollectionPersonnel"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
                                                                  canEdit={state.canEdit}/>
          <Reset ref="Reset"  visible={state.visible1}    title={state.title} hideModal={me.hideModal} record={state.record}/>
      </div>
    );
  }
})