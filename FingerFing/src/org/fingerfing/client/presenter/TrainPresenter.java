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

	private TrainView trainView;
	private Exercise exercise;

	public TrainPresenter(TrainView trainView, EventBus eventBus) {
		this.trainView = trainView;
		this.trainView.addNativeKeyInputHandler(new NativeKeyInputHandler() {
			@Override
			public void onNativeKeyInput(NativeKeyInputEvent event) {
				onKeyInput(event.getNativeKey());
			}
		});
	}

	public void onKeyInput(NativeKey nativeKey) {
		if (!exercise.isComplete()) {
			trainView.showAttempt(exercise.makeAttempt(nativeKey));
			startElement();
		}
		if (exercise.isComplete()) {
			startExercise();
		}
	}

	private void startElement() {
		if (!exercise.isComplete()) {
			trainView.showCurrentElement(exercise.getCurrentElement());
		}
	}

	private void startExercise() {
		exercise = new Exercise(Settings.exerciseDescriptor);
		if (exercise != null) {
			trainView.showSequence(exercise.getSequence());
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
		trainView.setKeyboardDescriptor(Settings.keyboardDescriptor);
		trainView.setKeyboardGeneralLabelDescriptor(Settings.keyboardGeneralLabelDescriptor);
		trainView.setKeyboardAlternativeLabelDescriptor(Settings.keyboardAlternativeLabelDescriptor);
	}

}
