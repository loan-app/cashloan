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
    DatePicker 
} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');

var AddWin = React.createClass({
    getInitialState() {
        return {
            status: {},
            loading: false,
            formData: {},
            record:"",
            tableNameList:"",
            tableCommentList:"",
            data:"",
            dataArray:[],
            tableName:"",
            canEdit:true,
            dataArr:[],
            dataRecord:[],
            dataName:[],
            values:"",
            tableComment:""
        };
    },
  handleCancel() {
        //console.log(11111111111)
        this.props.form.resetFields();
        this.props.hideModal();
          this.setState({
            tableName:"",
            canEdit:true,
            dataArr:[],
            dataRecord:"",
            dataName:[]   
        })  
    },
    componentWillReceiveProps(nextProps, nextState) {

        this.setState({
            dataRecord:nextProps.dataRecord
        })
       
  },

  handleOk() {
        var me = this;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
          //console.log("99999",values);
          if(this.props.title=="新增"){       
            var url= '/modules/manage/task/blacklist/addition.htm';
            var params={
                taskName: values.taskName,
                taskType:values.taskType,
                taskData: values.taskData,
                remark: values.remark,
                yn: values.yn
            };
          }   
         if(this.props.title=="编辑"){
            var url= '/modules/manage/task/blacklist/update.htm';
            var params={
                id: me.props.record.id,
                taskVersion: me.props.record.taskVersion,
                taskType:values.taskType,
                taskData: values.taskData,
                remark: values.remark,
            };
         }  
            Utils.ajaxData({
              url: url,
              data:params,
              method: 'post',
              callback: (result) => {
                 if(result.code=="200"){
                    Modal.success({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                 }else{
                     Modal.error({
                            title: result.msg,
                            onOk: () => {
                                this.handleCancel();
                            }
                        });
                 }
            }
          });     
        })
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading}  onClick={this.handleOk}>
                提 交
            </Button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 5
            },
            wrapperCol: {
                span: 19
            },
        };
        var index = 0;
        if(props.dataRecord){
            for(let i = 0; i < props.dataRecord.length; i++){
                if(props.dataRecord[i].name == props.record.name){
                    index=i;
                }
            }
        }
        //console.log(props.dataRecord[index]);
        return (
             <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={modalBtns} maskClosable={false} >
                <div style={{ position: "relative" }}>
                    <Form horizontal form={this.props.form} style={{ marginTop: "20px" }}>
                            <Row>
                                <Col span="24">
                                    <FormItem  {...formItemLayout} label="任务名:">
                                        <Input type="text" placeholder="请输入任务名" disabled={!props.canEdit} {...getFieldProps('taskName',{rules:[{ required: true, message: '必填'}]})} />
                                    </FormItem>
                                </Col>
                                </Row>
                                <Row>
                                <Col span="24">
                                    <FormItem  {...formItemLayout} label="任务标识:">
                                        <Input type="text" placeholder="请输入字母和数字组合" disabled={false} {...getFieldProps('taskType',{rules:[{ required: true, message: '必填'}]})} />
                                    </FormItem>
                                </Col>
                                </Row>
                                <Row>
                               <Col span="24">
                                    <FormItem  {...formItemLayout} label="备注信息:">
                                        <Input type="text" placeholder="请输入任务备注信息" disabled={false} {...getFieldProps('remark',{rules:[{ required: false, message: '必填'}]})} />
                                    </FormItem>
                              </Col>
                            </Row>
                            {props.canEdit ? <Row>
                                    <Col span="24">
                                        <FormItem  {...formItemLayout} label="是否启动:">
                                            <Select {...getFieldProps('yn', { initialValue: "1" }) }>
                                                <Option value="1">启动</Option>
                                                <Option value="2">禁用</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                </Row> :""}

                            <Row>                         
                               <Col span="24">
                                    <FormItem  {...formItemLayout} label="任务脚本:">
                                        <Input type="textarea" rows={8} placeholder="任务脚本" disabled={false} {...getFieldProps('taskData',{rules:[{ required: true, message: '必填'}]})} />
                                    </FormItem>
                              </Col>
                            </Row>
                    </Form>
                </div>
            </Modal>
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
