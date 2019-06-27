package com.xiji.cashloan.cl.model;


import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.core.common.context.Global;

/**
 * 渠道信息Model
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 *
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class ChannelModel extends Channel {

	private static final long serialVersionUID = 1L;
	
	/*********   状态 - 定义   *********   /
	/** 状态 - 启用 */
	public static final String STATE_ENABLE = "10";

	/** 状态 - 禁用 */
	public static final String STATE_DISABLE = "20";
	
	
	/**
	 * 渠道类型描述
	 */
	private String typeStr;
	
	/**
	 * 渠道状态中文描述
	 */
	private String stateStr;

	private Integer wechatCount = 0;
	private Integer qqCount = 0;
	private Integer weiboCount = 0;
	private Integer otherCount = 0;
	private Double wechatPercent;
	private Double qqPercent;
	private Double weiboPercent;
	private Double otherPercent;

    /**
     * 状态中文转换
     * 
     * @param state
     * @return
     */
    public static String stateConvert(String state) {
        String stateStr;
        if (ChannelModel.STATE_DISABLE.equals(state)) {
            stateStr = "禁用";
        } else {
            stateStr = "启用";
        }
        return stateStr;
    }

	/**
	 * 获取渠道类型描述
	 * @return typeStr
	 */
	public String getTypeStr() {
		return typeStr;
	}

	/**
	 * 设置渠道类型描述
	 * @param typeStr
	 */
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	/**
	 * 获取状态中文描述
	 * @return stateStr
	 */
	public String getStateStr() {
		this.stateStr = stateConvert(this.getState());
		return stateStr;
	}

	/**
	 * 设置状态中文描述
	 * @param stateStr
	 */
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	
	/**
	 * 获取渠道邀请注册链接
	 * @return link
	 */
	public String getLink() {
		return Global.getValue("server_host")+Global.getValue("h5_invite")+"?channelCode=" + getCode();
	}

	public Integer getWechatCount() {
		return wechatCount;
	}

	public void setWechatCount(Integer wechatCount) {
		this.wechatCount = wechatCount;
	}

	public Integer getQqCount() {
		return qqCount;
	}

	public void setQqCount(Integer qqCount) {
		this.qqCount = qqCount;
	}

	public Integer getWeiboCount() {
		return weiboCount;
	}

	public void setWeiboCount(Integer weiboCount) {
		this.weiboCount = weiboCount;
	}

	public Integer getOtherCount() {
		return otherCount;
	}

	public void setOtherCount(Integer otherCount) {
		this.otherCount = otherCount;
	}

	public Double getWechatPercent() {
		return wechatPercent;
	}

	public void setWechatPercent(Double wechatPercent) {
		this.wechatPercent = wechatPercent;
	}

	public Double getQqPercent() {
		return qqPercent;
	}

	public void setQqPercent(Double qqPercent) {
		this.qqPercent = qqPercent;
	}

	public Double getWeiboPercent() {
		return weiboPercent;
	}

	public void setWeiboPercent(Double weiboPercent) {
		this.weiboPercent = weiboPercent;
	}

	public Double getOtherPercent() {
		return otherPercent;
	}

	public void setOtherPercent(Double otherPercent) {
		this.otherPercent = otherPercent;
	}
}
