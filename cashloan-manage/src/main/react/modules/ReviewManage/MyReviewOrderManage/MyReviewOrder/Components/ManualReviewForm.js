import React from 'react'
import {Checkbox, Col, Form, Input, Row, Select} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;

let FromBox = React.createClass({
    // selectChange(name,value) {
    //     console.log("111");
    //     var newValue = this.state.formData;
    //     console.log("111");
    //     newValue[name] = value;
    //     this.setState({
    //         visibleRemark: true,
    //         formData:newValue
    //     });
    // },
    getSValue(sData) {
        sData.value = (sData.value == '20') ? '人工审核通过' : '人工复审拒绝';
        return sData;
    },
    // getBlackValue(sblack) {
    //     sblack.value = sblack.value ? '黑名单用户' : '非黑名单用户';
    //     return sblack;
    // },

    changeValue(e) {
        var newValue = this.state.formData;
        var name = e.target.name;
        newValue[name] = e.target.value;
        this.setState({
            formData:newValue
        });
    },
    render() {
        let { getFieldProps } = this.props.form;
        let props = this.props;
        console.log(props);
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 12
            },
        };
        var dhcp=$("#dhcpSle").val();
        return (
           <Form horizontal form={this.props.form}>
                <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" />
                <Row>
                    <Col span="24">
                    <FormItem  {...formItemLayout} label="审批意见:">
                        {props.title != "查看" ? (
                        <Select id="dhcpSle" onChange={this.changeValue} {...getFieldProps('state1', { initialValue: "27" }) } disabled={!props.canEdit}>
                            <Option value="27">人工复审拒绝</Option>
                            <Option value="26">人工复审通过</Option>
                        </Select>) : (<Input type="text" disabled={!props.canEdit}  { ...this.getSValue(getFieldProps('state')) } />)}
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                    <FormItem  {...formItemLayout} label="备注说明:">
                        <Input disabled={!props.canEdit} type="textarea" placeholder="" rows={4} style={{ width: "500px", height: "40px" }}   {...getFieldProps('remark', { initialValue: '',rules:[{ max: 20,message: '不能超过20个字符' }] }) } />
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                    <FormItem  {...formItemLayout} label="借款金额:">
                       <Input /*disabled={dhcp=="27" ? "disabled" :"true"}*/ type="text" {...getFieldProps('amount')  }/>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                    <FormItem  {...formItemLayout} label="是否加入黑名单:">
                        {props.title != "查看" ? (
                           <Checkbox {...getFieldProps('isBlack', { initialValue: false })} />
                           ) : (<Checkbox disabled={!props.canEdit} { ...getFieldProps('isBlack2', { valuePropName: 'checked' }) } />)}
                    </FormItem>
                    </Col>
                </Row>
            </Form>
        )
    }
})
FromBox = createForm()(FromBox);
export default FromBox