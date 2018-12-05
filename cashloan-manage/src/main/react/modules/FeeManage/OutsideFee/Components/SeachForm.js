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
const RangePicker = DatePicker.RangePicker;

let SeachForm = React.createClass({
  getInitialState() {
        return {
            
        }
    },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    var json = {endTime:'',startTime:'',type: params.type};
    if(params.gmtCreate){
          json.startTime = (DateFormat.formatDate(params.gmtCreate[0])).substring(0,10);
          json.endTime = (DateFormat.formatDate(params.gmtCreate[1])).substring(0,10);
    }
    this.props.passParams({
      searchParams : JSON.stringify(json),
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
          <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('gmtCreate', { initialValue: '' }) } />
      </FormItem>

      <FormItem label="调用类型:">
          <Select style={{ width: 100 }} {...getFieldProps('type',{initialValue: ''})} placeholder='请选择...'>
          <Option value="">全部</Option>
          <Option value="1">运营商</Option>
          <Option value="2">魔杖反欺诈</Option>
          <Option value="3">魔杖多头</Option>
          <Option value="4">魔杖黑灰名单</Option>
          <Option value="5">魔杖贷后行为</Option>
          </Select>
          </FormItem>
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;