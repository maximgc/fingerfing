package org.fingerfing.client.controller;

import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.Exercise;
import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.widget.TrainWidgetImpl;

public class TrainControllerImpl {

	private TrainWidgetImpl trainWidget;
	private Exercise exercise;

	public TrainControllerImpl(TrainWidgetImpl trainWidget) {
		this.trainWidget = trainWidget;
		this.trainWidget.setTrainController(this);
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exercise != null ? exercise.getExerciseDescriptor() : null;
	}

	public void setExerciseDescriptor(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor != null) {
			exercise = new Exercise(exerciseDescriptor);
		}
	}

	public void onActive() {
		if (exercise != null) {
			trainWidget.showSequence(exercise.getSequence());
			trainWidget.showCurElement(exercise.getCurElement());
		}
	}

	public void onKeyInput(int nativeKeyCode) {
		if (exercise.getCurElement() != null) {
			exercise.makeAttempt(NativeKey.getByNativeCode(nativeKeyCode));
			trainWidget.showLastAttempt(exercise.getLastAttempt());
			trainWidget.showCurElement(exercise.getCurElement());
		}
	}

}
