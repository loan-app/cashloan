package com.xiji.cashloan.core.common.exception;

/**
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public class ObjectNotFoundException extends ErongBaseException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
	}
	
	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
