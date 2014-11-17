package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.Key;
import org.fingerfing.client.widget.event.ElementInputHandler;
import org.fingerfing.client.widget.event.HasElementInputHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget for demonstrate exercise.
 * 
 * @author Max
 */
public class TrainWidgetImpl extends Composite implements HasElementInputHandler {

	interface TrainWidgetImplUiBinder extends UiBinder<Widget, TrainWidgetImpl> {
	}

	private static TrainWidgetImplUiBinder uiBinder = GWT
			.create(TrainWidgetImplUiBinder.class);

	@UiField
	ExerciseWidget trace;
	@UiField
	ExerciseWidget keyboard;

	public TrainWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void showCurrentElement(Element element) {
		trace.showCurrentElement(element);
		keyboard.showCurrentElement(element);
	}

	public void showAttempt(Attempt attempt) {
		trace.showAttempt(attempt);
		keyboard.showAttempt(attempt);
	}

	public void showSequence(List<Key> sequence) {
		trace.showSequence(sequence);
		keyboard.showSequence(sequence);
	}

	@Override
	public void setElementInputHandler(ElementInputHandler handler) {
		trace.setElementInputHandler(handler);
		keyboard.setElementInputHandler(handler);
	}
	
}
