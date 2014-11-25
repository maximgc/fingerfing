package org.fingerfing.client.presenter.event;

import org.fingerfing.client.presenter.Action;

import com.google.gwt.event.shared.GwtEvent;

public class ActionChangeEvent extends GwtEvent<ActionChangeHandler>{

	public static final Type<ActionChangeHandler> TYPE = new Type<ActionChangeHandler>();
	
	private Action action;
	
	public ActionChangeEvent(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return action;
	}

	@Override
	public Type<ActionChangeHandler> getAssociatedType() {
		return TYPE;
	}
	
	@Override
	protected void dispatch(ActionChangeHandler handler) {
		handler.onActionChange(this);
	}

}
