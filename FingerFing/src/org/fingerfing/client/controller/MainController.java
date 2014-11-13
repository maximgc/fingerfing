package org.fingerfing.client.controller;

import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.widget.DesignWidgetImpl;
import org.fingerfing.client.widget.MainWidget;
import org.fingerfing.client.widget.TrainWidgetImpl;

public class MainController {

	private MainWidget mainWidget;
	private TrainControllerImpl trainController;
	private DesignControllerImpl designController;
	private ExerciseDescriptorLoader edLoader;
	private ExerciseDescriptor currentEd;

	public MainController(MainWidget mw) {
		this.mainWidget = mw;
		this.mainWidget.setMainController(this);

		TrainWidgetImpl tw = new TrainWidgetImpl();
		DesignWidgetImpl dw = new DesignWidgetImpl();

		this.mainWidget.setTrainWidget(tw);
		this.mainWidget.setDesignWidget(dw);

		this.trainController = new TrainControllerImpl(tw);
		this.designController = new DesignControllerImpl(dw);
		
		this.trainController.setMainController(this);
		this.designController.setMainController(this);
		

		this.edLoader = new ExerciseDescriptorLoader();
	}

	public void start() {
		currentEd = edLoader.loadExerciseDescriptor(0);
		mainWidget.setExerciseList(edLoader.getDescriptorNameList());
		mainWidget.switchToTrain();
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
