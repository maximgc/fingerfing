package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.controller.TrainControllerImpl;
import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.resource.KeyboardResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;

/**
 * Widget for demonstrate exercise.
 * 
 * @author Max
 */
public class TrainWidgetImpl extends Composite {

	interface TrainWidgetImplUiBinder extends UiBinder<Widget, TrainWidgetImpl> {
	}

	private static TrainWidgetImplUiBinder uiBinder = GWT
			.create(TrainWidgetImplUiBinder.class);

	@UiField
	TextArea textArea;
	@UiField
	Keyboard keyboard;

	private TrainControllerImpl trainController;
	private DescriptorManager dm = new DescriptorManager();

	private List<NativeKey> keySeq;
	private Element curElement;
	private Attempt[] attempts;

	public TrainWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		KeyboardDescriptor kd = dm.decodeFromJson(KeyboardDescriptor.class,
				KeyboardResource.INST.getKeyboardDescriptor1().getText());
		KeyboardLabelDescriptor kldEN = dm.decodeFromJson(
				KeyboardLabelDescriptor.class, KeyboardResource.INST
						.getKeyboardLabelDescriptorEN().getText());
		KeyboardLabelDescriptor kldRU = dm.decodeFromJson(
				KeyboardLabelDescriptor.class, KeyboardResource.INST
						.getKeyboardLabelDescriptorRU().getText());
		keyboard.setDescriptor(kd, kldEN, kldRU);
	}

	public void setTrainController(TrainControllerImpl trainController) {
		this.trainController = trainController;
	}

	public void showCurrentElement(Element element) {
		this.curElement = element;
		refresh();
	}

	public void showAttempt(Attempt attempt) {
		this.attempts[attempt.getPos()] = attempt;
		refresh();
	}

	public void showSequence(List<NativeKey> sequence) {
		this.keySeq = sequence;
		this.attempts = new Attempt[keySeq.size()];
		this.curElement = null;
		refresh();
	}

	private void refresh() {
		StringBuilder sb = new StringBuilder();
		for (int p = 0; keySeq != null && p < keySeq.size(); p++) {
			sb.append(keySeq.get(p).toText());
			if (attempts[p] != null) {
				sb.append(" |").append(attempts[p].getEval());
			}
			if (curElement != null && p == curElement.getPos()) {
				sb.append(" <- ").append(keySeq.get(p).toText());
			}
			sb.append((char) 13);
		}
		textArea.setText(sb.toString());
	}

	@UiHandler("textArea")
	void onTextAreaKeyDown(KeyDownEvent event) {
		trainController.onKeyInput(event.getNativeKeyCode());
	}

}
