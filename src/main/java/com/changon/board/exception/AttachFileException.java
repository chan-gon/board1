package com.changon.board.exception;

@SuppressWarnings("serial")
public class AttachFileException extends RuntimeException {
	
	/*
	 * AttachFileException 클래스는 RuntimeException을 상속하고, 
	 * IOException을 처리하는 catch 문에서 Unchecked Exception을 throw 하기 때문에
	 * transferTo 메서드 사용하는 곳에서는 아무런 예외 처리를 진행하지 않아도 된다.
	 */

	public AttachFileException(String message) {
		super(message);
	}

	public AttachFileException(String message, Throwable cause) {
		super(message, cause);
	}

}