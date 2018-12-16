import React from 'react';
import {
    Table,
    Tabs
} from 'antd';
import XinyanDetail from './XinyanDetail'
import OperatorDetail from './OperatorDetail'

const objectAssign = require('object-assign');
const TabPane = Tabs.TabPane;
var Tab8 = React.createClass({
    getInitialState() {
        return {
        };
    },
    render() {
        return (<div className="block-panel">
                <Tabs type="card" onTabClick={this.handleTabClick}>
                    <TabPane tab="运营商报告" key='1'>
                        <OperatorDetail userId={this.props.userId}></OperatorDetail>
                    </TabPane>
                    <TabPane tab="信贷报告" key='2'>
                        <iframe style={{border: 0, width: "100%", height: 630,}}
                                src={'/build/magicReport/index.html?userId=' + this.props.userId}/>
                    </TabPane>
                    <TabPane tab="小额网贷报告" key="3">
                        <XinyanDetail userId={this.props.userId}/>
                    </TabPane>
                </Tabs>
            </div>
        );
    }
});
export default Tab8;