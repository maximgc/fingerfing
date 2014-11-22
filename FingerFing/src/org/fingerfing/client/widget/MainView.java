package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.presenter.MainPresenter;

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

public class MainView extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT
			.create(MainWidgetUiBinder.class);

	@UiField
	VerticalPanel pTrain;
	@UiField
	VerticalPanel pCourseDesigner;
	@UiField
	VerticalPanel pKeyboardDesigner;
	
	@UiField
	TabPanel tabPanel;
	@UiField
	ListBox exerciseSelector;

	private MainPresenter mainController;

	interface MainWidgetUiBinder extends UiBinder<Widget, MainView> {
	}

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setTrainWidget(Widget w) {
		pTrain.add(w);
	}

	public void setDesignWidget(Widget w) {
		pCourseDesigner.add(w);
	}

	public void setMainController(MainPresenter mainController) {
		this.mainController = mainController;
	}

	@UiHandler("tabPanel")
	void onTabPanelSelection(SelectionEvent<Integer> event) {
		mainController.onChangeTab(event.getSelectedItem());
	}

	public void switchToTab(int i) {
		tabPanel.selectTab(i);
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
