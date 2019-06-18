import React from 'react'
import {Table} from 'antd';
import Lookdetails from "./Lookdetails";
import AddWin from "./AddWin";
import AdjustCreditDetial from "./AdjustCreditDetial";
import Updatedetails from "./Updatedetails";

var repaymentTypeText={'10':'待审核', '20': '审核中' ,'30': '通过','40' :'已拒绝' ,'50': '还款中', '60' :'还款完成', '70': '逾期'}
const objectAssign = require('object-assign');
export default React.createClass({
    getInitialState() {
        return {
            selectedRows: [],
            selectedRowKeys: [], // 这里配置默认勾选列
            loading: false,
            data: [],
            pagination: {
                pageSize: 10,
                current: 1
            },
            pagination1: {
                pageSize: 5,
                current: 1
            },
            canEdit: true,
            visible: false,
            visible1: false,
            visible2: false,
            visibleRepay:false,
            pictureData: [],
            creditReportData: [],
            rowRecord:[],
            record:"",
            visibleAdd:false,
            visibleRemark:false,
            visibleAc:false,
            visibleShow:false,
            visibleDetails:false,

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        this.fetch(nextProps.params);
    },

    componentDidMount() {
        this.fetch();
        []
    },

    fetch(params = {}) {
        this.setState({
            loading: true
        });
        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: 10,
                current: 1
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/borrow/repay/list.htm',
            method: "post",
            data: params,
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;
                if (!pagination.current) {
                    pagination.current = 1
                }
                ;
                this.setState({
                    loading: false,
                    data: result.data,
                    pagination
                });
            }
        });
    },

    //查看弹窗
    showModal(title,record, canEdit) {
        this.setState({
            visible: true,
            canEdit: canEdit,
            record: record,
            title: title,
        },()=>{
            this.refs.Lookdetails.setFieldsValue(record);
        })
    },
    //修改还款金额
    updateModel(title,record, canEdit) {
        this.setState({
            visibleRepay: true,
            canEdit: canEdit,
            record: record,
            title: title,
        },()=>{
            this.refs.Updatedetails.setFieldsValue(record);
        })
    },
    //新增
    addModal(title, record, canEdit){
        this.setState({
            visibleAdd:true,
            title:title,  
        })
    },
    //隐藏弹窗
    hideModal() {
        this.clearSelectedList();
        this.setState({
            visible: false,
            visible1: false,
            visible2: false,
            visibleRepay:false,
            selectedIndex: '',
            selectedRowKeys: [],
            visibleAdd:false,
            visibleRemark:false,
            visibleAc:false,
            visibleDetails:false,

        });
        this.refreshList();
    },

    clearSelectedList() {
        this.setState({
            selectedRowKeys: [],
        });
    },


    showModalAc(title, record) {
        if(record.id) {
            var state = record.state;
            var id = record.id;
            var selectedRows = this.state.selectedRows;
            var selectedRowKeys = [];

            if (state == 10 || state == 30){
                this.setState({
                    visibleAc: false,})
            } else  {
                selectedRowKeys.push(id);
                selectedRows.push(record);
            }
            this.setState({
                selectedRows: selectedRows,
                selectedRowKeys: selectedRowKeys,
            });
        }
        if(title=='分配到期'){
            Utils.ajaxData({
                url: '/modules/manage/borrow/manual/repay/sysUserlist.htm',
                method: 'get',
                callback: (result) => {
                    this.setState({
                        visibleAc: true,
                        title: title,
                        selectedRowKeys: this.state.selectedRowKeys,
                        dataRecord:result.data,
                    });
                }
            });
        }
    },
    rowKey(record) {
        return record.id;
    },

    //选择
    onSelectChange(selectedRowKeys) {
        this.setState({
            selectedRowKeys
        });
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

    refreshList() {
        var pagination = this.state.pagination;
        var params = objectAssign({}, this.props.params, {
            current: pagination.current,
            pageSize: pagination.pageSize
        });
        this.fetch(params);
    },

    //行点击事件
    onRowClick(record, index, event) {
        var record = record;
        var state = record.state;
        var id = record.id;
        var selectedRows = this.state.selectedRows;
        var selectedRowKeys = this.state.selectedRowKeys;

        if (state == 10 || state == 30){
            this.setState({
                visibleAc: false,})
        } else if (selectedRowKeys.indexOf(id) < 0) {
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

    // 列表添加选中行样式
    rowClassName(record) {
        let selected = this.state.selectedIndex;
        //console.log('selected', this.state.selectedIndex)
        return (record.id == selected && selected !== '') ? 'selectRow' : '';

    },

    //选择
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
    download(){
        window.open('/modules/manage/borrow/repay/downRepayByFile.htm');
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
                const pagination1 = this.state.pagination1;
                pagination1.current = result.current;
                pagination1.pageSize =result.pageSize;
                pagination1.total = result.page.total;
                if (!pagination1.current) {
                    pagination1.current = 1
                };
                //console.log(result.data.logs);
                this.setState({
                    dataRecord: result.data,
                    canEdit: canEdit,
                    visibleRemark: true,
                    title: title,
                    pagination1:result.page,
                    record:record
                });
            }
        });
    },

    render() {
        const {
            loading,
            selectedRowKeys
            } = this.state;
        const rowSelection = {
            type: 'checkbox',
            selectedRowKeys,
            onSelectAll: this.onSelectAll,
        };
        let me=this;
        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: '真实姓名',
            dataIndex: 'realName'
        }, {
            title: '手机号码',
            dataIndex: "phone",
        }, {
            title: '订单号',
            dataIndex: 'orderNo'
        }, {
            title: '借款金额(元)',
            dataIndex: 'borrowAmount'
        }, {
            title: '逾期罚金(元)',
            dataIndex: 'penaltyAmout'
        },{
            title: '逾期天数',
            dataIndex: "penaltyDay"
        }, {
            title: '应还款金额(元)',
            dataIndex: 'repayAmount'
        }, {
            title: '应还款总额(元)',
            dataIndex: 'repayTotal'
        }, {
            title: '应还款日期',
            dataIndex: 'repayTime'
        },{
            title: '利息',
            dataIndex: 'fee'
        },{
            title: '放款本金',
            dataIndex: 'realAmount'
        },{
            title: '渠道',
            dataIndex: 'channelName'
        }, {
            title: '还款状态',
            dataIndex: "state",
            render: (text, record)=>{
                if(record.state==10){
                    return "已还款"
                }else if(record.state==20){
                    return "未还款"
                }else if(record.state==30){
                    return "展期还款"
                }else{
                    return "-"
                }
            }
        },{
            title: '备注',
            render(text, record) {
                return <div ><a href="#" onClick={me.showUserRemark.bind(me, '备注', record.userId, true)}>备注</a></div>
            }
        },{
            title: '操作',
            dataIndex: "",
            render: (text,record) => {
                if(record.state == 10 || record.state == 30){
                    return "-"
                }else{
                    return(
                    <div style={{ textAlign: "left" }}>
                        <a href="#" onClick={me.updateModel.bind(me, '修改还款金额',record, false)}>修改还款金额</a>
                        <span className="ant-divider"></span>
                        <a href="#" onClick={me.showModal.bind(me, '确认还款',record, false)}>确认还款 </a>
                    </div>
                    )
                }
            } 
        },{
            title: '分配',
            dataIndex: "",
            onCellClick: (record, event) => event.stopPropagation(),
            render(text, record) {
                if(record.state == 10 || record.state == 30){
                    return "-"
                }else{
                    if(record.allotState == "10"){
                        return(
                            <div style={{ textAlign: "left" }}>
                                <a href="#" onClick={me.showModalAc.bind(me, '分配到期',record, false)}>分配 </a>
                            </div>
                        )
                    } else {
                        return(
                            <div style={{ textAlign: "left" }}>
                                <a href="#" onClick={me.showModalAc.bind(me, '分配到期',record, false)}>重新分配 </a>
                            </div>
                        )
                    }
                }
            }
        }]

        var state = this.state;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <button onClick={me.addModal.bind(me,'批量')} className="ant-btn"> 
                        批量还款
                    </button>
                    <button onClick={me.download.bind(me,'下载')} className="ant-btn"> 
                        下载模板
                    </button>
                    <button disabled={!hasSelected} onClick={me.showModalAc.bind(me,'分配到期')} className="ant-btn">
                        批量分配
                    </button>
                </div>
                <Table columns={columns} rowKey={this.rowKey} ref="table"
                       rowSelection={rowSelection}
                       onRowClick={this.onRowClick}
                       dataSource={this.state.data}
                       rowClassName={this.rowClassName}
                       pagination={this.state.pagination}
                       onChange={this.handleTableChange}
                />
                <Lookdetails ref="Lookdetails" visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.record}
                             canEdit={state.canEdit} />
                <AddWin ref="AddWin"  visible={state.visibleAdd} hideModal={me.hideModal} title={state.title}/>
                <AdjustCreditDetial ref="AdjustCreditDetial"  visible={state.visibleAc}    title={state.title} hideModal={me.hideModal}
                                    record={state.selectedrecord} dataRecord={state.dataRecord}  canEdit={state.canEdit} selectedRowKeys={state.selectedRowKeys} />
                <Updatedetails ref="Updatedetails" visible={state.visibleRepay} title={state.title} hideModal={me.hideModal} record={state.record}
                             canEdit={state.canEdit} />
            </div>
        );
    }
})
