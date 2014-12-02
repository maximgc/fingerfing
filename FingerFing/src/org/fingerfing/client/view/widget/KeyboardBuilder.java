package org.fingerfing.client.view.widget;

import java.util.Map;

import org.fingerfing.client.domain.Key;
import org.fingerfing.client.view.KeyboardDescriptor;
import org.fingerfing.client.view.KeyboardLabelDescriptor;
import org.fingerfing.client.view.widget.event.KeyInputHandler;
import org.fingerfing.client.view.widget.event.NativeKeyInputHandler;

import com.google.gwt.user.client.ui.AbsolutePanel;

interface KeyboardBuilder {

	public abstract void addKeyInputHandler(KeyInputHandler handler);

	public abstract void addNativeKeyInputHandler(NativeKeyInputHandler handler);

	public abstract void setAlternativeLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor);

	public abstract void setGeneralLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor);

	public abstract void setKeyArea(AbsolutePanel keyArea);

	public abstract Map<Key, KeyWidget> setKeyboardDescriptor(
			KeyboardDescriptor keyboardDescriptor);

	public abstract Map<Key, KeyWidget> setKeyboardDescriptor(
			KeyboardDescriptor keyboardDescriptor,
			KeyboardLabelDescriptor keyboardGeneralLabelDescriptor,
			KeyboardLabelDescriptor keyboardAlternativeLabelDescriptor);

}