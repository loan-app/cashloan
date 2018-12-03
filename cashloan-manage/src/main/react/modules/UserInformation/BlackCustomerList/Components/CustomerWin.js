import React from 'react';
import {
  Form,
  Input,
  Modal,
  Row,
  Col,
  Select,
  Button

} from 'antd';
import './button.css';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const confirm = Modal.confirm;
const objectAssign = require('object-assign');
var CustomerWin = React.createClass({
  getInitialState() {
    return {
      checked: true,
      disabled: false,
      data: "",
      timeString: "",
      record: "",
      fileName: ''
    };
  },
  handleCancel() {
    this.state.fileName = '';
    var obj = document.getElementById("file"); 
    obj.value = null;  
    this.state.filea = undefined;
    this.props.form.resetFields();
    this.props.hideModal();
  },
  handleOk() {
    var me = this;
    let filea = '';
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      filea = me.state.filea;
      if(filea === undefined){
        Modal.warning({
          title: '请选择上传文件'
        })
      }
      filea.append('type', values.type);
      confirm({
        title: '你是否确定上传',
        onOk: function () {
          let req = new XMLHttpRequest();
          req.open('POST', `/modules/manage/userBlack/importUserBlack.htm`, true);
          req.onload = function (event) {
            let result = JSON.parse(req.responseText);
            if (req.status === 200) {
              if (result.code == 200) {
                // Modal.success({
                //   title: result.msg
                // })
                 confirm({
                    title: '导入处理完成，点击确定下载导入结果。',
                    onOk() {
                      window.open(result.data);
                    },
                    onCancel() {
                       me.handleCancel();
                    },
                  });
               
              } else {
                Modal.error({
                  title: result.msg
                })
              }
            } else {
              Modal.error({
                title: '请求失败'
              })
            }
          };
          req.send(filea);
        },
        onCancel: function(){}
      })

    })
  },
  handleFileChange(files) {
    let fileObj = document.getElementById('file').files[0];
    let data = new FormData();
    data.append('userInfoFile', fileObj);
    this.setState({
      filea: data,
      fileName: fileObj.name
    })
  },



  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;


    const formItemLayout = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 15
      },
    };
    const formItemLayoutone = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 15
      },
    };
    const formItemLayouttwo = {
      labelCol: {
        span: 6
      },
      wrapperCol: {
        span: 18
      },
    };
    var modalBtns = [
      <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
      <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading} onClick={this.handleOk}>
        提 交
            </Button>
    ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false} >
        <Form horizontal form={this.props.form}>
          <Row>
            <Col span="20">
              <FormItem  {...formItemLayout} label="类型:">
                <Select style={{ width: 100 }} {...getFieldProps('type', { initialValue: '10' }) }>
                  <Option value="10">黑名单</Option>
                  <Option value="20">白名单</Option>
                </Select>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="20">
              <FormItem  {...formItemLayout} label="文件:">
                <a href="#" className="a-upload"><input id="file" onChange={this.handleFileChange} type="file" />点击这里上传文件</a>
                <div title={state.fileName?state.fileName:''} id="file1">{state.fileName?(state.fileName.length > 20 ? state.fileName.substring(0,20)+'...' : state.fileName) :''}</div>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
});
CustomerWin = createForm()(CustomerWin);
export default CustomerWin;