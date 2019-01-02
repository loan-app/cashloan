import React from 'react';
import {
    Table,
    Card,
    Row,
    Col,
} from 'antd';
import './Paipaixin.css';

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
            url: '/modules/manage/blacklist/ppx/detail.htm',
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
            isBlack: '是否是不良用户',
            isAlert: '是否是关注用户',
            hk001: '逾期最早出现时间',
            hk002: '逾期最近出现时间',
            hk003: '逾期累计出现次数',
            hk004: '当前总逾期金额',
            hk005: '当前最大逾期时长',
            hk006: '历史最大逾期金额',
            hk007: '历史最大逾期时长',
            qz001: '欺诈最早出现时间',
            qz002: '欺诈最近出现时间',
            qz003: '欺诈累计出现次数',
            fm001: '负面最早出现时间',
            fm002: '负面最近出现时间',
            fm003: '负面累计出现次数',
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
                                <td style={{width: '20%', color: data[item[0].key] >= 2 || data[item[0].key] == '是' ? '#ff3c3c' : ''}}>{data[item[0].key]}</td>
                                <td style={{textAlign: 'right'}}>{item[1] && item[1].name}</td>
                                <td style={{width: '20%', color: (item[1] && data[item[1].key]) >= 2 || (item[1] && data[item[1].key]) == '是' ? '#ff3c3c' : ''}}>{item[1] && data[item[1].key]}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        );
    }
});