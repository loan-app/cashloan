import React from 'react';
import {
    Modal,
    Form,
    Table,
    Input,
    Row,
    Col,
} from 'antd';
import Show from './Show'

const createForm = Form.create;
const FormItem = Form.Item;
const objectAssign = require('object-assign');
const userbaseTit = {
    color: '#2db7f5',
    textAlign: 'center',
    fontSize: '14px',
    marginBottom: '10px',
    display: 'block',
    width: '150px',
}
var Tab1 = React.createClass({
    getInitialState() {
        return {
            visible: false,
            num: 0
        };
    },
    hidden() {
        this.setState({
            visible: false,
            num: 0
        })
    },
    show(num) {
        this.setState({
            visible: true,
            num: num,
            tit: '照片'
        })
    },
    render() {
        var props = this.props;
        var state = this.state;
        const {
            getFieldProps
        } = this.props.form;
        const data = this.props.dataForm;
        const formItemLayout = {
            labelCol: {
                span: 9
            },
            wrapperCol: {
                span: 14
            },
        };
        const formItemLayout2 = {
            labelCol: {
                span: 5
            },
            wrapperCol: {
                span: 19
            },
        };
        var aItem = [];
        if (props.recordSoure && props.recordSoure.workImgArr) {
            aItem = [];
            for (var i = 0; i < props.recordSoure.workImgArr.length; i++) {
                aItem.push(<a key={i} style={{marginRight: '10px'}} href={props.recordSoure.workImgArr[i]}
                              target="_blank"><img src={props.recordSoure.workImgArr[i]}
                                                   style={{width: 150, height: 150}}/></a>);
            }
        }
        return (
            <Form horizontal form={this.props.form} style={{marginTop: '20'}}>
                {props.recordSoure ?
                    <div>
                        <div className="uploadFile" style={{paddingLeft: '20'}}>
                            <div className="img" style={{height: '210'}}>
                                <span style={userbaseTit}>人脸照片</span>
                                {props.recordSoure.userbase.livingImg ?
                                    <a href='javascript:;' onClick={this.show.bind(this, 0)}><img
                                        src={props.recordSoure.userbase.livingImg}
                                        style={{width: 150, height: 150}}/></a> : <span>暂无</span>}
                            </div>
                            <div className="img" style={{height: '210'}}>
                                <span style={userbaseTit}>身份证正面</span>
                                {props.recordSoure.userbase.frontImg ?
                                    <a href='javascript:;' onClick={this.show.bind(this, 1)}><img
                                        src={props.recordSoure.userbase.frontImg}
                                        style={{width: 150, height: 150}}/></a> : <span>暂无</span>}
                            </div>
                            <div className="img" style={{height: '210'}}>
                                <span style={userbaseTit}>身份证背面</span>
                                {props.recordSoure.userbase.backImg ?
                                    <a href='javascript:;' onClick={this.show.bind(this, 2)}><img
                                        src={props.recordSoure.userbase.backImg}
                                        style={{width: 150, height: 150}}/></a> : <span>暂无</span>}
                            </div>
                            <div className="img" style={{height: '210', width: '500'}}>
                                <span style={userbaseTit}>工作照</span>
                                {aItem}
                            </div>
                        </div>
                    </div>
                    : null}
                <div className="navLine-wrap-left ant-table ant-table-middle ant-table-bordered">
                    <h2>基本信息</h2>
                    <table style={{margin: '6px 0 20px'}}>
                        <tbody>
                        <tr>
                            <td style={{textAlign: 'right', width: '12%'}}>真实姓名</td>
                            <td style={{width: '21%'}}>{data.realName}</td>

                            <td style={{textAlign: 'right', width: '12%'}}>性别</td>
                            <td style={{width: '21%'}}>{data.sex}</td>

                            <td style={{textAlign: 'right', width: '12%'}}>年龄</td>
                            <td style={{width: '22%'}}>{data.age}</td>
                        </tr>
                        <tr className={'table-tr-gray'}>
                            <td style={{textAlign: 'right'}}>身份证号码</td>
                            <td>{data.idNo}</td>

                            <td style={{textAlign: 'right'}}>银行卡号</td>
                            <td>{data.cardNo}</td>

                            <td style={{textAlign: 'right'}}>所属银行</td>
                            <td>{data.bank}</td>
                        </tr>
                        <tr>
                            <td style={{textAlign: 'right'}}>联系电话</td>
                            <td>{data.phone}</td>

                            <td style={{textAlign: 'right'}}>注册时间</td>
                            <td colSpan={3}>{data.registTime}</td>
                        </tr>
                        <tr className={'table-tr-gray'}>
                            <td style={{textAlign: 'right'}}>居住地址</td>
                            <td colSpan={5}>{data.liveAddr}</td>
                        </tr>
                        <tr>
                            <td style={{textAlign: 'right'}}>注册所在地</td>
                            <td colSpan={3}>{data.registerAddr}</td>

                            <td style={{textAlign: 'right'}}>注册地经纬度</td>
                            <td>{data.registerCoordinate}</td>

                        </tr>
                        <tr className={'table-tr-gray'}>
                            <td style={{textAlign: 'right'}}>注册客户端</td>
                            <td>{data.registerClient}</td>

                            <td style={{textAlign: 'right'}}>学历</td>
                            <td>{data.education}</td>

                            <td style={{textAlign: 'right'}}>常用邮箱</td>
                            <td>{data.email}</td>
                        </tr>
                        <tr>
                            <td style={{textAlign: 'right'}}>淘宝账号</td>
                            <td>{data.taobao}</td>

                            <td style={{textAlign: 'right'}}>微信账号</td>
                            <td>{data.wechat}</td>

                            <td style={{textAlign: 'right'}}>QQ账号</td>
                            <td>{data.qq}</td>
                        </tr>
                        </tbody>
                    </table>

                    <h2>单位信息</h2>
                    <table style={{margin: '6px 0 20px'}}>
                        <tbody>
                        <tr>
                            <td style={{textAlign: 'right', width: '12%'}}>单位名称</td>
                            <td style={{width: '21%'}}>{data.companyName}</td>

                            <td style={{textAlign: 'right', width: '12%'}}>单位电话</td>
                            <td style={{width: '21%'}}>{data.companyPhone}</td>

                            <td style={{textAlign: 'right', width: '12%'}}>工作年限</td>
                            <td style={{width: '22%'}}>{data.workingYears}</td>
                        </tr>
                        <tr >
                            <td style={{textAlign: 'right'}}>单位地址</td>
                            <td colSpan={5}>{data.companyAddr}</td>
                        </tr>
                        </tbody>
                    </table>

                    <h2>联系人信息</h2>
                    <table style={{margin: '6px 0 20px'}}>
                        <tbody>
                        <tr>
                            <td style={{textAlign: 'right', width: '12%'}}>紧急联系人姓名</td>
                            <td style={{width: '21%'}}>{data.urgentName}</td>

                            <td style={{textAlign: 'right', width: '12%'}}>紧急联系人联系方式</td>
                            <td style={{width: '21%'}}>{data.urgentPhone}</td>

                            <td style={{textAlign: 'right', width: '12%'}}>紧急联系人与本人关系</td>
                            <td style={{width: '22%'}}>{data.urgentRelation}</td>
                        </tr>
                        <tr >
                            <td style={{textAlign: 'right'}}>其他联系人姓名</td>
                            <td>{data.otherName}</td>

                            <td style={{textAlign: 'right'}}>其他联系人联系方式</td>
                            <td>{data.otherPhone}</td>

                            <td style={{textAlign: 'right'}}>其他联系人与本人关系</td>
                            <td>{data.otherRelation}</td>
                        </tr>
                        <tr>
                            <td style={{textAlign: 'right'}}>其他联系人姓名</td>
                            <td>{data.otherThirdName}</td>

                            <td style={{textAlign: 'right'}}>其他联系人联系方式</td>
                            <td>{data.otherThirdPhone}</td>

                            <td style={{textAlign: 'right'}}>其他联系人与本人关系</td>
                            <td>{data.otherThirdRelation}</td>
                        </tr>
                        </tbody>
                    </table>

                    <h2>认证状态</h2>
                    <table style={{margin: '6px 0 20px'}}>
                        <tbody>
                        <tr>
                            <td style={{textAlign: 'right', width: '12%'}}>身份认证状态</td>
                            <td style={{width: '21%'}}>{data.idState}</td>

                            <td style={{textAlign: 'right', width: '12%'}}>银行卡认证状态</td>
                            <td style={{width: '21%'}}>{data.bankCardState}</td>

                            <td style={{textAlign: 'right', width: '12%'}}>运营商认证状态</td>
                            <td style={{width: '22%'}}>{data.phoneState}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <Show ref='Show' visible={state.visible} recordSoure={props.recordSoure} num={state.num}
                      hidden={this.hidden}/>
            </Form>
        );
    }
});
Tab1 = createForm()(Tab1);
export default Tab1;