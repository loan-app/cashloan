let tabHtml7 = `
<Row>
    <Card :bordered="false">
        <p slot="title">运营商模块</p>
        
        <Tabs v-model="tab" @on-click="changeData">
            <TabPane label="联系人" name="contacts"></TabPane>
            
            <TabPane label="亲密联系人" name="intimateContacts"></TabPane>
        </Tabs>
        
        <h4 style="line-height: 30px">
            <template v-if="tab === 'contacts'">近{{ this.day }}天直接联系人命中黑名单的类型</template>
            <template v-else>近{{ this.day }}天亲密直接联系人命中黑名单的类型: </template>
        </h4>

        <div style="text-align: right">
            <RadioGroup v-model="day" @on-change="changeData" type="button" style="margin-top: -40px">
                <Radio :label="30">30天</Radio>
                <Radio :label="90">90天</Radio>
                <Radio :label="180">180天</Radio>
            </RadioGroup>
        </div>

        <div id="ecahrtsBar" style="height: 400px"></div>
    </Card>
</Row>
`;

let tab7 = {
    template: tabHtml7,
    props: {
        data: Object
    },
    mounted() {
        // 表1
        const ecahrtsBaroption = {
            color: ['#5aa2ff'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '8%',
                bottom: '3%',
                top: '5%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'value'
                }
            ],
            yAxis : [
                {
                    type : 'category',
                    data : [
                        '授权过的直接联系人 / 占比',
                        '命中黑名单的直接联系人 / 占比',
                        '授权过的间接联系人 / 占比',
                        '命中黑名单的间接联系人 / 占比',
                        '引起间接联系人在黑名单的直接联系人 / 占比',
                        '引起间接联系人授权的直接联系人 / 占比'
                    ],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            series : [
                {
                    name:'直接访问',
                    type:'bar',
                    barWidth: '60%',
                    data:[],
                    label: {
                        normal: {
                            position: 'right',
                            show: true,
                            formatter: params => this.rate(params.value, params.dataIndex)
                        }
                    }
                }
            ]
        };
        this.ecahrtsBar = echarts.init(document.getElementById('ecahrtsBar'));
        this.ecahrtsBar.setOption(ecahrtsBaroption);
    },
    data() {
        return {
            tab: 'contacts',
            day: 30,
            ecahrtsBar: '',
            axisNames: ['授权过的直接联系人 / 占比',
                '命中黑名单的直接联系人 / 占比',
                '授权过的间接联系人 / 占比',
                '命中黑名单的间接联系人 / 占比',
                '引起间接联系人在黑名单的直接联系人 / 占比',
                '引起间接联系人授权的直接联系人 / 占比'
            ]
        }
    },
    methods: {
        rate(value, index) {
            var ratioArray = [];
            if(this.tab + this.day === 'contacts30') {
                ratioArray = this.contacts30Ratio;
            } else if(this.tab + this.day === 'contacts90') {
                ratioArray = this.contacts90Ratio;
            } else if(this.tab + this.day === 'contacts180') {
                ratioArray = this.contacts90Ratio;
            } else if(this.tab + this.day === 'intimateContacts30') {
                ratioArray = this.intimateContacts30Ratio;
            } else if(this.tab + this.day === 'intimateContacts90') {
                ratioArray = this.intimateContacts90Ratio;
            } else if(this.tab + this.day === 'intimateContacts180') {
                ratioArray = this.intimateContacts180Ratio;
            }
            if(ratioArray[index]) {
                return `${value}人 / ${  parseInt(ratioArray[index] * 10000) / 100 }%`
            } else {
                return `${value}人 / 0%`
            }
        },
        changeData() {
            if (this.tab === 'contacts') {
                this.axisNames = ['授权过的直接联系人 / 占比',
                    '命中黑名单的直接联系人 / 占比',
                    '授权过的间接联系人 / 占比',
                    '命中黑名单的间接联系人 / 占比',
                    '引起间接联系人在黑名单的直接联系人 / 占比',
                    '引起间接联系人授权的直接联系人 / 占比'];
            } else {
                this.axisNames = ['授权过的亲密直接联系人 / 占比',
                    '命中黑名单的亲密直接联系人 / 占比',
                    '授权过的亲密间接联系人 / 占比',
                    '命中黑名单的亲密间接联系人 / 占比',
                    '引起亲密间接联系人在黑名单的亲密直接联系人 / 占比',
                    '引起亲密间接联系人授权的亲密直接联系人 / 占比'];
            };
            this.ecahrtsBar.setOption({
                series : [
                    {
                        data: this[this.tab + this.day]
                    }
                ],
                yAxis : [
                    {
                        data: this.axisNames
                    }
                ]
            });
        }
    },
    computed: {
        // 如果是一个数组，可以自己写个方法过滤，传2个参数
        contacts30() {
            return this.data.contacts30 || [];
        },
        contacts90() {
            return this.data.contacts90 || [];
        },
        contacts180() {
            return this.data.contacts180 || [];
        },
        contacts30Ratio() {
            return this.data.contacts30Ratio || [];
        },
        contacts90Ratio() {
            return this.data.contacts90Ratio || [];
        },
        contacts180Ratio() {
            return this.data.contacts180Ratio || [];
        },
        intimateContacts30() {
            return this.data.intimateContacts30 || [];
        },
        intimateContacts90() {
            return this.data.intimateContacts90 || [];
        },
        intimateContacts180() {
            return this.data.intimateContacts180 || [];
        },
        intimateContacts30Ratio() {
            return this.data.intimateContacts30Ratio || [];
        },
        intimateContacts90Ratio() {
            return this.data.intimateContacts90Ratio || [];
        },
        intimateContacts180Ratio() {
            return this.data.intimateContacts180Ratio || [];
        },
        // 当前表格数据
        currentData() {
            return this[this.tab + this.day];
        },
        // 当前表格数据-总数
        currentTotal() {
            return this.currentData.reduce((all, i) => all += i, 0);
        }
    },
    watch: {
        data(val) {
            this.changeData();
        }
    }
    // ...
};