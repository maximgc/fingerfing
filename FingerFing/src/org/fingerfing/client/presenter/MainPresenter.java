package org.fingerfing.client.presenter;

import org.fingerfing.client.domain.ExerciseDescriptor;
import org.fingerfing.client.widget.MainView;

public class MainPresenter {

	private MainView mainView;
	private TrainPresenter trainPresenter;
	private CourseDesignerPresenter designPresenter;
	
	private ExerciseDescriptorLoader edLoader;
	private ExerciseDescriptor currentEd;

	public MainPresenter(MainView mw) {
		this.mainView = mw;
		this.mainView.setMainController(this);

		this.trainPresenter = new TrainPresenter(mainView.getTrainView());
		this.designPresenter = new CourseDesignerPresenter(mainView.getCourseDesignerView());
		
		this.trainPresenter.setMainPresenter(this);
		this.designPresenter.setMainPresenter(this);
		

		this.edLoader = new ExerciseDescriptorLoader();
	}

	public void start() {
		currentEd = edLoader.loadExerciseDescriptor(0);
		mainView.setExerciseList(edLoader.getDescriptorNameList());
		mainView.switchToTab(2);
	}

	public void onChangeTab(Integer newTabIndex) {
		switch (newTabIndex) {
		case 0:
			trainPresenter.startNewExercise(currentEd);
			break;
		case 1:
			designPresenter.setExerciseDescriptor(currentEd);
			break;
		}

	}

	public void changeExercise(int index) {
		mainView.setExerciseListSelected(index);
	}

	public void onChangeExercise(int index) {
		if (index != -1) {
			currentEd = edLoader.loadExerciseDescriptor(index);
			trainPresenter.startNewExercise(currentEd);
			designPresenter.setExerciseDescriptor(currentEd);
		}
	}
}
