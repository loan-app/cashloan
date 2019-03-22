import React from 'react';
import {Icon, Modal, Tooltip} from "antd";
import AddWin from '../forgetPwd'

var Reflux = require('reflux');
var reqwest = require('reqwest');
 var AppActions = require('./actions/AppActions');
 var UserMessageStore = require('./stores/UserMessageStore');
const Top = React.createClass({
   mixins: [
     Reflux.connect(UserMessageStore, "userMessage")
   ],
  getInitialState() {
    return {
      userMessage: {},
      formData: {},
        totalFee:null,
        code:null,
    };
  },
  toggleMenu(){
    this.props.toggleMenu();
  },
   handleClick(e){
     reqwest({
       url: '/system/user/switch/role.htm',
       method: 'get',
       data: {
         role: e
       },
       type: 'json',
       success: (result) => {
         location.reload();
       }
     });
   },

    componentWillReceiveProps(nextProps, nextState) {
        this.fetch(nextProps.params);
    },

    fetch(params = {}) {
        this.setState({
            loading: true
        });
        Utils.ajaxData({
            url: '/modules/manage/calls/outside/getTotalFee.htm',
            data: params,

            callback: (result) => {
                console.log('result ===>'+ result.data),
                    this.setState({
                    loading: false,
                    totalFee: result.data,
                        code:result.code,

                });
            }
        });
    },

  signOut(e){ 
    let req = new XMLHttpRequest();
    req.open('POST', `/system/user/logout.htm`, true);
    req.onload = function (event) {
      let result = JSON.parse(req.responseText);
      if (req.status === 200) {
        if (result.code == 200) {
          localStorage.clear();
          location.reload();
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
    req.send();
  },
  showModal() {
    this.setState({
      visible: true,
      title:"修改密码",
    });
  },
  handleOk() {
    var me = this;
    reqwest({
      url: '/modules/system/modifyPassword.htm',
      method: 'post',
      data: {
        user: JSON.stringify(me.state.formData)
      },
      type: 'json',
      success: (result) => {
        if (result.code == 200) {
          Modal.success({
            title: result.msg,
            onOk: function() {
              me.setState({
                visible: false
              });
              window.location.href = "/j_spring_security_logout";
            }
          });
        }
        else {
          Modal.error({
            title: result.msg
          });
        }
      }
    });
  },
  handleCancel() {
    this.setState({
      visible: false
    });
  },
  changeValue(e) {
    var newValue = this.state.formData;
    var name = e.target.name;
    newValue[name] = e.target.value;
    this.setState({formData: newValue});
  },
   componentDidMount() {
     AppActions.initUserMessageStore();
   },
  hideModal() {
    this.setState({
      visible: false,
      title:"",
    });
  },
  render(){
    var fold = this.props.fold;
    var me = this;
    var formData = this.state.formData;
     this.props.setRoleName(this.state.userMessage.rolename);
     var style ={
         color:this.state.totalFee < 5000 ? (this.state.totalFee < 0 ? "red":"yellow" ):"",
         fontSize:'17px',
      }
    var modalBtns = [
      <button key="back" type="button" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" type="button" className="ant-btn ant-btn-primary" loading={this.state.loading}
              onClick={this.handleOk}>
        提 交
      </button>
    ];
    
     var userMessage = this.state.userMessage;
      var roleList;
    if (userMessage.roleList) {
       roleList = userMessage.roleList.map((role) => {
         return <a key={role.id} onClick={this.handleClick.bind(this,role.id)}>{role.name}</a>
       });
     }
     var toggleRole = (<div> {roleList} </div>);

    return (
      <div className="main-header">
        <div className="logo">
          <span className="logo-mini"></span>
          <span className="logo-lg"></span>
        </div>
        <div className="navbar navbar-static-top">
          <a href="#" className="sidebar-toggle" onClick={this.toggleMenu}>
            <Icon type={`${ fold ? 'menu-unfold' : 'menu-fold'}`}/><span className="system-name">后台管理系统</span>
          </a>
          <div className="navbar-custom-menu">
            <div className="fn-right right-block">


                {this.state.code === 200 ? (this.state.totalFee < 5000 ? (this.state.totalFee < 0 ? <dev id="dev-warning" style={style}> 您的数据费用已经欠费，请联系商务充值，以免影响您的正常使用 </dev> :
                    <dev style={style}>您的数据费用余额已经低于预警值，请联系商务尽快充值。 </dev>) : <dev> </dev>):<dev> </dev>}欢迎您，{ userMessage.name }
              <a onClick={this.signOut}>
                <Icon type="logout"/> 注销
              </a>
              <i className="anticon anticon-edit"></i><a style={{display:"inline-block",marginLeft:'5px'}}
                                                            onClick={this.showModal.bind(this,userMessage.id)}>修改密码</a>
               <Tooltip placement="bottom" title={toggleRole}>
                <a className="">[切换角色]</a>
              </Tooltip> 
            </div>

          </div>
        </div>

        <AddWin ref="AddWin"  visible={this.state.visible}    title={this.state.title} hideModal={me.hideModal} /> 
      </div>
    )
  }
});
export default Top;