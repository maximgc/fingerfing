package org.fingerfing.client.view;

import java.util.List;

import org.fingerfing.client.domain.Attempt;
import org.fingerfing.client.domain.Element;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.view.event.HasNativeKeyInputHandler;

public interface ExerciseWidget extends HasNativeKeyInputHandler {

	public void showCurrentElement(Element element);

	public void showAttempt(Attempt attempt);

	public void showSequence(List<Key> sequence);

}