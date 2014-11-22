package org.fingerfing.client.domain;

@SuppressWarnings("serial")
public class IllegalNativeCode extends RuntimeException {

	public IllegalNativeCode() {
	}
	
	public IllegalNativeCode(String msg) {
		super(msg);
	}

	
	
}
