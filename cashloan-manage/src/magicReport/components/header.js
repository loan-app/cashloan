const headerHtml = `
<Row type="flex" align="middle">
    <Col span="3" style="text-align: center">
        <div style="display: inline-block; width: 80px; height: 80px; background: #d8ebfe; font-size: 50px; text-align: center; line-height: 80px; color: #fff; border-radius: 50%;">
            {{ data.personInfo.name }}
        </div>
    </Col>

    <Col span="12">
        <div style="font-size: 24px; margin-bottom: 10px"><strong>{{ data.personInfo.name }}</strong> {{ data.personInfo.gender }} {{ data.personInfo.age }}</div>
        <div style="margin-bottom: 10px">
            <strong>身份证号：</strong> {{ data.personInfo.idcard }}
            <Tag type="border" color="primary">有效</Tag>
            <Tag type="border">无效</Tag>
        </div>
        <div style="margin-bottom: 10px">
            <strong>手机号码：</strong> {{ data.personInfo.mobile }}
            <strong>联系邮箱：</strong> {{ data.personInfo.email }}
        </div>
    </Col>

    <Col span="3">
        查询结果
        <div style="font-size: 32px">成功</div>
    </Col>
    <Col span="3">
        涉黑评分
        <div style="font-size: 32px" v-if="data.matchScore>40">{{ data.matchScore }}</div>
        <div style="font-size: 32px; color:#ed3f14" v-else>{{ data.matchScore }}(危险)</div>
    </Col>
</Row>
`;

let myHeader = {
    template: headerHtml,
    props: {
        data: Object
    },
    data() {
        return {

        }
    },
    methods: {}
    // ...
};
