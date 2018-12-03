package com.xiji.cashloan.rc.constant;

/**
 * 第三方征信 常量
 * @author zlh
 * @version 1.0
 * @date 2017年3月18日 下午2:59:24
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public final class TppConstant {

	/**
	 * Tpp包的路径
	 */
	public static final String PACKAGE_PATH = "com.rongdu.arc.modules.tpp";
	
	/**
	 * 黑名单状态 - 在黑名单中
	 */
	public static final String BLACK_YES = "00";
	
	/**
	 * 黑名单状态 - 不在黑名单中
	 */
	public static final String BLACK_NO = "10";
	

	/********************* 获取接口数据方式  ***********************/
	/** 获取一次 */
	public static final String GET_WAY_ONCE = "00";
	/** 每次获取 */
	public static final String GET_WAY_EACH_TIME = "10";
	/** 固定周期获取 */
	public static final String GET_WAY_FIXED_CYCLE = "20";

}
