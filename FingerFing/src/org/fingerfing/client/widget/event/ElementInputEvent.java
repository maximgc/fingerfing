package org.fingerfing.client.widget.event;

import org.fingerfing.client.core.NativeKey;

public class ElementInputEvent {
	
	private NativeKey nativeKey;

	public ElementInputEvent(NativeKey nativeKey) {
		this.nativeKey = nativeKey;
	}
	
	public NativeKey getNativeKey() {
		return nativeKey;
	}
	
}
