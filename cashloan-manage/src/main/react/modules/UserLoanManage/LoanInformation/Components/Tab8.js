import React from 'react';
import {
    Table,
    Tabs
} from 'antd';
import XinyanDetail from './XinyanDetail'
import OperatorDetail from './OperatorDetail'

const objectAssign = require('object-assign');
const TabPane = Tabs.TabPane;
var Tab8 = React.createClass({
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
    componentWillReceiveProps(nextProps) {
        if (nextProps.activeKey1 == '8') {
            this.fetch();
        }
    },
    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        pager.pageSize = pagination.pageSize;
        pager.userId = this.props.record.userId,
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
                pageSize: 5,
                current: 1,
                userId: this.props.record.userId,
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/invite/listInviteBorrow.htm',
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
    handleTabClick() {
        // TODO
    },
    render() {
        console.log(this.props.record);
        const rowSelection = {
            selectedRowKeys: this.state.selectedRowKeys,
        };
        var columns = [{
            title: '被邀请人',
            dataIndex: "inviteName",
        }, {
            title: '邀请时间',
            dataIndex: "inviteTime",
        }, {
            title: '借款总额（元）',
            dataIndex: "borrowAmout",
        }, {
            title: '逾期总罚金（元）',
            dataIndex: "penaltyAmout",
        }, {
            title: '还款总额（元）',
            dataIndex: "repayAmount",
        }, {
            title: '收益总奖金（元）',
            dataIndex: "agentAmount",
        }];
        return (<div className="block-panel card-container">
                <Tabs onTabClick={this.handleTabClick} >
                    <TabPane type="card" tab="运营商报告" key='1'>
                        <OperatorDetail userId={this.props.record.userId}></OperatorDetail>
                    </TabPane>
                    <TabPane tab="信贷报告" key='2'>
                        <iframe style={{border:0,width:"100%",height:630,}} src= {'/build/magicReport/index.html?userId=' + this.props.record.userId} />
                    </TabPane>
                    <TabPane tab="小额网贷报告" key="3">
                        <XinyanDetail visible={true} title={'XX'} record={this.props.record}/>
                    </TabPane>
                </Tabs>
            </div>
        );
    }
});
export default Tab8;