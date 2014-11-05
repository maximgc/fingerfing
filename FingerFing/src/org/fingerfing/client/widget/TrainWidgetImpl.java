package org.fingerfing.client.widget;

import java.util.ArrayList;
import java.util.List;

import org.fingerfing.client.controller.TrainControllerImpl;
import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.NativeKey;

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
 * @author Max
 */
public class TrainWidgetImpl extends Composite {

	interface TrainWidgetImplUiBinder extends UiBinder<Widget, TrainWidgetImpl> {
	}

	private static TrainWidgetImplUiBinder uiBinder = GWT
			.create(TrainWidgetImplUiBinder.class);

	@UiField
	TextArea textArea;

	private TrainControllerImpl trainController;
	//WARN нет сброса при новом exercise что то не то в модели
	private List<NativeKey> sequence;
	private Element curElement;
	private List<Attempt> lastAttempts;

	public TrainWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setTrainController(TrainControllerImpl trainController) {
		this.trainController = trainController;
	}

	public void showCurElement(Element el) {
		this.curElement = el;
		refresh();
	}

	public void showLastAttempt(Attempt lastAttempt) {
		if (lastAttempts == null) {
			lastAttempts = new ArrayList<Attempt>(sequence.size());
		}
		if (lastAttempts.size() <= lastAttempt.getPos()) {
			lastAttempts.add(lastAttempt);
		} else {
			lastAttempts.set(lastAttempt.getPos(), lastAttempt);
		}
		refresh();
	}

	public void showSequence(List<NativeKey> sequence) {
		this.sequence = sequence;
		refresh();
	}

	private void refresh() {
		StringBuilder sb = new StringBuilder();
		int pos = 0;
		for (NativeKey nk : sequence) {
			sb.append(nk.toText());
			if (lastAttempts != null && pos < lastAttempts.size()) {
				sb.append(" - ").append(lastAttempts.get(pos).getEval());
			}
			if (curElement != null && pos++ == curElement.getPos()) {
				sb.append(" <- ").append(curElement.getNativeKey().toString());
			}
			;
			sb.append((char) 13);
		}
		textArea.setText(sb.toString());
	}

	@UiHandler("textArea")
	void onTextAreaKeyDown(KeyDownEvent event) {
		trainController.onKeyInput(event.getNativeKeyCode());
	}
}
