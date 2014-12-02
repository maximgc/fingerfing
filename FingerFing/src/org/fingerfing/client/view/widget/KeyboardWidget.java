package org.fingerfing.client.view.widget;

import java.util.List;
import java.util.Map;

import org.fingerfing.client.domain.Attempt;
import org.fingerfing.client.domain.Element;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.view.KeyboardDescriptor;
import org.fingerfing.client.view.KeyboardLabelDescriptor;
import org.fingerfing.client.view.widget.event.HandlerManager;
import org.fingerfing.client.view.widget.event.HasKeyInputHandler;
import org.fingerfing.client.view.widget.event.KeyInputHandler;
import org.fingerfing.client.view.widget.event.NativeKeyInputEvent;
import org.fingerfing.client.view.widget.event.NativeKeyInputHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class KeyboardWidget extends Composite implements ExerciseWidget,
		HasKeyInputHandler {

	interface KeyboardUiBinder extends UiBinder<Widget, KeyboardWidget> {
	}

	private class KeyDownHandlerImpl implements KeyDownHandler {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			NativeKey nativeKey = NativeKey.getByNativeCode(event
					.getNativeKeyCode());
			nativeKeyInputHandlers
					.fireEvent(new NativeKeyInputEvent(nativeKey));
		}
	}

	private static KeyboardUiBinder uiBinder = GWT
			.create(KeyboardUiBinder.class);

	@UiField
	AbsolutePanel keyArea;
	@UiField
	FocusPanel focusPanel;

	private Map<Key, KeyWidget> keyWidgetMap;

	private Element curElement;

	private KeyboardBuilder keyboardBuilder;

	private HandlerManager<NativeKeyInputHandler> nativeKeyInputHandlers;

	public KeyboardWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		nativeKeyInputHandlers = new HandlerManager<NativeKeyInputHandler>();
		focusPanel.addKeyDownHandler(new KeyDownHandlerImpl());
	}

	@Override
	public void addKeyInputHandler(KeyInputHandler handler) {
		keyboardBuilder.addKeyInputHandler(handler);
	}

	@Override
	public void addNativeKeyInputHandler(NativeKeyInputHandler handler) {
		nativeKeyInputHandlers.addHandler(handler);
		keyboardBuilder.addNativeKeyInputHandler(handler);
	}

	public KeyWidget getKeyWidget(Key key) {
		return keyWidgetMap.get(key);
	}

	@Override
	public int getTabIndex() {
		return focusPanel.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		focusPanel.setAccessKey(key);
	}

	public void setAlternativeLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		keyboardBuilder.setAlternativeLabelDescriptor(labelDescriptor);
	}

	@Override
	public void setFocus(boolean focused) {
		focusPanel.setFocus(focused);
	}

	public void setGeneralLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		keyboardBuilder.setGeneralLabelDescriptor(labelDescriptor);
	}

	public void setKeyboardBuilder(KeyboardBuilder keyboardBuilder) {
		this.keyboardBuilder = keyboardBuilder;
		keyboardBuilder.setKeyArea(keyArea);
	}

	public void setKeyboardDescriptor(KeyboardDescriptor keyboardDescriptor) {
		keyWidgetMap = keyboardBuilder
				.setKeyboardDescriptor(keyboardDescriptor);
	}

	public void setKeyboardDescriptor(KeyboardDescriptor keyboardDescriptor,
			KeyboardLabelDescriptor keyboardGeneralLabelDescriptor,
			KeyboardLabelDescriptor keyboardAlternativeLabelDescriptor) {
		keyWidgetMap = keyboardBuilder.setKeyboardDescriptor(
				keyboardDescriptor, keyboardGeneralLabelDescriptor,
				keyboardAlternativeLabelDescriptor);
	}

	@Override
	public void setTabIndex(int index) {
		focusPanel.setTabIndex(index);
	}

	@Override
	public void showAttempt(final Attempt attempt) {
		if (keyWidgetMap == null)
			return;
		Key[] keys = attempt.getActualNativeKey().getKeys();
		for (Key k : keys) {
			final KeyWidget kw = keyWidgetMap.get(k);
			if (kw != null) {
				kw.showDelayedPressed(attempt.getEval(), 150);
				kw.showDelayedEval(attempt.getEval(), 150);
			}
		}
	}

	@Override
	public void showCurrentElement(Element element) {
		if (keyWidgetMap == null)
			return;
		if (curElement != null) {
			KeyWidget kw = keyWidgetMap.get(curElement.getKey());
			kw.resetExpected();
		}
		KeyWidget kw = keyWidgetMap.get(element.getKey());
		kw.showExpected();
		curElement = element;
	}

	@Override
	public void showSequence(List<Key> sequence) {
	}

}

// кнопка:
// видимость
// цвет
// прозрачность/enabled
// жирность обводки (по сторонам)
// цвет обводки (по сторонам)
//
// текст:
// видимость
// цвет
// жирность - рябит глаза
//

// постоянные:
// текущая раскладка (прозрачность текста в div)
// изученность (видимость)
// зона упражнения (enabled/прозрачность)
// палец (нижний бордер)
// ср. оценка(нагретость) (бордер - цвет и жирность)
// время нажатия/темп(нагретость2) (бордер - цвет и жирность)

// временные:
// активная (цвет)
// нажатие (цвет в нажатие)
// тек оценка (цвет после отпускания)