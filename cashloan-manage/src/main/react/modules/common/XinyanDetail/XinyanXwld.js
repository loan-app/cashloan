import React from 'react';
import {
    Table,
    Card,
    Row,
    Col,
} from 'antd';
import './XinyanXwld.css';

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
        console.log(this.props);
        this.setState({
            loading: true
        });
        Utils.getData({
            url: '/modules/manage/xinyan/xwld/detail.htm',
            data: {
                borrowId: this.props.borrowId,
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
            loans_score: '贷款行为分',
            loans_credibility: '贷款行为置信度',
            loans_count: '贷款放款总订单数',
            loans_settle_count: '贷款已结清订单数',
            loans_overdue_count: '贷款逾期订单数（30天内）',
            loans_org_count: '贷款机构数',
            consfin_org_count: '消费金融类机构数',
            loans_cash_count: '网络贷款类机构数',
            latest_one_month: '近1个月贷款笔数',
            latest_three_month: '近3个月贷款笔数',
            latest_six_month: '近6个月贷款笔数',
            history_suc_fee: '历史贷款机构成功扣款笔数',
            history_fail_fee: '历史贷款机构失败扣款笔数',
            latest_one_month_suc: '近1个月贷款机构成功扣款笔数',
            latest_one_month_fail: '近1个月贷款机构失败扣款笔数',
            loans_long_time: '信用贷款时长',
            loans_latest_time: '最近一次贷款时间',
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