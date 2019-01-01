// 反欺诈
import React from 'react';
import {
    Table,
    Card,
    Radio,
    Row,
    Col,
} from 'antd';
import './AntiFraud.css';
const RadioButton = Radio.Button;
const RadioGroup = Radio.Group;

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

const columns = {
    nameNum: [
        {
            title: '最后使用时间',
            key: 'latest_used_time',
            dataIndex: 'latest_used_time',
        },
        {
            title: '姓名',
            key: 'name',
            dataIndex: 'name',
        },
    ],
    idCardMobileColumn: [
        {
            title: '最后使用时间',
            key: 'latest_used_time',
            dataIndex: 'latest_used_time',
        },
        {
            title: '手机号',
            key: 'mobile',
            dataIndex: 'mobile',
        },
        {
            title: '号码归属地',
            key: 'mobile_location',
            dataIndex: 'mobile_location',
        },
        {
            title: '运营商',
            key: 'carrier',
            dataIndex: 'carrier',
        },
        {
            title: '是否命中黑灰名单',
            key: 'isblack',
            dataIndex: 'isblack',
            render: (text, record) => text ? '命中' : '未命中'
        },
    ],
    mobileIdCardColumn: [
        {
            title: '最后使用时间',
            key: 'latest_used_time',
            dataIndex: 'latest_used_time',
        },

        {
            title: '身份证号',
            key: 'idcard',
            dataIndex: 'idcard',
        },
        {
            title: '是否命中黑灰名单',
            key: 'isblack',
            dataIndex: 'isblack',
            render: (text, record) => text ? '命中' : '未命中'
        },
    ],
    mechanism: [
        {
            title: '最后使用时间',
            key: 'latest_used_time',
            dataIndex: 'latest_used_time',
        },
        {
            title: '机构类型',
            key: 'org_type',
            dataIndex: 'org_type',
            render: (text, record) => orgTypeMap[text]
        },
    ]
};

