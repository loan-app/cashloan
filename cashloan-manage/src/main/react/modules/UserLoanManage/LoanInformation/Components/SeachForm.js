import React from 'react';
import {Button, DatePicker, Form, Input, Message, Select} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RangePicker = DatePicker.RangePicker;

let SeachForm = React.createClass({
    getInitialState() {
        return {
            roleList: []
        }
    },
    handleQuery() {
        var params = this.props.form.getFieldsValue();
        var json = {endTime:'',startTime:'',realName:params.realName,phone:params.phone,orderNo:params.orderNo,channelID:params.channelID,again:params.again,state: params.state,minScore:params.minScore,maxScore:params.maxScore};
        if(params.createTime[0]){
            json.startTime = (DateFormat.formatDate(params.createTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.createTime[1])).substring(0,10);
        }
        if(json.realName){
            json.realName = json.realName.replace(/\s+/g, "")
        }
        //params.type = "repay";
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
            //searchParams: JSON.stringify({type:"repay"}),
            searchParams: JSON.stringify({}),
        });
    },
    componentDidMount() {
        this.fetch();
    },
    fetch(){
        Utils.ajaxData({
            url: '/modules/manage/promotion/channel/listChannel.htm',
            callback: (result) => {
            this.setState({
            data: result.data,
            });
            }
        });
    },
    disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
    render() {
        const {getFieldProps} = this.props.form;
        var channelList = [];
        if(this.state.data){
            channelList.push(<Option key={'全部'} value= {''} >全部</Option>);
            this.state.data.map(item => {
                channelList.push(<Option key={item.name} value= {item.id} >{item.name}</Option>)
            })
        }
        return (
            <Form inline>
             <Input type="hidden" {...getFieldProps('state',{initialValue: '30'})} />
             <FormItem label="真实姓名:">
                  <Input  {...getFieldProps('realName')} style={{ width: 80 }} />
             </FormItem>
             <FormItem label="手机号码:">
                  <Input  {...getFieldProps('phone')} style={{ width: 120 }} />
             </FormItem>
             <FormItem label="订单号:">
                  <Input  {...getFieldProps('orderNo')} style={{ width: 132 }}/>
             </FormItem>
             <FormItem label="订单状态:">
             <Select style={{ width: 130 }} {...getFieldProps('state',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="">全部</Option>
                        <Option value="20">自动审核通过</Option>
                        <Option value="21">自动审核不通过</Option>
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
            <FormItem label="注册渠道:">
                <Select style={{ width: 120 }} {...getFieldProps('channelID',{initialValue: ''})}>
                    {channelList}
                </Select>
            </FormItem>
            <FormItem label="是否复借:">
            <Select style={{ width: 80 }} {...getFieldProps('again',{initialValue: ''})} placeholder='请选择...'>
                <Option value="">全部</Option>
                <Option value="10">否</Option>
                <Option value="20">是</Option>
            </Select>
            </FormItem>
            <FormItem label="借款日期:">
            <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('createTime', { initialValue: '' }) } />
            </FormItem>
                <FormItem label="模型评分:">
                    <Input  {...getFieldProps('minScore')} style={{ width: 75 }}/> -- <Input  {...getFieldProps('maxScore')} style={{ width: 75 }}/>
                </FormItem>
             <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
             <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
            </Form>
        );
    }
});
SeachForm = createForm()(SeachForm);
export default SeachForm;