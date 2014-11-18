package org.fingerfing.client.widget;

import org.fingerfing.client.core.Finger;
import org.fingerfing.client.core.Key;
import org.fingerfing.client.widget.event.KeyInputEvent;
import org.fingerfing.client.widget.event.KeyInputHandler;

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

public class KeyboardDesignerWidget extends Composite {

	private static KeyboardDesignerWidgetUiBinder uiBinder = GWT
			.create(KeyboardDesignerWidgetUiBinder.class);

	interface KeyboardDesignerWidgetUiBinder extends
			UiBinder<Widget, KeyboardDesignerWidget> {
	}

	public KeyboardDesignerWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		for (Finger f : Finger.values()){
			fingerList.addItem(f.toString());
		}
		keyboard.addKeyInputHandler(new KeyInputHandler() {
			
			@Override
			public void onKeyInput(KeyInputEvent event) {
				Key key = event.getKey();
				keyboard.getKeyWidget(key).showFinger(curFinger);
				
				System.out.println(key.toString() + " - " + curFinger.toString());
			}
		});
	}

	@UiField
	ListBox fingerList;
	@UiField
	KeyboardWidget keyboard;
	@UiField Button nextFinger;

	Finger curFinger = Finger.LEFT_THUMB;

	@UiHandler("fingerList")
	void onFingerListChange(ChangeEvent event) {
		curFinger = Finger.valueOf(fingerList.getValue(fingerList.getSelectedIndex()));
	}
	
	@UiHandler("nextFinger")
	void onNextFingerClick(ClickEvent event) {
		fingerList.setSelectedIndex(fingerList.getSelectedIndex()+1);
	}
}
