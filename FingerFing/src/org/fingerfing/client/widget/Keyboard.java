package org.fingerfing.client.widget;

import java.util.Map;

import org.fingerfing.client.core.Key;

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

	private Map<Key, ? extends Widget> keyWidgetMap;

	interface KeyboardUiBinder extends UiBinder<Widget, Keyboard> {
	}

	public Keyboard() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setDescriptor(KeyboardDescriptor keyboardDescriptor,
			KeyboardLabelDescriptor generalLabelDescriptor,
			KeyboardLabelDescriptor alternativeLabelDescriptor) {
		KeyboardBuilder keyBuilder = new KeyboardBuilder(keyArea);
		keyWidgetMap = keyBuilder.build(keyboardDescriptor);
		keyBuilder.buildGeneralLabel(generalLabelDescriptor);
		keyBuilder.buildAlternativeLabel(alternativeLabelDescriptor);
	}

}
