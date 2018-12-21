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


var PaipaixinView = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {}
        };
    },
    componentWillMount() {
        Utils.ajaxData({
            url: '/modules/manage/blacklist/ppx/detail.htm',
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
                        <FormItem  {...formItemLayout} label="是否是不良用户:">
                            <Input disabled={!props.canEdit} value={this.state.formData.isBlack} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="是否是关注用户:">
                            <Input disabled={!props.canEdit} value={this.state.formData.isAlert} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="逾期最早出现时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.hk001} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="逾期最近出现时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.hk002} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="逾期累计出现次数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.hk003} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="当前总逾期金额:">
                            <Input disabled={!props.canEdit} value={this.state.formData.hk004} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="当前最大逾期时长:">
                            <Input disabled={!props.canEdit} value={this.state.formData.hk005} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="历史最大逾期金额:">
                            <Input disabled={!props.canEdit} value={this.state.formData.hk006}
                                   type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="历史最大逾期时长:">
                            <Input disabled={!props.canEdit} value={this.state.formData.hk007} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="欺诈最早出现时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.qz001} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="欺诈最近出现时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.qz002} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="欺诈累计出现次数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.qz003} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="负面最早出现时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.fm001} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="负面最近出现时间:">
                            <Input disabled={!props.canEdit} value={this.state.formData.fm002} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="负面累计出现次数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.fm003} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        );
    }
});
PaipaixinView = createForm()(PaipaixinView);
export default PaipaixinView;