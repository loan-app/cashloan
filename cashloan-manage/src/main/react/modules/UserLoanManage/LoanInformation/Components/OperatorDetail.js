import React from 'react';

import {
    Form
} from 'antd';

const createForm = Form.create;

const objectAssign = require('object-assign');

var OperatorDetail = React.createClass({
    getInitialState() {
        return {
            status: {},
            reportLink: ''
        };
    },
    componentWillMount() {
        Utils.ajaxData({
            url: '/modules/manage/operator/reportLink/detail.htm',
            data: {
                userId: this.props.userId
            },
            callback: (result) => {
                this.setState({
                    reportLink: result.reportLink
                });
            }
        });
    },
    render() {
        if (this.state.reportLink) {
            return (
                <iframe style={{border:0,width:"100%",height:630,}} src={`https://tenant.51datakey.com/carrier/report_data?data=${this.state.reportLink}`} />
            );
        } else {
            return (
                <div>
                    <h5>加载中...</h5>
                </div>
            );
        }
    }
});
OperatorDetail = createForm()(OperatorDetail);
export default OperatorDetail;