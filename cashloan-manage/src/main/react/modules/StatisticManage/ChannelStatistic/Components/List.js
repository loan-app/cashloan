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
    return record.channelCode;
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
      url: '/modules/manage/statistic/listChannelStatistic.htm',
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
        title: '渠道标识',
        dataIndex: 'channelCode',
    }, {
        title: '渠道名称',
        dataIndex: 'channelName',
    }, {
        title: '注册数',
        dataIndex: 'userRegister',
    },{
        title: '新客申请笔数',
        dataIndex: 'newBorrowApplyCount'
    },{
        title: '老客申请笔数',
        dataIndex: 'oldBorrowApplyCount',
        render(text, record) {
            return record.borrowApplyCount - record.newBorrowApplyCount
        }
    }, {
        title: '新客下款数',
        dataIndex: "firstLoadCount",
    }, {
        title: '老客下款数',
        dataIndex: "againLoadCount",
    }, {
        title: '新客机审通过率',
        dataIndex: "machineAuditPassRate",
        render(text,record){
            return record.machineAuditPassRate +"%"
        }
    },  {
        title: '新客转化率',
        dataIndex: "newTransformRate",
        render(text,record){
            return record.newTransformRate +"%"
        }
    }, {
        title: '新客下款/注册数',
        dataIndex: "newLoadRegisterRate",
        render(text,record){
            return record.newLoadRegisterRate +"%"
        }
    }, {
        title: '新客下款/申请数',
        dataIndex: "newLoadApplyRate",
        render(text,record){
            return record.newLoadApplyRate +"%"
        }
    }, {
        title: '新客首逾率',
        dataIndex: "firstOverdueRate",
        render(text,record){
           return record.firstOverdueRate+"%"
        }
    }, {
        title: '总逾期率',
        dataIndex: "overdueRate",
        render(text,record){
            return record.overdueRate +"%"
        }
    }];
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