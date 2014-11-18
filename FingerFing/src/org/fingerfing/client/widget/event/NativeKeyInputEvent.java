package org.fingerfing.client.widget.event;

import org.fingerfing.client.core.NativeKey;

public class NativeKeyInputEvent {
	
	private NativeKey nativeKey;

	public NativeKeyInputEvent(NativeKey nativeKey) {
		this.nativeKey = nativeKey;
	}
	
	public NativeKey getNativeKey() {
		return nativeKey;
	}
	
}
