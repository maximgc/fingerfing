package org.fingerfing.client.controller;

import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Exercise;
import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.widget.TrainWidgetImpl;

public class TrainControllerImpl {

	private TrainWidgetImpl trainWidget;
	private Exercise exercise;
	
	@SuppressWarnings("unused") //WARN unused
	private MainController mainController;

	public TrainControllerImpl(TrainWidgetImpl trainWidget) {
		this.trainWidget = trainWidget;
		this.trainWidget.setTrainController(this);
	}

	public void startNewExercise(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor != null) {
			exercise = new Exercise(exerciseDescriptor);
			startExercise();
		}
	}

	private void startExercise() {
		if (exercise != null) {
			trainWidget.showSequence(exercise.getSequence());
			startElement();
		}
	}

	private void startElement() {
		if (exercise.hasCurrentElement()) {
			trainWidget.showCurrentElement(exercise.getCurrentElement());
		}
	}

	public void onKeyInput(int nativeKeyCode) {
		if (!exercise.isComplete()) {
			Attempt lastAttempt = exercise.makeElementAttempt(NativeKey
					.getByNativeCode(nativeKeyCode));
			trainWidget.showAttempt(lastAttempt);
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

}
