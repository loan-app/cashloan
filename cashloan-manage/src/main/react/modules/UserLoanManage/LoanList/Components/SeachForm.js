import React from 'react';
import {Button, Form, Input, Select,Message,DatePicker } from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RangePicker = DatePicker.RangePicker;

let SeachForm = React.createClass({
    getInitialState() {
        return {
            roleList: []
        }
    },
    handleQuery() {
        var params = this.props.form.getFieldsValue();
        params.type = "repay";
        var json = {endTime:'',startTime:'',realName:params.realName,phone:params.phone,orderNo:params.orderNo,state: params.state,type: params.type};
        if(params.createTime[0]){
            json.startTime = (DateFormat.formatDate(params.createTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.createTime[1])).substring(0,10);
        }

        this.props.passParams({
            searchParams: JSON.stringify(json),
            pageSize: 10,
            current: 1,
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            pageSize: 10,
            current: 1,
            searchParams: JSON.stringify({type:"repay"}),
        });
    },
    render() {
        const {getFieldProps} = this.props.form;
        return (
            <Form inline>
             <Input type="hidden" {...getFieldProps('state',{initialValue: '30'})} />
             <FormItem label="真实姓名:">
                  <Input  {...getFieldProps('realName')} />
             </FormItem>
             <FormItem label="手机号码:">
                  <Input  {...getFieldProps('phone')} />
             </FormItem>
             <FormItem label="订单号:">
                  <Input  {...getFieldProps('orderNo')} />
             </FormItem>
             <FormItem label="订单状态:">
             <Select style={{ width: 170 }} {...getFieldProps('state',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="">全部</Option>
                        <Option value="20">自动审核通过</Option>
                        <Option value="26">人工复审通过</Option>
                        <Option value="301">待放款审核</Option>
	   		            <Option value="302">放款审核通过</Option>
	   		            <Option value="303">放款审核不通过</Option>
                        <Option value="30">放款成功</Option>
                        <Option value="31">放款失败</Option>
                        <Option value="40">还款成功</Option>
                        <Option value="41">还款成功-金额减免</Option>
                        <Option value="43">还款处理中</Option>
                        <Option value="50">逾期</Option>
                        <Option value="90">坏账</Option>
            </Select>
            </FormItem>
            <FormItem label="借款日期：">
            <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('createTime', { initialValue: '' }) } />
            </FormItem>
             <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
             <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
            </Form>
        );
    }
});
SeachForm = createForm()(SeachForm);
export default SeachForm;