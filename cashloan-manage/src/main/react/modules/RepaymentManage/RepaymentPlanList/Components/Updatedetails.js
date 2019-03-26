import React from 'react';
import {
  Button,
  Form,
  Input,
  InputNumber,
  Modal,
  Row,
  Col,
  Select,
  Checkbox,
  Radio,
  message,
  DatePicker,
  Switch,

} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
var confirm = Modal.confirm;
var Updatedetails = React.createClass({
  getInitialState() {
    return {
      value: 1,
      startValue: null
    };
  },
  handleCancel() {
    this.state.value = 1;
    this.state.startValue = null;
    this.props.form.resetFields();
    this.props.hideModal();
  },
  componentWillReceiveProps(nextProps) {
    this.setState({
        record: nextProps.record
    })
  },
  handleOk() {
    var me = this;
    var re = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var re1 = /^(\d|[1-9]\d+)(\.\d+)?$/;
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      var tips = "您是否确定提交";

      confirm({
        title: tips,
        onOk: function () {
          Utils.ajaxData({
            url: "/modules/manage/borrow/repay/updateRepay.htm",
            data: {
              id: values.id,
              repayTotal:values.repayTotal
            },
            callback: (result) => {
              if (result.code == 200) {
                me.handleCancel();
              };
              let resType = result.code == 200 ? 'success' : 'warning';
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

    const formItemLayout = {
      labelCol: {
        span: 4
      },
      wrapperCol: {
        span: 20
      },
    };
    var modalBtns = [
      <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
      <Button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
        提 交
            </Button>
    ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false} >
        <Form horizontal form={this.props.form}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" />
          <Row>
            <Col span="24">
              <FormItem {...formItemLayout} label="还款金额:">
                <Input type="text" placeholder="请输入要修改的还款金额" {...getFieldProps('repayTotal') } />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
});
Updatedetails = createForm()(Updatedetails);
export default Updatedetails;