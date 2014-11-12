package org.fingerfing.client.controller;

import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Exercise;
import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.widget.TrainWidgetImpl;

public class TrainControllerImpl {

	private TrainWidgetImpl trainWidget;
	private Exercise exercise;
	private MainController mainController;

	public TrainControllerImpl(TrainWidgetImpl trainWidget) {
		this.trainWidget = trainWidget;
		this.trainWidget.setTrainController(this);
	}

	public void startNewExercise(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor != null) {
			exercise = new Exercise(exerciseDescriptor);
		}
		startExercise();
	}

	private void startExercise() {
		if (exercise != null) {
			trainWidget.showSequence(exercise.getSequence());
			if (exercise.hasCurrentElement()) {
				trainWidget.showCurElement(exercise.getCurrentElement()
						.getPos(), true);
			}
		}
	}

	public void onKeyInput(int nativeKeyCode) {
		if (exercise.hasCurrentElement()) {
			Attempt lastAttempt = exercise.makeElementAttempt(NativeKey
					.getByNativeCode(nativeKeyCode));
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

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

}
