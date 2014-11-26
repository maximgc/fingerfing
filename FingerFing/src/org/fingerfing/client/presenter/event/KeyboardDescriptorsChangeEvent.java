package org.fingerfing.client.presenter.event;

import com.google.gwt.event.shared.GwtEvent;

public class KeyboardDescriptorsChangeEvent extends GwtEvent<KeyboardDescriptorsChangeHandler> {
	
	public static final Type<KeyboardDescriptorsChangeHandler> TYPE = new Type<KeyboardDescriptorsChangeHandler>();
	
	@Override
	public Type<KeyboardDescriptorsChangeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(KeyboardDescriptorsChangeHandler handler) {
		handler.onKeyboardDescriptorsChange(this);
	}

}
