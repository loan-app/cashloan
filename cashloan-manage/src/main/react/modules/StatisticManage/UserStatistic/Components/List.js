import React from 'react'
import {Modal, Table} from 'antd';

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
      visibleAdd:false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      visibleAdd:false
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record
    },()=>{
      this.refs.CustomerWin.setFieldsValue(record);
    });
  },
  //新增
  addModal(title, record, canEdit){
      this.setState({
        visibleAdd:true,
        title:title,  
      })

  },
  rowKey(record) {
    return record.id;
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
      url: '/modules/manage/statistic/listUserStatistic.htm',
      data: params,

      callback: (result) => {
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
    var columns = [{
      title: '统计时间',
      dataIndex: 'countTimeStr',
    }, {
        title: '注册人数',
        dataIndex: 'userRegister',
    },{
      title: '实名认证人数',
      dataIndex: 'authCount',
    }, {
      title: '通讯录认证数',
      dataIndex: "contactCount",
    }, {
        title: '银行卡绑定数',
        dataIndex: "bankCount",
    }, {
        title: '运营商认证数',
        dataIndex: "phoneCount",
    }, {
        title: '申请总数',
        dataIndex: "borrowApplyCount",
    }, {
        title: '新客借款数',
        dataIndex: "newBorrowCount",
    }, {
        title: '老客借款数',
        dataIndex: "oldBorrowCount",
    }, {
        title: '新客下款数',
        dataIndex: "newLoadCount",
    }, {
        title: '老客下款数',
        dataIndex: "oldLoadCount",
    }, {
        title: '下款总数',
        dataIndex: "loadCount",
    },{
        title: '注册下款率',
        dataIndex: "-- --",
        render(text,record){

          if (!record.userRegister) {
            return "0%";
          }else if(record.newBorrowCount === 0){
              return record.newLoadCount/record.userRegister*100+"%"
          }else {
              return (record.newLoadCount/record.userRegister*100).toFixed(2)+"%"
          }
        }
    }];
    var state = this.state;
    return (
      <div className="block-panel">

           <Table columns={columns} rowKey={this.rowKey}
             onRowClick={this.onRowClick}
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
         </div>
    );
  }
})