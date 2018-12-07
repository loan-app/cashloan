const tabHtml1 = `
<Row :gutter="16">
    <Col span="12" style="margin-bottom: 20px;">
        <Card :bordered="false">
            <p slot="title">多头信息 - 申请多头</p>
            <Row style="text-align: center">
                <Col span="6" offset="1">
                    <p>注册机构数量</p>
                     <strong>{{ registerInfo.org_count }}</strong>
                </Col>
                <Col span="6" offset="1">
                    <p>第三方渠道注册机构数量</p>
                     <strong>{{ registerInfo.other_count }}</strong>
                </Col>
                <Col span="6" offset="1">
                    <p>机构查询次数</p>
                     <strong>{{ data.queried_detail_org_count }}</strong>
                </Col>
            </Row>
            <h4>机构查询历史</h4>
            <Table size="small" :columns="columns1" :data="queriedInfos"></Table>
        </Card>
    </Col>
    <Col span="12" style="margin-bottom: 20px;">
        <Card :bordered="false">
            <p slot="title">多头信息 - 机构类型</p>
            <RadioGroup @on-change="changeType" v-model="type" type="button" slot="extra">
                <Radio v-for="{org_type} in radios" :label="org_type" :key="org_type">{{ org_type }}</Radio>
            </RadioGroup>
            <h6>多平台借贷分析-近X天贷款申请次数</h6>
            <div id="ecahrtsBar1" style="height: 300px"></div>
        </Card>
    </Col>

    <Col span="12" style="margin-bottom: 20px;">
        <Card :bordered="false">
            <p slot="title">多头信息 - 机构数</p>

            <h6>借贷多头-近X天贷款的机构数</h6>
            <div id="ecahrtsBar2" style="height: 300px"></div>
        </Card>
    </Col>

    <Col span="12" style="margin-bottom: 20px;">
        <Card :bordered="false">
            <p slot="title">多头信息 - 借贷次数</p>

            <h6>借贷多头-近X天贷款的次数</h6>
            <div id="ecahrtsBar3" style="height: 300px"></div>
        </Card>
    </Col>
</Row>
`;

let tab1 = {
    template: tabHtml1,
    mounted() {
        // 表1
        const ecahrtsBaroption1 = {
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['15天','1个月', '3个月', '6个月'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'近15天贷款申请',
                    type:'bar',
                    barWidth: '60%',
                    data: []
                }
            ]
        };
        this.ecahrtsBar1 = echarts.init(document.getElementById('ecahrtsBar1'));
        this.ecahrtsBar1.setOption(ecahrtsBaroption1);

        // 表2
        const ecahrtsBaroption2 = {
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['15天','1个月', '3个月', '6个月'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'贷款的机构数',
                    type:'bar',
                    barWidth: '60%',
                    data:[]
                }
            ]
        };
        this.ecahrtsBar2 = echarts.init(document.getElementById('ecahrtsBar2'));
        this.ecahrtsBar2.setOption(ecahrtsBaroption2);

        // 表3
        const ecahrtsBaroption3 = {
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['15天','1个月', '3个月', '6个月'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'借贷次数',
                    type:'bar',
                    barWidth: '60%',
                    data:[]
                }
            ]
        };
        this.ecahrtsBar3 = echarts.init(document.getElementById('ecahrtsBar3'));
        this.ecahrtsBar3.setOption(ecahrtsBaroption3);
    },
    props: {
        data: Object
    },
    data() {
        return {
            ecahrtsBar1: '',
            ecahrtsBar2: '',
            ecahrtsBar3: '',
            // 表格1
            columns1: [
                {
                    title: '查询日期',
                    key: 'date'
                },
                {
                    title: '查询机构类型',
                    key: 'org_type'
                },
                {
                    title: '是否为本机构',
                    key: 'is_self'
                }
            ],
            type: ''
        }
    },
    methods: {
        changeType(val) {
            this.ecahrtsBar1.setOption({
                series : [
                    {
                        data: this.analyze(this.type)
                    }
                ]
            });
        },
        analyze(checkType) {
            const analyzeNum = [];
            if(this.data.queried_analyze) {
                const analyze = this.data.queried_analyze.filter(e => e.org_type === checkType)[0];
                if(analyze) {
                    analyzeNum[0]= analyze.loan_cnt_15d;
                    analyzeNum[1]= analyze.loan_cnt_30d;
                    analyzeNum[2]= analyze.loan_cnt_90d;
                    analyzeNum[3]= analyze.loan_cnt_180d;
                }
            }
            return analyzeNum;
        }
    },
    computed: {
        radios() {
            return this.data.queried_analyze || [];
        },
        registerInfo() {
            return this.data.register_info || {};
        },
        queriedInfos() {
            return this.data.queried_infos || [];
        },
        num() {
            const num = [];
            if(this.data.loan_info) {
                num[0]= this.data.loan_info.loan_org_cnt_15d;
                num[1]= this.data.loan_info.loan_org_cnt_30d;
                num[2]= this.data.loan_info.loan_org_cnt_90d;
                num[3]= this.data.loan_info.loan_org_cnt_180d;
            }
            return num;
        },
        count() {
            const count = [];
            if(this.data.loan_info) {
                count[0]= this.data.loan_info.loan_cnt_15d;
                count[1]= this.data.loan_info.loan_cnt_30d;
                count[2]= this.data.loan_info.loan_cnt_90d;
                count[3]= this.data.loan_info.loan_cnt_180d;
            }
            return count;
        },
    },
    watch: {
        data(val) {
            this.type = (this.radios[0] || {}).org_type;
            this.ecahrtsBar1.setOption({
                series : [
                    {
                        data: this.analyze(this.type)
                    }
                ]
            });

            this.ecahrtsBar2.setOption({
                series : [
                    {
                        data: this.num
                    }
                ]
            });

            this.ecahrtsBar3.setOption({
                series : [
                    {
                        data: this.count
                    }
                ]
            });
        }
    }
    // ...
};