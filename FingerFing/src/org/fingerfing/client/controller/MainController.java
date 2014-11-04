package org.fingerfing.client.controller;

import org.fingerfing.client.widget.DesignWidgetImpl;
import org.fingerfing.client.widget.MainWidget;
import org.fingerfing.client.widget.TrainWidgetImpl;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;

public class MainController {

	private MainWidget mainWidget;
	TrainControllerImpl trainController;
	DesignControllerImpl designController;

	public MainController(MainWidget mw) {
		this.mainWidget = mw;
		
		mw.setTrainWidget(new TrainWidgetImpl());
		mw.setDesignWidget(new DesignWidgetImpl());
		
		trainController = new TrainControllerImpl(mw.getTrainWidget());
		designController = new DesignControllerImpl(mw.getDesignWidget());
		
		mainWidget.tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				switch (event.getSelectedItem()){
				case 0:
					trainController.setExerciseDescriptor(designController.getExerciseDescriptor());
					break;
				case 1:
					break;
				}
				
			}
		});
	}

}
