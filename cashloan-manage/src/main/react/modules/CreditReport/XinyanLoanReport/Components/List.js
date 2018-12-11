import React from 'react'
import {Table, Modal, Icon} from 'antd';
import Lookdetails from "./Lookdetails";
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
            visibleAc: false,
            rowRecord:[]

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        this.fetch(nextProps.params);
    },

    componentDidMount() {
        this.fetch();
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
            url: '/modules/manage/xinyan/loan/report/list.htm',
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
       // var record = this.state.selectedRow;
        var record = record;
        this.setState({
            visible: true,
            canEdit: canEdit,
            record: record,
            title:title
        }, () => {
            Utils.ajaxData({
                url: '/modules/manage/xinyan/loan/report/detail.htm',
                data: {
                    userId: record.userId
                },
                callback: (result) => {
                    if (result.code == 200) {
                    var dataForm = {};
                    dataForm.score = result.data.score;
                    dataForm.curMaxCredit = result.data.curMaxCredit;
                    dataForm.curAvgCredit = result.data.curAvgCredit;
                    dataForm.curLoanCnt30d = result.data.curLoanCnt30d;
                    dataForm.curLoanCnt90d = result.data.curLoanCnt90d;
                    dataForm.curLoanCnt180d = result.data.curLoanCnt180d;
                    dataForm.curLoanTotalCnt = result.data.curLoanTotalCnt;
                    dataForm.curLoanOrgTotalCnt = result.data.curLoanOrgTotalCnt;
                    dataForm.curLastToEndLoan = result.data.curLastToEndLoan;
                    dataForm.curLoanClearCnt = result.data.curLoanClearCnt;
                    dataForm.curOverdueCnt30d = result.data.curOverdueCnt30d;
                    dataForm.curOverdueCntMore30d = result.data.curOverdueCntMore30d;
                    dataForm.queryOrgCnt = result.data.queryOrgCnt;
                    dataForm.queryCnt = result.data.queryCnt;
                    dataForm.lastToEndDays = result.data.lastToEndDays;
                    dataForm.queryCnt30d = result.data.queryCnt30d;
                    dataForm.queryCnt90d = result.data.queryCnt90d;
                    dataForm.queryCnt180d = result.data.queryCnt180d;
                    dataForm.loanClearNum = result.data.loanClearNum;
                    dataForm.overdueCnt30d = result.data.overdueCnt30d;
                    dataForm.overdueCntMore30d = result.data.overdueCntMore30d;
                    dataForm.workDayNotOverdueAmountRadio30d = result.data.workDayNotOverdueAmountRadio30d;
                    dataForm.notOverdueOrderRadio180d = result.data.notOverdueOrderRadio180d;
                    dataForm.overdueOrderRadio90d = result.data.overdueOrderRadio90d;
                    dataForm.maxLoanRate12m = result.data.maxLoanRate12m;
                    dataForm.avgLoanRate12m = result.data.avgLoanRate12m;
                    dataForm.overdueOrgCnt6m = result.data.overdueOrgCnt6m;
                    dataForm.ddOverdueDays20time = result.data.ddOverdueDays20time;
                    dataForm.ddWorkDayOverdueDays3time = result.data.ddWorkDayOverdueDays3time;
                    dataForm.ddOverdueDays12m = result.data.ddOverdueDays12m;
                    dataForm.ddMaxOverdueDays3m = result.data.ddMaxOverdueDays3m;
                    //console.log(result.data);
                    this.refs.Lookdetails.setFieldsValue(dataForm);
                }
            }
        });
    });
    },

    //隐藏弹窗
    hideModal(state) {
        this.setState({
            visible: false,
            visibleAc: false,
            selectedIndex: '',
            selectedRowKeys: [],
        });
        this.refreshList();
    },


    rowKey(record) {
        return record.userId;
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
        this.setState({
            selectedRowKeys: [record.id],
            selectedRow: record,
            rowRecord:record
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
            title: '手机号',
            dataIndex: 'phone'
        }, {
            title: '身份证号',
            dataIndex: "idNo"
        }, {
            title: '操作',
            dataIndex: "",
            render(text,record){
                return <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.showModal.bind(me, '查看报告', record, false)}>查看报告</a>
                            <span className="ant-divider"></span>
                        </div>
            }
        }];

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
                <Lookdetails ref="Lookdetails" visible={state.visible} title={state.title} hideModal={me.hideModal.bind(me,state)} record={state.rowRecord}
                canEdit={state.canEdit} />
            </div>
        );
    }
})
