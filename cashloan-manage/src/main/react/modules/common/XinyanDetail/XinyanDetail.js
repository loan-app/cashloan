import React from 'react';
import {
    Table,
    Card,
    Row,
    Col,
} from 'antd';
import './XinyanDetail.css';

export default React.createClass({
    getInitialState() {
        return {
            loading: false,
            data: {},
            pagination: {},
        };
    },
    rowKey(record) {
        return record.id;
    },
    componentDidMount() {
        this.fetch();
    },
    fetch() {
        this.setState({
            loading: true
        });
        Utils.getData({
            url: '/modules/manage/xinyan/loan/report/detail.htm',
            data: {
                userId: this.props.userId,
            },
            callback: (result) => {
                this.setState({
                    loading: false,
                    data: result.data
                });
            }
        });
    },

    handleReset() {
        console.log(this.props);
        this.props.form.resetFields();
        this.fetch();
    },

    render() {
        const props = this.props;
        const data = this.state.data;

        const names = {
            score: '分数',
            curMaxCredit: '本业务最大授信额度',
            curAvgCredit: '本业务平均授信额度',
            curLoanCnt30d: '本业务近1个月贷款笔数',
            curLoanCnt90d: '本业务近3个月贷款笔数',
            curLoanCnt180d: '本业务近6个月贷款笔数',
            curLoanTotalCnt: '本业务贷款总笔数',
            curLoanOrgTotalCnt: '本业务贷款机构数',
            curLastToEndLoan: '本业务最近一次贷款距今天数',
            curLoanClearCnt: '本业务贷款已结清笔数',
            curOverdueCnt30d: '本业务贷款逾期订单数（30天内）',
            curOverdueCntMore30d: '本业务贷款逾期订单数（大于30天）',
            queryOrgCnt: '查询多头机构数',
            queryCnt: '总查询次数',
            lastToEndDays: '最近查询时间距今天数:',
            queryCnt30d: '近1个月查询多头',
            queryCnt90d: '近3个月查询多头',
            queryCnt180d: '近6个月查询多头',
            loanClearNum: '贷款已结清笔数',
            overdueCnt30d: '贷款逾期订单数（30天内）',
            overdueCntMore30d: '贷款逾期订单数（大于30天）',
            workDayNotOverdueAmountRadio30d: '最近1个月工作日全部产品非逾期借贷在总借贷中金额占比',
            notOverdueOrderRadio180d: '最近6个月全部时间全部产品非逾期借贷在总借贷中订单数占比',
            overdueOrderRadio90d: '最近3个月全部时间消费金融类在全部产品中借贷确定逾期订单数占比',
            maxLoanRate12m: '最近一年新增平台全部时间全部产品最大借贷费率:',
            avgLoanRate12m: '最近12个月全部时间超短期现金贷平均借贷费率',
            overdueOrgCnt6m: '最近半年新增平台全部时间全部产品平均借贷确定逾期平台数:',
            ddOverdueDays20time: '最近20次全部时间全部产品最大借贷疑似逾期天数',
            ddWorkDayOverdueDays3time: '最近3次工作日全部产品平均借贷疑似逾期天数',
            ddOverdueDays12m: '最近一年新增平台全部时间全部产品平均还款疑似逾期天数:',
            ddMaxOverdueDays3m: '最近3个月全部时间全部产品最大还款疑似逾期天数',
        };

        const namesList = Object.keys(names).map(key => ({
            key,
            name: names[key]
        }));
        // 2个数据排一列，
        let newNamesList = [];
        for (let i = 0, len = namesList.length; i < len; i += 2) {
            newNamesList.push(namesList.slice(i, i + 2));
        }

        return (
            <div className="ant-table ant-table-middle ant-table-bordered">
                <table>
                    <tbody>
                    {
                        newNamesList.map((item, index) => (
                            <tr key={index} className={index % 2 ? 'table-tr-gray' : ''}>
                                <td style={{textAlign: 'right'}}>{item[0].name}</td>
                                <td style={{width: '20%'}}>{data[item[0].key]}</td>
                                <td style={{textAlign: 'right'}}>{item[1] && item[1].name}</td>
                                <td style={{width: '20%'}}>{item[1] && data[item[1].key]}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        );
    }
});