package org.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget for demonstrate exercise.
 * 
 * 
 * @author Max
 * 
 */
public class TrainWidgetImpl extends Composite {

	private static TrainWidgetImplUiBinder uiBinder = GWT
			.create(TrainWidgetImplUiBinder.class);

	interface TrainWidgetImplUiBinder extends UiBinder<Widget, TrainWidgetImpl> {
	}

	public TrainWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	public TextArea textArea;

}
