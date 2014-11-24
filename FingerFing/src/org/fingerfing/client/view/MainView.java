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
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;

public class MainView extends Composite {

	interface MainWidgetUiBinder extends UiBinder<Widget, MainView> {
	}

	private static MainWidgetUiBinder uiBinder = GWT
			.create(MainWidgetUiBinder.class);
	
	@UiField
	TabPanel tabPanel;
	@UiField
	SettingsView settingsView;
	@UiField
	CourseDesignerView courseDesignerView;
	@UiField
	TrainView trainView;
	@UiField
	KeyboardDesignerView keyboardDesignerView;

	private MainPresenter presenter;

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public CourseDesignerView getCourseDesignerView() {
		return courseDesignerView;
	}

	public KeyboardDesignerView getKeyboardDesignerView() {
		return keyboardDesignerView;
	}

	public SettingsView getSettingsView() {
		return settingsView;
	}

	public TrainView getTrainView() {
		return trainView;
	}

	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
	}

	public void switchTab(int i) {
		tabPanel.selectTab(i);
	}

	@UiHandler("tabPanel")
	void onTabPanelSelection(SelectionEvent<Integer> event) {
		presenter.onSelectTab(event.getSelectedItem());
	}
	
}
