import React from 'react';
import {
    Button,
    Form,
    Input,
    InputNumber,
    Modal,
    Select,
    Tree,
    TreeSelect,
    Row,
    Col,
    DatePicker,
    Message,
    Upload,
    Icon,
    message,
    Alert
} from 'antd';
import './button.css';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const confirm = Modal.confirm;
const objectAssign = require('object-assign');
const file1 ={
    position: 'absolute',
    width: '120px',
    left: '4px',
    top: '-1px',
    'borderColor': '#f50',
    'boxShadow': '0 0 0 2px rgba(255, 85, 0, 0.2)',
     opacity: '0'
};
const textSty ={
    position: 'absolute',
    left: '-68px',
    color: '#f50',
     opacity: '1'
};
const textSty1={
    position: 'absolute',
    top: '26px',
    left: '9px',
    color: '#f50',
    opacity: '0'
}

var AddWin = React.createClass({

    getInitialState() {
        return {
            loading: false,
            record:"",
            data:"",
            canEdit:true,
            dataRecord:[],
            
        };
    },
  handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
          this.setState({
            canEdit:true,
            dataRecord:"",  
            fileLength: 0
        })  
        document.getElementById('file').value = '';
    },

   componentWillReceiveProps(nextProps, nextState) {
        
    },

   componentDidMount() {
    },

    // 验证排序号
    isNumber (rule, value, callback) {
        if (!value) {
            value = '';
        }
        if (!!value && value.match(/^\+?[1-9][0-9]*$/g)) {
            callback();
        } else {
            callback('请输入正整数');
        }
    },

   handleOk1() {
    var me = this;
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      let filea = me.state.filea;
      if(!filea){
        //   alert('请上传图片');
          var Input1 = document.getElementById('file1');  
          var span1 = document.getElementById('text1');
          var span = document.getElementById('text');
          Input1.style.opacity="1";  
          span1.style.opacity="1"; 
          span.style.opacity="1"; 
          return;
      }else{
          var Input1 = document.getElementById('file1');  
          var span1 = document.getElementById('text1');
          var span = document.getElementById('text');
          Input1.style.opacity="0";  
          span1.style.opacity="0"; 
          span.style.opacity="0"; 

         filea.append('sort', values.sort);
         filea.append('state', values.state);
         filea.append('title', values.title);
         filea.append('link', values.link?values.link:"");
         let req = new XMLHttpRequest();
            req.open('POST', `/modules/manage/adver/save.htm`, true);
            req.onload = function (event) {
                let result = JSON.parse(req.responseText);
                if (req.status === 200) {
                    if (result.code == 200) {
                        Modal.success({
                            title:result.msg
                        })
                        me.handleCancel();
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
        }
    })
  }, 
 
    handleFileChange(files) {
    let fileObj = document.getElementById('file').files;
    let data = new FormData();
    data.append('bannerFile', fileObj[0]);

    this.setState({
      filea:data,
      fileLength:fileObj.length 
    })
  },
   
    render() {
       
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        let formData = this.props.form.getFieldsValue();
        
        var modalBtns = [
            <button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk1}>
                确定
            </button>,
            <button key="back" className="ant-btn" onClick={this.handleCancel}>取消</button>
        ];
        var modalBtnsLook = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>
        ];
         const formItemLayout = {
            labelCol: {
                span: 6
            },
            wrapperCol: {
                span: 16
            },
        };

        return (
             <Modal  title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={props.title =='查看'?modalBtnsLook:modalBtns} maskClosable={false} >
                <Form horizontal form={this.props.form} >
                 <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" /> 
                       <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="标题:">
                                    <Input type="text"  disabled={!props.canEdit}  {...getFieldProps('title', { rules: [{ required: true,  message: '必填' },{max:30,message: '不得超过30个字符'}] }) } />                                 
                                </FormItem>
                            </Col>
                        </Row>   
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="链接地址:">
                                    <Input type="text"  disabled={!props.canEdit}  {...getFieldProps('link', { rules: [{  max:200, message: '不得超过200个字符', }] }) } />                                 
                                </FormItem>
                            </Col>
                        </Row>     
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="排序号:">
                                    <Input type="text" disabled={!props.canEdit}  {...getFieldProps('sort', {
                                            rules: [
                                                { required: true, message: '必填' },
                                                { validator: this.isNumber },
                                            ] 
                                         })} style={{ width: 80 }}/>                                 
                                </FormItem>
                            </Col>
                        </Row>                       
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="启用状态:">
                                   <Select disabled={!props.canEdit}   {...getFieldProps('state', { initialValue: '10' , rules: [{ required: true, message: '必填', }] } ) } style={{ width: 80 }} >
                                        <Option value="10"> 启用</Option>
                                        <Option value="20"> 禁用</Option>
                                    </Select>                              
                                </FormItem>
                            </Col>
                        </Row>  
                        <Row>
                            <Col span="24">
                                <FormItem  {...formItemLayout} label="图片文件:">
                                    <span id='text' style={textSty}>*</span>
                                    <Input id="file1" style={file1} />
                                    <span id='text1' style={textSty1}>必填</span>
                                    <a className="a-upload">
                                        <Input id="file" onChange={this.handleFileChange} type="file" />点击这里上传图片
                                    </a>
                                    {this.state.fileLength > 0 ? 
                                        <span style={{'position':'relative',left: 12,top: -6,}}>已上传图片</span>:
                                        <span style={{'position':'relative',left: 12,top: -6,color:'red'}}>未选择任何文件</span>
                                    }
                                </FormItem>
                            </Col>
                        </Row>           
                </Form>
            </Modal>
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
