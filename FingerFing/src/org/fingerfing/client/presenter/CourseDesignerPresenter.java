package org.fingerfing.client.presenter;

import java.util.ArrayList;
import java.util.List;

import org.fingerfing.client.Settings;
import org.fingerfing.client.domain.ExerciseDescriptor;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.view.CourseDesignerView;

public class CourseDesignerPresenter {

	private class DescriptorMaker{
		
		private List<Key> keySeq;
		
		public DescriptorMaker() {
			if (exerciseDescriptor == null){
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

	public CourseDesignerPresenter(CourseDesignerView designWidget) {
		this.designWidget = designWidget;
		this.designWidget.setDesignController(this);
		descriptorMaker = new DescriptorMaker();
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

	public void start() {
		exerciseDescriptor = Settings.exerciseDescriptor;
		if (exerciseDescriptor == null) {
			throw new ClientException("ExerciseDescriptor is null");
		}
		descriptorMaker = new DescriptorMaker();
		showExerciseDescriptor();
	}
	
	public void onKeyInput(int nativeKeyCode) {
		NativeKey nk = NativeKey.getByNativeCode(nativeKeyCode);
		Key key = nk.getKeys()[0];
		descriptorMaker.addKey(key);
		showExerciseDescriptor();
	}

	private void showExerciseDescriptor() {
		designWidget.showExercise(exerciseDescriptor);
		designWidget.showJson(dm.encodeToJson(exerciseDescriptor));
	}

}
