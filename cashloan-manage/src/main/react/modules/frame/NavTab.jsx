import React from 'react';
import { Tabs} from 'antd';
var TabPane = Tabs.TabPane; 
var Workbench = require('../Public/Workbench/Index');
var UserBasicInformation = require('../UserInformation/UserBasicInformation/index');//客户管理-用户认证信息
/*加载默认页*/
import Reflux from 'reflux'; 
var AppActions = require('./actions/AppActions');
var TabListStore = require('./stores/TabListStore');
 
var NavTab = React.createClass({
  mixins: [ 
    Reflux.listenTo(TabListStore, 'onMatch')
  ],
  onMatch(data) { 
        this.setState({
            activeId:data.activeId,
            tablist: data.tablist
        });
  },
  getInitialState() {
    return {
      tablist: [],
      activeId: "",
    }
  }, 
  componentWillReceiveProps(nextProps,nextState) {
    let scriptid = nextProps.menuData[0].scriptid;
    if(window.tablist == undefined){
      this.setState({
        tablist: scriptid=='ShowWorkbench'? [{'key': 'ShowWorkbench', 'tabName': '工作台',"tabId":'ShowWorkbench',tabContent:<Workbench/>}] 
                  : [],
        activeId: scriptid=='ShowWorkbench'?'ShowWorkbench':"",
      })
    }
  }, 
  onChange(activeId) { 
    this.setState({activeId});
  },
  remove(tabId, e) {
    e.stopPropagation();
    AppActions.removeTab(tabId); 
  }, 

  
  render() { 
    // console.log(this.state.tablist);
    // console.log(window.tablist);
    return (
      <div className="Mytabs">
          <Tabs activeKey={this.state.activeId} onChange={this.onChange} destroyInactiveTabPane animation={null} contentStyle={{height:document.body.clientHeight-100,overflow: 'auto'}}>
                {
                  this.state.tablist.map((t, i)=> {
                    return<TabPane key={t.tabId}
                                 tab={<div>{t.tabName} <i className="anticon anticon-cross-circle"  onClick={this.remove.bind(this,t.tabId)}></i> </div>}>
                        {t.tabContent} 
                    </TabPane>;
                  })
                }
          </Tabs>
      </div>);
  }
});

export default NavTab;
