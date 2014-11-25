package org.fingerfing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import org.fingerfing.client.Settings;
import org.fingerfing.client.domain.ExerciseDescriptor;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ActionChangeHandler;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeHandler;
import org.fingerfing.client.presenter.event.ExerciseDescriptorModifyEvent;
import org.fingerfing.client.view.CourseDesignerView;

import com.google.gwt.event.shared.EventBus;

public class CourseDesignerPresenter implements ActionChangeHandler,
		ExerciseDescriptorChangeHandler {

	private class DescriptorMaker {

		private List<Key> keySeq;

		public DescriptorMaker() {
			if (exerciseDescriptor == null) {
				exerciseDescriptor = dm.create(ExerciseDescriptor.class);
			}
			if (exerciseDescriptor.getSequence() == null) {
				keySeq = new ArrayList<Key>();
				exerciseDescriptor.setSequence(keySeq);
			} else {
				keySeq = exerciseDescriptor.getSequence();
			}
		}

		private void addKey(Key key) {
			keySeq.add(key);
		}

	}

	private CourseDesignerView designWidget;
	private DescriptorManager dm = new DescriptorManager();

	private ExerciseDescriptor exerciseDescriptor;
	private DescriptorMaker descriptorMaker;
	private EventBus eventBus;

	public CourseDesignerPresenter(CourseDesignerView designWidget,
			EventBus eventBus) {
		this.designWidget = designWidget;
		this.eventBus = eventBus;
		this.designWidget.setDesignController(this);
		descriptorMaker = new DescriptorMaker();
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

	public void onKeyInput(int nativeKeyCode) {
		eventBus.fireEventFromSource(new ExerciseDescriptorModifyEvent(), this);
		NativeKey nk = NativeKey.getByNativeCode(nativeKeyCode);
		Key key = nk.getKeys()[0];
		descriptorMaker.addKey(key);
		showExerciseDescriptor();
	}

	private void showExerciseDescriptor() {
		designWidget.showExercise(exerciseDescriptor);
		designWidget.showJson(dm.encodeToJson(exerciseDescriptor));
	}

	@Override
	public void onExerciseDescriptorChange(ExerciseDescriptorChangeEvent event) {
		exerciseDescriptor = Settings.exerciseDescriptor;
		if (exerciseDescriptor == null) {
			throw new PresenterLevelException("ExerciseDescriptor is null");
		}
		descriptorMaker = new DescriptorMaker();
		showExerciseDescriptor();
	}

	@Override
	public void onActionChange(ActionChangeEvent event) {
		if (event.getAction() == Action.COURSE_DESIGNER) {
			exerciseDescriptor = Settings.exerciseDescriptor;
			if (exerciseDescriptor == null) {
				throw new PresenterLevelException("ExerciseDescriptor is null");
			}
			descriptorMaker = new DescriptorMaker();
			showExerciseDescriptor();
		}
	}

}
