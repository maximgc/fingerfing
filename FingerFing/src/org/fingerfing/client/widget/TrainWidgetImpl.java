package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.controller.TrainControllerImpl;
import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.Key;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;

/**
 * Widget for demonstrate exercise.
 * 
 * @author Max
 */
public class TrainWidgetImpl extends Composite {

	interface TrainWidgetImplUiBinder extends UiBinder<Widget, TrainWidgetImpl> {
	}

	private static TrainWidgetImplUiBinder uiBinder = GWT
			.create(TrainWidgetImplUiBinder.class);

	@UiField
	ExerciseWidget trace;
	@UiField
	ExerciseWidget keyboard;

	private TrainControllerImpl trainController;

	public TrainWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setTrainController(TrainControllerImpl trainController) {
		this.trainController = trainController;
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

	@UiHandler("trace")
	void onTraceKeyDown(KeyDownEvent event) {
		trainController.onKeyInput(event.getNativeKeyCode());
	}

}
