package org.fingerfing.client.controller;

import org.fingerfing.client.core.ExerciseDescriptor;

import com.google.gwt.user.client.ui.Widget;

public class TrainControllerImpl {

	private Widget trainWidget;
	private ExerciseDescriptor exerciseDescriptor;

	public void setExerciseDescriptor(ExerciseDescriptor exerciseDescriptor) {
		this.exerciseDescriptor = exerciseDescriptor;
	}

	public TrainControllerImpl(Widget trainWidget) {
		this.trainWidget = trainWidget;
	}

}
