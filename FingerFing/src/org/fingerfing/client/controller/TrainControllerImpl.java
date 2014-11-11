package org.fingerfing.client.controller;

import org.fingerfing.client.core.Attempt;
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
		startExercise();
	}

	private void startExercise() {
		if (exercise != null) {
			trainWidget.showSequence(exercise.getSequence());
			if (exercise.hasCurrentElement()){
				trainWidget.showCurElement(exercise.getCurrentElement().getPos(), true);
			}
		}
	}

	public void onKeyInput(int nativeKeyCode) {
		if (exercise.hasCurrentElement()) {
			exercise.makeElementAttempt(NativeKey
					.getByNativeCode(nativeKeyCode));
			Attempt lastAttempt = exercise.getLastAttempt();
			trainWidget.showEnv(lastAttempt.getPos(), lastAttempt.getEval());
			
			if (exercise.hasCurrentElement()) {
				trainWidget.showCurElement(exercise.getCurrentElement()
						.getPos(), true);
			} else {
				trainWidget.showCurElement(lastAttempt.getPos(), false);
				exercise = new Exercise(exercise.getExerciseDescriptor());
				startExercise();
			}
		}
	}

}
