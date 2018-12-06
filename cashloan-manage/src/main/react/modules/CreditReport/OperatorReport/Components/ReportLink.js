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

} from 'antd';
const CheckboxGroup = Checkbox.Group
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var ReportLink = React.createClass({
    getInitialState() {
        return {
            checked: true,
            disabled: false,
            data:"",
            timeString:"",
            record:""
        };
    },
    handleCancel() {
        this.props.hideModal();
    },
    componentWillReceiveProps(nextProps) {
        this.setState({
            record:nextProps.record,
        })
    },
    render(){
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
                span:15
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
        return(
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="90%" footer={[<button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>]} maskClosable={false} >
                <div>
                    <Col className="view_Form">
                        <iframe style={{border:0,width:"100%",height:630,}} src={this.state.record.operateUrl} />
                    </Col>
                </div>
            </Modal>
        )
    }
});
ReportLink = createForm()(ReportLink);
export default ReportLink;