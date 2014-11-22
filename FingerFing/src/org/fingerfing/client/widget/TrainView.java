package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.domain.Attempt;
import org.fingerfing.client.domain.Element;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.widget.event.NativeKeyInputHandler;
import org.fingerfing.client.widget.event.HasNativeKeyInputHandler;

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
public class TrainView extends Composite implements HasNativeKeyInputHandler {

	interface TrainWidgetImplUiBinder extends UiBinder<Widget, TrainView> {
	}

	private static TrainWidgetImplUiBinder uiBinder = GWT
			.create(TrainWidgetImplUiBinder.class);

	@UiField
	ExerciseWidget trace;
	@UiField
	ExerciseWidget keyboard;

	public TrainView() {
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
	public void addNativeKeyInputHandler(NativeKeyInputHandler handler) {
		trace.addNativeKeyInputHandler(handler);
		keyboard.addNativeKeyInputHandler(handler);
	}
	
}
