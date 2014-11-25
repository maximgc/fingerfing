package org.fingerfing.client.presenter.event;

import com.google.gwt.event.shared.GwtEvent;

public class KeyboardDescriptorChangeEvent extends GwtEvent<KeyboardDescriptorChangeHandler> {
	
	public static final Type<KeyboardDescriptorChangeHandler> TYPE = new Type<KeyboardDescriptorChangeHandler>();
	
	@Override
	public Type<KeyboardDescriptorChangeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(KeyboardDescriptorChangeHandler handler) {
		handler.onKeyboardDescriptorChange(this);
	}

}
