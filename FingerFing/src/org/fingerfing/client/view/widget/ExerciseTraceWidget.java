package org.fingerfing.client.view.widget;

import java.util.List;

import org.fingerfing.client.domain.Attempt;
import org.fingerfing.client.domain.Element;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.view.widget.event.HandlerManager;
import org.fingerfing.client.view.widget.event.HasNativeKeyInputHandler;
import org.fingerfing.client.view.widget.event.NativeKeyInputEvent;
import org.fingerfing.client.view.widget.event.NativeKeyInputHandler;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.TextArea;

public class ExerciseTraceWidget extends TextArea implements ExerciseWidget, HasNativeKeyInputHandler {

	private class KeyDownHandlerImpl implements KeyDownHandler {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			NativeKey nativeKey = NativeKey.getByNativeCode(event
					.getNativeKeyCode());
			nativeKeyInputHandlers.fireEvent(new NativeKeyInputEvent(
					nativeKey));
		}
	}

	private List<Key> keySeq;
	private Element curElement;
	private Attempt[] attempts;

	private HandlerManager<NativeKeyInputHandler> nativeKeyInputHandlers = new HandlerManager<NativeKeyInputHandler>();

	public ExerciseTraceWidget() {
		this.addKeyDownHandler(new KeyDownHandlerImpl());
	}

	@Override
	public void addNativeKeyInputHandler(NativeKeyInputHandler handler) {
		nativeKeyInputHandlers.addHandler(handler);
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

	@Override
	public void showAttempt(Attempt attempt) {
		this.attempts[attempt.getPos()] = attempt;
		refresh();
	}

	@Override
	public void showCurrentElement(Element element) {
		this.curElement = element;
		refresh();
	}

	@Override
	public void showSequence(List<Key> sequence) {
		this.keySeq = sequence;
		this.attempts = new Attempt[keySeq.size()];
		this.curElement = null;
		refresh();
	}

}
