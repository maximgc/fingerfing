package org.fingerfing.client.presenter;

import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ActionChangeEventHandler;
import org.fingerfing.client.view.MainView;

import com.google.gwt.user.client.History;

public class MainPresenter implements ActionChangeEventHandler {

	private MainView mainView;

	public MainPresenter(MainView mw) {
		this.mainView = mw;
		this.mainView.setPresenter(this);
	}

	public void switchTab(int tab) {
		mainView.switchTab(tab);
	}

	public void onSelectTab(Integer newTabIndex) {
		switch (newTabIndex) {
		case 0:
			History.newItem("train");
			break;
		case 1:
			History.newItem("courseDesign");
			break;
		case 2:
			History.newItem("keyboardDesign");
			break;
		}
	}

	@Override
	public void onActionChange(ActionChangeEvent event) {
		switch (event.getAction()) {
		case TRAIN:
			mainView.switchTab(0);
			break;
		case COURSE_DESIGNER:
			mainView.switchTab(1);
			break;
		case KEYBOARD_DESIGNER:
			mainView.switchTab(2);
			break;
		default:
			break;
		}
	}

}
