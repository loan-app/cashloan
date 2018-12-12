const page = `
<div>
    <myHeader :data="headerData" style="margin-bottom: 20px"></myHeader>

    <Tabs>
        <TabPane label="运营商模块">
            <tab7 :data="tabData7"></tab7>
        </TabPane>

        <TabPane label="多头信息">
            <tab1 :data="tabData1"></tab1>
        </TabPane>

        <TabPane label="失信情况">
            <tab2 :data="tabData2"></tab2>
        </TabPane>

        <TabPane label="QQ群风险">
            <tab3 :data="tabData3"></tab3>
        </TabPane>

        <TabPane label="设备指纹风险">
            <tab6 :data="tabData6"></tab6>
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
        tab6,
        tab7
    },
    data() {
        return {
            headerData: {
                personInfo: {},
                matchScore: 0
            },
            tabData1: {},
            tabData2: {},
            tabData3: {},
            tabData4: {},
            tabData5: {
                blackInfoDetail: {},
                grayInfoDetail: {}
            },
            tabData6: [],
            tabData7: {}
        }
    },
    methods: {
        getData() {
            const searchParam = "/modules/manage/credit/loan/report/detail.htm" + window.location.search;
            // 请求数据
            axios.get(searchParam).then(res => {
                if (res && res.data.code === 200) {
                    this.headerData.personInfo = res.data.data.apply.person_info;
                    this.headerData.matchScore = res.data.data.apply.mobile_info.match_score;
                    this.tabData1 = res.data.data.apply.auth_queried_detail;
                    this.tabData2 = res.data.data.antiFraud.untrusted_info;
                    this.tabData3 = res.data.data.antiFraud.risk_qqgroup;
                    this.tabData4 = res.data.data.antiFraud;
                    this.tabData5.blackInfoDetail = res.data.data.apply.black_info_detail;
                    this.tabData5.grayInfoDetail = res.data.data.apply.gray_info_detail;

                    //tabData7
                    this.tabData7.contacts30 = this.getContacts30(res.data.data.apply.mobile_info);
                    this.tabData7.contacts90 = this.getContacts90(res.data.data.apply.mobile_info);
                    this.tabData7.contacts180 = this.getContacts180(res.data.data.apply.mobile_info);
                    this.tabData7.intimateContacts30 = this.getIntimateContacts30(res.data.data.apply.mobile_info);
                    this.tabData7.intimateContacts90 = this.getIntimateContacts90(res.data.data.apply.mobile_info);
                    this.tabData7.intimateContacts180 = this.getIntimateContacts180(res.data.data.apply.mobile_info);

                    this.tabData7.contacts30Ratio = this.getContacts30Ratio(res.data.data.apply.mobile_info);
                    this.tabData7.contacts90Ratio = this.getContacts90Ratio(res.data.data.apply.mobile_info);
                    this.tabData7.contacts180Ratio = this.getContacts180Ratio(res.data.data.apply.mobile_info);
                    this.tabData7.intimateContacts30Ratio = this.getIntimateContacts30Ratio(res.data.data.apply.mobile_info);
                    this.tabData7.intimateContacts90Ratio = this.getIntimateContacts90Ratio(res.data.data.apply.mobile_info);
                    this.tabData7.intimateContacts180Ratio = this.getIntimateContacts180Ratio(res.data.data.apply.mobile_info);

                    this.tabData6 = this.getTabData6(res.data.data.apply.risk_device);
                    console.log(this.tabData6);
                }
            });
        },

        getContacts30(mobileInfo) {
            var contacts30 = [];
            var contactsInfo30 = mobileInfo.mobile_contact_30d;
            if(contactsInfo30) {
                contacts30[0] = contactsInfo30.auth_contactnum;
                contacts30[1] = contactsInfo30.black_contactnum;
                contacts30[2] = contactsInfo30.auth_indirectnum;
                contacts30[3] = contactsInfo30.black_indirectnum;
                contacts30[4] = contactsInfo30.black_indirect_peernum;
                contacts30[5] = contactsInfo30.auth_indirect_peernum;
            }
            return contacts30;
        },
        getContacts30Ratio(mobileInfo) {
            var contactsInfo30 = mobileInfo.mobile_contact_30d;
            var contacts30Ratio = [];
            if(contactsInfo30) {
                contacts30Ratio[0] = contactsInfo30.auth_contact_ratio;
                contacts30Ratio[1] = contactsInfo30.black_contactnum_ratio;
                contacts30Ratio[2] = contactsInfo30.auth_indirectnum_ratio;
                contacts30Ratio[3] = contactsInfo30.black_indirectnum_ratio;
                contacts30Ratio[4] = contactsInfo30.black_indirect_peernum_ratio;
                contacts30Ratio[5] = contactsInfo30.auth_indirect_peernum_ratio;
            }
            return contacts30Ratio;
        },
        getContacts90(mobileInfo) {
            var contacts90 = [];
            var contactsInfo90 = mobileInfo.mobile_contact_90d;
            if(contactsInfo90) {
                contacts90[0] = contactsInfo90.auth_contactnum;
                contacts90[1] = contactsInfo90.black_contactnum;
                contacts90[2] = contactsInfo90.auth_indirectnum;
                contacts90[3] = contactsInfo90.black_indirectnum;
                contacts90[4] = contactsInfo90.black_indirect_peernum;
                contacts90[5] = contactsInfo90.auth_indirect_peernum;
            }
            return contacts90;
        },
        getContacts90Ratio(mobileInfo) {
            var contactsInfo90 = mobileInfo.mobile_contact_90d;
            var contacts90Ratio = [];
            if(contactsInfo90) {
                contacts90Ratio[0] = contactsInfo90.auth_contact_ratio;
                contacts90Ratio[1] = contactsInfo90.black_contactnum_ratio;
                contacts90Ratio[2] = contactsInfo90.auth_indirectnum_ratio;
                contacts90Ratio[3] = contactsInfo90.black_indirectnum_ratio;
                contacts90Ratio[4] = contactsInfo90.black_indirect_peernum_ratio;
                contacts90Ratio[5] = contactsInfo90.auth_indirect_peernum_ratio;
            }
            return contacts90Ratio;
        },
        getContacts180(mobileInfo) {
            var contacts180 = [];
            var contactsInfo180 = mobileInfo.mobile_contact_180d;
            if(contactsInfo180) {
                contacts180[0] = contactsInfo180.auth_contactnum;
                contacts180[1] = contactsInfo180.black_contactnum;
                contacts180[2] = contactsInfo180.auth_indirectnum;
                contacts180[3] = contactsInfo180.black_indirectnum;
                contacts180[4] = contactsInfo180.black_indirect_peernum;
                contacts180[5] = contactsInfo180.auth_indirect_peernum;
            }
            return contacts180;
        },
        getContacts180Ratio(mobileInfo) {
            var contactsInfo180 = mobileInfo.mobile_contact_180d;
            var contacts180Ratio = [];
            if(contactsInfo180) {
                contacts180Ratio[0] = contactsInfo180.auth_contact_ratio;
                contacts180Ratio[1] = contactsInfo180.black_contactnum_ratio;
                contacts180Ratio[2] = contactsInfo180.auth_indirectnum_ratio;
                contacts180Ratio[3] = contactsInfo180.black_indirectnum_ratio;
                contacts180Ratio[4] = contactsInfo180.black_indirect_peernum_ratio;
                contacts180Ratio[5] = contactsInfo180.auth_indirect_peernum_ratio;
            }
            return contacts180Ratio;
        },
        getIntimateContacts30(mobileInfo) {
            var intimateContactInfo30 = mobileInfo.intimate_contact_info_30d;
            var intimateContacts30 = [];
            if(intimateContactInfo30) {
                intimateContacts30[0] = intimateContactInfo30.auth_intimatenum;
                intimateContacts30[1] = intimateContactInfo30.black_intimatenum;
                intimateContacts30[2] = intimateContactInfo30.auth_intimate_indirectnum;
                intimateContacts30[3] = intimateContactInfo30.black_intimate_indirectnum;
                intimateContacts30[4] = intimateContactInfo30.black_intimate_indirect_peernum;
                intimateContacts30[5] = intimateContactInfo30.auth_intimate_indirect_peernum;
            }
            return intimateContacts30;
        },
        getIntimateContacts30Ratio(mobileInfo) {
            var intimateContactInfo30 = mobileInfo.intimate_contact_info_30d;
            var intimateContacts30Ratio = [];
            if(intimateContactInfo30) {
                intimateContacts30Ratio[0] = intimateContactInfo30.auth_intimatenum_ratio;
                intimateContacts30Ratio[1] = intimateContactInfo30.black_intimatenum_ratio;
                intimateContacts30Ratio[2] = intimateContactInfo30.auth_intimate_indirectnum_ratio;
                intimateContacts30Ratio[3] = intimateContactInfo30.black_intimate_indirectnum_ratio;
                intimateContacts30Ratio[4] = intimateContactInfo30.black_intimate_indirect_peernum_ratio;
                intimateContacts30Ratio[5] = intimateContactInfo30.auth_intimate_indirect_peernum_ratio;
            }
            return intimateContacts30Ratio;
        },
        getIntimateContacts90(mobileInfo) {
            var contactsInfo90 = mobileInfo.intimate_contact_info_90d;
            var intimateContacts90 = [];
            if(contactsInfo90) {
                intimateContacts90[0] = contactsInfo90.auth_intimatenum;
                intimateContacts90[1] = contactsInfo90.black_intimatenum;
                intimateContacts90[2] = contactsInfo90.auth_intimate_indirectnum;
                intimateContacts90[3] = contactsInfo90.black_intimate_indirectnum;
                intimateContacts90[4] = contactsInfo90.black_intimate_indirect_peernum;
                intimateContacts90[5] = contactsInfo90.auth_intimate_indirect_peernum;
            }
            return intimateContacts90;
        },
        getIntimateContacts90Ratio(mobileInfo) {
            var intimateContactInfo90 = mobileInfo.intimate_contact_info_90d;
            var intimateContacts90Ratio = [];
            if(intimateContactInfo90) {
                intimateContacts90Ratio[0] = intimateContactInfo90.auth_intimatenum_ratio;
                intimateContacts90Ratio[1] = intimateContactInfo90.black_intimatenum_ratio;
                intimateContacts90Ratio[2] = intimateContactInfo90.auth_intimate_indirectnum_ratio;
                intimateContacts90Ratio[3] = intimateContactInfo90.black_intimate_indirectnum_ratio;
                intimateContacts90Ratio[4] = intimateContactInfo90.black_intimate_indirect_peernum_ratio;
                intimateContacts90Ratio[5] = intimateContactInfo90.auth_intimate_indirect_peernum_ratio;
            }
            return intimateContacts90Ratio;
        },
        getIntimateContacts180(mobileInfo) {
            var contactsInfo180 = mobileInfo.intimate_contact_info_180d;
            var intimateContacts180 = [];
            if(contactsInfo180) {
                intimateContacts180[0] = contactsInfo180.auth_intimatenum;
                intimateContacts180[1] = contactsInfo180.black_intimatenum;
                intimateContacts180[2] = contactsInfo180.auth_intimate_indirectnum;
                intimateContacts180[3] = contactsInfo180.black_intimate_indirectnum;
                intimateContacts180[4] = contactsInfo180.black_intimate_indirect_peernum;
                intimateContacts180[5] = contactsInfo180.auth_intimate_indirect_peernum;
            }
            return intimateContacts180;
        },
        getIntimateContacts180Ratio(mobileInfo) {
            var intimateContactInfo180 = mobileInfo.intimate_contact_info_180d;
            var intimateContacts180Ratio = [];
            if(intimateContactInfo180) {
                intimateContacts180Ratio[0] = intimateContactInfo180.auth_intimatenum_ratio;
                intimateContacts180Ratio[1] = intimateContactInfo180.black_intimatenum_ratio;
                intimateContacts180Ratio[2] = intimateContactInfo180.auth_intimate_indirectnum_ratio;
                intimateContacts180Ratio[3] = intimateContactInfo180.black_intimate_indirectnum_ratio;
                intimateContacts180Ratio[4] = intimateContactInfo180.black_intimate_indirect_peernum_ratio;
                intimateContacts180Ratio[5] = intimateContactInfo180.auth_intimate_indirect_peernum_ratio;
            }
            return intimateContacts180Ratio;
        },

        getTabData6(riskDevice) {
            var data6 = [];
            if(riskDevice) {
                data6[0] = {'key': '设备指纹对应的借贷APP数量', 'value': riskDevice.loan_cnt};
                data6[1] = {'key': '设备指纹对应的消费分期APP数量', 'value': riskDevice.consumption_cnt};
                data6[2] = {'key': '设备指纹对应的彩票APP数量', 'value': riskDevice.lottery_cnt};
                data6[3] = {'key': '设备指纹对应的借贷APP数量占比', 'value': riskDevice.loan_cnt_ratio};
                data6[4] = {'key': '设备指纹对应的消费分期APP数量占比', 'value': riskDevice.consumption_cnt_ratio};
                data6[5] = {'key': '设备指纹对应的彩票APP数量占比', 'value': riskDevice.lottery_cnt_ratio};
            }
            return data6;
        }
    }
});