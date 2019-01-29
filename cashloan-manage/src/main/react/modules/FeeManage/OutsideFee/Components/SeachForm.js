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
    var json = {endTime:'',startTime:'',type: params.type,castType:params.castType};
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
          <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('gmtCreate', { initialValue: '' }) } />
      </FormItem>

      <FormItem label="调用类型:">
          <Select style={{ width: 100 }} {...getFieldProps('type',{initialValue: ''})} placeholder='请选择...'>
          <Option value="">全部</Option>
          <Option value="1">运营商</Option>
          <Option value="2">反欺诈</Option>
          <Option value="3">申请准入</Option>
          <Option value="4">黑灰名单</Option>
          <Option value="5">贷后行为</Option>
          <Option value="6">发送短信</Option>
          <Option value="7">人脸识别</Option>
          <Option value="8">小额网贷</Option>
          <Option value="9">借贷评估</Option>
          <Option value="10">欺诈甄别</Option>
          <Option value="11">染黑统计</Option>
          <Option value="11">行为雷达</Option>
          </Select>
          </FormItem>

          <FormItem label="费用类型:">
              <Select style={{ width: 100 }} {...getFieldProps('castType',{initialValue: ''})} placeholder='请选择...'>
                  <Option value="">全部</Option>
                  <Option value="0">消费</Option>
                  <Option value="1">充值</Option>
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