package org.fingerfing.client.controller;

import org.fingerfing.client.core.ExerciseDescriptor;

import com.google.gwt.user.client.ui.Widget;

public class DesignControllerImpl {

	private Widget designWidget;
	private ExerciseDescriptor exerciseDescriptor;

	public DesignControllerImpl(Widget designWidget) {
		this.designWidget = designWidget;
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

}
