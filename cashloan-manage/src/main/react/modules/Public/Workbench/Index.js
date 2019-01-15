import React from 'react';
import {Col, Message, Row} from 'antd';
import './style.css';
import Map from './Map';
import Bar1 from './Bar1';
import Pie2 from './Pie2';

export default React.createClass({
    getInitialState() {
        var change = true;
        if (window.innerWidth < 1600) {
            change = false;
        }
        return {
            menuData: [],
            assetsData: {},
            investmentData: {},
            loading: false,
            data: [],
            change: change,
            formsToday: {},      //今日数据
            formsTotal: {},      //累计数据
            formsRealTime: {},  //实时数据
            Map: '',
            Pie2: '',
            Bar1: '',
            Bar2: ''
        }
    },

    fetch() {
        let paramsToday = {};
        let paramsTotal = {};
        let formsRealTime = {};
        let _me = this;
        
        this.setState({
            loading: true
        }); 
        
        this.getData({url: '/modules/manage/workbench/todayInfo.htm', value: 'formsToday'})
            .then(() => {
                this.getData({url: '/modules/manage/workbench/cumulativeInfo.htm', value: 'formsTotal'})
            })
            .then(() => {
                this.getData({url: '/modules/manage/workbench/realTimeInfo.htm', value: 'formsRealTime'})
            })
    },
    getData(obj) {
        return new Promise((resolve, reject) => {
            Utils.ajaxData({
                url: obj.url,
                type: 'json',
                callback: (result) => {
                    if (result.code == '200') {
                        resolve();
                        let newObj = {};
                        newObj[`${obj.value}`] = result.data
                        this.setState(newObj)
                    } else if (result.code == '800') {
                        reject();
                        localStorage.clear();
                        location.reload();
                    }else {
                        reject();
                        Message.warning(result.message)
                    }
                }
            })
        })
        
    },
    onWindowResize(){
        if(document.getElementById('box').offsetWidth < 1376){
            this.setState({
                change: false
            })
        }else{
            this.setState({
                change: true
            })
        }
        this.forceUpdate();
    },
    componentDidMount() {
        this.fetch();
        window.addEventListener('resize', this.onWindowResize);
        this.getMap().then(() => {
            this.getPie2()
        }).then(() => {
            this.getBar1()
        })   
    },

    componentWillUnmount() {
        window.removeEventListener('resize', this.onWindowResize)
    },
    getMap() {
         return new Promise((resolve, reject) => {
             location.Map = <Map/>
             resolve();
         })
    },
    getPie2() {
         return new Promise((resolve, reject) => {
             location.Pie2 = <Pie2/>;
             resolve();
         })
    },
    getBar1() {
         return new Promise((resolve, reject) => {
             location.Bar1 = <Bar1/>;
             resolve();
         })
    },
    render() {
        
        var { data,change } = this.state;

        let formsToday = this.state.formsToday;
        let formsTotal = this.state.formsTotal;
        let formsRealTime = this.state.formsRealTime;
        return (
            <div id='box' style={{ minWidth: 820 }}>
                <div className="block-panel">
                    <h2 className="navLine-title">今日数据</h2>
                    <div className='blk-top'>
                        <div className='blk-top-item'>
                            <div className='blk-title'>当天注册数</div>
                            <div className='blk-number'>{formsToday.register}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>借款申请数</div>
                            <div className='blk-number'>{formsToday.borrow}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>通过次数</div>
                            <div className='blk-number'>{formsToday.borrowPass}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>通过率</div>
                            <div className='blk-number'>{formsToday.passApr}<span style={{ fontSize: '12px' }}>% </span></div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>放款量</div>
                            <div className='blk-number'>{formsToday.borrowLoan}</div>
                        </div>
                        <div className='blk-top-item' >
                            <div className='blk-title'>还款量</div>
                            <div className='blk-number'>{formsToday.borrowRepay}</div>
                        </div>
                        <div className='blk-top-item blk-top-item-last' >
                            <div className='blk-title'>放款率</div>
                            <div className='blk-number'>{formsToday.borrowRate}<span style={{ fontSize: '12px' }}>% </span></div>
                        </div>
                    </div>
                </div>

                <div className="block-panel">
                    <h2 className="navLine-title">逾期统计</h2>
                    <div className='blk-top'>
                        <div className='blk-top-item'>
                            <div className='blk-title'>今日应还</div>
                            <div className='blk-number'>{formsToday.todayShouldCnt}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>今日结清</div>
                            <div className='blk-number'>{formsToday.todayRepayCnt}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>今日待还</div>
                            <div className='blk-number'>{formsToday.todayNotRepayCnt}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>今日展期</div>
                            <div className='blk-number'>{formsToday.todayDeferredCnt}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>今日逾期率</div>
                            <div className='blk-number'>{formsToday.todayOverdueRate}<span style={{ fontSize: '12px' }}>% </span></div>
                        </div>
                    </div>
                </div>

                <div>
                    <div className="data-panel">
                        <div className="block-panel">
                            <h2 className="navLine-title">累计数据</h2>
                            <Row>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='workBench-icon icon1'> </span>
                                        <span className='blk-title'>历史放款总量</span>
                                        <span className='blk-number'>{formsTotal.borrowLoanHistory}</span>笔
                                    </div>
                                </Col>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='workBench-icon icon2'> </span>
                                        <span className='blk-title'>历史还款总量</span>
                                        <span className='blk-number'>{formsTotal.borrowRepayHistory}</span>笔
                                    </div>
                                </Col>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='blk-title'>历史总注册量</span>
                                        <span className='blk-number'>{formsTotal.registerHistory}</span>
                                    </div>
                                </Col>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='blk-title'>历史总申请量</span>
                                        <span className='blk-number'>{formsTotal.borrowApplyHistory}</span>
                                    </div>
                                </Col>
                            </Row>
                        </div>
                    </div>
                    <div className="data-panel">
                        <div className="block-panel">
                            <h2 className="navLine-title">实时数据</h2>
                            <Row>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='workBench-icon icon3'> </span>
                                        <span className='blk-title'>待还款总余额</span>
                                        <span className='blk-number'>{formsRealTime.needRepay}</span>元
                                    </div>
                                </Col>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='workBench-icon icon4'> </span>
                                        <span className='blk-title'>逾期未还款总额</span>
                                        <span className='blk-number'>{formsRealTime.overdueRepay}</span>元
                                    </div>
                                </Col>
                            </Row>
                        </div>
                    </div>
                </div>
                { change ? <div className="block-chart">
                    <div className='blk-top'>
                        <div className='blk-top-item'>
                            {location.Map}
                            <div className="border-top">
                                {location.Pie2}
                            </div>
                        </div>
                        <div className='blk-top-item blk-top-item-last'>  
                            {location.Bar1}
                        </div>
                    </div>
                </div> : <div className="block-chart">
                    <div className='blk-top-change'>
                            <div>
                                {location.Map}
                            </div>
                            <div>
                                {location.Pie2}
                            </div>
                            <div> 
                                {location.Bar1}
                            </div>
                    </div>
                </div>}
            </div>
        )
    }
});