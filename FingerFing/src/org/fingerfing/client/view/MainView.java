package org.fingerfing.client.view;

import org.fingerfing.client.presenter.MainPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.CaptionPanel;

public class MainView extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT
			.create(MainWidgetUiBinder.class);

	@UiField
	TabPanel tabPanel;
	
	@UiField
	CourseDesignerView courseDesignerView;
	@UiField
	TrainView trainView;
	@UiField
	KeyboardDesignerView keyboardDesignerView;

	public CourseDesignerView getCourseDesignerView() {
		return courseDesignerView;
	}

	public TrainView getTrainView() {
		return trainView;
	}

	public KeyboardDesignerView getKeyboardDesignerView() {
		return keyboardDesignerView;
	}

	private MainPresenter mainPresenter;

	interface MainWidgetUiBinder extends UiBinder<Widget, MainView> {
	}

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setMainController(MainPresenter mainController) {
		this.mainPresenter = mainController;
	}

	@UiHandler("tabPanel")
	void onTabPanelSelection(SelectionEvent<Integer> event) {
		mainPresenter.onChangeTab(event.getSelectedItem());
	}

	public void switchToTab(int i) {
		tabPanel.selectTab(i);
	}
	
}
