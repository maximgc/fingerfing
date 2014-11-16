package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.Key;

import com.google.gwt.user.client.ui.TextArea;

public class ExerciseTraceWidget extends TextArea implements ExerciseWidget{

	private List<Key> keySeq;
	private Element curElement;
	private Attempt[] attempts;

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
	
}
