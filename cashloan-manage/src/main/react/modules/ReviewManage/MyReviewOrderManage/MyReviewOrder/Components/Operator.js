import React from 'react';
import './Operator.css';
import {
    Table,
    Input,
    Form,
    Tag,
    Row,
    Col,
    Button,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
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
    componentWillReceiveProps(nextProps){
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
            },
            callback: (result) => {
                this.setState({
                    loading: false,
                    data: result.data
                });
            }
        });
    },

    handleReset(){
        console.log(this.props);
        this.props.form.resetFields();
        this.fetch();
    },

    render() {
        const props = this.props;
        const data = this.state.data;
        const basic = data.basic || {};

        return (<div className="block-panel">
                <h1>用户申请检测</h1>
                <h2>用户申请表检测</h2>
                <Form horizontal className="ant-advanced-search-form">
                    <Row gutter={16}>
                        <Col sm={24}>
                            <FormItem
                                label="姓名"
                                labelCol={{span: 4}}
                                wrapperCol={{span: 20}}
                            >
                                <div>{basic.name}</div>
                            </FormItem>
                        </Col>
                        <Col sm={24}>
                            <FormItem
                                label="身份证"
                                labelCol={{span: 4}}
                                wrapperCol={{span: 20}}
                            >
                                <div>
                                    {basic.idNo}
                                    <Tag color={basic.in_court_black ? 'red' : 'green'}>
                                        {basic.in_court_black ? '' : '不'}在法院黑名单
                                    </Tag>
                                    <Tag color={basic.in_finance_black ? 'red' : 'green'}>
                                        {basic.in_finance_black ? '' : '不'}在机构黑名单
                                    </Tag>
                                    {basic.gender}
                                    {basic.age}
                                    {basic.native_place}
                                </div>
                            </FormItem>
                        </Col>
                        <Col sm={24}>
                            <FormItem
                                label="手机号码"
                                labelCol={{span: 4}}
                                wrapperCol={{span: 20}}
                            >
                                <div>
                                    {basic.operator_name}
                                    <Tag color={basic.reliability ? 'green' : 'red'}>
                                        {basic.reliability ? '实名认证' : '未实名认证'}
                                    </Tag>
                                    {basic.phone}
                                    <Tag color={basic.phone_in_finance_black ? 'red' : 'green'}>
                                        手机号{basic.phone_in_finance_black ? '' : '不'}在金融机构黑名单
                                    </Tag>
                                </div>
                                <div>用户姓名与运营商提供的姓名{basic.name_match}</div>
                                <div>用户身份证与运营商提供的身份证{basic.idcard_match}</div>
                            </FormItem>
                        </Col>
                        <Col sm={24}>
                            <FormItem
                                label="手机号码"
                                labelCol={{span: 4}}
                                wrapperCol={{span: 20}}
                            >
                                {(basic.emer_contacts || []).map(item => {
                                    return (
                                        <div key={item.phone}>
                                            {item.name}
                                            {item.relation}
                                            {item.phone}
                                            <Tag color={item.is_temp_num ? 'red' : 'green'}>
                                                {item.is_temp_num ? '' : '非'}临时小号
                                            </Tag>
                                        </div>
                                    )
                                })}
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
                <div>{JSON.stringify(this.state.data.basic)}</div>
            </div>
        );
    }
});
Operator = createForm()(Operator);
export default Operator;