import React from 'react'
import {Button, Modal, Table} from 'antd';
import AdjustCreditDetial from './AdjustCreditDetial';
import UserRemarkList from '../../../../common/UserRemark/UserRemarkList';

var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRows:[],
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
      visibleAdd: false,
      visibleAc:false,
        visibleRemark:false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.clearSelectedList();
    this.setState({
      visible: false,
      visibleAdd:false,
      visibleAc:false,
      visibleRemark:false
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
      url: '/modules/manage/borrow/manual/review/list.htm',
      data: params,
      method: "get",
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
    var record = record;
    var state = record.state;
    var id = record.id;
    var selectedRows = this.state.selectedRows;
    var selectedRowKeys = this.state.selectedRowKeys;

    if (state == 40){
        this.setState({
            visibleAc: false,})
    }
    else if (selectedRowKeys.indexOf(id) < 0) {
      selectedRowKeys.push(id);
      selectedRows.push(record);
    } else {
      selectedRowKeys.remove(id);
      selectedRows.remove(record);
    }
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
    });
  },
  onSelectAll(selected, selectedRows, changeRows) {
    var selectedRowKeys = this.state.selectedRowKeys;
    if (selected) {
      for (var i = 0; i < selectedRows.length; i++) {
        selectedRowKeys.push(selectedRows[i].id);
      }
    } else {
      selectedRowKeys = [];
    }
    this.setState({
      selectedRows: selectedRows,
      selectedRowKeys: selectedRowKeys,
    })
  },

  showModalAc(title) {
    if(title=='分配审核员'){
     Utils.ajaxData({
        url: '/modules/manage/borrow/manual/review/sysUserlist.htm',
        method: 'get',
        callback: (result) => {
          this.setState({
            visibleAc: true,
            title: title,
            selectedRowKeys1: this.state.selectedRowKeys,
            dataRecord:result.data,
          });
        }
      });
    }  
  },


    showUserRemark(title, record, canEdit) {
        Utils.ajaxData({
            url: '/modules/manage/user/remark/list.htm',
            data: {
                pageSize: 5,
                current: 1,
                searchParams:JSON.stringify({userId:record})
            },
            method: 'get',
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = result.current;
                pagination.pageSize =result.pageSize;
                pagination.total = result.page.total;
                if (!pagination.current) {
                    pagination.current = 1
                };
                //console.log(result.data.logs);
                this.setState({
                    dataRecord: result.data,
                    canEdit: canEdit,
                    visibleRemark: true,
                    title: title,
                    pagination:result.page,
                    record:record
                });
            }
        });
    },
 
  render() {
    var me = this;
    
    const {
      loading,
      selectedRowKeys
    } = this.state;
    
    let hasSelected = selectedRowKeys.length > 0;
    const rowSelection = {
       getCheckboxProps: record => ({
             disabled: record.state === "20" || record.state === "30" ,    // 配置无法勾选的列
        }),
       onSelectAll:this.onSelectAll
    }; 
    
    
    var columns = [{
      title: '真实姓名',
      dataIndex: 'borrowName',
    }, {
      title: '手机号码',
      dataIndex: "phone",
    }, {
      title: '借款金额',
      dataIndex: 'amount'
    }, {
      title: '借款时间',
      dataIndex: 'borrowTime',
    }, {
      title: '借款期限(天)',
      dataIndex: 'timeLimit',
    }, {
      title: '实际到账金额',
      dataIndex: "realAmount",
    }, {
      title: '综合费用',
      dataIndex: 'fee'
    }, {
      title: '订单状态',
      dataIndex: "stateStr",
    }, {
      title: '审核人',
      dataIndex: 'userName',
    }, {
      title: '渠道',
      dataIndex: 'channelName',
    }, {
      title: '审核状态',
      dataIndex: 'state',
      render:(text,record) =>  {
        switch(text){
          case "10": 
                    return "未分配";
          case "11":
                    return "待审核";
          case "20":
                    return "审核通过";
          case "30":
                    return "审核拒绝";
        }
      }
    },{
        title: '备注',
        render(text, record) {
            console.log('record == >'+record.userId)
            return <div ><a href="#" onClick={me.showUserRemark.bind(me, '备注', record.userId, true)}>备注</a></div>
        }
    },{
      title: '操作',
      render(text, record) {
        if(record.state == "10"){
          return <div ><a href="#" onClick={me.showModalAc.bind(me,"分配审核员")}>分配审核员</a></div>
        }else if(record.state == "20" || record.state == "30"){
           return <div ></div>
        }
        else{
          return <div ><a href="#" onClick={me.showModalAc.bind(me,"分配审核员")}>重新分配审核员</a></div>
        }
        
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
          <div className="actionBtns" style={{ marginBottom: 16 }}>
            <Button disabled={!hasSelected} onClick={me.showModalAc.bind(me, '分配审核员')}>
              批量分配审核员
            </Button>
          </div>
          <div  className="devTotal" style={{ marginBottom: 16, align: "right"}}>
              <span >订单总数:</span> {this.state.pagination.total}
          </div>
           <Table columns={columns} rowKey={this.rowKey}  
             rowSelection={rowSelection}
             onRowClick={this.onRowClick}  
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
             <AdjustCreditDetial ref="AdjustCreditDetial"  visible={state.visibleAc}    title={state.title} hideModal={me.hideModal} 
             record={state.selectedrecord} dataRecord={state.dataRecord}  canEdit={state.canEdit} selectedRowKeys1={state.selectedRowKeys1} />

             <UserRemarkList ref="UserRemarkList" visible={state.visibleRemark}    title={state.title} hideModal={me.hideModal}
                             dataRecord={state.dataRecord}  record={state.record} canEdit={state.canEdit} pagination={state.pagination}/>
      </div>
    );
  }
})