let tabHtml6 = `
<div>
    <Card :bordered="false">
        <p slot="title">设备指纹风险</p>
        
        <Table size="small" :columns="columns" :data="data"></Table>  
    </Card>
</div>    
`;

let tab6 = {
    template: tabHtml6,
    props: {
        data: Object
    },
    data() {
        return {
            columns: [
                {
                    title: '描述',
                    key: 'key'
                },
                {
                    title: '结果',
                    key: 'value'
                }
            ]
        }
    },
    methods: {},
    computed: {

    }
    // ...
};