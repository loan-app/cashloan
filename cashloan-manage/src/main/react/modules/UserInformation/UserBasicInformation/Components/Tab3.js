import React from 'react';
import {Table,} from 'antd';

const objectAssign = require('object-assign');
var Tab3 = React.createClass({
  getInitialState() {
    return {
      loading: false,
      data: [],
      pagination: {},
    };
  },
  rowKey(record) {
    return record.id;
  },
  componentWillReceiveProps(nextProps){
    if(nextProps.activeKey1 == '3'){
      this.setState({
        data: []
      },() => {
        this.fetch();
      })
    }
  },
  scrolling(){
    var scrollTop = document.getElementById('scrolling').scrollTop;
    var height = document.getElementById('scrolling').scrollHeight;
    var allCurrent = Math.ceil(this.state.pagination.total / 10);
    if( 300 + scrollTop >= height && this.state.pagination.current != allCurrent ){
      var params = {};
      params = {
        pageSize: 10,
        current: this.state.pagination.current + 1,
        userId: this.props.record.id,
      }
      this.setState({
        pagination: {
          current: this.state.pagination.current + 1,
          total: this.state.pagination.total,
          pageSize: this.state.pagination.pageSize
        }
      })
      this.fetch(params);
    }
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
        userId: this.props.record.id,
      }
    }
    var data = this.state.data;
    Utils.ajaxData({
      url: '/modules/manage/msg/listRecords.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        if(data[0]){
          data = data.concat(result.data.list);
        }else{
          data = result.data.list;
        }
        this.setState({
          loading: false,
          data: data,
          pagination
        });
      }
    });
  },
  render() {
    var columns = [{
      title: '用户号码',
      dataIndex: "mobile",
    }, {
      title: '对方号码',
      dataIndex: "peerNumber",
    }, {
      title: '通话时间',
      dataIndex: "time",
    }, {
      title: '通话时长(秒)',
      dataIndex: "duration",
    }, {
      title: '通话地',
      dataIndex: "location",
    }, {
      title: '通话状态',
      dataIndex: "locationType",
    }, {
      title: '通话类型',
      dataIndex: "dialType",
        render(text, record) {
            return record.dialType == 'DIAL' ? '主叫' : '被叫'
        }
    }];
    return (<div className="block-panel">
              <div id='scrolling' onScroll={this.scrolling} style={{height: 300,  overflow: 'scroll'}}>
                      <Table columns={columns} rowKey={this.rowKey}  
                      dataSource={this.state.data}
                      pagination={false}
                      loading={this.state.loading}
                      onChange={this.handleTableChange}  />
              </div>
          </div>
    );
  }
});
export default Tab3;