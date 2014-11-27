package org.fingerfing.client.view;

import java.util.List;
import java.util.Map;

import org.fingerfing.client.domain.Attempt;
import org.fingerfing.client.domain.Element;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.view.event.HasKeyInputHandler;
import org.fingerfing.client.view.event.KeyInputHandler;
import org.fingerfing.client.view.event.NativeKeyInputEvent;
import org.fingerfing.client.view.event.NativeKeyInputHandler;

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

	private static KeyboardUiBinder uiBinder = GWT
			.create(KeyboardUiBinder.class);

	@UiField
	AbsolutePanel keyArea;
	@UiField
	FocusPanel focusPanel;

	private Map<Key, KeyWidget> keyWidgetMap;

	private Element curElement;

	private KeyboardBuilder keyboardBuilder;

	private NativeKeyInputHandler nativeKeyInputHandler;

	public KeyboardWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		focusPanel.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				NativeKey nativeKey = NativeKey.getByNativeCode(event
						.getNativeKeyCode());
				if (nativeKeyInputHandler != null) {
					nativeKeyInputHandler
							.onNativeKeyInput(new NativeKeyInputEvent(nativeKey));
				}
			}
		});
	}

	public void setKeyboardBuilder(KeyboardBuilder keyboardBuilder) {
		this.keyboardBuilder = keyboardBuilder;
		keyboardBuilder.setKeyArea(keyArea);
	}

	@Override
	public void addKeyInputHandler(KeyInputHandler handler) {
		keyboardBuilder.addKeyInputHandler(handler);
	}

	@Override
	public void addNativeKeyInputHandler(NativeKeyInputHandler handler) {
		this.nativeKeyInputHandler = handler;
		keyboardBuilder.addNativeKeyInputHandler(handler);
	}

	public KeyWidget getKeyWidget(Key key) {
		return keyWidgetMap.get(key);
	}

	public void setAlternativeLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		keyboardBuilder.setAlternativeLabelDescriptor(labelDescriptor);
	}

	public void setGeneralLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		keyboardBuilder.setGeneralLabelDescriptor(labelDescriptor);
	}

	public void setKeyboardDescriptor(KeyboardDescriptor keyboardDescriptor) {
		keyWidgetMap = keyboardBuilder.setKeyboardDescriptor(keyboardDescriptor);
	}

	public void setKeyboardDescriptor(KeyboardDescriptor keyboardDescriptor,
			KeyboardLabelDescriptor keyboardGeneralLabelDescriptor,
			KeyboardLabelDescriptor keyboardAlternativeLabelDescriptor) {
		keyWidgetMap = keyboardBuilder.setKeyboardDescriptor(keyboardDescriptor,
				keyboardGeneralLabelDescriptor,
				keyboardAlternativeLabelDescriptor);
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

// WARN unused
// Timer t;
// @SuppressWarnings("unused")
// private void showBlock(final List<Key> sequence, final int i) {
// if (keyWidgetMap==null) return;
// if (t != null)
// t.cancel();
// t = new Timer() {
// int n = i;
// KeyWidget kw;
//
// @Override
// public void run() {
// if (n < sequence.size()) {
// if (kw != null)
// kw.resetInSeq();
// kw = keyWidgetMap.get(sequence.get(n));
// kw.showInSeq();
// n++;
// } else if (n == sequence.size()) {
// if (kw != null)
// kw.resetInSeq();
// // this.cancel();
// n = i;
// }
// }
// };
// t.scheduleRepeating(200);
// }

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