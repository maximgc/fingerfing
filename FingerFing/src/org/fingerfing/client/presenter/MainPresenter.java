package org.fingerfing.client.presenter;

import org.fingerfing.client.view.MainView;

import com.google.gwt.user.client.History;

public class MainPresenter {

	private MainView mainView;
	

	public MainPresenter(MainView mw) {
		this.mainView = mw;
		this.mainView.setMainController(this);

	}

	public void switchTab(int tab) {
		mainView.switchToTab(tab);
	}

	public void onChangeTab(Integer newTabIndex) {
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

}
