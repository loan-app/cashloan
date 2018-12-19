import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Tree,
    TreeSelect,
    Row,
    Col
} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const objectAssign = require('object-assign');


var XindeBlacklist = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {}
        };
    },
    componentWillMount() {
        Utils.ajaxData({
            url: '/modules/manage/xinde/blacklist/detail.htm',
            data: {
                borrowId: this.props.borrowId
            },
            callback: (result) => {
                this.setState({
                    formData: result.data
                })
            }
        });
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>
        ];
        if (!props.canEdit) {
            modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
        }
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };

        return (
            <Form horizontal form={this.props.form}>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最早借款时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.firstLoanTime} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="历史借款次数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.totalLoanCount} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="历史逾期次数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.totalOverdueCount} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="已经还清的历史逾期最长时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.longestOverduePaid} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="当前处于逾期状态的借款笔数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.currentOverdueCount} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="当前逾期总金额:">
                            <Input disabled={!props.canEdit} value={this.state.formData.currentOverdueAmountStr} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="当前最长逾期时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.longestOverdueUnpaid} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最后一次申请是否被拒贷:">
                            <Input disabled={!props.canEdit} value={this.state.formData.isLastLoanRefusedStr}
                                   type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最后一次拒贷原因:">
                            <Input disabled={!props.canEdit} value={this.state.formData.lastLoanRefuseReason} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最后一次拒贷时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.lastLoanRefuseTime} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="有逾期90天以上运营商联系人个数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.overDue90ContactsCount} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        );
    }
});
XindeBlacklist = createForm()(XindeBlacklist);
export default XindeBlacklist;