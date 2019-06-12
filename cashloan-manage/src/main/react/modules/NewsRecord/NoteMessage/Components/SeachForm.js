import React from 'react';
import {
    Button,
    Form,
    Input,
    Select,
    Message,
    DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;

let SeachForm = React.createClass({
    getInitialState() {
        return {
            roleList: []
        }
    },
    handleQuery() {
        var params = this.props.form.getFieldsValue();
        if(params.sendTime[0]){
        var year = params.sendTime.getFullYear();
        var month = params.sendTime.getMonth() + 1 ;
        var newMonth = month > 9 ? (month + "") : ("0" + month);
        var day = params.sendTime.getDate();
        var newDay =  day > 9 ? (day + "") : ("0" + day);
       }
        this.props.passParams({
            searchParams: JSON.stringify({sendTime :params.sendTime? year + "-" + newMonth + "-" + newDay:'',phone:params.phone,state:params.state}),
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
    render() {

        const {
            getFieldProps
        } = this.props.form;

        return (
            <Form inline>
                <FormItem label="手机号:">
                    <Input type="text" {...getFieldProps('phone') } />
                </FormItem>
                <FormItem label="发送时间:">
                     <DatePicker  type="text" format="yyyy-MM-dd" {...getFieldProps('sendTime') } />
                </FormItem>
                <FormItem label="发送状态:">
                    <Select style={{ width: 100 }}  placeholder="请选择" {...getFieldProps('state',{initialValue:""}) }>
                        <Option value={""}>全部</Option>
                        <Option value={"10"}>发送成功未使用</Option>
                        <Option value={"20"}>发送成功已使用</Option>
                        <Option value={"30"}>发送中</Option>
                        <Option value={"40"}>发送失败</Option>
                    </Select>
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;