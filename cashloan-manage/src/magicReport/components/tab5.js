let tabHtml5 = `
<Row>
    <Card :bordered="false">
        <p slot="title">黑名单信息</p>
        
        <Tabs>
            <TabPane label="黑名单信息">
                <div>
                    手机和姓名是否在黑名单
                    <Icon color="#19be6b" type="md-checkmark-circle" v-if="blackList.mobile_name_in_blacklist==true"/>
                    <Icon color="#ed3f14" type="md-close-circle" v-else/>
                </div>
                
                <div>
                    手机和姓名黑名单更新时间 {{ blackList.mobile_name_blacklist_updated_time }}
                </div>
                
                <div>
                    身份证和姓名是否在黑名单
                    <Icon color="#19be6b" type="md-checkmark-circle" v-if="blackList.idcard_name_in_blacklist==true"/>
                    <Icon color="#ed3f14" type="md-close-circle" v-else/>
                </div>
                
                <div>
                    身份证和姓名黑名单更新时间 {{ blackList.idcard_name_blacklist_updated_time }}
                </div>
                 
                <div>
                    被标记的黑名单分类 {{ blackList.black_types }}
                </div>
                
                <Row style="padding: 20px; margin-bottom: 20px; background: #f9fcff"> 
                    <Col span="8">
                        <div>逾期次数</div>
                        <H4>{{ blackListRecord.overdue_count }}</H4>
                    </Col>
                    <Col span="8">
                        <div>最大逾期金额(元)</div>
                        <H4>{{ blackListRecord.overdue_amount }}</H4>
                    </Col>
                    <Col span="8">
                        <div>最大逾期天数</div>
                        <H4>{{ blackListRecord.overdue_status }}</H4>
                    </Col>
                </Row>
            </TabPane>
    
            <TabPane label="灰名单信息">
                <div>
                    手机和姓名是否在灰名单
                    <Icon color="#19be6b" type="md-checkmark-circle" v-if="grayList.mobile_name_in_gray==true"/>
                    <Icon color="#ed3f14" type="md-close-circle" v-else/>
                </div>
                
                <div>
                    手机和姓名灰名单更新时间 {{ grayList.mobile_name_gray_updated_time }}
                </div>
                
                <div>
                    身份证和姓名是否在灰名单
                    <Icon color="#19be6b" type="md-checkmark-circle" v-if="grayList.idcard_name_in_gray==true"/>
                    <Icon color="#ed3f14" type="md-close-circle" v-else/>
                </div>
                
                <div>
                    身份证和姓名灰名单更新时间 {{ grayList.idcard_name_gray_updated_time }}
                </div>
                 
                <div>
                    被标记的灰名单分类 {{ grayList.gray_types }}
                </div>
                
                <Row style="padding: 20px; margin-bottom: 20px; background: #f9fcff"> 
                    <Col span="8">
                        <div>逾期次数</div>
                        <H4>{{ grayListRecord.overdue_count }}</H4>
                    </Col>
                    <Col span="8">
                        <div>最大逾期金额(元)</div>
                        <H4>{{ grayListRecord.overdue_amount }}</H4>
                    </Col>
                    <Col span="8">
                        <div>最大逾期天数</div>
                        <H4>{{ grayListRecord.overdue_status }}</H4>
                    </Col>
                </Row>
            </TabPane>
        </Tabs>
    </Card>
    


</Row>    
`;

let tab5 = {
    template: tabHtml5,
    props: {
        data: Object
    },
    data() {
        return {

        }
    },
    methods: {},
    computed: {
        blackList() {
            return this.data.blackInfoDetail;
        },
        grayList() {
            return this.data.grayInfoDetail;
        },
        blackListRecord() {
            return this.data.blackInfoDetail.blacklist_record || {};
        },
        grayListRecord() {
            return this.data.grayInfoDetail.graylist_record || {};
        }
    }
    // ...
};