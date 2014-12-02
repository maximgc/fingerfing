package org.fingerfing.client.view.widget.event;

import org.fingerfing.client.domain.NativeKey;

public class NativeKeyInputEvent extends Event<NativeKeyInputHandler> {
	
	private NativeKey nativeKey;

	public NativeKeyInputEvent(NativeKey nativeKey) {
		this.nativeKey = nativeKey;
	}
	
	public NativeKey getNativeKey() {
		return nativeKey;
	}

	@Override
	public void dispatch(NativeKeyInputHandler handler) {
		handler.onNativeKeyInput(this);
	}
	
}
