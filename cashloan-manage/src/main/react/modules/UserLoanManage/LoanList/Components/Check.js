import React from 'react';
import {Col, Form, Input, Modal, Row, Select, Tabs, Checkbox} from 'antd';

import RuleReport from './RuleReport';
import Lookdetails from './Lookdetails';
import Tab1 from './Tab1';
import Tab2 from './Tab2';
import Tab3 from './Tab3';
import Tab6 from './Tab6';
import Tab9 from "./Tab9";
import Operator from "../../../common/OperatorInfo/Operator";
import Tab8 from "../../../common/LoanReport/Tab8";

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const TabPane = Tabs.TabPane;
var confirm = Modal.confirm;
var Check = React.createClass({
  getInitialState() {
    return {
      checked: true,
      disabled: false,
      data: "",
      record: "",
      activekey: '1',
      tabClick:false,
      visibleAmount:true
    };
  },
    changeValue(e) {
        if(e == 302) {
            this.setState({
                visibleAmount:false
            });
        } else {
            this.setState({
                visibleAmount:true
            });
        }
    },
  handleCancel() {
    this.props.form.resetFields();
    this.props.hideModal();
  },
  changeTabState() {
      this.setState({
          activekey: '1',
      })
  },
  handleTabClick(key) {
      this.setState({
          activekey: key,
          tabClick:true,
      })
  },
  tabClickChange(){
      this.setState({
          tabClick:false,
      })
  },
  handleOk() {

    let me = this;
    let params = this.props.form.getFieldsValue();
    let record = this.props.record;
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      var tips = '是否确定提交';
      confirm({
        title: tips,
        onOk: function () {
          Utils.ajaxData({
            url: '/modules/manage/borrow/auditBorrowLoan.htm',
            data: { borrowId: record.id, state: params.state1, remark: params.remark ,isBlack :params.state1 == "0" ? "20" :params.isBlack,amount:params.amount},
            callback: (result) => {
              if (result.code == 200) {
                me.handleCancel();
              };
              let resType = result.code == 200 ? 'success' : 'error';
              Modal[resType]({
                title: result.msg,
              });
            }
          });
        },
        onCancel: function () { }
      })

    })

  },
  render() { 
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>取消</button>,
      <button key="sure" className="ant-btn ant-btn-primary" onClick={this.handleOk}>确定</button>
    ];
    var modalBtnstwo = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>,
    ];
    const formItemLayout = {
      labelCol: {
        span: 8
      },
      wrapperCol: {
        span: 12
      },
    };
    const formItemLayouttwo = {
      labelCol: {
        span: 4
      },
      wrapperCol: {
        span: 20
      },
    };

    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="1200" footer={props.title == "查看详情" ? [modalBtnstwo] : [modalBtns]} maskClosable={false} >

        <Tabs onTabClick={this.handleTabClick}  activekey={this.state.activekey}>
          {/*<TabPane tab="基本信息" key="3">*/}
              {/*<Tab1 record={props.record} dataForm={props.dataForm} ref="Tab1" canEdit={props.canEdit} visible={props.visible} recordSoure={props.recordSoure} activeKey1={this.state.activekey}/>*/}
          {/*</TabPane>*/}
          {/*<TabPane tab="规则报告" key='1'>*/}
            {/*<RuleReport ref='RuleReport' record={this.props.record} visible={props.visible} activeKey1={this.state.activekey}/>*/}
          {/*</TabPane>*/}
          {/*<TabPane tab="详细信息" key='2'>*/}
            {/*<Lookdetails dataRecord={props.dataRecord} canEdit={props.canEdit} record={this.props.record} visible={props.visible} activeKey1={this.state.activekey}/>*/}
          {/*</TabPane>*/}
          {/*<TabPane tab="通讯录" key="4">*/}
            {/*<Tab2 record={props.record} ref="Tab2" canEdit={props.canEdit} visible={props.visible} activeKey1={this.state.activekey} tabClick={this.state.tabClick} tabClickChange={this.tabClickChange}/>*/}
          {/*</TabPane>*/}
          {/*<TabPane tab="通话记录" key="5">*/}
            {/*<Tab3 record={props.record} ref="Tab3" canEdit={props.canEdit} visible={props.visible} activeKey1={this.state.activekey}/>*/}
          {/*</TabPane>*/}
          {/*<TabPane tab="借款记录" key="6">*/}
            {/*<Tab6 record={props.record} ref="Tab6" canEdit={props.canEdit} activeKey1={this.state.activekey}/>*/}
          {/*</TabPane>*/}
          {/*<TabPane tab="邀请记录" key="7">*/}
            {/*<Tab7 record={props.record} ref="Tab7" canEdit={props.canEdit} activeKey1={this.state.activekey}/>*/}
          {/*</TabPane>*/}
                <TabPane tab="基本信息" key="3">
                    <Tab1 record={props.record} dataForm={props.dataForm} ref="Tab1" canEdit={props.canEdit} visible={props.visible} recordSoure={props.recordSoure} activeKey1={this.state.activekey}/>
                </TabPane>
                <TabPane tab="运营商信息" key='Operator'>
                    <Operator record={props.record} visible={props.visible} activekey={this.state.activekey}/>
                </TabPane>
                <TabPane tab="通话详情统计" key="9">
                    <Tab9 record={props.record} ref="Tab9" canEdit={props.canEdit} activeKey1={this.state.activekey}/>
                </TabPane>
                <TabPane tab="通讯录" key="4">
                    <Tab2 record={props.record} ref="Tab2" canEdit={props.canEdit} visible={props.visible} activeKey1={this.state.activekey} tabClick={this.state.tabClick} tabClickChange={this.tabClickChange}/>
                </TabPane>
                <TabPane tab="通话记录" key="5">
                    <Tab3 record={props.record} ref="Tab3" canEdit={props.canEdit} visible={props.visible} activeKey1={this.state.activekey}/>
                </TabPane>
                <TabPane tab="借款记录" key="6">
                    <Tab6 record={props.record} ref="Tab6" canEdit={props.canEdit} activeKey1={this.state.activekey}/>
                </TabPane>
                <TabPane tab="信用报告" key="8">
                    <Tab8 userId={props.record.userId} borrowId={props.record.id} ref="Tab8" canEdit={props.canEdit} activeKey1={this.state.activekey}/>
                </TabPane>
                <TabPane tab="详细信息" key='2'>
                    <Lookdetails dataRecord={props.dataRecord} canEdit={props.canEdit} record={this.props.record} visible={props.visible} activeKey1={this.state.activekey}/>
                </TabPane>
                <TabPane tab="规则报告" key='1'>
                    <RuleReport ref='RuleReport' record={this.props.record} visible={props.visible} activeKey1={this.state.activekey}/>
                </TabPane>
        </Tabs>
       {props.title == '审核' ?  <Form horizontal form={this.props.form}>
          <Row>
            <Col span="24">
              <FormItem  {...formItemLayout} label="放款审核:">
                  <Select style={{width:'200px'}}  {...getFieldProps('state1', {onChange:this.changeValue ,rules:[{ required: true, message: '不能为空,请您选择' }]}) }>
                    <Option value="302">审核通过</Option>
                    <Option value="303">审核不通过</Option>
                  </Select>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="24">
              <FormItem  {...formItemLayout} label="审核备注:">
                <Input type="textarea" placeholder="" rows={4} style={{ width: "500px", height: "40px" }}   {...getFieldProps('remark', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
           <Row>
               <Col span="24">
                   <FormItem  {...formItemLayout} label="借款金额:">
                       <Input disabled={ this.state.visibleAmount } type="number" placeholder="请输入要修改的借款金额"{...getFieldProps('amount', {rules:[{ required: true, message: '不能为空' }]})  }/>
                   </FormItem>
               </Col>
           </Row>
           <Row>
               <Col span="24">
                   <FormItem  {...formItemLayout} label="是否加入黑名单:">
                       {props.title == "审核" ? (
                           <Checkbox disabled={ !this.state.visibleAmount } {...getFieldProps('isBlack', { initialValue: false })} />
                       ) : (<Checkbox { ...getFieldProps('isBlack2', { valuePropName: 'checked' }) } />)}
                   </FormItem>
               </Col>
           </Row>
        </Form> : <Form horizontal form={this.props.form}><Row></Row></Form>}
      </Modal>
    );
  }
});
Check = createForm()(Check);
export default Check;