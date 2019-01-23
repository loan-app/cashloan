import React from 'react';
import { Button, DatePicker, Modal, Form, Input, Select, Message } from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const confirm = Modal.confirm;
const RangePicker = DatePicker.RangePicker;

let SeachForm = React.createClass({
    getInitialState() {
        return {
            roleList: []
        }
    },
    handleQuery() {
        var params = this.props.form.getFieldsValue();
        var json = {endTime:'',startTime:'',realName:params.realName,phone:params.phone,orderNo:params.orderNo,serialNumber:params.serialNumber,repayAccount:params.repayAccount,type:params.type,repayType:params.repayType};
        if(params.repayTime){
            json.startTime = (DateFormat.formatDate(params.repayTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.repayTime[1])).substring(0,10);
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
        var json = {endTime:'',startTime:'',realName:params.realName,phone:params.phone,orderNo:params.orderNo,serialNumber:params.serialNumber,repayAccount:params.repayAccount,type:params.type,repayType:params.repayType};
        if(params.repayTime){
            json.startTime = (DateFormat.formatDate(params.repayTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.repayTime[1])).substring(0,10);
        }
        window.open("/modules/manage/borrowRepayLog/export.htm?searchParams="+JSON.stringify(json));

    },
    render() {

        const { getFieldProps } = this.props.form;

        return (
            <Form inline>
                <FormItem label="真实姓名:">
                    <Input  {...getFieldProps('realName', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="手机号码:">
                    <Input  {...getFieldProps('phone', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="订单号:">
                    <Input  {...getFieldProps('orderNo', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="流水号:">
                    <Input  {...getFieldProps('serialNumber', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="还款账号:">
                    <Input  {...getFieldProps('repayAccount', { initialValue: '' }) } />
                </FormItem>
                <FormItem label="是否金额减免:">
                    <Select style={{ width: 100 }} {...getFieldProps('type', { initialValue: '' }) }>
                        <Option value="">全部</Option>
                        <Option value="10">是</Option>
                        <Option value="20">不是</Option>
                    </Select>
                </FormItem>
                <FormItem label="还款类型:">
                    <Select style={{ width: 100 }} {...getFieldProps('repayType', { initialValue: '' }) }>
                        <Option value="">全部</Option>
                        <Option value="10">还款</Option>
                        <Option value="20">展期还款</Option>
                    </Select>
                </FormItem>
                <FormItem label="实际还款日期：">
                    <RangePicker style={{width:"310"}} {...getFieldProps('repayTime', { initialValue: '' }) } />
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