package org.fingerfing.client.core;

public class Element {
	
	private int pos;
	private Key nativeKey;
	
	public Element(int pos, Key nk) {
		this.pos = pos;
		this.nativeKey = nk;
	}
	
	public Key getNativeKey() {
		return nativeKey;
	}

	public int getPos() {
		return pos;
	}

	public boolean is(Key nativeKey) {
		return this.nativeKey.equals(nativeKey);
	}
	
	

}
