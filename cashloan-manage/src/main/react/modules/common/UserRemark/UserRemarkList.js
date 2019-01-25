import React from 'react';
import {Modal, Table,} from 'antd';

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
    },

    componentWillReceiveProps(nextProps,nextState){
        this.setState({
            userId: nextProps.record
        })
        // var searchdata = {};
        // var record=nextProps.record;
        //
        // console.log('record_1 = '+nextProps);
        // console.log('userId = '+record);
        // searchdata = {
        //     userId:record
        //
        // }
        // var params=objectAssign({},{pageSize: 10, current: 1,}, {search:JSON.stringify(searchdata)  })
        // this.fetch(params);
        this.fetch();
    },

    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        pager.pageSize = pagination.pageSize;
        pager.userId = this.state.record,
            console.log('pager.userId = '+ this.state.record);
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
                pageSize: 2,
                current: 1,
                userId: this.props.record,
            }
            console.log('fetch userId = '+ this.props.record);
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
        return (
            <Modal title={this.props.title} visible={this.props.visible} onCancel={this.handleCancel} width="1000">
                <div className="block-panel">
                    <div className="actionBtns" style={{ marginBottom: 16 }}>
                        <button className="ant-btn" >
                            新增
                        </button>
                    </div>
                    <Table columns={columns} rowKey={this.rowKey}  size="middle"
                           dataSource={this.state.data}
                           pagination={this.state.pagination}
                           loading={this.state.loading}
                           onChange={this.handleTableChange}  />
                </div>
            </Modal>

        );
    }
});
export default UserRemarkList;