export default React.createClass({
    getInitialState() {
        return {
            loading: false,
            data: {},
            tabKey: 'idCard',
            card: 1,
            tableDesc: '身份证存疑姓名',
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
            url: '/modules/manage/credit/loan/report/detail.htm',
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

    changeTab(tabKey, item) {
        this.setState({
            tabKey,
            card: item.card,
            tableDesc: item.title
        });
    },

    changeCard(item) {
        this.setState({
            card: item.card,
            tableDesc: item.title
        });
    },
    render() {
        const props = this.props;
        const data = this.state.data;
        const antiFraud = data.antiFraud || {};

        const device = antiFraud.suspicious_device || {};
        const idCard = antiFraud.suspicious_idcard || {};
        const mobile = antiFraud.suspicious_mobile || {};

        const deviceData = {
            idCard: [
                {
                    card: 1,
                    title: '身份证存疑姓名数',
                    titleCnt: idCard.other_names_cnt,
                    TableData: idCard.other_names || [],
                    tableColumn: columns.nameNum,
                },
                {
                    card: 2,
                    titleCnt: idCard.other_mobiles_cnt,
                    title: '身份证存疑手机号码数',
                    subTitleCnt: idCard.other_mobiles_black_cnt,
                    subTitle: '触黑手机号码数',
                    TableData: idCard.other_mobiles || [],
                    tableColumn: columns.idCardMobileColumn,
                },
                {
                    card: 3,
                    titleCnt: idCard.information_sources_cnt,
                    title: '提供数据的机构数',
                    TableData: idCard.information_sources || [],
                    tableColumn: columns.mechanism,
                },
            ],
            mobile: [
                {
                    card: 21,
                    titleCnt: mobile.other_names_cnt,
                    title: '手机存疑姓名数',
                    TableData: mobile.other_names || [],
                    tableColumn: columns.nameNum,
                },
                {
                    card: 22,
                    titleCnt: mobile.other_idcards_cnt,
                    title: '手机存疑身份证号码数',
                    subTitleCnt: mobile.other_idcards_black_cnt,
                    subTitle: '触黑身份证号码数',
                    TableData: mobile.other_idcards || [],
                    tableColumn: columns.mobileIdCardColumn,
                },
                {
                    card: 23,
                    titleCnt: mobile.information_sources_cnt,
                    title: '提供数据的机构数',
                    TableData: mobile.information_sources || [],
                    tableColumn: columns.mechanism,
                },
            ],
            device: [
                {
                    card: 31,
                    titleCnt: device.other_names_cnt,
                    title: '使用过的设备上登陆的其他姓名数',
                    TableData: device.other_names || [],
                    tableColumn: columns.nameNum,
                },
                {
                    card: 32,
                    titleCnt: device.other_mobiles_cnt,
                    title: '使用过的设备上登陆的其他手机号码数',
                    subTitleCnt: device.other_mobiles_black_cnt,
                    subTitle: '触黑手机号码数',
                    TableData: device.other_mobiles || [],
                    tableColumn: columns.idCardMobileColumn,
                },
                {
                    card: 33,
                    titleCnt: device.other_idcards_cnt,
                    title: '使用过的设备上登陆的其他身份证号码数',
                    subTitleCnt: device.other_idcards_black_cnt,
                    subTitle: '触黑身份证号码数',
                    TableData: device.other_idcards || [],
                    tableColumn: columns.mobileIdCardColumn,
                },
                {
                    card: 34,
                    titleCnt: device.information_sources_cnt,
                    title: '提供数据的机构数',
                    TableData: device.information_sources || [],
                    tableColumn: columns.mechanism,
                },
            ],
        };

        const one = deviceData[this.state.tabKey].find(item => item.card === this.state.card) || {};
        const TableData = one.TableData || [];
        const tableColumn = one.tableColumn || [];

        return (<div className="block-panel">
            <div style={{ paddingBottom: '12px', borderBottom: '1px solid #e8eaec', marginBottom: '16px'}}>
                <RadioGroup defaultValue="idCard" size="large" onChange={(e) =>  this.changeTab(e.target.value, deviceData[e.target.value][0])}>
                    <RadioButton value="idCard">身份证存疑</RadioButton>
                    <RadioButton value="mobile">手机存疑</RadioButton>
                    <RadioButton value="device">设备存疑</RadioButton>
                </RadioGroup>
            </div>

            {this.state.tabKey === 'device' ?
            <Row style={{padding: '20px', marginBottom: '20px', background: '#f9fcff', fontSize: '14px'}}>
                <Col span={8}>
                    <div>使用过的设备数</div>
                    <h4>{ device.other_devices_cnt }</h4>
                </Col>
                <Col span={8}>
                    <div>手机号码使用过的设备数</div>
                    <h4>{ device.mobile_other_devices_cnt }</h4>
                </Col>
                <Col span={8}>
                    <div>身份证号码使用过的设备数</div>
                    <h4>{ device.idcard_other_devices_cnt }</h4>
                </Col>
            </Row> : null}

            <Row>
                <Col span={6}>
                    {
                        deviceData[this.state.tabKey].map(item => (
                            <Card key={item.card} style={{marginBottom: '20px', cursor: 'pointer'}} onClick={() => this.changeCard(item)}>
                                {item.title}
                                <h1>{item.titleCnt}</h1>
                                {item.subTitle ? <div style={{textAlign: 'right'}}>{item.subTitle}: {item.subTitleCnt}</div> : null}
                            </Card>
                        ))
                    }
                </Col>
                <Col span={17} offset={1}>
                    <h3 style={{marginBottom: '10px', fontSize: '14px'}}>{this.state.tableDesc.slice(0, this.state.tableDesc.length -1)}</h3>
                    <Table size="middle" bordered
                           dataSource={TableData}
                           columns={tableColumn}
                           rowClassName={(record, index) => index % 2 ? '' : 'table-tr-gray'}
                           rowKey={(record, index) => `antiFraud${index}`}/>
                </Col>
            </Row>

        </div>);
    }
});