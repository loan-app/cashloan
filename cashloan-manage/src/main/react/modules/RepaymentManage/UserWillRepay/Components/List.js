import React from 'react'
import {Table} from 'antd';
import AdjustCreditDetial from "./AdjustCreditDetial";
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
            url: '/modules/manage/manual/repay/list.htm',
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
            visibleAc: false,
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
        var record = record;
        var state = record.state;
        var id = record.id;
        var selectedRows = this.state.selectedRows;
        var selectedRowKeys = this.state.selectedRowKeys;

        if (selectedRowKeys.indexOf(id) < 0) {
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


    // 列表添加选中行样式
    rowClassName(record) {
        let selected = this.state.selectedIndex;
        //console.log('selected', this.state.selectedIndex)
        return (record.id == selected && selected !== '') ? 'selectRow' : '';

    },

    showModalAc(title) {
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
            title: '借款金额(元)',
            dataIndex: 'borrowAmount'
        }, {
            title: '应还款日期',
            dataIndex: 'repayTime'
        },{
            title: '操作',
            dataIndex: "",
            render: (text,record) => {
                if(record.state == "10"){
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
        }]

        var state = this.state;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
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
                <AdjustCreditDetial ref="AdjustCreditDetial"  visible={state.visibleAc}    title={state.title} hideModal={me.hideModal}
                                    record={state.selectedrecord} dataRecord={state.dataRecord}  canEdit={state.canEdit} selectedRowKeys={state.selectedRowKeys} />
            </div>
        );
    }
})
