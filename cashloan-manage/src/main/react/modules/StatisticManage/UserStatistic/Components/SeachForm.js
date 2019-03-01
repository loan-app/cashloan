import React from 'react';
import {Button, DatePicker, Form, Select} from 'antd';

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
    var json = {endDate:'',startDate:''};
    if(params.countTime[0]){
          json.startDate = (DateFormat.formatDate(params.countTime[0])).substring(0,10);
          json.endDate = (DateFormat.formatDate(params.countTime[1])).substring(0,10);
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

      <FormItem label="统计时间：">
          <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('countTime', { initialValue: '' }) } />
      </FormItem>
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
          <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;