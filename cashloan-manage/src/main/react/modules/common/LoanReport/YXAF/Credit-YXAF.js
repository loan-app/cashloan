import React from 'react';
import {
    Table,
    Card,
    Row,
    Col,
} from 'antd';
import './Credit-YXAF.css';

// 还款状态 NORMAL ：正常，OVERDUE ：逾期，COMPLETED：结清
const loanStatus = {
    NORMAL:'正常',
    OVERDUE:'逾期',
    COMPLETED:'结清'
};
// 审核状态 IN_PROGRESS ： 审核中，ACCEPT：批贷已放款，REJECT：拒贷，CUSTOMER_REJECT：客户放弃
const approvalStatus = {
    IN_PROGRESS: '审核中',
    ACCEPT: '批贷已放款',
    REJECT: '拒贷',
    CUSTOMER_REJECT: '客户放弃'
};
// 机构类型 P2P：P2P ，P2P_CAR_LOAN ：P2P-车贷,P2P_HOUSE_LOAN：P2P-房贷,NONE_LICENSED_CONSUMER_FINANCE：非持牌消金,NONE_LICENSED_CASH_LOAN：非持牌消金-小额信贷,NONE_LICENSED_CONSUMPTION_PERIOD：非持牌消金-消费分期,LICENSED_CONSUMER_FINANCE：持牌消费金融,BANK：银行, FINANCE_LEASING:融资租赁,MICRO_FINANCE:小贷 ,PAWN_SHOP:典当,GUARANTEE:担保,PORTAL：平台门户 ，CAPITAL_PLATFORM ：资金平台INSURANCE：保险 ，FACTORING：保理
const orgType = {
    P2P: 'P2P',
    P2P_CAR_LOAN: 'P2P-车贷',
    P2P_HOUSE_LOAN: 'P2P-房贷',
    NONE_LICENSED_CONSUMER_FINANCE: '非持牌消金',
    NONE_LICENSED_CASH_LOAN: '非持牌消金-小额信贷',
    NONE_LICENSED_CONSUMPTION_PERIOD: '非持牌消金-消费分期',
    LICENSED_CONSUMER_FINANCE: '持牌消费金融',
    BANK: '银行',
    FINANCE_LEASING: '融资租赁',
    MICRO_FINANCE: '小贷 ',
    PAWN_SHOP: '典当',
    GUARANTEE: '担保',
    PORTAL: '平台门户 ',
    CAPITAL_PLATFORM : '资金平台',
    INSURANCE: '保险',
    FACTORING: '保理',
};

// 借款类型
const loanType = {
    CREDIT: '信用',
    MORTGAGE: '抵押',
    GUARANTEE: '担保'
};

