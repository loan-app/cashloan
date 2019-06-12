import React from 'react';
import {Button, DatePicker, Form, Input, Select} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RangePicker = DatePicker.RangePicker;

let SeachForm = React.createClass({
  getInitialState() {
        return {

        }
    },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
      var json = {startTime:'',endTime:'',realName:params.realName,idNo:params.idNo,phone:params.phone};
    if(params.realName){
        json.realName = json.realName.replace(/\s+/g, "");
    }
      if(params.time[0]){
          json.endTime=(DateFormat.formatDate(params.time[1])).substring(0,10);
          json.startTime=(DateFormat.formatDate(params.time[0])).substring(0,10);
      }
    this.props.passParams({
        search : JSON.stringify(json),
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
          <FormItem label="报告时间:">
              <RangePicker disabledDate={this.disabledDate}  {...getFieldProps('time', { initialValue: '' }) } />
          </FormItem>
        <FormItem label="真实姓名：">
          <Input  {...getFieldProps('realName') } />
        </FormItem>
        <FormItem label="手机号码：">
          <Input  {...getFieldProps('phone') } />
        </FormItem>
          <FormItem label="身份证号：">
              <Input  {...getFieldProps('idNo') } />
          </FormItem>
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
        <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;