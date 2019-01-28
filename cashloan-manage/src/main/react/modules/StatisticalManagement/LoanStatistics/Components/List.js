import React from 'react'
import {Modal, Table} from 'antd';

const objectAssign = require('object-assign');
export default React.createClass({
    getInitialState() {
        return {
            selectedRowKeys: [], // 这里配置默认勾选列
            loading: false,
            data: [],
            pagination: {},
            canEdit: true,
            record:"",
            dataRecord:[],
        };
    },
    componentWillReceiveProps(nextProps, nextState) {
        this.clearSelectedList();
        this.fetch(nextProps.params);
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
        var date = new Date();
        date = DateFormat.formatDate(date);
        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: 10,
                current: 1,
                searchParams: JSON.stringify({beforeTime:date.substring(0,10),afterTime:date.substring(0,10)}),
            }
        }
        if(!params.searchParams){
            params.searchParams= JSON.stringify({beforeTime:date.substring(0,10),afterTime:date.substring(0,10)})
        }
        Utils.ajaxData({
            url: '/modules/manage/promotion/channel/channelUserCount.htm',
            data:params,
            method: 'post',
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
        var id = record.id;
        this.setState({
            selectedRowKeys: [id],
            record: record
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
            title: '渠道标识',
            dataIndex: "code"
        },{
            title: '渠道名称',
            dataIndex: "name"
        },{
            title: '注册人数',
            dataIndex: "registerCount"
        },{
            title: '借款人数',
            dataIndex: "borrowMember"
        },{
            title:"借款次数",
            dataIndex:"borrowCount",
        },{
            title:"借款金额",
            dataIndex:"borrowAmout",
        },{
            title:"首贷放款笔数",
            dataIndex:"newPayCount",
        },{
            title:"再贷放款笔数",
            dataIndex:"repeatPayCount",
        },{
            title:"放款笔数",
            dataIndex:"loanCount",
        },{
            title:"逾期笔数",
            dataIndex:"overdueCount",
        },{
            title:'放款成功金额',
            dataIndex: 'payAccount'
        },{
            title:"首逾率",
            dataIndex:"countFirstPassRate",
            render(text, record) {
                return record.countFirstPassRate + "%"
            }
        },{
            title:"逾期率",
            dataIndex:"overdueRate",
            render(text, record) {
                return record.overdueRate + "%"
            }
        },{
            title:"放款率",
            dataIndex:"loanRate",
            render(text, record) {
                return record.loanRate + "%"
            }
        }];
       
        var state = this.state;
        var record = state.record;
        return (
            <div className="block-panel">
                <Table columns={columns} rowKey={this.rowKey} size="middle"
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    pagination={this.state.pagination}
                    loading={this.state.loading}
                    onChange={this.handleTableChange} />
            </div>
        );
    }
})