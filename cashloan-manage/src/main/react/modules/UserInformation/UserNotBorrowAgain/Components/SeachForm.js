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
    var json = {realName:params.realName,phone:params.phone};
    if(json.realName){
        json.realName = json.realName.replace(/\s+/g, "")
    }
    this.props.passParams({
        searchParams : JSON.stringify(json),
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
        var json = encodeURI(JSON.stringify(params));
        window.open("/modules/manage/notBorrowAgain/export.htm?searchParams="+json);

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
        <FormItem label="手机号码：">
          <Input {...getFieldProps('phone') } />
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