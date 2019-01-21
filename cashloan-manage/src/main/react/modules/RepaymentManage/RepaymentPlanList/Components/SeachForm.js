import React from 'react';
import {Button, DatePicker, Form, Input, Message, Select} from 'antd';

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
        var json = {endTime:'',startTime:'',realName:params.realName,phone:params.phone,orderNo:params.orderNo,state:params.state};
        if(params.registTime){
            json.startTime = (DateFormat.formatDate(params.registTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.registTime[1])).substring(0,10);
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
        });
    },
    handleOut() {
        var params = this.props.form.getFieldsValue();
        var json = {endTime:'',startTime:'',realName:params.realName,phone:params.phone,orderNo:params.orderNo,state:params.state};
        if(params.registTime){
            json.startTime = (DateFormat.formatDate(params.registTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.registTime[1])).substring(0,10);
        }
        window.open("/modules/manage/repayment/plan/export.htm?searchParams="+JSON.stringify(json));

    },
    disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
    render() {

        const { getFieldProps } = this.props.form;

        return (
            <Form inline>
                <FormItem label="真实姓名:">
                    <Input  {...getFieldProps('realName') } />
                </FormItem>
                <FormItem label="手机号码:">
                    <Input  {...getFieldProps('phone') } />
                </FormItem>
                <FormItem label="订单号:">
                    <Input  {...getFieldProps('orderNo') } />
                </FormItem>
                <FormItem label="还款状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('state', { initialValue: '' }) }>
                        <Option value="">全部</Option>
                        <Option value="10">已还款</Option>
                        <Option value="20">未还款</Option>
                        <Option value="30">展期还款</Option>
                    </Select>
                </FormItem>
                <FormItem label="应还日期：">
                    <RangePicker style={{width:"310"}} {...getFieldProps('registTime', { initialValue: '' }) } />
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
                <FormItem><Button onClick={this.handleOut}>导出</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;