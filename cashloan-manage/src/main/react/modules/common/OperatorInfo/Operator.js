import React from 'react';
import './Operator.css';
import {
    Table,
    Tag,
} from 'antd';

const objectAssign = require('object-assign');
const orgTypeMap = {
    ZHENGXIN: '征信机构',
    DATACOVERGE: '数据平台',
    BANK: '银行',
    CUSTOMER_FINANCE: '消费金融',
    CASH_LOAN: '现金贷',
    P2P: 'P2P理财',
    CREDITPAY: '信用支付',
    CONSUMSTAGE: '消费分期',
    COMPENSATION: '信用卡代偿',
    DIVERSION: '导流平台',
    其它: '其它'
};

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
    componentWillReceiveProps(nextProps) {
        if (nextProps.activekey == 'Operator') {
            this.setState({
                data: {}
            }, () => {
                this.fetch();
            })
        }
    },
    fetch() {
        this.setState({
            loading: true
        });
        Utils.getData({
            url: '/modules/manage/operator/report.htm',
            data: {
                userId: this.props.record.borrowUserId,
                borrowId: this.props.record.borrowId,
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
        const data = this.state.data;
        const basic = data.basic || {};
        // 用户申请检测
        const basic_html = (
            <div className="ant-table ant-table-middle ant-table-bordered" style={mb20}>
                <table>
                    <tbody>
                    <tr>
                        <td className={'table-td-title'}>姓名</td>
                        <td colSpan="4">{basic.name}</td>
                        <td>年龄</td>
                        <td>{basic.age}</td>
                        <td>性别</td>
                        <td>{basic.gender}</td>
                    </tr>
                    <tr className={'table-tr-gray'}>
                        <td className={'table-td-title'}>身份证</td>
                        <td colSpan="4">
                            {basic.idNo} &nbsp;
                            {
                                basic.in_court_black !== undefined
                                    ?
                                    <Tag color={basic.in_court_black ? 'red' : 'green'}>
                                        {basic.in_court_black ? '' : '不'}在法院黑名单
                                    </Tag>
                                    : null
                            } &nbsp;
                            {
                                basic.in_finance_black !== undefined
                                    ?
                                    <Tag color={basic.in_finance_black ? 'red' : 'green'}>
                                        {basic.in_finance_black ? '' : '不'}在机构黑名单
                                    </Tag>
                                    : null
                            }
                        </td>
                        <td>地址</td>
                        <td colSpan="3">{basic.native_place}</td>
                    </tr>
                    <tr>
                        <td rowSpan="3" className={'table-td-title'}>手机号码</td>
                        <td colSpan="8">
                            {basic.phone} &nbsp;
                            {
                                basic.reliability !== undefined
                                    ?
                                    <Tag color={basic.reliability ? 'green' : 'red'}>
                                        {basic.reliability ? '实名认证' : '未实名认证'}
                                    </Tag>
                                    : null
                            } &nbsp;
                            {
                                basic.phone_in_finance_black !== undefined
                                    ?
                                    <Tag color={basic.phone_in_finance_black ? 'red' : 'green'}>
                                        手机号{basic.phone_in_finance_black ? '' : '不'}在金融机构黑名单
                                    </Tag>
                                    : null
                            }
                            {
                                basic.operator_name !== undefined ? <Tag color="blue">{basic.operator_name}</Tag> : null
                            }
                        </td>
                    </tr>
                    <tr>
                        <td colSpan="8">用户姓名与运营商提供的姓名{basic.name_match}</td>
                    </tr>
                    <tr>
                        <td colSpan="8">用户身份证与运营商提供的身份证{basic.idcard_match}</td>
                    </tr>
                    <tr className={'table-tr-gray'}>
                        <td rowSpan="5" className={'table-td-title'}>联系人</td>
                        <td>姓名</td>
                        <td>关系</td>
                        <td colSpan="6">手机号码</td>
                    </tr>
                    {(basic.emer_contacts || []).map(item => {
                        return (
                            <tr key={item.phone}>
                                <td>{item.name}</td>
                                <td>{item.relation}</td>
                                <td colSpan="6">
                                    {item.phone} &nbsp;
                                    <Tag color={item.is_temp_num ? 'red' : 'green'}>
                                        {item.is_temp_num ? '' : '非'}临时小号
                                    </Tag>
                                </td>
                            </tr>
                        )
                    })}
                    </tbody>
                </table>
            </div>
        );

        // 表格样式
        const mb20 = {marginBottom: '20px'};
        const width30 = {width: '30%'};

        // 用户行为检测
        const behavior_check = data.behavior_check || [];
        const behavior_check_columns = [{
            title: '检查项',
            dataIndex: 'check_point_cn',
            key: 'check_point_cn',
            width: '180'
        }, {
            title: '结果',
            dataIndex: 'result',
            key: 'result',
            width: '160'
        }, {
            title: '依据',
            dataIndex: 'evidence',
            key: 'evidence',
        }];
        const behavior_check_html = (
            <Table size="middle" bordered
                   style={{marginBottom: '50px'}}
                   pagination={false}
                   dataSource={behavior_check}
                   columns={behavior_check_columns}
                   rowClassName={(record, index) => index % 2 ? '' : 'table-tr-gray'}
                   rowKey={(record, index) => `behavior_check${index}`}/>
        );

        // 运营商消费数据
        const consum_behavior = data.consum_behavior || [];
        const consum_behavior_columns = [{
            title: '运营商',
            dataIndex: 'cell_operator',
            key: 'cell_operator',
        }, {
            title: '手机号',
            dataIndex: 'cell_phone_num',
            key: 'cell_phone_num',
        }, {
            title: '归属地',
            dataIndex: 'cell_loc',
            key: 'cell_loc',
        }, {
            title: '月份',
            dataIndex: 'cell_mth',
            key: 'cell_mth',
        }, {
            title: '呼叫次数',
            dataIndex: 'call_cnt',
            key: 'call_cnt',
        }, {
            title: '主叫次数',
            dataIndex: 'dial_cnt',
            key: 'dial_cnt',
        }, {
            title: '主叫时间（分）',
            dataIndex: 'dial_time',
            key: 'dial_time',
        }, {
            title: '被叫次数',
            dataIndex: 'dialed_cnt',
            key: 'dialed_cnt',
        }, {
            title: '被叫时间（分）',
            dataIndex: 'dialed_time',
            key: 'dialed_time',
        }, {
            title: '短信数量',
            dataIndex: 'sms_cnt',
            key: 'sms_cnt',
        }, {
            title: '话费消费（元）',
            dataIndex: 'total_amount',
            key: 'total_amount',
        }];
        const consum_behavior_html = (
            <Table size="middle" bordered
                   dataSource={consum_behavior}
                   columns={consum_behavior_columns}
                   rowClassName={(record, index) => index % 2 ? '' : 'table-tr-gray'}
                   rowKey={(record, index) => `consum_behavior${index}`}/>
        );

        // 用户查询信息检测
        const check_search_info = data.check_search_info || {};
        const check_search_info_html = (
            <div className="ant-table ant-table-middle ant-table-bordered" style={mb20}>
                <table>
                    <tbody>
                        <tr>
                            <td rowSpan="8" className={'table-td-title'}>用户查询信息</td>
                            <td style={width30}>查询过该用户的企业数量</td>
                            <td>{check_search_info.searched_org_cnt}</td>
                        </tr>
                        <tr className={'table-tr-gray'}>
                            <td>查询过该用户的企业类型</td>
                            <td>{String(check_search_info.searched_org_type).split(',').map(key => orgTypeMap[key]).toString()}</td>
                        </tr>
                        <tr>
                            <td>身份证组合过的其他姓名</td>
                            <td>{String(check_search_info.idcard_with_other_names)}</td>
                        </tr>
                        <tr className={'table-tr-gray'}>
                            <td>身份证组合过的其他手机号</td>
                            <td>{String(check_search_info.idcard_with_other_phones)}</td>
                        </tr>
                        <tr>
                            <td>电话号码组合过的其他姓名</td>
                            <td>{String(check_search_info.phone_with_other_names)}</td>
                        </tr>
                        <tr className={'table-tr-gray'}>
                            <td>电话号码组合过的其他身份证</td>
                            <td>{String(check_search_info.phone_with_other_idcards)}</td>
                        </tr>
                        <tr>
                            <td>电话号码注册过的相关企业数量</td>
                            <td>{check_search_info.register_org_cnt}</td>
                        </tr>
                        <tr className={'table-tr-gray'}>
                            <td>电话号码注册过的企业类型</td>
                            <td>{String(check_search_info.register_org_type).split(',').map(key => orgTypeMap[key]).toString()}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        );

        // 用户查询信息检测
        const check_black_info = data.check_black_info || {};
        const check_black_info_html = (
            <div className="ant-table ant-table-middle ant-table-bordered" style={mb20}>
                <table>
                    <tbody>
                    <tr>
                        <td rowSpan="6" className={'table-td-title'}>黑名单信息</td>
                        <td style={width30}>黑中介分数</td>
                        <td style={{ color: check_black_info.phone_gray_score < 40 ? '#ff3c3c' : ''}}>{check_black_info.phone_gray_score} {check_black_info.phone_gray_score < 40 ? '(40分以下为高风险人群)' : ''}</td>
                    </tr>
                    <tr className={'table-tr-gray'}>
                        <td>直接联系人中黑名单人数</td>
                        <td>{check_black_info.contacts_class1_blacklist_cnt}</td>
                    </tr>
                    <tr>
                        <td>间接联系人中黑名单人数</td>
                        <td>{check_black_info.contacts_class2_blacklist_cnt}</td>
                    </tr>
                    <tr className={'table-tr-gray'}>
                        <td>直接联系人人数</td>
                        <td>{check_black_info.contacts_class1_cnt}</td>
                    </tr>
                    <tr>
                        <td>引起黑名单的直接联系人数量</td>
                        <td>{check_black_info.contacts_router_cnt}</td>
                    </tr>
                    <tr className={'table-tr-gray'}>
                        <td>直接联系人中引起间接黑名单占比</td>
                        <td>{check_black_info.contacts_router_ratio}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        );

        // 运营商出行及漫游分析
        const trip_info = data.trip_info || [];
        const trip_info_columns = [{
            title: '出发地',
            dataIndex: 'trip_leave',
            key: 'trip_leave',
        }, {
            title: '目的地',
            dataIndex: 'trip_dest',
            key: 'trip_dest',
        }, {
            title: '出发时间',
            dataIndex: 'trip_start_time',
            key: 'trip_start_time',
        }, {
            title: '结束时间',
            dataIndex: 'trip_end_time',
            key: 'trip_end_time',
        }, {
            title: '类型',
            dataIndex: 'trip_type',
            key: 'trip_type',
        }];
        const trip_info_html = (
            <Table size="middle" bordered
                   dataSource={trip_info}
                   columns={trip_info_columns}
                   rowClassName={(record, index) => index % 2 ? '' : 'table-tr-gray'}
                   rowKey={(record, index) => `trip_info${index}`}/>
        );

        // 漫游详情统计
        const roam_detail = data.roam_detail || [];
        const roam_detail_columns = [{
            title: '漫游日期',
            dataIndex: 'roam_day',
            key: 'roam_day',
        }, {
            title: '漫游城市',
            dataIndex: 'roam_location',
            key: 'roam_location',
        }];
        const roam_detail_html = (
            <Table size="middle" bordered
                   dataSource={roam_detail}
                   columns={roam_detail_columns}
                   rowClassName={(record, index) => index % 2 ? '' : 'table-tr-gray'}
                   rowKey={(record, index) => `roam_detail${index}`}/>
        );

        // 联系人区域汇总
        const contact_region = data.contact_region || [];
        const contact_region_columns = [{
            title: '地址',
            dataIndex: 'region_loc',
            key: 'region_loc',
        }, {
            title: '通话次数',
            dataIndex: 'region_call_cnt',
            key: 'region_call_cnt',
        }, {
            title: '通话号码数',
            dataIndex: 'region_uniq_num_cnt',
            key: 'region_uniq_num_cnt',
        }, {
            title: '通话时长（秒）',
            dataIndex: 'region_call_time',
            key: 'region_call_time',
        }, {
            title: '主叫次数',
            dataIndex: 'region_dial_cnt',
            key: 'region_dial_cnt',
        }, {
            title: '被叫次数',
            dataIndex: 'region_dialed_cnt',
            key: 'region_dialed_cnt',
        }, {
            title: '主叫时长（秒）',
            dataIndex: 'region_dial_time',
            key: 'region_dial_time',
        }, {
            title: '被叫时长（秒）',
            dataIndex: 'region_dialed_time',
            key: 'region_dialed_time',
        }];
        const contact_region_html = (
            <Table size="middle" bordered
                   dataSource={contact_region}
                   columns={contact_region_columns}
                   rowClassName={(record, index) => index % 2 ? '' : 'table-tr-gray'}
                   rowKey={(record, index) => `contact_region${index}`}/>
        );

        return (<div className="block-panel">
                <h1 className={'operator-title'}>用户申请表检测</h1>
                {basic_html}
                <h1 className={'operator-title'}>用户行为检测</h1>
                {behavior_check_html}
                <h1 className={'operator-title'}>运营商消费数据</h1>
                {consum_behavior_html}
                <h1 className={'operator-title'}>用户查询信息检测</h1>
                {check_search_info_html}
                <h1 className={'operator-title'}>用户黑名单信息检测</h1>
                {check_black_info_html}
                <h1 className={'operator-title'}>运营商出行及漫游分析</h1>
                {trip_info_html}
                <h1 className={'operator-title'}>漫游详情统计</h1>
                {roam_detail_html}
                <h1 className={'operator-title'}>联系人区域汇总</h1>
                {contact_region_html}
            </div>
        );
    }
});
export default Operator;