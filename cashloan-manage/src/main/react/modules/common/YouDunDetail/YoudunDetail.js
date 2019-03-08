import React from 'react';
import {
    Table,
    Card,
    Row,
    Col,
} from 'antd';
import './YoudunDetail.css';

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
            url: '/modules/manage/youdun/risk/report/list.htm',
            data: {
                borrowId: this.props.borrowId,
                //borrowId: 7,
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
            apply_actual_loan_platform_count_1m: '近1月申请/实际借款平台数',
            repayment_platform_count_1m: '近1月还款平台数',
            apply_actual_loan_platform_count_3m: '近3月申请/实际借款平台数',
            repayment_platform_count_3m: '近3月还款平台数',
            apply_actual_loan_platform_count_6m: '近6月申请/实际借款平台数',
            repayment_platform_count_6m: '近6月还款平台数',
            apply_actual_loan_platform_count: '申请/实际借款平台数',
            repayment_platform_count: '还款平台数',
            repayment_times_count: '还款笔数',
            score: '欺诈得分',
            risk_evaluation: '欺诈等级',
            last_modified_time:'最后更新时间',
            user_features: '用户特征列表',
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
                            <td style={{width: '20%', color: [item[0].name] == '用户特征列表' ? '#ff3c3c' : ''}}>{data[item[0].key]+' '}</td>
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