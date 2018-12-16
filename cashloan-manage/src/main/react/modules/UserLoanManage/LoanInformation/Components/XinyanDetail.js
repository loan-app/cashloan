import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Tree,
    TreeSelect,
    Row,
    Col
} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');

let treeData = [];
/*Utils.ajaxData({
 url: '/modules/system/checkboxoffices.htm',
 method: 'get',
 type: 'json',
 callback: (result) => {
 let data = result.data;
 treeData = data;
 }
 });*/

var Lookdetails = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {}
        };
    },
    componentWillMount() {
        Utils.ajaxData({
            url: '/modules/manage/xinyan/loan/report/detail.htm',
            data: {
                userId: this.props.userId
            },
            callback: (result) => {
                this.setState({
                    formData: result.data
                })
            }
        });
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>
        ];
        if (!props.canEdit) {
            modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
        }
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };

        return (
            <Form horizontal form={this.props.form}>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="分数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.score} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务最大授信额度:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curMaxCredit} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务平均授信额度:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curAvgCredit} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务近1个月贷款笔数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curLoanCnt30d} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务近3个月贷款笔数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curLoanCnt90d} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务近6个月贷款笔数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curLoanCnt180d} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务贷款总笔数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curLoanTotalCnt} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务贷款机构数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curLoanOrgTotalCnt}
                                   type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务最近一次贷款距今天数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curLastToEndLoan} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务贷款已结清笔数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curLoanClearCnt} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务贷款逾期订单数（30天内）:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curOverdueCnt30d} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="本业务贷款逾期订单数（大于30天）:">
                            <Input disabled={!props.canEdit} value={this.state.formData.curOverdueCntMore30d}
                                   type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="查询多头机构数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.queryOrgCnt} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="总查询次数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.queryCnt} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近查询时间距今天数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.lastToEndDays} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="近1个月查询多头:">
                            <Input disabled={!props.canEdit} value={this.state.formData.queryCnt30d} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="近3个月查询多头:">
                            <Input disabled={!props.canEdit} value={this.state.formData.queryCnt90d} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="近6个月查询多头:">
                            <Input disabled={!props.canEdit} value={this.state.formData.queryCnt180d} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="贷款已结清笔数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.loanClearNum} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="贷款逾期订单数（30天内）:">
                            <Input disabled={!props.canEdit} value={this.state.formData.overdueCnt30d} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="贷款逾期订单数（大于30天）:">
                            <Input disabled={!props.canEdit} value={this.state.formData.overdueCntMore30d} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近1个月工作日全部产品非逾期借贷在总借贷中金额占比:">
                            <Input disabled={!props.canEdit} value={this.state.formData.workDayNotOverdueAmountRadio30d}
                                   type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近6个月全部时间全部产品非逾期借贷在总借贷中订单数占比:">
                            <Input disabled={!props.canEdit} value={this.state.formData.notOverdueOrderRadio180d}
                                   type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近3个月全部时间消费金融类在全部产品中借贷确定逾期订单数占比:">
                            <Input disabled={!props.canEdit} value={this.state.formData.overdueOrderRadio90d}
                                   type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近一年新增平台全部时间全部产品最大借贷费率:">
                            <Input disabled={!props.canEdit} value={this.state.formData.maxLoanRate12m} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近12个月全部时间超短期现金贷平均借贷费率:">
                            <Input disabled={!props.canEdit} value={this.state.formData.avgLoanRate12m} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近半年新增平台全部时间全部产品平均借贷确定逾期平台数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.overdueOrgCnt6m} type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近20次全部时间全部产品最大借贷疑似逾期天数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.ddOverdueDays20time}
                                   type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近3次工作日全部产品平均借贷疑似逾期天数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.ddWorkDayOverdueDays3time}
                                   type="text"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近一年新增平台全部时间全部产品平均还款疑似逾期天数:">
                            <Input disabled={!props.canEdit} value={this.state.formData.ddOverdueDays12m} type="text"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="最近3个月全部时间全部产品最大还款疑似逾期天数	:">
                            <Input disabled={!props.canEdit} value={this.state.formData.ddMaxOverdueDays3m}
                                   type="text"/>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        );
    }
});
Lookdetails = createForm()(Lookdetails);
export default Lookdetails;