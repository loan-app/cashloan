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
        var json = {endTime:'',startTime:'',realName:params.realName,phone:params.phone,bankCardState:params.bankCardState,contactState:params.contactState,idState:params.idState,phoneState:params.phoneState,channelId:params.channelId,userFlag:params.userFlag};
        if(params.registTime[0]){
            json.startTime = (DateFormat.formatDate(params.registTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.registTime[1])).substring(0,10);
        }

        if(json.realName){
            json.realName = json.realName.replace(/\s+/g, "")
        }
        // if(params.realName){
        //   params.realName = params.realName.replace(/\s+/g, "");
        // }
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
        var json = {endTime:'',startTime:'',realName:params.realName,phone:params.phone,bankCardState:params.bankCardState,contactState:params.contactState,idState:params.idState,phoneState:params.phoneState,channelId:params.channelId,userFlag:params.userFlag};
        if(params.registTime[0]){
            json.startTime = (DateFormat.formatDate(params.registTime[0])).substring(0,10);
            json.endTime = (DateFormat.formatDate(params.registTime[1])).substring(0,10);
        }

        if(json.realName){
            json.realName = json.realName.replace(/\s+/g, "");
        }
        // json =encodeURI(json);
        // var json = encodeURI(JSON.stringify(params));
        window.open("/modules/manage/userAuth/export.htm?searchParams="+encodeURI(JSON.stringify(json)));

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

    render() {
        const {
            getFieldProps
            } = this.props.form;
        var channelList = [];
        if(this.state.data){
            channelList.push(<Option key={'全部'} value= {''} >全部</Option>);
            this.state.data.map(item => {
                channelList.push(<Option key={item.name} value= {item.id} >{item.name}</Option>)
            })
        }
        return (
            <Form inline >
                <FormItem label="真实姓名：">
                    <Input  {...getFieldProps('realName') } />
                </FormItem>
                <FormItem label="手机号码：">
                    <Input  {...getFieldProps('phone') } />
                </FormItem>
                <FormItem label="银行卡状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('bankCardState',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="10">未认证</Option>
                        <Option value="20">认证中</Option>
                        <Option value="30">已认证</Option>
                    </Select>
                </FormItem>
                <FormItem label="紧急联系人状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('contactState',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="10">未完善</Option>
                        <Option value="20">完善中</Option>
                        <Option value="30">已完善</Option>
                    </Select>
                </FormItem>
                <FormItem label="身份证认证状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('idState',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="10">未认证</Option>
                        <Option value="20">认证中</Option>
                        <Option value="30">已认证</Option>
                    </Select>
                </FormItem>
                <FormItem label="手机运营商认证状态:">
                    <Select style={{ width: 100 }} {...getFieldProps('phoneState',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="10">未认证</Option>
                        <Option value="20">认证中</Option>
                        <Option value="30">已认证</Option>
                    </Select>
                </FormItem>
                {/*<FormItem label="芝麻授信状态:">*/}
                {/*<Select style={{ width: 100 }} {...getFieldProps('zhimaState',{initialValue: ''})} placeholder='请选择...'>*/}
                {/*<Option value="10">未授信</Option>*/}
                {/*<Option value="20">授信中</Option>*/}
                {/*<Option value="30">已授信</Option>*/}
                {/*</Select>*/}
                {/*</FormItem>*/}
                <FormItem label="注册渠道：">
                    <Select style={{ width: 100 }} {...getFieldProps('channelId',{initialValue: ''})}>
                        {channelList}
                    </Select>
                </FormItem>
                <FormItem label="是否借款:">
                    <Select style={{ width: 80 }} {...getFieldProps('userFlag',{initialValue: ''})} placeholder='请选择...'>
                        <Option value="">全部</Option>
                        <Option value="0">否</Option>
                        <Option value="1">是</Option>
                    </Select>
                </FormItem>
                <FormItem label="注册时间：">
                    <RangePicker disabledDate={this.disabledDate} style={{width:"310"}} {...getFieldProps('registTime', { initialValue: '' }) } />
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