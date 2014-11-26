package org.fingerfing.client;

import org.fingerfing.client.presenter.Action;
import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ActionChangeHandler;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;

public class FlowController implements ValueChangeHandler<String>,
		ActionChangeHandler {

	private final EventBus eventBus;

	public FlowController(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		Action a;
		try {
			a = Action.valueOf(event.getValue());
		} catch (IllegalArgumentException e) {
			a = Action.TRAIN;
		}
		eventBus.fireEventFromSource(new ActionChangeEvent(a), this);
	}

	@Override
	public void onActionChange(ActionChangeEvent event) {
		History.newItem(event.getAction().name(), false);
	}
}