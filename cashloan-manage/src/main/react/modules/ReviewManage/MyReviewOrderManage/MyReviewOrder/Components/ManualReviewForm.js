import React from 'react'
import {Checkbox, Col, Form, Input, Row, Select} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;

let FromBox = React.createClass({
    getSValue(sData) {
        sData.value = (sData.value == '20') ? '人工审核通过' : '人工复审拒绝';
        return sData;
    },

    getBlackValue(sblack) {
        sblack.value = (sblack.value == '10') ? '加入黑名单' : '不加入黑名单';
        return sblack;
    },

    onchange(e){
        console.log(e.target.checked ? '10' : '20');
        this.setState({isBlack:e.target.checked ? '10' : '20'});
    },
    render() {
        let { getFieldProps } = this.props.form;
        let props = this.props;
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 12
            },
        };
        
        return (
           <Form horizontal form={this.props.form}>
                <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" />
                <Row>
                    <Col span="24">
                    <FormItem  {...formItemLayout} label="审批意见:">
                        {props.title != "查看" ? (
                        <Select  {...getFieldProps('state1', { initialValue: "27" }) } disabled={!props.canEdit}>
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

               {/*<Row>*/}
                   {/*<Col span="24">*/}
                       {/*<FormItem  {...formItemLayout} label="是否加入黑名单:">*/}
                           {/*{props.title != "查看" ? (*/}
                               {/*<Select  {...getFieldProps('isBlack1', { initialValue: "20" }) } disabled={!props.canEdit}>*/}
                                   {/*<Option value="20">不加入黑名单</Option>*/}
                                   {/*<Option value="10">加入黑名单</Option>*/}
                               {/*</Select>) : (<Input type="text" disabled={!props.canEdit}  { ...this.getBlackValue(getFieldProps('isBlack')) } />)}*/}
                       {/*</FormItem>*/}
                   {/*</Col>*/}
               {/*</Row>*/}
               {/*<Row>*/}
                   {/*<Col span="24">*/}
                       {/*<FormItem  {...formItemLayout} label="是否加入黑名单:">*/}

                           {/*/!*<input name="isBlack1" type="checkbox" >*!/*/}
                           {/*{props.title != "查看" ? (*/}
                               {/*<Checkbox onChange={onChange} value ={props.isBlack1}>加入黑名单</Checkbox>,*/}
                                   {/*mountNode) : (<Input type="text" disabled={!props.canEdit}  { ...this.getBlackValue(getFieldProps('isBlack')) } />)}*/}
                       {/*</FormItem>*/}
                   {/*</Col>*/}
               {/*</Row>*/}

               <Row>
                   <Col span="24">
                       <FormItem  {...formItemLayout} label="是否加入黑名单:">

                           {props.title != "查看" ? (

                               <Checkbox  {...getFieldProps('isBlack', { initialValue: false }) } onChange={this.onchange} />

                               ) : (<Input type="text" disabled={!props.canEdit}  { ...this.getBlackValue(getFieldProps('isBlack')) } />)}
                       </FormItem>
                   </Col>
               </Row>
            </Form>
        )
    }
})
FromBox = createForm()(FromBox);
export default FromBox