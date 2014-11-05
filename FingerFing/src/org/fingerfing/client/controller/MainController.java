package org.fingerfing.client.controller;

import org.fingerfing.client.widget.DesignWidgetImpl;
import org.fingerfing.client.widget.MainWidget;
import org.fingerfing.client.widget.TrainWidgetImpl;

public class MainController {

	private MainWidget mainWidget;
	TrainControllerImpl trainController;
	DesignControllerImpl designController;

	public MainController(MainWidget mw) {
		this.mainWidget = mw;
		this.mainWidget.setMainController(this);
		
		TrainWidgetImpl tw = new TrainWidgetImpl();
		DesignWidgetImpl dw = new DesignWidgetImpl();

		this.mainWidget.setTrainWidget(tw);
		this.mainWidget.setDesignWidget(dw);
		
		trainController = new TrainControllerImpl(tw);
		designController = new DesignControllerImpl(dw);
		
	}

	public void switchToTrain() {
		mainWidget.switchToTrain();
	}

	public void onChangeTab(Integer newTabIndex) {
		switch (newTabIndex){
		case 0:
			trainController.setExerciseDescriptor(designController.getExerciseDescriptor());
			trainController.onActive();
			break;
		case 1:
			designController.setExerciseDescriptor(trainController.getExerciseDescriptor());
			designController.onActive();
			break;
		}

	}
}
