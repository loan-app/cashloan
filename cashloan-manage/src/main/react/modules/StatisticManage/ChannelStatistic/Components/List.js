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
      url: '/modules/manage/statistic/listChannelStatistic.htm',
      data: params,

      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        //pagination.total = result.page.total;

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
      title: '注册数',
      dataIndex: 'userRegister',
    }, {
          title: '老客申请笔数',
          dataIndex: 'oldBorrowApplyCount'
    },{
      title: '老客人工审核通过数',
      dataIndex: 'newMachineAuditPassCount'
    }, {
      title: '老客人工审核拒绝数',
      dataIndex: "newMachineAuditNotPassCount"
    }, {
        title: '首贷放款数',
        dataIndex: "firstLoadCount",
    }, {
        title: '复贷放款数',
        dataIndex: "againLoadCount",
    }, {
        title: '到期逾期笔数',
        dataIndex: "expireOverdueCount",
    }, {
        title: '到期首逾笔数',
        dataIndex: "firstExpireOverdueCount",
    }, {
      title: '到期展期数',
        dataIndex: "extendCount",
    },{
        title: '到期逾期数',
        dataIndex: "extendOverdueCount",
    }, {
        title: '到期首贷展期数',
        dataIndex: "firstExtendCount",
    }, {
        title: '到期首贷展期逾期数',
        dataIndex: "firstExtendOverdueCount",
    }, {
        title: '到期首贷放款笔数',
        dataIndex: "firstExpireLoadCount",
    }, {
        title: '到期复贷放款笔数',
        dataIndex: "againExpireLoadCount",
    }, {
        title: '新客申请量',
        dataIndex: "newBorrowApplyCount",
    }, {
        title: '新客机审通过数',
        dataIndex: "newMachineAuditPassCount",
    }, {
        title: '新客机审拒绝数',
        dataIndex: "newMachineAuditNotPassCount",
    }, {
        title: '新客人工通过数',
        dataIndex: "newReviewPassCount",
    }, {
        title: '新客人工拒绝数',
        dataIndex: "newReviewNotPassCount",
    }, {
        title: '到期复贷逾期笔数',
        dataIndex: "againExpireOverdueCount",
    }, {
        title: '新客机审通过率',
        dataIndex: "machineAuditPassRate",
    }, {
        title: '新客机审拒绝率',
        dataIndex: "machineAuditNotPassRate",
    }, {
        title: '新客人工复审通过率',
        dataIndex: "newReviewPassRate",
    }, {
        title: '新客人工复审拒绝率',
        dataIndex: "newReviewNotPassRate",
    },{
        title: '新客人工复审通过率',
        dataIndex: "oldReviewPassRate",
    }, {
        title: '新客人工复审拒绝率',
        dataIndex: "oldReviewNotPassRate",
    }, {
        title: '首逾率',
        dataIndex: "firstOverdueRate",
    }, {
        title: '总逾期率',
        dataIndex: "overdueRate",
    }, {
        title: '复贷逾期率',
        dataIndex: "againOverdueRate",
    }, {
        title: '新客放款率',
        dataIndex: "loadRate",
    }];
    var state = this.state;
    return (
      <div className="block-panel">
          {/*<div className="actionBtns" style={{ marginBottom: 16 }}>*/}
              {/*<span> 消费总金额：{this.state.totalFee}</span>*/}
              {/*<dev></dev>*/}
          {/*</div>*/}

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