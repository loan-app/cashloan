import React from 'react'
import {Table} from 'antd';
import AdjustCreditDetial from "./AdjustCreditDetial";
var repaymentTypeText={'10':'待审核', '20': '审核中' ,'30': '通过','40' :'已拒绝' ,'50': '还款中', '60' :'还款完成', '70': '逾期'}
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
    //隐藏弹窗
    hideModal() {
        this.setState({
            visible: false,
            visible1: false,
            visible2: false,
            visibleRepay:false,
            selectedIndex: '',
            selectedRowKeys: [],
            visibleAdd:false,
            visibleRemark:false,

        });
        this.refreshList();
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
    onRowClick(record) {
        //console.log(record)
        this.setState({
            selectedRowKeys: [record.id],
            selectedRow: record,
            rowRecord:record
        },()=>{
            this
        });
    },

    // 列表添加选中行样式
    rowClassName(record) {
        let selected = this.state.selectedIndex;
        //console.log('selected', this.state.selectedIndex)
        return (record.id == selected && selected !== '') ? 'selectRow' : '';

    },

    //选择
    onSelectAll(selected, selectedRowKeys, selectedRows, changeRows) {
        if (selected) {
            this.setState({
                selectedRowKeys
            })
        } else {
            this.setState({
                selectedRowKeys: []
            })
        }
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
            //type: 'checkbox',
            selectedRowKeys,
            //onSelectAll: this.onSelectAll,
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
            title: '借款金额(元)',
            dataIndex: 'borrowAmount'
        }, {
            title: '应还款日期',
            dataIndex: 'repayTime'
        },{
            title: '操作',
            dataIndex: "",
            render: (text,record) => {
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
                <Table columns={columns} rowKey={this.rowKey} ref="table" 
                       onRowClick={this.onRowClick}
                       dataSource={this.state.data}
                       rowClassName={this.rowClassName}
                       pagination={this.state.pagination}
                       onChange={this.handleTableChange}
                />
                <AdjustCreditDetial ref="AdjustCreditDetial"  visible={state.visibleAc}    title={state.title} hideModal={me.hideModal}
                                    record={state.selectedrecord} dataRecord={state.dataRecord}  canEdit={state.canEdit} selectedRowKeys={state.selectedRowKeys} />
            </div>
        );
    }
})
