package org.fingerfing.client.presenter;

import org.fingerfing.client.Settings;
import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ActionChangeHandler;
import org.fingerfing.client.presenter.event.KeyboardDescriptorsChangeEvent;
import org.fingerfing.client.presenter.event.KeyboardDescriptorsChangeHandler;
import org.fingerfing.client.view.KeyboardDesignerView;

import com.google.gwt.event.shared.EventBus;

public class KeyboardDesignerPresenter implements ActionChangeHandler, KeyboardDescriptorsChangeHandler{

	private EventBus eventBus;
	private KeyboardDesignerView view;

	public KeyboardDesignerPresenter(KeyboardDesignerView view,
			EventBus eventBus) {
		this.eventBus = eventBus;
		this.view = view;
		view.setPresenter(this);
	}

	
	
	
	@Override
	public void onActionChange(ActionChangeEvent event) {
		view.setKeyboardDescriptor(Settings.keyboardDescriptor,
				Settings.keyboardGeneralLabelDescriptor,
				Settings.keyboardAlternativeLabelDescriptor);
	}

	@Override
	public void onKeyboardDescriptorsChange(KeyboardDescriptorsChangeEvent event) {
		view.setKeyboardDescriptor(Settings.keyboardDescriptor,
				Settings.keyboardGeneralLabelDescriptor,
				Settings.keyboardAlternativeLabelDescriptor);
	}

}
