const page = `
<div>
    <myHeader :data="headerData" style="margin-bottom: 20px"></myHeader>

    <Tabs>
        <TabPane label="多头信息">
            <tab1 :data="tabData1"></tab1>
        </TabPane>

        <TabPane label="失信情况">
            <tab2 :data="tabData2"></tab2>
        </TabPane>

        <TabPane label="QQ群风险">
            <tab3 :data="tabData3"></tab3>
        </TabPane>

        <TabPane label="变更信息">
            <tab4 :data="tabData4"></tab4>
        </TabPane>

        <TabPane label="黑名单信息">
            <tab5 :data="tabData5"></tab5>
        </TabPane>
    </Tabs>
</div>
`;

Vue.component('my-page', {
    created() {
        // ajax
        setTimeout(() => {
            this.getData();
        },3000)
    },
    template: page,
    components: {
        myHeader,
        tab1,
        tab2,
        tab3,
        tab4,
        tab5,
    },
    data() {
        return {
            headerData: {},
            tabData1: {},
            tabData2: {},
            tabData3: {},
            tabData4: {},
            tabData5: {},
        }
    },
    methods: {
        getData() {
            const searchParam = "/modules/manage/credit/loan/report/detail.htm" + window.location.search;
            console.log(searchParam);
            // 请求数据
                axios.get(searchParam).then(res => {
                    if (res && res.data.code === 200) {
                        this.headerData = res.data.data.blackGray.person_info;
                        this.tabData1 = res.data.data.multiInfo.auth_queried_detail;
                        this.tabData2 = res.data.data.antiFraud.untrusted_info;
                        this.tabData3 = res.data.data.antiFraud.risk_qqgroup;
                        this.tabData4 = res.data.data.antiFraud;
                        this.tabData5 = res.data.data.blackGray;
                    }
                });

        }
    }
});