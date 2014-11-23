package org.fingerfing.client.presenter;

import org.fingerfing.client.Settings;
import org.fingerfing.client.domain.Exercise;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEventHandler;
import org.fingerfing.client.view.TrainView;
import org.fingerfing.client.widget.event.NativeKeyInputEvent;
import org.fingerfing.client.widget.event.NativeKeyInputHandler;

public class TrainPresenter implements ExerciseDescriptorChangeEventHandler{

	private TrainView trainWidget;
	private Exercise exercise;

	public TrainPresenter(TrainView trainWidget) {
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

	public void start() {
//			exercise = new Exercise(Settings.exerciseDescriptor);
//			startExercise();
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

}
