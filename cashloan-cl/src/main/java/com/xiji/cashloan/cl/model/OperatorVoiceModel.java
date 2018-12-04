package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.OperatorVoice;
/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class OperatorVoiceModel extends OperatorVoice {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 通话次数
	 */
	private Integer totalCount;
	
	/**
	 * 通话总时长（秒）
	 */
	private Integer sumDuration;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getSumDuration() {
		return sumDuration;
	}

	public void setSumDuration(Integer sumDuration) {
		this.sumDuration = sumDuration;
	}
	
	
}
