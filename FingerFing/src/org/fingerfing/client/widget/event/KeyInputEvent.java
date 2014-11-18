package org.fingerfing.client.widget.event;

import org.fingerfing.client.core.Key;

public class KeyInputEvent {

	private Key key;

	public KeyInputEvent(Key key) {
		super();
		this.key = key;
	}

	public Key getKey() {
		return key;
	}

}