const objectAssign = require('object-assign');
var Operator = React.createClass({
    getInitialState() {
        return {
            loading: false,
            data: {},
            pagination: {},
        };
    },
    rowKey(record) {
        return record.id;
    },
    componentDidMount() {
        this.fetch();
    },
    fetch() {
        this.setState({
            loading: true
        });
        Utils.getData({
            url: '/modules/manage/yixin/risk/report/list.htm',
            data: {
                userId: this.props.userId,
            },
            callback: (result) => {
                this.setState({
                    loading: false,
                    data: result.data
                });
            }
        });
    },

    handleReset() {
        console.log(this.props);
        this.props.form.resetFields();
        this.fetch();
    },

    render() {
        const props = this.props;
        const data = this.state.data || {};

        //
        const yixinRiskReport = data.yixinRiskReport || {};
        const loanRecords = yixinRiskReport.loanRecords || [];
        const queriedHistory = yixinRiskReport.queriedHistory || {};
        const checkedRecords = queriedHistory.checkedRecords || [];
        const riskResults = yixinRiskReport.riskResults || [];

        // 借款记录
        const loanRecords_columns = [{
            title: '机构名称',
            dataIndex: 'orgName',
            key: 'orgName',
            sorter: (a, b) => a.orgName - b.orgName
        }, {
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
        }, {
            title: '借款时间',
            dataIndex: 'loanDate',
            key: 'loanDate',
            sorter: (a, b) => a.loanDate - b.loanDate
        }, {
            title: '借款金额',
            dataIndex: 'loanAmount',
            key: 'loanAmount',
            sorter: (a, b) => a.loanAmount - b.loanAmount,
            render: (text, record, index) => text.replace(/^\((\d*),(\d*)]$/, function(){
                if (arguments.length > 1) {
                    return `( ${arguments[1]/10000}w, ${arguments[2]/10000}w ]`;
                }
            })
        }, {
            title: '期数',
            dataIndex: 'periods',
            key: 'periods',
            sorter: (a, b) => a.periods - b.periods,
        }, {
            title: '借款类型',
            dataIndex: 'loanType',
            key: 'loanType',
            render: (text, record, index) => loanType[text]
        }, {
            title: '审核状态',
            dataIndex: 'approvalStatus',
            key: 'approvalStatus',
            filters: Object.keys(approvalStatus).map(value => ({
                text: approvalStatus[value],
                value
            })),
            onFilter: (value, record) => record.approvalStatus === value,
            render: (text, record, index) => approvalStatus[text]
        }, {
            title: '还款状态',
            dataIndex: 'loanStatus',
            key: 'loanStatus',
            filters: Object.keys(loanStatus).map(value => ({
                text: loanStatus[value],
                value
            })),
            onFilter: (value, record) => record.loanStatus === value,
            render: (text, record, index) => loanStatus[text]
        }, {
            title: '逾期期数',
            dataIndex: 'overdueStatus',
            key: 'overdueStatus',
            sorter: (a, b) => a.overdueStatus - b.overdueStatus,
        }, {
            title: '逾期金额',
            dataIndex: 'overdueAmount',
            key: 'overdueAmount',
            sorter: (a, b) => a.overdueAmount - b.overdueAmount,
        }, {
            title: '历史逾期总次数',
            dataIndex: 'overdueTotal',
            key: 'overdueTotal',
            width: 80,
            sorter: (a, b) => a.overdueTotal - b.overdueTotal,
        }, {
            title: 'M3+逾期次数',
            dataIndex: 'overdueM3',
            key: 'overdueM3',
            sorter: (a, b) => a.overdueM3 - b.overdueM3,
        }, {
            title: 'M6+逾期次数',
            dataIndex: 'overdueM6',
            key: 'overdueM6',
            sorter: (a, b) => a.overdueM6 - b.overdueM6,
        }];
        const loanRecords_html = (
            <Table size="middle" bordered
                   style={{marginBottom: '50px'}}
                   pagination={false}
                   dataSource={loanRecords}
                   columns={loanRecords_columns}
                   rowClassName={(record, index) => index % 2 ? '' : 'table-tr-gray'}
                   rowKey={(record, index) => `loanRecords${index}`}/>
        );

        // 多头查询记录
        const checkedRecords_columns = [ {
            title: '机构名称',
            dataIndex: 'orgName',
            key: 'orgName',
            sorter: (a, b) => a.orgName - b.orgName
        }, {
            title: '机构类型',
            dataIndex: 'orgType',
            key: 'orgType',
            filters: Object.keys(orgType).map(value => ({
                text: orgType[value],
                value
            })),
            onFilter: (value, record) => record.orgType === value,
            render: (text, record, index) => orgType[text]
        }, {
            title: '查询原因',
            dataIndex: 'queryReason',
            key: 'queryReason',
        }, {
            title: '最近查询时间',
            dataIndex: 'time',
            key: 'time',
            sorter: (a, b) => new Date(a.time).getTime() - new Date(b.time).getTime()
        }];
        const checkedRecords_html = (
            <Table size="middle" bordered
                   dataSource={checkedRecords}
                   columns={checkedRecords_columns}
                   rowClassName={(record, index) => index % 2 ? '' : 'table-tr-gray'}
                   rowKey={(record, index) => `checkedRecords${index}`}/>
        );

        // 共享风险名单
        const riskResults_columns = [ {
            title: '提供数据的机构代号',
            dataIndex: 'orgName',
            key: 'orgName',
            sorter: (a, b) => a.orgName - b.orgName
        }, {
            title: '命中项',
            dataIndex: 'riskItemType',
            key: 'riskItemType',
        }, {
            title: '命中内容',
            dataIndex: 'riskItemValue',
            key: 'riskItemValue',
        }, {
            title: '风险最近时间',
            dataIndex: 'riskTime',
            key: 'riskTime',
            sorter: (a, b) => new Date(a.riskTime).getTime() - new Date(b.riskTime).getTime()
        }];
        const riskResults_html = (
            <Table size="middle" bordered
                   dataSource={riskResults}
                   columns={riskResults_columns}
                   rowClassName={(record, index) => index % 2 ? '' : 'table-tr-gray'}
                   rowKey={(record, index) => `riskResults${index}`}/>
        );

        return (<div className="block-panel">
                <h1 style={{fontSize: '24px', textAlign: 'center', marginBottom: '20px'}}>借贷评估报告</h1>
                <h2 className={'loanRecord-h2'}>借款记录</h2>
                <Row gutter={16}>
                    <Col span={8}><div className={'loanRecord-blueBlock'}><span className={'fontTitle'}>借款机构数</span> <span className="font blue">{data.countCorporateBorrower}</span> 家</div></Col>
                    <Col span={8}><div className={'loanRecord-blueBlock'}><span className={'fontTitle'}>审批机构数</span> <span className="font blue">{data.countApprovalMechanism}</span> 家</div></Col>
                    <Col span={8}><div className={'loanRecord-blueBlock'}><span className={'fontTitle'}>审批放款笔数</span> <span className="font blue">{data.countApprovalAccept}</span> 笔</div></Col>
                    <Col span={8}><div className={'loanRecord-blueBlock'}><span className={'fontTitle'}>借款申请笔数</span> <span className="font red">{data.countBorrowApply}</span> 笔</div></Col>
                    <Col span={8}><div className={'loanRecord-blueBlock'}><span className={'fontTitle'}>逾期历史数</span> <span className="font red">{data.countOverdueHistory}</span> 笔</div></Col>
                    <Col span={8}><div className={'loanRecord-blueBlock'}><span className={'fontTitle'}>历史逾期M3+/M6+笔数</span> <span className="font red">{data.countOverdueHistoryM3} / {data.countOverdueHistoryM6}</span> 笔</div></Col>
                </Row>
                {loanRecords_html}

                <h2 className={'loanRecord-h2'}>多头查询记录</h2>
                <Row gutter={16}>
                    <Col span={8}><div className={'loanRecord-blueBlock'}><span className={'fontTitle'}>查询总次数</span> <span className="font blue">{queriedHistory.orgCountTotal}</span> 次</div></Col>
                    <Col span={8}><div className={'loanRecord-blueBlock'}><span className={'fontTitle'}>查询机构数</span> <span className="font blue">{queriedHistory.otherOrgCount}</span> 家</div></Col>
                    <Col span={8}><div className={'loanRecord-blueBlock'}><span className={'fontTitle'}>本机构查询次数</span> <span className="font blue">{queriedHistory.timesByCurrentOrg}</span> 次</div></Col>
                </Row>
                {checkedRecords_html}

                <h2 className={'loanRecord-h2'}>共享风险名单</h2>
                {riskResults_html}
            </div>
        );
    }
});
export default Operator;