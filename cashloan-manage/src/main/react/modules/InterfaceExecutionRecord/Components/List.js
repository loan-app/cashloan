import React from 'react'
import {Table, Modal, Icon} from 'antd';
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
            canEdit: true,
            visible: false,
            visibleAc: false,
            rowRecord:[]

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        this.fetch(nextProps.params);
    },

    componentDidMount() {
        this.fetch();
    },

    fetch(params = {}) {
        this.setState({
            loading: true
        });
        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: 10,
                current: 1
            }
        }
        Utils.ajaxData({
            url: '/credit/sceneBusinessLog/page.htm',
            method: "post",
            data: params,
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;
                if (!pagination.current) {
                    pagination.current = 1
                }
                ;
                this.setState({
                    loading: false,
                    data: result.data,
                    pagination
                });
            }
        });
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
        var pagination = this.state.pagination;
        var params = objectAssign({}, this.props.params, {
            current: pagination.current,
            pageSize: pagination.pageSize
        });
        this.fetch(params);
    },

    //行点击事件
    onRowClick(record) {
        this.setState({
            selectedRowKeys: [record.id],
            selectedRow: record,
            rowRecord:record
        });
    },

    // 列表添加选中行样式
    rowClassName(record) {
        let selected = this.state.selectedIndex;
        //console.log('selected', this.state.selectedIndex)
        return (record.id == selected && selected !== '') ? 'selectRow' : '';

    },

    //选择
    onSelectAll(selected, selectedRowKeys, selectedRows, changeRows) {
        if (selected) {
            this.setState({
                selectedRowKeys
            })
        } else {
            this.setState({
                selectedRowKeys: []
            })
        }
    },

    render() {
        const {
            loading,
            selectedRowKeys
            } = this.state;
        const rowSelection = {
            type: 'checkbox',
            selectedRowKeys,
            onSelectAll: this.onSelectAll,
        };
        let me=this;
        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: '借款订单号',
            dataIndex: 'borrowOrderNo'
        }, {
            title: '第三方名称',
            dataIndex: 'tppName'
        }, {
            title: '接口名称',
            dataIndex: "businessName",
        }, {
            title: '接口昵称',
            dataIndex: "nid"
        }, {
            title: '请求时间',
            dataIndex: "createTime"
        }, {
            title: '更新时间',
            dataIndex: "updateTime"
        }, ];

        var state = this.state;
        return (
            <div className="block-panel">
                <Table columns={columns} rowKey={this.rowKey} ref="table" 
                       onRowClick={this.onRowClick}
                       dataSource={this.state.data}
                       rowClassName={this.rowClassName}
                       pagination={this.state.pagination}
                       onChange={this.handleTableChange}
                />
            </div>
        );
    }
})
