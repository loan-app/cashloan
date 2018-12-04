package com.xiji.cashloan.core.common.exception;

/**
 *
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
public class TaskNotFoundException extends ErongBaseException{

	
	
	private static final long serialVersionUID = 1L;

	public TaskNotFoundException() {
	}

	public TaskNotFoundException(String message) {
		super(message);
	}

	public TaskNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
