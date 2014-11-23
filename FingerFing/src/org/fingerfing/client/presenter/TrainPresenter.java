package org.fingerfing.client.presenter;

import org.fingerfing.client.Settings;
import org.fingerfing.client.domain.Exercise;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ActionChangeEventHandler;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEventHandler;
import org.fingerfing.client.view.TrainView;
import org.fingerfing.client.view.event.NativeKeyInputEvent;
import org.fingerfing.client.view.event.NativeKeyInputHandler;

import com.google.gwt.event.shared.EventBus;

public class TrainPresenter implements ExerciseDescriptorChangeEventHandler,
		ActionChangeEventHandler {

	private TrainView trainWidget;
	private Exercise exercise;
	private EventBus eventBus;

	public TrainPresenter(TrainView trainWidget, EventBus eventBus) {
		this.trainWidget = trainWidget;
		this.eventBus = eventBus;
		trainWidget.addNativeKeyInputHandler(new NativeKeyInputHandler() {
			@Override
			public void onNativeKeyInput(NativeKeyInputEvent event) {
				onKeyInput(event.getNativeKey().getNativeCode());
			}
		});
	}

	public void onKeyInput(int nativeKeyCode) {
		if (!exercise.isComplete()) {
			NativeKey nk = NativeKey.getByNativeCode(nativeKeyCode);
			trainWidget.showAttempt(exercise.makeAttempt(nk));
			startElement();
		}
		if (exercise.isComplete()) {
			exercise = new Exercise(exercise.getExerciseDescriptor());
			startExercise();
		}
	}

	private void startElement() {
		if (!exercise.isComplete()) {
			trainWidget.showCurrentElement(exercise.getCurrentElement());
		}
	}

	private void startExercise() {
		if (exercise != null) {
			trainWidget.showSequence(exercise.getSequence());
			startElement();
		}
	}

	@Override
	public void onExerciseDescriptorChange(ExerciseDescriptorChangeEvent event) {
		exercise = new Exercise(Settings.exerciseDescriptor);
		startExercise();
	}

	@Override
	public void onActionChange(ActionChangeEvent event) {
		if (event.getAction() == Action.TRAIN) {
			exercise = new Exercise(Settings.exerciseDescriptor);
			startExercise();
		}
	}

}
