package org.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainWidget extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT
			.create(MainWidgetUiBinder.class);
	
	@UiField VerticalPanel pTrain;
	@UiField VerticalPanel pDesign;
	//WARN
	@UiField
	public TabPanel tabPanel;
	
	Widget trainWidget;
	Widget designWidget;

	interface MainWidgetUiBinder extends UiBinder<Widget, MainWidget> {
	}

	public MainWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		tabPanel.selectTab(0);
	}
	
	public void setTrainWidget(Widget w) {
		trainWidget = w;
		pTrain.add(trainWidget);
	}
	
	public void setDesignWidget(Widget w) {
		designWidget = w;
		pDesign.add(designWidget);
	}

	public Widget getTrainWidget() {
		return trainWidget;
	}

	public Widget getDesignWidget() {
		return designWidget;
	}
	
	

}
