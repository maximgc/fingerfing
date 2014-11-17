package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.Key;
import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.widget.event.ElementInputEvent;
import org.fingerfing.client.widget.event.ElementInputHandler;

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
				fireElementInput(new ElementInputEvent(nativeKey));
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
		//WARN
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

	private ElementInputHandler elementInputHandler;

	private void fireElementInput(ElementInputEvent event) {
		elementInputHandler.onElementInput(event);
	}
	
	@Override
	public void setElementInputHandler(ElementInputHandler handler) {
		this.elementInputHandler = handler;
	}
	
}
