package org.fingerfing.client.view;

import org.fingerfing.client.domain.Finger;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.view.event.KeyInputEvent;
import org.fingerfing.client.view.event.KeyInputHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;

public class KeyboardDesignerView extends Composite {

	private static KeyboardDesignerWidgetUiBinder uiBinder = GWT
			.create(KeyboardDesignerWidgetUiBinder.class);

	interface KeyboardDesignerWidgetUiBinder extends
			UiBinder<Widget, KeyboardDesignerView> {
	}

	public KeyboardDesignerView() {
		initWidget(uiBinder.createAndBindUi(this));
		keyboardBuilder = new KeyboardConstructor();
		keyboard.setKeyboardBuilder(keyboardBuilder);
		for (Finger f : Finger.values()) {
			fingerList.addItem(f.toString());
		}
		keyboard.addKeyInputHandler(new KeyInputHandler() {

			@Override
			public void onKeyInput(KeyInputEvent event) {
				Key key = event.getKey();
				keyboard.getKeyWidget(key).showFinger(curFinger);
			}
		});
	}

	@UiField
	ListBox fingerList;
	@UiField
	KeyboardWidget keyboard;
	@UiField
	Button nextFinger;

	private Finger curFinger = Finger.LEFT_THUMB;
	private KeyboardConstructor keyboardBuilder;

	@UiHandler("fingerList")
	void onFingerListChange(ChangeEvent event) {
		curFinger = Finger.valueOf(fingerList.getValue(fingerList
				.getSelectedIndex()));
	}

	@UiHandler("nextFinger")
	void onNextFingerClick(ClickEvent event) {
		fingerList.setSelectedIndex(fingerList.getSelectedIndex() + 1);
	}
}
