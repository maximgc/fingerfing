package org.fingerfing.client.domain;

public class Element {
	
	private int pos;
	private Key key;
	
	public Element(int pos, Key key) {
		this.pos = pos;
		this.key = key;
	}
	
	public Key getKey() {
		return key;
	}

	public int getPos() {
		return pos;
	}

}
