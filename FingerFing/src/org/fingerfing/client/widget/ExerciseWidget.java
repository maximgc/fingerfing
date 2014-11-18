package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.Key;
import org.fingerfing.client.widget.event.HasNativeKeyInputHandler;

public interface ExerciseWidget extends HasNativeKeyInputHandler {

	public void showCurrentElement(Element element);

	public void showAttempt(Attempt attempt);

	public void showSequence(List<Key> sequence);

}