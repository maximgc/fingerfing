package org.fingerfing.client.view;

import java.util.List;

import org.fingerfing.client.domain.Attempt;
import org.fingerfing.client.domain.Element;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.presenter.TrainPresenter;
import org.fingerfing.client.view.widget.ExerciseWidget;
import org.fingerfing.client.view.widget.KeyboardBuilderSimple;
import org.fingerfing.client.view.widget.KeyboardWidget;
import org.fingerfing.client.view.widget.event.NativeKeyInputEvent;
import org.fingerfing.client.view.widget.event.NativeKeyInputHandler;

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
public class TrainView extends Composite {

	private class NativeKeyInputHandlerImpl implements NativeKeyInputHandler {
		@Override
		public void onNativeKeyInput(NativeKeyInputEvent event) {
			presenter.onKeyInput(event.getNativeKey());
		}
	}

	interface TrainWidgetImplUiBinder extends UiBinder<Widget, TrainView> {
	}

	private static TrainWidgetImplUiBinder uiBinder = GWT
			.create(TrainWidgetImplUiBinder.class);

	@UiField
	ExerciseWidget trace;
	@UiField
	KeyboardWidget keyboard;

	private TrainPresenter presenter;

	public TrainView() {
		initWidget(uiBinder.createAndBindUi(this));
		keyboard.setKeyboardBuilder(new KeyboardBuilderSimple());
		
		NativeKeyInputHandler handler = new NativeKeyInputHandlerImpl();
		trace.addNativeKeyInputHandler(handler);
		keyboard.addNativeKeyInputHandler(handler);
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
		keyboard.setFocus(true);
	}

	public void setKeyboardDescriptor(KeyboardDescriptor keyboardDescriptor) {
		keyboard.setKeyboardDescriptor(keyboardDescriptor);
	}

	public void setKeyboardGeneralLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		keyboard.setGeneralLabelDescriptor(labelDescriptor);
	}

	public void setKeyboardAlternativeLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		keyboard.setAlternativeLabelDescriptor(labelDescriptor);
	}

	public void setKeyboardDescriptor(KeyboardDescriptor keyboardDescriptor,
			KeyboardLabelDescriptor keyboardGeneralLabelDescriptor,
			KeyboardLabelDescriptor keyboardAlternativeLabelDescriptor) {
		keyboard.setKeyboardDescriptor(keyboardDescriptor,
				keyboardGeneralLabelDescriptor,
				keyboardAlternativeLabelDescriptor);
	}

	public void setPresenter(TrainPresenter presenter) {
		this.presenter = presenter;
	}

}
