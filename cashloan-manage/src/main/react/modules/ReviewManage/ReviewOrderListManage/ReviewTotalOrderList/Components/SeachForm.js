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
        <FormItem label="借款人姓名：">
          <Input  {...getFieldProps('borrowName') } />
        </FormItem>
        <FormItem label="订单号：">
          <Input  {...getFieldProps('orderNo') } />
        </FormItem>
        <FormItem label="手机号码：">
          <Input  {...getFieldProps('phone') } />
        </FormItem>
        <FormItem label="逾期天数：">
        <Input  {...getFieldProps('penaltyDay') } />
        </FormItem>
        <FormItem label="订单状态:">
          <Select style={{ width: 150 }} {...getFieldProps('borrowState',{initialValue: ''})} placeholder='请选择...'>
              <Option value="">全部</Option>
              <Option value="20">自动审核通过</Option>
              <Option value="22">待人工复审</Option>
              <Option value="26">人工复审通过</Option>
              <Option value="27">人工复审不通过</Option>
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
        <FormItem label="分配状态:">
          <Select style={{ width: 100 }} {...getFieldProps('isDestribute',{initialValue: ''})} placeholder='请选择...'>
              <Option value="">全部</Option>
              <Option value="10">已分配</Option>
              <Option value="20">未分配</Option>
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