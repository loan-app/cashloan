package com.xiji.cashloan.core.common.exception;


/**
 * 
 * 用户操作异常类
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public class OperatorException extends BaseRuntimeException {

	private static final long serialVersionUID = -7400559552805824955L;

	public OperatorException() {
		super();
	}

	public OperatorException(String message) {
		super(message);
	}

	public OperatorException(String message, int type) {
		super(message, type);
	}

	public OperatorException(String msg, RuntimeException ex) {
		super(msg, ex);
	}

	public OperatorException(String message, String url) {
		super(message, url);
	}

}
