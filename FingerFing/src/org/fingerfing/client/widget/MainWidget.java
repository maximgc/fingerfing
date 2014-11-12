package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.controller.MainController;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.event.dom.client.ChangeEvent;

public class MainWidget extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT
			.create(MainWidgetUiBinder.class);

	@UiField
	VerticalPanel pTrain;
	@UiField
	VerticalPanel pDesign;
	@UiField
	public TabPanel tabPanel;
	@UiField
	ListBox exerciseSelector;

	private MainController mainController;

	interface MainWidgetUiBinder extends UiBinder<Widget, MainWidget> {
	}

	public MainWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setTrainWidget(Widget w) {
		pTrain.add(w);
	}

	public void setDesignWidget(Widget w) {
		pDesign.add(w);
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	@UiHandler("tabPanel")
	void onTabPanelSelection(SelectionEvent<Integer> event) {
		mainController.onChangeTab(event.getSelectedItem());
	}

	public void switchToTrain() {
		tabPanel.selectTab(0);
	}
	
	public void setExerciseList(List<String> nameList) {
		for (String s : nameList){
			exerciseSelector.addItem(s);
		}
	}
	
	public void setExerciseListSelected(int index){
		exerciseSelector.setSelectedIndex(index);
	}
	
	@UiHandler("exerciseSelector")
	void onExerciseSelectorChange(ChangeEvent event) {
		mainController.onChangeExercise(exerciseSelector.getSelectedIndex());
	}
}
