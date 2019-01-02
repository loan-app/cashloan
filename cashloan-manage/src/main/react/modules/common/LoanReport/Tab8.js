import React from 'react';
import {
    Table,
    Tabs
} from 'antd';
import XinyanDetail from '../XinyanDetail/XinyanDetail';
import OperatorDetail from './OperatorDetail';
import XindeBlacklist from './XindeBlacklist';
import PaipaixinView from './PaipaixinView';
import YXAF from './YXAF/Credit-YXAF';
import AntiFraud from './AntiFraud/AntiFraud';
import PinAn from './PinAn/PinAn';


const objectAssign = require('object-assign');
const TabPane = Tabs.TabPane;
var Tab8 = React.createClass({
    getInitialState() {
        return {
            activekey: '1'
        };
    },
    render() {
        return (<div className="block-panel">
                <Tabs type="card">
                    <TabPane tab="宜信阿福" key='YXAF'>
                        <YXAF userId={this.props.userId}></YXAF>
                    </TabPane>
                    <TabPane tab="反欺诈" key='AntiFraud'>
                        <AntiFraud userId={this.props.userId}></AntiFraud>
                    </TabPane>
                    {/*<TabPane tab="运营商报告" key='1'>*/}
                        {/*<OperatorDetail userId={this.props.userId}></OperatorDetail>*/}
                    {/*</TabPane>*/}
                    {/*<TabPane tab="信贷报告" key='2'>*/}
                        {/*<iframe style={{border: 0, width: "100%", height: 630,}}*/}
                                {/*src={'/build/magicReport/index.html?userId=' + this.props.userId}/>*/}
                    {/*</TabPane>*/}
                    <TabPane tab="小额网贷报告" key="3">
                        <XinyanDetail userId={this.props.userId}/>
                    </TabPane>
                    <TabPane tab="最近一次凭安报告" key="PinAn">
                        <PinAn userId={this.props.userId}/>
                    </TabPane>

                    {/*<TabPane tab="信德数聚（灰名单信息）" key="4">*/}
                        {/*<XindeBlacklist borrowId={this.props.borrowId}/>*/}
                    {/*</TabPane>*/}
                    {/*<TabPane tab="拍拍信（黑名单）" key="5">*/}
                        {/*<PaipaixinView borrowId={this.props.borrowId}/>*/}
                    {/*</TabPane>*/}
                </Tabs>
            </div>
        );
    }
});
export default Tab8;