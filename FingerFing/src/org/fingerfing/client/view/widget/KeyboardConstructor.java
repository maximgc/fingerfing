package org.fingerfing.client.view.widget;

import java.util.Map;

import org.fingerfing.client.domain.Key;
import org.fingerfing.client.view.KeyboardDescriptor;
import org.fingerfing.client.view.KeyboardLabelDescriptor;
import org.fingerfing.client.view.widget.event.KeyInputHandler;
import org.fingerfing.client.view.widget.event.NativeKeyInputHandler;

import com.google.gwt.user.client.ui.AbsolutePanel;

public class KeyboardConstructor implements KeyboardBuilder{
	
	private AbsolutePanel keyPanel;

	public KeyboardConstructor(AbsolutePanel keyPanel) {
		this.keyPanel = keyPanel;
	}
	
	public KeyboardConstructor() {
	}

	@Override
	public void addKeyInputHandler(KeyInputHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNativeKeyInputHandler(NativeKeyInputHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAlternativeLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGeneralLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setKeyArea(AbsolutePanel keyPanel) {
		this.keyPanel = keyPanel;
	}

	@Override
	public Map<Key, KeyWidget> setKeyboardDescriptor(
			KeyboardDescriptor keyboardDescriptor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Key, KeyWidget> setKeyboardDescriptor(
			KeyboardDescriptor keyboardDescriptor,
			KeyboardLabelDescriptor keyboardGeneralLabelDescriptor,
			KeyboardLabelDescriptor keyboardAlternativeLabelDescriptor) {
		// TODO Auto-generated method stub
		return null;
	}
}
