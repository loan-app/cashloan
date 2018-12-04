package com.xiji.cashloan.core.common.exception;

/**
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 */
public class TaskExecutionException extends ErongBaseException{

	
	private static final long serialVersionUID = 1L;

	public TaskExecutionException() {
	}

	public TaskExecutionException(String message) {
		super(message);
	}

	public TaskExecutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
