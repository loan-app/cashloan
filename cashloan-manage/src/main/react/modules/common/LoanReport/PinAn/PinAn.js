import React from 'react';
import {
    Table,
    Card,
    Row,
    Col,
} from 'antd';
import './PinAn.css';

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
            url: '/modules/manage/pingan/grayscale/report.htm',
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

        const names= {
            l1wwdcn_TNumsCon: '近一周与几个催收号码有过联系',
            l1wwdcn_TNumsCon_bank: '近一周与几家银行机构催收号码有过联系',
            l1wwdcn_TNumsCon_cf: '近一周与几家消费金融机构催收号码有过联系',
            l1wwdcn_TNumsCon_f: '近一周与几家委外催收机构催收号码有过联系',
            l1wwdcn_TNumsCon_if: '近一周与几家互联网金融机构催收号码有过联系',
            l1wwdcn_TNumsCon_org: '近一周涉及催收号码的总机构数',
            l1wwdcn_TTimesIn: '近一周被催收号码呼叫次数',
            l1wwdcn_TTimesOut: '近一周主叫催收号码次数',
            l2wwdcn_TNumsCon_orgtype: '近两周联系机构类型总数',
            l3wwdcn_TNumsCon_if: '近三周联系互联网金融机构的总个数',
            l1mwdcn_TNumsCon: '近一月与几个催收号码有过联系',
            l1mwdcn_TNumsCon_bank: '近一月与几家银行机构催收号码有过联系',
            l1mwdcn_TNumsCon_cf: '近一月与几家消费金融机构催收号码有过联系',
            l1mwdcn_TNumsCon_f: '近一月与几家委外催收机构催收号码有过联系',
            l1mwdcn_TNumsCon_if: '近一月与几家互联网金融机构催收号码有过联系',
            l1mwdcn_TNumsCon_org: '近一月涉及催收号码的总机构数',
            l1mwdcn_TTimesIn: '近一月被催收号码呼叫次数',
            l1mwdcn_TTimesOut: '近一月主叫催收号码次数',
            l2mwdcn_TTimesIn: '近两月被催收号码呼叫次数',
            l2mwdcn_TNumsIn: '近两月申请人收到催收号的总个数',
            l2mwdcn_MaxTimesIn: '近两月被单个催收号码呼叫的最大次数',
            l2mwdcn_MaxTimesCon: '近两月申请人联系次数最大的催收号的总时长',
            l2mencn_TDaysCon: '近两月晚上联系催收号的总天数',
            l3mwdcn_TNumsCon: '近三月与几个催收号码有过联系',
            l3mwdcn_TNumsCon_bank: '近三月与几家银行机构催收号码有过联系',
            l3mwdcn_TNumsCon_cf: '近三月与几家消费金融机构催收号码有过联系',
            l3mwdcn_TNumsCon_f: '近三月与几家委外催收机构催收号码有过联系',
            l3mwdcn_TNumsCon_if: '近三月与几家互联网金融机构催收号码有过联系',
            l3mencn_TNumsCon_org: '近三月晚上涉及催收号码的总机构数',
            l3mwdcn_TNumsCon_org: '近三月涉及催收号码的总机构数',
            l3mwdcn_TTimesIn: '近三月被催收号码呼叫次数',
            l3mwdcn_TTimesOut: '近三月主叫催收号码次数',
            l3mwdcn_AddTNumsIn_org: '近三月新增机构数',
            l3mwdcn_MaxDaysIn: '近三月被每个催收号呼叫的天数的最大值',
            l4mwdcn_TNumsCon_orgtype: '近四月申请人联系机构类型的总个数',
            l4mwdcn_TTimesIn_nonbank: '近四月非银机构呼入的总次数',
            l4mwdcn_TDurCon: '近四月联系催收号的总时长',
            l4mwdcn_TTimesCon: '近四月联系催收号的总次数',
            l4mwdcn_TNumsCon_f: '近四月与几家委外催收机构催收号码有过联系',
            l4mwdcn_TDurIn: '近四月被催收号呼叫的总时长',
            l4mwdcn_TDurIn_MaxTimes: '近四月被叫次数最大的催收号的总时长',
            l4mwdcn_TNumsIn_f: '近四月被几家委外催收机构呼叫过',
            l5mwdcn_TDaysCon: '近五月联系催收号的总天数',
            l5mwdcn_TDurIn_f: '近五月被委外催收号码呼叫的总时长',
            l5mwdcm_TDurCon: '近五月联系催收手机总时长',
            l5mwdcn_TNumsCon_if: '近五月与几家互联网金融机构催收号码有过联系',
            allwdcn_TNumsCon: '详单周期内与几个催收号码有过联系',
            allwdcn_TNumsCon_bank: '详单周期内与几家银行机构催收号码有过联系',
            allwdcn_TNumsCon_cf: '详单周期内与几家消费金融机构催收号码有过联系',
            allwdcn_TNumsCon_f: '详单周期内与几家委外催收机构催收号码有过联系',
            allwdcn_TNumsCon_if: '详单周期内与几家互联网金融机构催收号码有过联系',
            allwdcn_TNumsCon_org: '详单周期内涉及催收号码的总机构数',
            allwdcn_TTimesIn: '详单周期内被催收号码呼叫次数',
            allwdcn_TTimesOut: '详单周期内主叫催收号码次数',
            allwdcn_TDurIn_f: '详单周期内被委外催收号码呼叫的总时长',
            allwdcm_MaxOrgtypeIn: '详单周期内被叫次数最大的催收手机号的机构类型',
            alldtcnwk_Adur: '全部详单周期工作日(周一~周五)白天(8~18 点)_平 均时长',
            allwdcn_Adur: '全部详单周期全天(0~23 点)_平均时长',
            l1mdtcn_Adur: '近一月白天(8~18 点)_平均时长',
            l1mwdcn_Adur: '近一月全天(0~23 点)_平均时长',
            allmncnrt_numsCon: '全部详单周期周末(周六周日)午夜(0~7 点)_详单中 的联系人总个数',
            l1wwdcnrt_numsCon: '近一周周末(周六周日)全天(0~23 点)_详单中的联系 人总个数',
            l2mwdcn_numsCon: '近两月全天(0~23 点)_详单中的联系人总个数',
            l2wwdcnwk_numsCon: '近两周工作日(周一~周五)全天(0~23 点)_详单中的 联系人总个数',
            allwdcn_ADurOneNums: '全部详单周期全天(0~23 点)_通话一次催收号码的 平均时长',
            l4mwdcn_ADurOneNums: '近四月全天(0~23 点)_通话一次催收号码的平均时 长',
            l6mwdcn_ADurOneNums: '近六月全天(0~23 点)_通话一次催收号码的平均时 长',
            l1mdtcnwk_RankCount: '近一月工作日(周一~周五)白天(8~18 点)_最高排名 比例',
            l1wdtcnrt_RankCount: '近一周周末(周六周日)白天(8~18 点)_最高排名比例',
            l2mdtcn_RankCount: '近两月白天(8~18 点)_最高排名比例',
            l3wwdcnwk_RankCount: '近三周工作日(周一~周五)全天(0~23 点)_最高排名 比例',
            l1mevcnrt_SepcialhourCount: '近一月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量',
            l1mmncnwk_SepcialhourCount: '近一月工作日(周一~周五)午夜(0~7 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量',
            l1wevcn_SepcialhourCount: '近一周夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量',
            l1wwdcnrt_SepcialhourCount: '近一周周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量',
            l2mevcn_SepcialhourCount: '近两月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量',
            l2mevcnrt_SepcialhourCount: '近两月周末(周六周日)夜晚(19~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量',
            l2mwdcn_SepcialhourCount: '近两月全天(0~23 点)_将一小时打 6 个及以上电话称 为特殊小时，统计这种小时的数量',
            l3mevcn_SepcialhourCount: '近三月夜晚(19~23 点)_将一小时打 6 个及以上电话 称为特殊小时，统计这种小时的数量',
            l3mwdcnrt_SepcialhourCount: '近三月周末(周六周日)全天(0~23 点)_将一小时打 6 个及以上电话称为特殊小时，统计这种小时的数量',
            l6mwdcnwk_SepcialhourCount: '近六月工作日(周一~周五)全天(0~23 点)_将一小时 打 6 个及以上电话称为特殊小时，统计这种小时的数 量',
            l2mdtcn_ActivedaysCount: '近两月白天(8~18 点)_活跃的天数',
            l2wwdcn_ActivedaysCount: '近两周全天(0~23 点)_活跃的天数',
            l3mwdcnrt_ActivedaysCount: '近三月周末(周六周日)全天(0~23 点)_活跃的天数',
            l2wdtcn_MaxTimesIn: '近两周白天(8~18 点)_被单个催收号码呼叫的最大 次数',
            l2wevcn_MaxdaysofOnenumberCount: '近两周夜晚(19~23 点)_申请人联系一个号码的天数 的最大值',
            l3mwdcnrt_MaxdaysofOnenumberCount: '近三月周末(周六周日)全天(0~23 点)_申请人联系一 个号码的天数的最大值',
            l3wwdcn_MaxdaysofOnenumberCount: '近三周全天(0~23 点)_申请人联系一个号码的天数 的最大值',
            l2wwdcn_ADurOneNumsIn: '近两周全天(0~23 点)_被催收号码呼叫一次催收号 码的平均时长',
            l2wwdcn_TTimesIn: '近两周全天(0~23 点)_被催收号码呼叫次数',
            l3mwdcn_AdurIn: '近三月全天(0~23 点)_被催收号码呼叫的平均时长',
            l1mevcnrt_TDur: '近一月周末(周六周日)夜晚(19~23 点)_与催收号码 通话总时长',
            l1mmncnwk_TDur: '近一月工作日(周一~周五)午夜(0~7 点)_与催收号 码通话总时长',
            l2mevcn_TDur: '近两月夜晚(19~23 点)_与催收号码通话总时长',
            l2mwdcnrt_TDur: '近两月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长',
            l3mevcn_TDur: '近三月夜晚(19~23 点)_与催收号码通话总时长',
            l3mwdcnrt_TDur: '近三月周末(周六周日)全天(0~23 点)_与催收号码通 话总时长',
            l6mwdcnwk_TDur: '近六月工作日(周一~周五)全天(0~23 点)_与催收号 码通话总时长',
            l4mwdcn_TDur: '近四月全天(0~23 点)_与催收号码通话总时长',
            l6mwdcn_MinItvDaysIn: '近六月全天(0~23 点)_被催收号呼叫最小间隔天数',
            l6mwdcn_TDur_f: '近六月全天(0~23 点)_与委外机构催收号呼叫的总 时长',
            all25acfri_Dur: '周五的凌晨 2 点至 5 点通话时长',
            allwdacwk_Dur: '周一至周五的通话时长',
            all25ac_Times: '凌晨 2 点凌晨 5 点之间的通话次数',
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
                                <td style={{width: '20%', color: data[item[0].key] >= 10 ? '#ff3c3c' : ''}}>{data[item[0].key]}</td>
                                <td style={{textAlign: 'right'}}>{item[1] && item[1].name}</td>
                                <td style={{width: '20%', color: (item[1] && data[item[1].key]) >= 10 ? '#ff3c3c' : ''}}>{item[1] && data[item[1].key]}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        );
    }
});