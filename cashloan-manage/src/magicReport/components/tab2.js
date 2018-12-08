let tabHtml2 = `
<Row :gutter="16">
    <Col span="6">
        <Card :bordered="false">
            <p slot="title">失信情况</p>
            <p>法院执行人次数</p>
            <p>{{ data.courtcase_cnt }}</p>
            <p>失信未执行次数</p>
            <p>{{ data.dishonest_cnt }}</p>
        </Card>
    </Col>
    
    <Col span="18">
        <Card :bordered="false" v-for="item in detail">
            <p slot="title">失信明细</p>
            
            <h4>{{ item.court_name }}</h4>
            <tag>{{ item.area_name }}</tag> <tag>{{ item.publish_date }}</tag>
            
            <div><strong>执行依据文号：</strong>{{ item.case_code }} <strong>案号：</strong>{{ item.gist_id }}</div>
            <div><strong>生效法律文书确定的义务：</strong>{{ item.duty }}</div>
            <div><strong>被执行人的履行性质：</strong>{{ item.performance }}</div>
            <div><strong>失信被执行人行为具体情形：</strong>{{ item.disrupt_type_name }}</div>
        </Card>
    </Col>
</Row>    
`;

let tab2 = {
    template: tabHtml2,
    props: {
        data: Object
    },
    data() {
        return {
        }
    },
    methods: {},
    computed: {
        detail() {
            return this.data.dishonest_detail_info || [];
        },
    }
    // ...
};