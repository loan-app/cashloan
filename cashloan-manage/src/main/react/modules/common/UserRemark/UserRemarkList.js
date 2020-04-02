import React from 'react';

import {Form, Modal, Table} from 'antd';
import SaveUserRemark from '../UserRemark/SaveUserRemark';

const FormItem = Form.Item;
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
var UserRemarkList = React.createClass({
    getInitialState() {
        return {
            loading: false,
            data: [],
            pagination: {},
        };
    },
    rowKey(record) {
        return record.id;
    },

    handleCancel() {
        this.props.hideModal();
        this.refs.SaveUserRemark.resetFields();
    },

    componentWillReceiveProps(nextProps,nextState){
        this.setState({
            data: nextProps.dataRecord,
            pagination:nextProps.pagination
        })
    },

    handleOk(){
        let me = this;
        let params = this.refs.SaveUserRemark.getFieldsValue();
        this.refs.SaveUserRemark.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            if (!params.remark) {
                alert("请填写备注");
                return;
            }
            var tips = '是否确定提交';
            confirm({
                title: tips,
                onOk: function () {
                    Utils.ajaxData({
                        url: '/modules/manage/user/remark/save.htm',
                        data: {remark:params.remark,userId:params.userId},
                        callback: (result) => {
                            if (result.code == 200) {
                                me.handleCancel();
                            };
                            let resType = result.code == 200 ? 'success' : 'warning';
                            Modal[resType]({
                                title: result.msg,
                            });
                        }
                    });
                },
                onCancel: function () { }
            })
            this.handleCancel();
        })
    },

    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        pager.pageSize = pagination.pageSize;
        pager.userId = this.state.record,
            pager.searchParams = JSON.stringify({userId:this.props.record})
            this.setState({
                pagination: pager,
            });
        this.fetch(pager);
    },
    fetch(params = {}) {
        this.setState({
            loading: true
        });
        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: 5,
                current: 1,
            }
        }

        if(!params.searchParams){
            params.searchParams = JSON.stringify({userId:this.state.record});
        }
        Utils.ajaxData({
            url: '/modules/manage/user/remark/list.htm',
            data: params,
            method: 'get',
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;
                if (!pagination.current) {
                    pagination.current = 1
                };
                this.setState({
                    loading: false,
                    data: result.data,
                    pagination,
                });
            }
        });
    },
    render() {
        var me = this;
        var columns = [{
            title: '备注内容',
            dataIndex: "remark",
        }, {
            title: '操作时间',
            dataIndex: "operateTime",
        }, {
            title: '操作人',
            dataIndex: "operateName",
        }];
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 12
            },
        };
        var modalBtns  = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>,
            <button key="go" className="ant-btn" onClick={this.handleOk}>保存</button>
        ];
        return (
            <Modal title={this.props.title} visible={this.props.visible} onCancel={this.handleCancel} width="800" footer={modalBtns} >
                <div className="block-panel">
                    <Table columns={columns} rowKey={this.rowKey}  size="middle"
                           dataSource={this.state.data}
                           pagination={this.state.pagination}
                           loading={this.state.loading}
                           onChange={this.handleTableChange}  />
                    <SaveUserRemark ref="SaveUserRemark" canEdit={this.props.canEdit} userId={this.props.record} />
                </div>
            </Modal>

        );
    }
});
export default UserRemarkList;