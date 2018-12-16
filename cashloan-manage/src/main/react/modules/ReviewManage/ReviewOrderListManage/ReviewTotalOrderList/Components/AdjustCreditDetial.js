import React from 'react'
import {
  Table,
  Modal,
  Form,
  Row,
  Col,
  Input,
  Popover,
  Select
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var AdjustCreditDetial = React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      canEdit: true,
      visible: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.setState({
                dataRecord: nextProps.dataRecord
            })
  },
  hideModal() {
    this.setState({
      visible: false,
    });

  },
  handleCancel() {
    this.props.form.resetFields();
    this.props.hideModal(); 
  },
  handleOk(){
    var me = this;
    var index;
    for(let i = 0; i < me.props.dataRecord.length; i++){
      if(me.props.dataRecord[i].id == me.props.form.getFieldsValue().name){
        index = i;
      }
    }
    Utils.ajaxData({
        url: "/modules/manage/borrow/manual/review/allotUser.htm",
        data: {
          id: this.props.selectedRowKeys1.toString(),
          userId: this.props.dataRecord[index].id,
          userName: this.props.dataRecord[index].name,
        },
        method: 'get',
        callback: (result) => {
          
          if (result.code == 200) {
            this.handleCancel();
          };
          let resType = result.code == 200 ? 'success' : 'warning';
          Modal[resType]({
              title: result.msg,
            });
        }
      });
  },
  render() {
    var me = this;
    const {
            getFieldProps
        } = me.props.form;
    var props = me.props;
     const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 12
            },
        };
    var modalBtns  = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>,
            <button key="go" className="ant-btn" onClick={this.handleOk}>保存</button>
            ];
    var optionItem = [];
    if(typeof props.dataRecord != "undefined"){
      for(var i = 0; i < props.dataRecord.length; i++){
        optionItem.push(<Option key={props.dataRecord[i].id} value={props.dataRecord[i].id}>{props.dataRecord[i].name}</Option>);
      }
    }
    
    return (
      <Modal title={this.props.title} visible={this.props.visible} onCancel={this.handleCancel} width="800"  footer={modalBtns} >
           <Form horizontal form={this.props.form}>
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="审核人员：">
                                <Select style={{ width: 150 }} placeholder="请选择"  {...getFieldProps('name',{ initialValue:' ', rules: [{ required: true, message: '必填' }] })}>
                                    {optionItem}
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                </Form> 
         </Modal>
        
    );
  }
});
AdjustCreditDetial = createForm()(AdjustCreditDetial);
export default AdjustCreditDetial;