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
        totalFee:null,
        balance_is:null
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
      url: '/modules/manage/calls/outside/fee/list.htm',
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
          balance_is:result.balance ? result.balance:'0.00',
          data: result.data,
          totalFee:result.data[0] ? result.data[0].totalFee:'0.00',
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
      title: '时间',
      dataIndex: 'gmtCreate',
    }, {
          title: '手机号码',
          dataIndex: 'phone',
      },{
      title: '调用类型',
      dataIndex: 'type',
      render: (text, record)=> {
          if (record.type == 1) {
              return "运营商"
          } else if (record.type == 2) {
              return "反欺诈"
          } else if (record.type == 3) {
              return "申请准入"
          } else if (record.type == 4) {
              return "黑灰名单"
          } else if (record.type == 5) {
              return "魔杖贷后行为"
          } else if (record.type == 6) {
              return "发送短信"
          } else if (record.type == 7) {
              return "人脸识别"
          } else if (record.type == 8) {
              return "小额网贷"
          } else if (record.type == 9) {
              return "借贷评估"
          } else if (record.type == 10) {
              return "欺诈甄别"
          } else if (record.type == 11) {
              return "染黑统计"
          } else if (record.type == 12) {
              return "还款行为"
          } else if (record.type == 13) {
              return "借贷多头"
          } else if (record.type == 14) {
              return "模型评分3"
          }  else if(record.type == 15) {
              return "模型评分2"
          } else if (record.type == 16) {
              return "模型评分1"
          } else{
              return "-"
          }
      }
    }, {
      title: '费用',
      dataIndex: "fee",
    }];
    var state = this.state;
    return (
      <div className="block-panel">
          <div className="actionBtns" style={{ marginBottom: 16 }}>
              <span> 消费金额：{this.state.totalFee}</span>
              <span> 余 额：{this.state.balance_is}</span>
              <dev></dev>
          </div>

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