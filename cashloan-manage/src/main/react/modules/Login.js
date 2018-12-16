import React from 'react'
import {
    Button,
    Form,
    Input,
    Select,
    Checkbox,
    Modal
} from 'antd';

import './login.css';
const FormItem = Form.Item;
const Option = Select.Option;
let Login = React.createClass({
    getInitialState() {
        return {
            ischecked: 0,
            height: window.innerHeight
        }
    },
    handleSubmit(e) {
        e.preventDefault();
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
            //console.log(values);
            var params = values;
            this.login(params);
        });
    },
    login(params) {
        var me = this;
        Utils.ajaxData({
            url: '/system/user/login.htm',
            contentType: 'application/x-www-form-urlencoded',
            data: params,
            callback: function (result) {
                if (result.code == 200) {
                    localStorage.isLogin = true;
                    localStorage.Token = result.Token;
                    location.reload();
                } else {
                    me.changeImg();
                    Modal.error({
                        title: result.msg,
                        onOk: () => {
                            me.props.form.resetFields(['code']);
                        }
                    });
                }
            }
        });
    },
    onWindowResize() {
        this.setState({
            height: window.innerHeight
        })
    },
    componentDidMount() {
        var username = Cookies.get("username");
        var password = Cookies.get("password");
        if (username) {
            this.props.form.setFieldsValue({ username, password });
        }
        this.move();
        window.addEventListener('resize', this.onWindowResize);
    },
    move() {
        var x = 300, y = 200;
        $('.center').mousemove(function (e) {
            e = e || window.event;
            e.stopPropagation();
            var mX = x - e.clientX;
            var mY = x - e.clientY;
            mX = mX / 100;
            mY = mY / 30;
            $('ul').css({ 'transform': 'rotateX(' + mY + 'deg) rotateY(' + mX + 'deg)' });
        });
    },
    changeImg() {
        var imgSrc = document.getElementsByClassName("imgCode")[0];
        var times = Date.now();
        imgSrc.setAttribute("src", '/system/user/imgCode/generate.htm?timestamp=' + times);
    },
    componentWillUnmount() {
        window.removeEventListener('resize', this.onWindowResize)
    },
    render() {

        const {
            getFieldProps
        } = this.props.form;
        return (
            <div style={{minHeight:700,minWidth:1200}}>
                <div className="g-loginbox">
                    <div className="g-bd">
                        <div className="m-loginbg" style={{ height: this.state.height }}>
                            <img src="/dev/images/bg-logo.png" />
                            <img src="/dev/images/support.png" />
                        </div>
                        <div className="m-bgwrap" style={{ cursor: "pointer" }}></div>
                        <div className='center'>
                            <ul>
                                <li><img width='150px' src="/dev/images/lg.png" /></li>
                                <li><img width='500px' src="/dev/images/Graphics.png" /></li>
                                <li><img width='500px' src="/dev/images/light.png" /></li>
                            </ul>
                            <div className="m-loginboxbg" ></div>
                            <div className="m-loginbox">
                                <div className="lbinner" id="J_body_bg">
                                    <div className="login-form">
                                        <div className="login-hd">智能管理</div>
                                        <div className="login_input">
                                            <Form inline-block onSubmit={this.handleSubmit} form={this.props.form}>
                                                <FormItem>
                                                    <Input type="text" className="ipt ipt-user" name="username" autoComplete="off"
                                                        {...getFieldProps('username', {

                                                            rules: [{
                                                                required: true,
                                                                message: '请输入账户名'
                                                            }],
                                                            trigger: 'onBlur'
                                                        })
                                                        }
                                                        placeholder="用户名" />
                                                </FormItem>
                                                <FormItem >
                                                    <Input type="password" className="ipt ipt-pwd" name="password" autoComplete="off"
                                                        {...getFieldProps('password', {

                                                            rules: [{
                                                                required: true,
                                                                whitespace: false,
                                                                message: '请输入密码'
                                                            }],
                                                            trigger: 'onBlur'
                                                        })
                                                        }
                                                        placeholder="密码" />
                                                </FormItem>
                                                {/*<FormItem >
                                                    <Input type="password" className="ipt ipt-pwd" name="accessCode" autoComplete="off"
                                                       {...getFieldProps('accessCode')}
                                                       placeholder="访问码"/>
                                            </FormItem>*/}
                                                <FormItem >
                                                    <Input type="text" className="ipt ipt-pwd1" name="code" autoComplete="off"
                                                        {...getFieldProps('code', {
                                                            rules: [{
                                                                required: true,
                                                                whitespace: false,
                                                                message: '请输入图片验证码'
                                                            }],
                                                            trigger: 'onBlur'
                                                        })
                                                        }
                                                        placeholder="验证码" />
                                                    <img onClick={this.changeImg} className='imgCode' src="/system/user/imgCode/generate.htm" alt="图片验证码" />
                                                </FormItem>
                                                <Button type="primary" size="large" className="ant-input u-loginbtn" htmlType="submit">登录</Button>
                                            </Form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
})

Login = Form.create()(Login);
export default Login;