package org.fingerfing.client.presenter;

import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ActionChangeHandler;
import org.fingerfing.client.view.MainView;

import com.google.gwt.event.shared.EventBus;

public class MainPresenter implements ActionChangeHandler {

	private MainView mainView;
	private EventBus eventBus;

	public MainPresenter(MainView mw, EventBus eventBus) {
		this.mainView = mw;
		this.mainView.setPresenter(this);
		this.eventBus = eventBus;
	}

	public void switchTab(int tab) {
		mainView.switchTab(tab);
	}

	public void onSelectTab(Integer newTabIndex) {
		Action a;
		switch (newTabIndex) {
		case 1:
			a = Action.COURSE_DESIGNER;
			break;
		case 2:
			a = Action.KEYBOARD_DESIGNER;
			break;
		case 0:
		default:
			a = Action.TRAIN;
			break;
		}
		eventBus.fireEventFromSource(new ActionChangeEvent(a), this);
	}

	@Override
	public void onActionChange(ActionChangeEvent event) {
		if (event.getSource() != this) {
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

}
