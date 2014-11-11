package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.controller.TrainControllerImpl;
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

	private TrainControllerImpl trainController;
	// WARN нет сброса при новом exercise что то не то в модели
	private List<NativeKey> keySeq;
	private int curPos = -1;
	private int[] evals;

	public TrainWidgetImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setTrainController(TrainControllerImpl trainController) {
		this.trainController = trainController;
	}

	public void showCurElement(int currentPos, boolean showed) {
		if (showed) {
			this.curPos = currentPos;
		} else if (this.curPos == currentPos) {
			this.curPos = -1;
		}
		refresh();

	}

	public void showEnv(int pos, int eval) {
		this.evals[pos] = eval;
		refresh();
	}

	public void showSequence(List<NativeKey> sequence) {
		this.keySeq = sequence;
		this.evals = new int[keySeq.size()];
		this.curPos = -1;
		refresh();
	}

	private void refresh() {
		StringBuilder sb = new StringBuilder();
		for (int p = 0; p < keySeq.size(); p++) {
			sb.append(keySeq.get(p).toText());
			sb.append(" |").append(evals[p]);
			if (p == curPos) {
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
	
	public void reset(){
		
	}
}
