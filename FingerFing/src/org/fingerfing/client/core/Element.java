package org.fingerfing.client.core;

public class Element {
	
	private int pos;
	private NativeKey nativeKey;
	
	public Element(int pos, NativeKey nk) {
		this.pos = pos;
		this.nativeKey = nk;
	}
	
	public NativeKey getNativeKey() {
		return nativeKey;
	}

	public int getPos() {
		return pos;
	}

	public boolean is(NativeKey nativeKey) {
		return this.nativeKey.equals(nativeKey);
	}
	
	

}
