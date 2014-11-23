package org.fingerfing.client.presenter.event;

import org.fingerfing.client.presenter.Action;

import com.google.gwt.event.shared.GwtEvent;

public class ActionChangeEvent extends GwtEvent<ActionChangeEventHandler>{

	public static final Type<ActionChangeEventHandler> TYPE = new Type<ActionChangeEventHandler>();
	
	private Action action;
	
	public ActionChangeEvent(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return action;
	}

	@Override
	public Type<ActionChangeEventHandler> getAssociatedType() {
		return TYPE;
	}
	
	@Override
	protected void dispatch(ActionChangeEventHandler handler) {
		handler.onActionChange(this);
	}

}
