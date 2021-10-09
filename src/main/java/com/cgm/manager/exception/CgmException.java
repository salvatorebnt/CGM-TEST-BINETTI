package com.cgm.manager.exception;

public class CgmException extends Exception {

	private static final long serialVersionUID = 941653192954735842L;

	public CgmException() {
		super();
	}

	public CgmException(String message) {
		super(message);
	}

	public CgmException(Throwable cause) {
		super(cause);
	}

	public CgmException(String message, Throwable cause) {
		super(message, cause);
	}

}
