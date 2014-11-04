package org.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextArea;

public class DesignWidgetImpl extends Composite {

	private static DesignWidgetImplUiBinder uiBinder = GWT
			.create(DesignWidgetImplUiBinder.class);
	@UiField
	TextArea textArea;

	interface DesignWidgetImplUiBinder extends
			UiBinder<Widget, DesignWidgetImpl> {
	}

	public DesignWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
