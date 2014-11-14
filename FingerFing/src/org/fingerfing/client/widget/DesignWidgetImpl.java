package org.fingerfing.client.widget;

import java.util.HashMap;

import org.fingerfing.client.controller.DesignControllerImpl;
import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.core.Key;
import org.fingerfing.client.json.DescriptorManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;

public class DesignWidgetImpl extends Composite {

	private static DesignWidgetImplUiBinder uiBinder = GWT
			.create(DesignWidgetImplUiBinder.class);
	@UiField
	TextArea textArea;
	@UiField
	TextArea jsonArea;
	private DesignControllerImpl designController;

	interface DesignWidgetImplUiBinder extends
			UiBinder<Widget, DesignWidgetImpl> {
	}

	public DesignWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setDesignController(DesignControllerImpl designController) {
		this.designController = designController;
	}
	
	@UiHandler("textArea")
	void onTextAreaKeyDown(KeyDownEvent event) {
		designController.onKeyInput(event.getNativeKeyCode());
	}
 
	public void showExercise(ExerciseDescriptor exerciseDescriptor) {
		StringBuilder sb = new StringBuilder();
		for (Key nk : exerciseDescriptor.getSequence()){
			sb.append(nk.toText());
			sb.append(" ");
		}
		textArea.setText(sb.toString());
	}
	
	public void showJson(String json) {
		jsonArea.setText(json);
//		DescriptorManager dm = new DescriptorManager();
//		KeyboardLabelDescriptor kld = dm.create(KeyboardLabelDescriptor.class);
//		kld.setLabelTextMap(new HashMap<Key, String>());
//		for (Key nk : Key.values()){
//			kld.getLabelTextMap().put(nk, nk.toText());
//		}
//		jsonArea.setText(dm.encodeToJson(kld));
	}
}
