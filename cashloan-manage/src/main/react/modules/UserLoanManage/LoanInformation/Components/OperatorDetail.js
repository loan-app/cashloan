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
            detail: {}
        };
    },
    componentWillMount() {
        Utils.ajaxData({
            url: '/modules/manage/operator/reportLink/detail.htm',
            data: {
                userId: this.props.userId
            },
            callback: (result) => {
                console.log(result);
                this.setState({
                    detail: result.data
                });
            }
        });
    },
    render() {

        return (
            <iframe style={{border:0,width:"100%",height:630,}} src={this.state.detail.operateUrl} />
        );
    }
});
OperatorDetail = createForm()(OperatorDetail);
export default OperatorDetail;