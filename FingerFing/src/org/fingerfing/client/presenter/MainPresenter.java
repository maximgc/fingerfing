package org.fingerfing.client.presenter;

import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.widget.CourseDesignerView;
import org.fingerfing.client.widget.MainView;
import org.fingerfing.client.widget.TrainView;

public class MainPresenter {

	private MainView mainWidget;
	private TrainPresenter trainController;
	private CourseDesignerPresenter designController;
	private ExerciseDescriptorLoader edLoader;
	private ExerciseDescriptor currentEd;

	public MainPresenter(MainView mw) {
		this.mainWidget = mw;
		this.mainWidget.setMainController(this);

		TrainView tw = new TrainView();
		CourseDesignerView dw = new CourseDesignerView();

		this.mainWidget.setTrainWidget(tw);
		this.mainWidget.setDesignWidget(dw);

		this.trainController = new TrainPresenter(tw);
		this.designController = new CourseDesignerPresenter(dw);
		
		this.trainController.setMainController(this);
		this.designController.setMainController(this);
		

		this.edLoader = new ExerciseDescriptorLoader();
	}

	public void start() {
		currentEd = edLoader.loadExerciseDescriptor(0);
		mainWidget.setExerciseList(edLoader.getDescriptorNameList());
		mainWidget.switchToTab(2);
	}

	public void onChangeTab(Integer newTabIndex) {
		switch (newTabIndex) {
		case 0:
			trainController.startNewExercise(currentEd);
			break;
		case 1:
			designController.setExerciseDescriptor(currentEd);
			break;
		}

	}

	public void changeExercise(int index) {
		mainWidget.setExerciseListSelected(index);
	}

	public void onChangeExercise(int index) {
		if (index != -1) {
			currentEd = edLoader.loadExerciseDescriptor(index);
			trainController.startNewExercise(currentEd);
			designController.setExerciseDescriptor(currentEd);
		}
	}
}
