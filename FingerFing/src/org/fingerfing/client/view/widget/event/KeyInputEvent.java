package org.fingerfing.client.view.widget.event;

import org.fingerfing.client.domain.Key;

public class KeyInputEvent extends Event<KeyInputHandler> {

	private Key key;

	public KeyInputEvent(Key key) {
		super();
		this.key = key;
	}

	public Key getKey() {
		return key;
	}

	@Override
	public void dispatch(KeyInputHandler handler) {
		handler.onKeyInput(this);
	}

}
