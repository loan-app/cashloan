package com.xiji.cashloan.core.common.util.excel;

/**
 * excel处理model
 * TODO 用于批量处理，逐行读取excel并记录处理结果
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public class ExcelModel {

	/**
	 * 原数据
	 */
	private String data ;
	
	/**
	 * 处理结果
	 */
	private String result ;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
