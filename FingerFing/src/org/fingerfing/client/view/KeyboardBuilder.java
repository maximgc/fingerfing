package org.fingerfing.client.view;

import java.util.Map;

import org.fingerfing.client.domain.Key;
import org.fingerfing.client.view.event.KeyInputHandler;
import org.fingerfing.client.view.event.NativeKeyInputHandler;

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