package org.fingerfing.client.widget;

import java.util.Map;

import org.fingerfing.client.core.NativeKey;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class Keyboard extends Composite {

	private static KeyboardUiBinder uiBinder = GWT
			.create(KeyboardUiBinder.class);

	@UiField
	AbsolutePanel keyArea;

	private Map<NativeKey, ? extends Widget> keyMap;

	interface KeyboardUiBinder extends UiBinder<Widget, Keyboard> {
	}

	public Keyboard() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setDescriptor(KeyboardDescriptor keyboardDescriptor,
			KeyboardLabelDescriptor generalLabelDescriptor,
			KeyboardLabelDescriptor alternativeLabelDescriptor) {
		KeyboardBuilder keyBuilder = new KeyboardBuilder(keyArea);
		keyMap = keyBuilder.build(keyboardDescriptor);
		keyBuilder.buildGeneralLabel(generalLabelDescriptor);
		keyBuilder.buildAlternativeLabel(alternativeLabelDescriptor);
	}

}
