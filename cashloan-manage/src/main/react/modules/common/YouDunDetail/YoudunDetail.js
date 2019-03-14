import React from 'react';
import {
    Table,
    Card,
    Row,
    Col,
    Button,
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
        const devicesData = this.state.data.devices_list;
        const userFeatures = this.state.data.user_features;
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

                                        {[item[0].name] == '用户特征列表'  ? (<td colSpan={3}>
                                            <div>{ (userFeatures || []).map((item, index)=> {
                                                return (
                                                    <Button type='danger' style={{backgroundColor:'#DE1514' ,color:'#FFFFFF'}} key={index}>
                                                        <div>{item}</div>
                                                    </Button>
                                                )
                                            })}
                                            </div>
                                        </td>) : ([
                                            <td key="td1" style={{width: '20%'}} > {data[item[0].key]}</td>,
                                            <td key="td2" style={{textAlign: 'right'}} > {item[1] && item[1].name}</td>,
                                            <td key="td3" style={{width: '20%'}}>{item[1] && data[item[1].key]}</td>
                                        ])}

                                </tr>
                            ))
                        }
                        <tr key='deviceData'  >
                            <td style={{textAlign: 'right'}}>设备信息</td>
                            <td style={{padding: '0px'}} colSpan={3}>
                                {(devicesData || []).map((item, index)=> {
                                    return (
                                        <div key={index} >
                                            <div>设备名称:&emsp;{item.device_name}</div>
                                            <span>可疑设备:&emsp;{ item.fraud_device == '1' ? '是' : ' ---' }&emsp;&emsp;</span>
                                            <span>&emsp;&emsp;设备越狱(root):&emsp;{item.device_detail.is_rooted == '0' ? '是' : '否'}&emsp;&emsp;</span>
                                            <span >&emsp;&emsp;设备使用代理:&emsp;{item.is_using_proxy_port == 1 ? item.is_using_proxy_port : ' ---'}</span>
                                            <div></div>
                                            <span>同设备使用用户总数: {item.device_link_id_count ? item.device_link_id_count : " ---" }个&emsp;&emsp;</span>
                                            <span>&emsp;&emsp;借贷APP安装数是:&emsp;{item.app_instalment_count ? item.app_instalment_count :" ---" }</span>
                                            {index+1 == devicesData.length ? '' : <hr style={{backgroundColor:'#dedcde', height:'1px', border:'none'}}/>}
                                        </div>

                                    )
                                })}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            );

    }
});