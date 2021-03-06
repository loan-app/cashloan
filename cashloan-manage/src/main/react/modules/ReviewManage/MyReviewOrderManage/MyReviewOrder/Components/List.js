import React from 'react'
import {Table} from 'antd';
import Lookdetails from "./Lookdetails"
import UserRemarkList from "../../../../common/UserRemark/UserRemarkList";

const objectAssign = require('object-assign');
export default React.createClass({
    getInitialState() {
        return {
            selectedRowKeys: [], // 这里配置默认勾选列
            loading: false,
            data: [],
            pagination: {
                pageSize: 10,
                current: 1
            },
            pagination1: {
                pageSize: 5,
                current: 1
            },
            canEdit: true,
            visible: false,
            visible1: false,
            visible2: false,
            pictureData: [],
            creditReportData: [],
            rowRecord: [],
            dataRecord: [],
            recordSoure: '',
            dataForm: '',
            visibleRemark:false,

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        this.fetch(nextProps.params);
    },

    componentDidMount() {
        this.fetch(this.props.params);
    },

    fetch(params = {}) {
        this.setState({
            loading: true
        });
        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: 10,
                current: 1,
                searchParams: JSON.stringify({borrowState:'22'}),
            }
        }
        if(!params.searchParams){
            params.searchParams= JSON.stringify({ borrowState: '22' })
        }
        Utils.ajaxData({
            url: '/modules/manage/borrow/my/manual/review/list.htm',
            method: "post",
            data: params,
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;

                this.setState({
                    loading: false,
                    data: result.data,
                    pagination
                });
            }
        });
    },

    //查看弹窗
    showModal(title, record, canEdit) {
        // var record = this.state.selectedRow;
        var record = record;
        var me = this;
        this.setState({
            visible: true,
            canEdit: canEdit,
            rowRecord: record,
            title: title,
            keyModal: Date.now(),
        }, () => {
            Utils.ajaxData({
                url: '/modules/manage/cl/cluser/detail.htm',
                data: {
                    userId: record.borrowUserId
                },
                callback: (result) => {
                    if (result.code == 200) {
                        var dataForm = {};
                        dataForm.realName = result.data.userbase.realName;
                        dataForm.sex = result.data.userbase.sex;
                        dataForm.idNo = result.data.userbase.idNo;                   
                        dataForm.age = result.data.userbase.age;
                        dataForm.cardNo = result.data.userbase.cardNo;
                        dataForm.bank = result.data.userbase.bank;
                        dataForm.bankPhone = result.data.userbase.bankPhone;
                        dataForm.phone = result.data.userbase.phone;
                        dataForm.liveAddr = result.data.userbase.liveAddr;
                        dataForm.registTime = result.data.userbase.registTime;
                        dataForm.registerAddr = result.data.userbase.registerAddr;
                        dataForm.registerClient = result.data.userbase.registerClient;
                        dataForm.education = result.data.userbase.education;
                        dataForm.companyName = result.data.userbase.companyName;
                        dataForm.companyPhone = result.data.userbase.companyPhone;
                        dataForm.salary = result.data.userbase.salary;
                        dataForm.workingYears = result.data.userbase.workingYears;
                        dataForm.companyAddr = result.data.userbase.companyAddr;
                        dataForm.taobao = result.data.userOtherInfo != null ? result.data.userOtherInfo.taobao : '';
                        dataForm.email = result.data.userOtherInfo != null ? result.data.userOtherInfo.email : '';
                        dataForm.qq = result.data.userOtherInfo != null ? result.data.userOtherInfo.qq : '';
                        dataForm.wechat = result.data.userOtherInfo != null ? result.data.userOtherInfo.wechat : '';
                        dataForm.registerCoordinate = result.data.userbase.registerCoordinate;
                        if (result.data.userContactInfo.length > 0) {
                            if (result.data.userContactInfo[0].type == "10") {
                                dataForm.urgentName = result.data.userContactInfo[0].name;
                                dataForm.urgentPhone = result.data.userContactInfo[0].phone;
                                dataForm.urgentRelation = result.data.userContactInfo[0].relation;
                            } else {
                                dataForm.otherName = result.data.userContactInfo[0].name;
                                dataForm.otherPhone = result.data.userContactInfo[0].phone;
                                dataForm.otherRelation = result.data.userContactInfo[0].relation;
                            }
                            if (result.data.userContactInfo[1].type == "20") {
                                dataForm.otherName = result.data.userContactInfo[1].name;
                                dataForm.otherPhone = result.data.userContactInfo[1].phone;
                                dataForm.otherRelation = result.data.userContactInfo[1].relation;
                            } else {
                                dataForm.urgentName = result.data.userContactInfo[1].name;
                                dataForm.urgentPhone = result.data.userContactInfo[1].phone;
                                dataForm.urgentRelation = result.data.userContactInfo[1].relation;
                            }
                        }
                        if (result.data.userAuth) {
                            if (result.data.userAuth.bankCardState == 10) {
                                dataForm.bankCardState = "未认证"
                            } else if (result.data.userAuth.bankCardState == 20) {
                                dataForm.bankCardState = "认证中"
                            } else if (result.data.userAuth.bankCardState == 30) {
                                dataForm.bankCardState = "已认证"
                            }
                            if (result.data.userAuth.idState == 10) {
                                dataForm.idState = "未认证"
                            } else if (result.data.userAuth.idState == 20) {
                                dataForm.idState = "认证中"
                            } else if (result.data.userAuth.idState == 30) {
                                dataForm.idState = "已认证"
                            }
                            if (result.data.userAuth.phoneState == 10) {
                                dataForm.phoneState = "未认证"
                            } else if (result.data.userAuth.phoneState == 20) {
                                dataForm.phoneState = "认证中"
                            } else if (result.data.userAuth.phoneState == 30) {
                                dataForm.phoneState = "已认证"
                            }
                            if (result.data.userAuth.zhimaState == 10) {
                                dataForm.zhimaState = "未认证"
                            } else if (result.data.userAuth.zhimaState == 20) {
                                dataForm.zhimaState = "认证中"
                            } else if (result.data.userAuth.zhimaState == 30) {
                                dataForm.zhimaState = "已认证"
                            }
                        }
                        me.setState({
                            recordSoure: result.data,
                            dataForm:dataForm
                        })
                        me.refs.Lookdetails.refs.Tab1.setFieldsValue(dataForm);
                        record.isBlack2 = result.data.userbase.state == '10' ? true : false;
                    }
                    record.state1 = title == "人工复审" ? "27" : record.state;
                    this.refs.Lookdetails.refs.ManualReviewForm.setFieldsValue(record);
                }
            });
            //console.log(record);
            //record.state1 = title == "人工复审" ? "27" : record.state;
            //record.isBlack = title == "人工复审" ? true : false;
            //record.isBlack2 =  true;
            //this.refs.Lookdetails.refs.ManualReviewForm.setFieldsValue(record);
        })
    },

    //隐藏弹窗
    hideModal(state) {
        this.setState({
            visible: false,
            selectedIndex: '',
            selectedRowKeys: [],
            dataRecord: [],
            recordSoure: '',
            dataForm: '',
            visibleRemark:false,
        });
        this.refreshList();
    },


    rowKey(record) {
        return record.id;
    },

    //选择
    onSelectChange(selectedRowKeys) {
        this.setState({
            selectedRowKeys
        });
    },

    //分页
    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        this.setState({
            pagination: pager,
        });
        this.refreshList();
    },

    refreshList() {
        var pagination = this.state.pagination; //console.log(pagination)
        pagination.pageSize = 10;
        var params = objectAssign({}, this.props.params, {
            current: pagination.current,
            pageSize: pagination.pageSize,
            // searchParams: JSON.stringify({ state: "22" }),
        });
        this.fetch(params);
    },

    showUserRemark(title, record, canEdit) {
        Utils.ajaxData({
            url: '/modules/manage/user/remark/list.htm',
            data: {
                pageSize: 5,
                current: 1,
                searchParams:JSON.stringify({userId:record})
            },
            method: 'get',
            callback: (result) => {
                const pagination1 = this.state.pagination1;
                pagination1.current = result.current;
                pagination1.pageSize =result.pageSize;
                pagination1.total = result.page.total;
                if (!pagination1.current) {
                    pagination1.current = 1
                };
                //console.log(result.data.logs);
                this.setState({
                    dataRecord: result.data,
                    canEdit: canEdit,
                    visibleRemark: true,
                    title: title,
                    pagination1:result.page,
                    record:record
                });
            }
        });
    },


    render() {
        const {
            loading,
            selectedRowKeys
            } = this.state;

        let me = this;
        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: '真实姓名',
            dataIndex: 'borrowName'
        }, {
            title: '手机号码',
            dataIndex: "phone"
        }, {
            title: '借款时间',
            dataIndex: 'borrowTime'
        }, {
            title: '借款金额(元)',
            dataIndex: "amount"
        }, {
            title: '借款期限(天)',
            dataIndex: "timeLimit"
        }, {
            title: '实际到账金额(元)',
            dataIndex: "realAmount"
        }, {
            title: '渠道',
            dataIndex: 'channelName',
        }, {
            title: '是否复借',
            dataIndex: 'again',
            render:(text,record) =>  {
                switch(record.again){
                    case "10":
                        return "否";
                    case "20":
                        return <span style={{ color: "red" }}>是</span>;
                }
            }
        }, {
            title: '状态',
            dataIndex: "stateStr",
        }, {
            title: '备注',
            render(text, record) {
                return <div ><a href="#" onClick={me.showUserRemark.bind(me, '备注', record.borrowUserId, true)}>备注</a></div>
            }
        },{
            title: '操作',
            render(text, record) {
                if (record.borrowState == '22') {
                    return (
                        <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.showModal.bind(me, '人工复审', record, true)}>人工复审</a>
                        </div>
                    )
                } else {
                    return (
                        <div style={{ textAlign: "left" }}>
                            <a href="#" onClick={me.showModal.bind(me, '查看', record, false)}>查看</a>
                        </div>
                    )
                }
            }
        }];

        var state = this.state;
        return (
            <div className="block-panel">

                <Table columns={columns} rowKey={this.rowKey} ref="table"
                    dataSource={this.state.data}
                    pagination={this.state.pagination}
                    onChange={this.handleTableChange}
                />
                <Lookdetails ref="Lookdetails" dataForm={state.dataForm} key={state.keyModal} recordSoure={state.recordSoure}  visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.rowRecord}
                    canEdit={state.canEdit} />


                <UserRemarkList ref="UserRemarkList" visible={state.visibleRemark}    title={state.title} hideModal={me.hideModal}
                                dataRecord={state.dataRecord}  record={state.record} canEdit={state.canEdit} pagination={state.pagination1}/>
            </div>
        );
    }
})
