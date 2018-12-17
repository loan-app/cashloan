import React from 'react';
import {
  Table,
} from 'antd';
const objectAssign = require('object-assign');
var Tab9 = React.createClass({
  getInitialState() {
    return {
      loading: false,
      data: [],
      pagination: {},
      selectedRowKeys: [],
    };
  },
  rowKey(record) {
    return record.inviteId;
  },
  componentWillReceiveProps(nextProps){
    if(nextProps.activeKey == '9'){
      this.fetch();
    }
  },
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    pager.current = pagination.current;
    pager.pageSize = pagination.pageSize;
    pager.userId = this.props.record.borrowUserId,
    this.setState({
      pagination: pager,
    });
    this.fetch(pager);
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
        userId: this.props.record.borrowUserId,
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/operatorVoiceCnt/listVoiceCnt.htm',
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
          data: result.data.list,
          pagination
        });
      }
    });
  },
  onRowClick(record) {
      var id = record.inviteId;
      this.setState({
          selectedRowKeys: [id],
          records: record
      });
  },
  render() {
    const rowSelection = {
        selectedRowKeys: this.state.selectedRowKeys,
    };
    var columns = [{
      title: '运营商号码',
      dataIndex: "peerNum",
    }, {
      title: '运营商',
      dataIndex: "peerName",
    }, {
      title: '号码归属地',
      dataIndex: "city",
    }, {
      title: '通讯录联系号码',
      dataIndex: "contactPhone",
    }, {
        title: '通讯录联系人姓名',
        dataIndex: "contactName",
    },{
        title: '近6个月联系次数/联系时间',
        dataIndex: "callCntNum",
    },{
        title: '近6个月主叫次数/主叫时间',
        dataIndex: "dialCntNum",
    },{
        title: '近6个月被叫次数/被叫时间',
        dataIndex: "dialedCntNum",
    }, {
      title: '统计时间',
      dataIndex: "createtime",
    }];
    return (<div className="block-panel">
              <Table columns={columns} rowKey={this.rowKey}  
              dataSource={this.state.data}
              onRowClick={this.onRowClick}
              pagination={this.state.pagination}
              loading={this.state.loading}
              onChange={this.handleTableChange}  />
              {/**<Tab9 records={this.state.records} ref="Tab9" /> */}
          </div>
    );
  }
});
export default Tab9;