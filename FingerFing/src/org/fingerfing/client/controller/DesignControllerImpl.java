package org.fingerfing.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.json.BeanManager;
import org.fingerfing.client.widget.DesignWidgetImpl;

public class DesignControllerImpl {

	private DesignWidgetImpl designWidget;
	private List<NativeKey> keySeq;
	private ExerciseDescriptor exerciseDescriptor;
	private BeanManager bm = new BeanManager();

	public DesignControllerImpl(DesignWidgetImpl designWidget) {
		this.designWidget = designWidget;
		this.designWidget.setDesignController(this);
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

	public void setExerciseDescriptor(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor == null) {
			keySeq = new ArrayList<NativeKey>();
			this.exerciseDescriptor = bm.create(ExerciseDescriptor.class);
			this.exerciseDescriptor.setSequence(keySeq);
		} else {
			this.exerciseDescriptor = exerciseDescriptor;
		}
	}
	
	public void onActive() {
		showEx();
	}

	public void onKeyInput(int nativeKeyCode) {
		NativeKey nk = NativeKey.getByNativeCode(nativeKeyCode);
		keySeq.add(nk);
		showEx();
	}

	private void showEx() {
		designWidget.showExercise(exerciseDescriptor);
		designWidget.showJson(bm.encode(exerciseDescriptor));
	}

}
