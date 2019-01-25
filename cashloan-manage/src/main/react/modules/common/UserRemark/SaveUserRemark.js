import React from 'react'
import {Col, Form, Input, Row, Select} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;

let SaveUserRemark = React.createClass({


    render() {
        let { getFieldProps } = this.props.form;
        let props = this.props;
        console.log(this.props.form);
        const formItemLayout = {
            labelCol: {
                span: 6
            },
            wrapperCol: {
                span: 12
            },
        };

        return (
            <Form horizontal form={this.props.form}>
                <Input  {...getFieldProps('userId', { initialValue: props.userId }) } type="hidden" />
                <Row>
                    <Col span="24">
                            <FormItem  {...formItemLayout} label="备注说明:">
                                <Input disabled={!props.canEdit} type="textarea" placeholder="" rows={4} style={{
                                    width: "500px",
                                    height: "100px"
                                }}  {...getFieldProps('remark', {
                                    initialValue: '',
                                    rules: [{max: 30, message: '不能超过30个字符'}]
                                })} />
                            </FormItem>
                    </Col>
                </Row>
            </Form>
        )
    }
})
SaveUserRemark = createForm()(SaveUserRemark);
export default SaveUserRemark