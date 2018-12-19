let tabHtml4 = `
<Row>
     <Card :bordered="false" style="margin-bottom: 20px">
        <div slot="title">
            <h3 style="margin-bottom: 20px">变更信息</h3>
            <RadioGroup v-model="type" type="button">
                <Radio label="idCard">身份证存疑</Radio>
                <Radio label="mobile">手机存疑</Radio>
                <Radio label="device">设备存疑</Radio>
            </RadioGroup>
        </div>
        
        <Row v-if="type == 'device'" style="padding: 20px; margin-bottom: 20px; background: #f9fcff">
            <Col span="8">
                <div>使用过的设备数</div>
                <H4>{{ device.other_devices_cnt }}</H4>
            </Col>
            <Col span="8">
                <div>手机号码使用过的设备数</div>
                <H4>{{ device.mobile_other_devices_cnt }}</H4>
            </Col>
            <Col span="8">
                <div>身份证号码使用过的设备数</div>
                <H4>{{ device.idcard_other_devices_cnt }}</H4>
            </Col>
        </Row>
        
        <Row>
            <Col span="6">
                <div v-if="type == 'idCard'">
                    <Card @click.native="card = 1" style="margin-bottom: 20px; cursor: pointer">
                        <div>身份证存疑姓名数</div> <h3>{{ idCard.other_names_cnt }}</h3>
                    </Card>
                    
                    <Card @click.native="card = 2" style="margin-bottom: 20px; cursor: pointer">
                        <div>身份证存疑手机号码数</div> <h3>{{ idCard.other_mobiles_cnt }}</h3>
                        <div style="text-align: right">触黑手机号码数：{{ idCard.other_mobiles_black_cnt }}</div>
                    </Card>
                    
                    <Card @click.native="card = 3" style="margin-bottom: 20px; cursor: pointer">
                        <div>提供数据的机构数</div> <h3>{{ idCard.information_sources_cnt }}</h3>
                    </Card>
                </div>
                
                <div v-if="type == 'mobile'">
                    <Card @click.native="card = 21" style="margin-bottom: 20px; cursor: pointer">
                        <div>手机存疑姓名数</div> <h3>{{ mobile.other_names_cnt }}</h3>
                    </Card>
                    
                    <Card @click.native="card = 22" style="margin-bottom: 20px; cursor: pointer">
                        <div>手机存疑身份证号码数</div> <h3>{{ mobile.other_idcards_cnt }}</h3>
                        <div style="text-align: right">触黑身份证号码数：{{ mobile.other_idcards_black_cnt }}</div>
                    </Card>
                    
                    <Card @click.native="card = 23" style="margin-bottom: 20px; cursor: pointer">
                        <div>提供数据的机构数</div> <h3>{{ mobile.information_sources_cnt }}</h3>
                    </Card>
                </div>
                
                <div v-if="type == 'device'">
                    <Card @click.native="card = 31" style="margin-bottom: 20px; cursor: pointer">
                        <div>使用过的设备上登陆的其他姓名数</div> <h3>{{ device.other_names_cnt }}</h3>
                    </Card>
                    
                    <Card @click.native="card = 32" style="margin-bottom: 20px; cursor: pointer">
                        <div>使用过的设备上登陆的其他手机号码数</div> <h3>{{ device.other_mobiles_cnt }}</h3>
                        <div style="text-align: right">触黑手机号码数：{{ device.other_mobiles_black_cnt }}</div>
                    </Card>
                    
                    <Card @click.native="card = 33" style="margin-bottom: 20px; cursor: pointer">
                        <div>使用过的设备上登陆的其他身份证号码数</div> <h3>{{ device.other_idcards_cnt }}</h3>
                        <div style="text-align: right">触黑身份证号码数：{{ device.other_idcards_black_cnt }}</div>
                    </Card>
                    
                    <Card @click.native="card = 34" style="margin-bottom: 20px; cursor: pointer">
                        <div>提供数据的机构数</div> <h3>{{ device.information_sources_cnt }}</h3>
                    </Card>
                </div>
            </Col>

            <Col span="17" offset="1">
                <div v-if="card === 1">
                    <h4 style="margin-bottom: 20px">身份证存疑姓名</h4>
                    <Table size="small" :columns="nameNum" :data="idCardName"></Table>
                </div>
                <div v-if="card === 2">
                    <h4 style="margin-bottom: 20px">身份证存疑手机号码</h4>
                    <Table size="small" :columns="idCardMobileColumn" :data="idCardMobile"></Table>
                </div>
                <div v-if="card === 3">
                    <h4 style="margin-bottom: 20px">提供数据的机构</h4>
                    <Table size="small" :columns="mechanism" :data="idCardMechanism"></Table>
                </div>
                
                <div v-if="card === 21">
                    <h4 style="margin-bottom: 20px">手机存疑姓名</h4>
                    <Table size="small" :columns="nameNum" :data="mobileName"></Table>
                </div>
                <div v-if="card === 22">
                    <h4 style="margin-bottom: 20px">手机存疑身份证号码</h4>
                    <Table size="small" :columns="mobileIdCardColumn" :data="mobileIdCard"></Table>
                </div>
                <div v-if="card === 23">
                    <h4 style="margin-bottom: 20px">提供数据的机构</h4>
                    <Table size="small" :columns="mechanism" :data="mobileMechanism"></Table>
                </div>
                
                <div v-if="card === 31">
                    <h4 style="margin-bottom: 20px">使用过的设备上登陆的其他姓名</h4>
                    <Table size="small" :columns="nameNum" :data="deviceName"></Table>
                </div>
                <div v-if="card === 32">
                    <h4 style="margin-bottom: 20px">使用过的设备上登陆的其他手机号码</h4>
                    <Table size="small" :columns="idCardMobileColumn" :data="deviceMobile"></Table>
                </div>
                <div v-if="card === 33">
                    <h4 style="margin-bottom: 20px">使用过的设备上登陆的其他身份证号码</h4>
                    <Table size="small" :columns="mobileIdCardColumn" :data="deviceIdCard"></Table>
                </div>
                <div v-if="card === 34">
                    <h4 style="margin-bottom: 20px">提供数据的机构</h4>
                    <Table size="small" :columns="mechanism" :data="deviceMechanism"></Table>
                </div>
                
            </Col>
        </Row>
    </Card>
</Row>    
`;

