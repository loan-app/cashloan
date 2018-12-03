import React from 'react';
import {
  Button,
  Form,
  Input,
  Select,
  DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;

let SeachForm = React.createClass({
  getInitialState() {
        return {
            
        }
    },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    this.props.passParams({
      searchParams : JSON.stringify(params),
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
      <Form inline >
        <FormItem label="真实姓名：">
          <Input  {...getFieldProps('realName') } />
        </FormItem>
        <FormItem label="身份证号码：">
          <Input  {...getFieldProps('idNo') } />
        </FormItem>
        <FormItem label="手机号：">
          <Input  {...getFieldProps('phone') } />
        </FormItem>
         <FormItem label="类型：">
           <Select size="large" style={{width:160}}   {...getFieldProps('type') } >
                  <Option value="">全部</Option>
                  <Option value="10">黑名单</Option> 
                  <Option value="20">白名单</Option> 
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