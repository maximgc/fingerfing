package org.fingerfing.client.widget;

import org.fingerfing.client.domain.ExerciseDescriptor;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.presenter.CourseDesignerPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;

public class CourseDesignerView extends Composite {

	private static DesignWidgetImplUiBinder uiBinder = GWT
			.create(DesignWidgetImplUiBinder.class);
	@UiField
	TextArea textArea;
	@UiField
	TextArea jsonArea;
	
	private CourseDesignerPresenter designController;

	interface DesignWidgetImplUiBinder extends
			UiBinder<Widget, CourseDesignerView> {
	}

	public CourseDesignerView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setDesignController(CourseDesignerPresenter designController) {
		this.designController = designController;
	}
	
	@UiHandler("textArea")
	void onTextAreaKeyDown(KeyDownEvent event) {
		designController.onKeyInput(event.getNativeKeyCode());
	}
 
	public void showExercise(ExerciseDescriptor exerciseDescriptor) {
		StringBuilder sb = new StringBuilder();
		for (Key key : exerciseDescriptor.getSequence()){
			sb.append(key.toText());
			sb.append(" ");
		}
		textArea.setText(sb.toString());
	}
	
	public void showJson(String json) {
		jsonArea.setText(json);
	}
}