let tab4 = {
    template: tabHtml4,
    props: {
        data: Object
    },
    data() {
        return {
            type: 'idCard',
            card: 1,
            orgTypeMap: { ZHENGXIN: '征信机构', DATACOVERGE: '数据平台', BANK: '银行', CUSTOMER_FINANCE: '消费金融', CASH_LOAN: '现金贷', P2P: 'P2P理财', CREDITPAY: '信用支付', CONSUMSTAGE: '消费分期', COMPENSATION: '信用卡代偿', DIVERSION: '导流平台', 其它: '其它' },
            // 表格1
            nameNum: [
                {
                    title: '最后使用时间',
                    key: 'latest_used_time'
                },
                {
                    title: '姓名',
                    key: 'name'
                },
            ],
            idCardMobileColumn: [
                {
                    title: '最后使用时间',
                    key: 'latest_used_time'
                },
                {
                    title: '手机号',
                    key: 'mobile'
                },
                {
                    title: '号码归属地',
                    key: 'mobile_location'
                },
                {
                    title: '运营商',
                    key: 'carrier'
                },
                {
                    title: '是否命中黑灰名单',
                    key: 'isblack',
                    render: (h, params) => h('span', params.row.isblack ? '命中' : '未命中')
                },
            ],
            mobileIdCardColumn: [
                {
                    title: '最后使用时间',
                    key: 'latest_used_time'
                },

                {
                    title: '身份证号',
                    key: 'idcard'
                },
                {
                    title: '是否命中黑灰名单',
                    key: 'isblack',
                    render: (h, params) => h('span', params.row.isblack == true ? '命中' : '未命中')
                },
            ],
            mechanism: [
                {
                    title: '最后使用时间',
                    key: 'latest_used_time'
                },
                {
                    title: '机构类型',
                    key: 'org_type',
                    render: (h, params) => h('span', this.orgTypeMap[params.row.org_type])
                },
            ]
        }
    },
    methods: {
        checkCard(val) {
            console.log(val)
        }
    },
    computed: {
        device() {
            return this.data.suspicious_device || {};
        },
        idCard() {
            return this.data.suspicious_idcard || {};
        },
        mobile() {
            return this.data.suspicious_mobile || {};
        },
        idCardName() {
            return ((this.data.suspicious_idcard || {}).other_names) || [];
        },
        idCardMobile() {
            return this.data.suspicious_idcard.other_mobiles || [];
        },
        idCardMechanism() {
            return this.data.suspicious_idcard.information_sources || [];
        },
        mobileName() {
            return this.data.suspicious_mobile.other_names || [];
        },
        mobileIdCard() {
            return this.data.suspicious_mobile.other_idcards || [];
        },
        mobileMechanism() {
            return this.data.suspicious_mobile.information_sources || [];
        },
        deviceName() {
            return this.data.suspicious_device.other_names || [];
        },
        deviceMobile() {
            return this.data.suspicious_device.other_mobiles || [];
        },
        deviceIdCard() {
            return this.data.suspicious_device.other_idcards || [];
        },
        deviceMechanism() {
            return this.data.suspicious_device.information_sources || [];
        }
    }
    // ...
};