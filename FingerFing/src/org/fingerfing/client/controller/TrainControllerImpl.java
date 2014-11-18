package org.fingerfing.client.controller;

import org.fingerfing.client.core.Exercise;
import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.widget.TrainWidgetImpl;
import org.fingerfing.client.widget.event.NativeKeyInputEvent;
import org.fingerfing.client.widget.event.NativeKeyInputHandler;

public class TrainControllerImpl {

	private TrainWidgetImpl trainWidget;
	private Exercise exercise;

	@SuppressWarnings("unused")
	// WARN unused
	private MainController mainController;

	public TrainControllerImpl(TrainWidgetImpl trainWidget) {
		this.trainWidget = trainWidget;
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

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void startNewExercise(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor != null) {
			exercise = new Exercise(exerciseDescriptor);
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

}
