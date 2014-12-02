package org.fingerfing.client.view.widget;

import java.util.List;

import org.fingerfing.client.domain.Attempt;
import org.fingerfing.client.domain.Element;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.view.event.HasNativeKeyInputHandler;

import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.IsWidget;

public interface ExerciseWidget extends IsWidget, Focusable, HasNativeKeyInputHandler {

	public void showCurrentElement(Element element);

	public void showAttempt(Attempt attempt);

	public void showSequence(List<Key> sequence);

}