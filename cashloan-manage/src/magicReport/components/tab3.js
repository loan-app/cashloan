let tabHtml3 = `
<Row>
    <Card :bordered="false">
        <p slot="title">QQ群风险</p>
        
        <div id="echartRadar" style="height: 480px"></div>
    </Card>
</Row>    
`;



let tab3 = {
    template: tabHtml3,
    props: {
        data: Array
    },
    mounted() {
        const option = {
            tooltip: {},
            legend: {
                data: ['命中借贷群数量', '分期群数量', '理财群数量', '薅羊毛群数量', '命中赌博彩票群数量']
            },
            radar: {
                // shape: 'circle',
                name: {
                    textStyle: {
                        color: '#fff',
                        backgroundColor: '#999',
                        borderRadius: 3,
                        padding: [3, 5]
                    }
                },
                indicator: [
                    { name: '命中借贷群数量', max: 5},
                    { name: '分期群数量', max: 5},
                    { name: '理财群数量', max: 5},
                    { name: '薅羊毛群数量', max: 5},
                    { name: '命中赌博彩票群数量', max: 5},
                ]
            },
            series: [{
                type: 'radar',
                data : [
                    {
                        value : [],
                        label: {
                            normal: {
                                show: true,
                                formatter:function(params) {
                                    return params.value;
                                }
                            }
                        }
                    },
                ]
            }]
        };
        this.echartRadar = echarts.init(document.getElementById('echartRadar'));
        this.echartRadar.setOption(option);
    },
    data() {
        return {

        }
    },
    methods: {

    },
    computed: {
        num() {
            const num = [];
            if(this.data) {
                num[0]= this.data.loan_groupcnt;
                num[1]= this.data.installment_groupcnt;
                num[2]= this.data.financial_management_groupcnt;
                num[3]= this.data.woolen_groupcnt;
                num[4]= this.data.gambling_groupcnt;
            }
            return num;
        },
    },
    watch: {
        data(val) {
            this.echartRadar.setOption({
                series: [{
                    type: 'radar',
                    data : [
                        {
                            value: this.num,
                        }
                    ]
                }]
            });
        }
    }
    // ...
};