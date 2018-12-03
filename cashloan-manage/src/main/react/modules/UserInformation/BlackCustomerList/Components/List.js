import React from 'react'
import {
  Table,
  Modal,
  Button
} from 'antd';
import CustomerWin from './CustomerWin'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {
        pageSize: 10,
        current: 1
      },
      canEdit: true,
      visible: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });
    
  },
  //新增跟编辑弹窗
  showModal(title) {
    this.setState({
      visible: true,
      title: title
    });
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
        searchParams:JSON.stringify({idNo:'',realName:''})
      }
    }
    if(!params.searchParams){
      params.searchParams=JSON.stringify({idNo:'',realName:''})
    }
    Utils.ajaxData({
      url: '/modules/manage/userBlack/listInfo.htm',
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
  refresh(title){
    this.refreshList();
  },
  refreshList() {
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize,
      // searchParams: JSON.stringify({state:"10"})
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
  download(title){
    window.open('/modules/manage/userBlack/downTemplete.htm');
  },
  delt(title,record){
    let me = this;
    confirm({
            title: '是否确定删除',
            content: (
            	      <div>
            	        <p style={{'color':'red'}}>删除后用户将恢复为常规用户！！！</p>
            	      </div>
            	    ),

            onOk: function() {
                Utils.ajaxData({
                    url: '/modules/manage/userBlack/deleteBlack.htm',
                    data: {
                        id:record.id
                    },
                    method: 'post',
                    callback: (result) => {
                        if (result.code == 200) {
                            Modal.success({
                                title: result.msg,
                            });
                            me.refreshList();
                        } else {
                           Modal.error({
                                title: result.msg,
                            });
                        }
                        
                    }
                });
            },
            onCancel: function() { }
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
      dataIndex: 'realName'
    }, {
      title: '身份证号码',
      dataIndex: 'idNo'
    }, {
      title: '手机号码',
      dataIndex: 'phone'
    }, {
      title: '类型',
      dataIndex: 'typeStr'
    }, {
      title: '导入时间',
      dataIndex: "createTime",
    }, {
      title: '操作',
      render(text,record){
          return  (
              <div style={{ textAlign: "left" }}>
                  {record.typeStr == '黑名单' | record.typeStr == '白名单' ? <a href="#" onClick={me.delt.bind(me, '删除',record)}>删除</a>  : '-'}       
              </div>
          )
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          <Button onClick={me.showModal.bind(me, '导入')}>
              批量导入
          </Button>
          <Button onClick={me.download.bind(me, '下载')}>
              下载模板
          </Button>
          <Button onClick={me.refresh.bind(me, '刷新')}>
              手动刷新
          </Button>
        </div>
        <Table columns={columns} rowKey={this.rowKey}
          onRowClick={this.onRowClick}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange} />
        <CustomerWin ref="CustomerWin" visible={state.visible} title={state.title} hideModal={me.hideModal} />
      </div>
    );
  }
})