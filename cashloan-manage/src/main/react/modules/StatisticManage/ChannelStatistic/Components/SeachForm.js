import React from 'react';
import {Button, DatePicker, Form, Select} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const RangePicker = DatePicker.RangePicker;
const Option = Select.Option;

let SeachForm = React.createClass({
  getInitialState() {
        return {
            data: [],
        }
    },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    var json = {endDate:'',startDate:'',code:params.code,channelName:params.channelName};
    if(params.countTime[0]){
          json.startDate = (DateFormat.formatDate(params.countTime[0])).substring(0,10);
          json.endDate = (DateFormat.formatDate(params.countTime[1])).substring(0,10);
    }

      if(json.code){
          json.code = json.code.replace(/\s+/g, "")
      }

      if(json.channelName){
          json.channelName = json.channelName.replace(/\s+/g, "")
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


    componentWillMount(){
      this.fetch()
    },
    fetch() {
        this.setState({
            loading: true
        });
        Utils.ajaxData({
            url: '/modules/manage/promotion/channel/listChannel.htm',

            callback: (result) => {
                    this.setState({
                        loading: false,
                        data : result.data,
                    });
            }
        });
    },
  render() {
    const {
      getFieldProps
    } = this.props.form;

      var optionItem = [];
      if(typeof this.state.data != "undefined"){
          for(var i = 0; i < this.state.data.length; i++){
              optionItem.push(<Option key={this.state.data[i].id} value={this.state.data[i].code}>{this.state.data[i].code}</Option>);
          }
      }

      var optionName = [];
      if(typeof this.state.data != "undefined"){
          for(var i = 0; i < this.state.data.length; i++){
              optionName.push(<Option key={this.state.data[i].id} value={this.state.data[i].name}>{this.state.data[i].name}</Option>);
          }
      }

    return (
      <Form inline >
          <FormItem label="渠道标识：">
              <Select style={{ width: 150 }} placeholder="请选择"  {...getFieldProps('code',{ initialValue:' ', rules: [{ required: true, message: '必填' }] })}>
                  {optionItem}
              </Select>
          </FormItem>
          <FormItem label="渠道名称：">
              <Select style={{ width: 150 }} placeholder="请选择"  {...getFieldProps('channelName',{ initialValue:' ', rules: [{ required: true, message: '必填' }] })}>
                  {optionName}
              </Select>
          </FormItem>
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