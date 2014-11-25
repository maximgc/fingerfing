package org.fingerfing.client.presenter;

import org.fingerfing.client.Settings;
import org.fingerfing.client.domain.Exercise;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ActionChangeHandler;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeHandler;
import org.fingerfing.client.presenter.event.KeyboardDescriptorChangeEvent;
import org.fingerfing.client.presenter.event.KeyboardDescriptorChangeHandler;
import org.fingerfing.client.view.TrainView;
import org.fingerfing.client.view.event.NativeKeyInputEvent;
import org.fingerfing.client.view.event.NativeKeyInputHandler;

import com.google.gwt.event.shared.EventBus;

public class TrainPresenter implements ExerciseDescriptorChangeHandler,
		ActionChangeHandler, KeyboardDescriptorChangeHandler {

	private TrainView trainWidget;
	private Exercise exercise;

	// private EventBus eventBus;

	public TrainPresenter(TrainView trainWidget, EventBus eventBus) {
		this.trainWidget = trainWidget;
		// this.eventBus = eventBus;
		trainWidget.addNativeKeyInputHandler(new NativeKeyInputHandler() {
			@Override
			public void onNativeKeyInput(NativeKeyInputEvent event) {
				onKeyInput(event.getNativeKey());
			}
		});
	}

	public void onKeyInput(NativeKey nativeKey) {
		if (!exercise.isComplete()) {
			trainWidget.showAttempt(exercise.makeAttempt(nativeKey));
			startElement();
		}
		if (exercise.isComplete()) {
			startExercise();
		}
	}

	private void startElement() {
		if (!exercise.isComplete()) {
			trainWidget.showCurrentElement(exercise.getCurrentElement());
		}
	}

	private void startExercise() {
		exercise = new Exercise(Settings.exerciseDescriptor);
		if (exercise != null) {
			trainWidget.showSequence(exercise.getSequence());
			startElement();
		}
	}

	@Override
	public void onExerciseDescriptorChange(ExerciseDescriptorChangeEvent event) {
		startExercise();
	}

	@Override
	public void onActionChange(ActionChangeEvent event) {
		if (event.getAction() == Action.TRAIN) {
			startExercise();
		}
	}

	@Override
	public void onKeyboardDescriptorChange(KeyboardDescriptorChangeEvent event) {
		trainWidget.setKeyboardDescriptor(Settings.keyboardDescriptor);
	}

}
