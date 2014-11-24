package org.fingerfing.client;

import org.fingerfing.client.presenter.Action;
import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ActionChangeEventHandler;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;

public class FlowController implements ValueChangeHandler<String>, ActionChangeEventHandler {
	
	private final EventBus eventBus;

	public FlowController(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		switch (event.getValue()) {
		case "train":
			eventBus.fireEvent(new ActionChangeEvent(Action.TRAIN));
			break;
		case "courseDesign":
			eventBus.fireEvent(new ActionChangeEvent(Action.COURSE_DESIGNER));
			break;
		case "keyboardDesign":
			eventBus.fireEvent(new ActionChangeEvent(Action.KEYBOARD_DESIGNER));
			break;
		default:
			eventBus.fireEvent(new ActionChangeEvent(Action.TRAIN));
//			History.newItem("train");
			break;
		}
	}

	@Override
	public void onActionChange(ActionChangeEvent event) {
		switch (event.getAction()) {
		case TRAIN:
			History.newItem("train", false);
			break;
		case COURSE_DESIGNER:
			History.newItem("courseDesign", false);
			break;
		case KEYBOARD_DESIGNER:
			History.newItem("keyboardDesign", false);
			break;
		}		
	}
}