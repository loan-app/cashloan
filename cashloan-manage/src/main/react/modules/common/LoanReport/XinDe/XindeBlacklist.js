import React from 'react';
import {
    Table,
    Card,
    Row,
    Col,
} from 'antd';
import './XinDe.css';

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
        Utils.ajaxData({
            url: '/modules/manage/xinde/blacklist/detail.htm',
            data: {
                borrowId: this.props.borrowId
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

        const names= {
            firstLoanTime: '最早借款时间',
            totalLoanCount: '历史借款次数',
            totalOverdueCount: '历史逾期次数',
            longestOverduePaid: '已经还清的历史逾期最长时间',
            currentOverdueCount: '当前处于逾期状态的借款笔数',
            currentOverdueAmountStr: '当前逾期总金额',
            longestOverdueUnpaid: '当前最长逾期时间',
            isLastLoanRefusedStr: '最后一次申请是否被拒贷',
            lastLoanRefuseReason: '最后一次拒贷原因',
            lastLoanRefuseTime: '最后一次拒贷时间',
            overDue90ContactsCount: '有逾期90天以上运营商联系人个数',
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
                                <td style={{width: '20%', color: data[item[0].key] >= 2 ? '#ff3c3c' : ''}}>{data[item[0].key]}</td>
                                <td style={{textAlign: 'right'}}>{item[1] && item[1].name}</td>
                                <td style={{width: '20%', color: (item[1] && data[item[1].key]) >= 30 ? '#ff3c3c' : ''}}>{item[1] && data[item[1].key]}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        );
    }
});