package com.xiji.cashloan.core.common.exception;


/**
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public class RDRuntimeException extends BaseRuntimeException{

	private static final long serialVersionUID = 1L;

	public RDRuntimeException() {
	}
	
	public RDRuntimeException(String message) {
		super(message);
	}
	
	public RDRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
