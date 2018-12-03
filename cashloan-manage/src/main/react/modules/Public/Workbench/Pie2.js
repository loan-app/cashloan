import React from 'react';
var echarts = require('echarts');
require("echarts/theme/macarons.js");
export default React.createClass({
    getInitialState() {
        return {
            menuData: [],
            assetsData: {},
            investmentData: {},
            loading: false,
            item:[],
            value:[],
            first: true
        }
    },
    fetch() {
        var me = this;
        this.setState({
            loading: true
        });
        Utils.ajaxData({
            url: '/modules/manage/workbench/repayWayInfo.htm',
            method: "get",
            callback: (result) => {
				me.setState({
                    	loading: false,
                        value1:result.data.repaySource[0]['自动代扣'],
                        value2:result.data.repaySource[1]['银行卡转账'],
                        value3:result.data.repaySource[2]['支付宝转账'],
                        value4:result.data.repaySource[3]['认证支付'],
                });
				me.drawPie();
            }
        });
    },
    drawPie() {
        var me = this
        var pie = echarts.init(document.getElementById('pie2'),'macarons');
        var option = {
            title: {
                text: '还款方式',
                x: 400,
				y:20,
                textStyle: {
                    color: '#666',
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c}笔 ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 400,
				top:'30%', 
                data: ['自动代扣','银行卡转账','支付宝转账','认证支付']
            },
            series: [{
                name: '还款方式',
                type: 'pie',
                radius: ['35%', '65%'],
                itemStyle: {
                    normal: {
                        label: {
                            position: 'inner',
                            formatter: function (params) {
                                return (params.percent - 0).toFixed(0) + '%'
                            }
                        },
                        labelLine: {
                            show: false
                        }
                    },
                },
                data: [{
                    value: me.state.value1,
                    name: '自动代扣'
                }, {
                    value: me.state.value2,
                    name: '银行卡转账'
                }, {
                    value: me.state.value3,
                    name: '支付宝转账'
                },{
                    value:me.state.value4,
                    name:'认证支付'
                }]
            }]
        };
        pie.setOption(option);
    },

    componentDidMount() {
        this.fetch();
    },
    
    render() {
        return <div id="pie2" style={{ height: '280px', width: '500px', margin: '0 auto'  }}></div>
    }
});