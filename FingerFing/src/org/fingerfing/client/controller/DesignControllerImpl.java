package org.fingerfing.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.widget.DesignWidgetImpl;

public class DesignControllerImpl {

	private class DescriptorMaker{
		
		private List<NativeKey> keySeq;
		
		public DescriptorMaker() {
			if (exerciseDescriptor == null){
				exerciseDescriptor = dm.create(ExerciseDescriptor.class);
			}
			if (exerciseDescriptor.getSequence() == null) {
				keySeq = new ArrayList<NativeKey>();
				exerciseDescriptor.setSequence(keySeq);
			} else {
				keySeq = exerciseDescriptor.getSequence();
			}
		}
		
		private void addKey(NativeKey nativeKey) {
			keySeq.add(nativeKey);
		}
		
	}
	
	private DesignWidgetImpl designWidget;
	private DescriptorManager dm = new DescriptorManager();

	private ExerciseDescriptor exerciseDescriptor;
	private DescriptorMaker descriptorMaker;
	private MainController mainController;

	public DesignControllerImpl(DesignWidgetImpl designWidget) {
		this.designWidget = designWidget;
		this.designWidget.setDesignController(this);
		descriptorMaker = new DescriptorMaker();
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

	public void setExerciseDescriptor(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor == null) {
			throw new ClientException("ExerciseDescriptor is null");
		}
		this.exerciseDescriptor = exerciseDescriptor;
		descriptorMaker = new DescriptorMaker();
		showExerciseDescriptor();
	}
	
	public void onKeyInput(int nativeKeyCode) {
		mainController.changeExercise(-1);
		NativeKey nk = NativeKey.getByNativeCode(nativeKeyCode);
		descriptorMaker.addKey(nk);
		showExerciseDescriptor();
	}

	private void showExerciseDescriptor() {
		designWidget.showExercise(exerciseDescriptor);
		designWidget.showJson(dm.encodeToJson(exerciseDescriptor));
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

}
