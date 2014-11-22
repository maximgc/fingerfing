package org.fingerfing.client.view;

import java.util.List;

import org.fingerfing.client.domain.Attempt;
import org.fingerfing.client.domain.Element;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.widget.event.NativeKeyInputEvent;
import org.fingerfing.client.widget.event.NativeKeyInputHandler;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.TextArea;

public class ExerciseTraceWidget extends TextArea implements ExerciseWidget{

	private List<Key> keySeq;
	private Element curElement;
	private Attempt[] attempts;

	public ExerciseTraceWidget() {
		this.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				NativeKey nativeKey = NativeKey.getByNativeCode(event.getNativeKeyCode());
				fireElementInput(new NativeKeyInputEvent(nativeKey));
			}
		});
	}
	
	@Override
	public void showCurrentElement(Element element) {
		this.curElement = element;
		refresh();
	}

	@Override
	public void showAttempt(Attempt attempt) {
		this.attempts[attempt.getPos()] = attempt;
		refresh();
	}

	@Override
	public void showSequence(List<Key> sequence) {
		this.keySeq = sequence;
		this.attempts = new Attempt[keySeq.size()];
		this.curElement = null;
		//WARN временно setFocus
		this.setFocus(true);
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
		this.setText(sb.toString());
	}

	private NativeKeyInputHandler elementInputHandler;

	private void fireElementInput(NativeKeyInputEvent event) {
		elementInputHandler.onNativeKeyInput(event);
	}
	
	@Override
	public void addNativeKeyInputHandler(NativeKeyInputHandler handler) {
		this.elementInputHandler = handler;
	}
	
}
