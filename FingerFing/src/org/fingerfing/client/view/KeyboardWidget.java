package org.fingerfing.client.view;

import java.util.List;
import java.util.Map;

import org.fingerfing.client.domain.Attempt;
import org.fingerfing.client.domain.Element;
import org.fingerfing.client.domain.Finger;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.view.event.HasKeyInputHandler;
import org.fingerfing.client.view.event.HasNativeKeyInputHandler;
import org.fingerfing.client.view.event.KeyInputEvent;
import org.fingerfing.client.view.event.KeyInputHandler;
import org.fingerfing.client.view.event.NativeKeyInputEvent;
import org.fingerfing.client.view.event.NativeKeyInputHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class KeyboardWidget extends Composite implements ExerciseWidget,
		HasKeyInputHandler {

	interface KeyboardUiBinder extends UiBinder<Widget, KeyboardWidget> {
	}

	static class KeyWidget extends Button implements HasNativeKeyInputHandler,
			HasKeyInputHandler {
		// private int left, top, width, height;
		private String generalLabel = "";
		private String alternativeLabel = "";
		private Key key;

		public KeyWidget(Key key, int left, int top, int width, int height) {
			this(key, left, top, width, height, null);
		}

		public KeyWidget(Key key, int left, int top, int width, int height,
				String label) {
			super(label);
			// this.left = left;
			// this.top = top;
			// this.width = width;
			// this.height = height;
			this.key = key;
			super.setWidth(width + "px");
			super.setHeight(height + "px");
			setAttribute("finger", "l1");
		}

		@Override
		public void addKeyInputHandler(final KeyInputHandler handler) {
			this.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handler.onKeyInput(new KeyInputEvent(key));
				}
			});
		}

		@Override
		public void addNativeKeyInputHandler(final NativeKeyInputHandler handler) {
			final NativeKey nativeKey = NativeKey.getByNativeCode(key
					.getNativeCode());
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handler.onNativeKeyInput(new NativeKeyInputEvent(nativeKey));
				}
			});
		}

		private void applyLabel() {
			this.setHTML("<table class=\"keyWidget_label\">"
					+ "<tr><td class=\"general\">" + generalLabel + "</td><td>"
					+ "</td></tr><tr><td>" + "</td><td class=\"alternative\">"
					+ alternativeLabel + "</td></tr></table>");
		}

		private void removeAttribute(String name) {
			this.getElement().removeAttribute(name);
		}

		public void resetEval() {
			removeAttribute("eval");
			removeAttribute("heated");
		}

		public void resetExpected() {
			removeAttribute("expected");
		}

		public void resetFinger() {
			removeAttribute("finger");
		}

		public void resetInSeq() {
			removeAttribute("sequence");
		}

		public void resetPressed() {
			removeAttribute("pressed");
		}

		public void setAlternativeLabel(String alternativeLabel) {
			this.alternativeLabel = alternativeLabel;
			applyLabel();
		}

		private void setAttribute(String name, String value) {
			this.getElement().setAttribute(name, value);
		}

		public void setGeneralLabel(String generalLabel) {
			this.generalLabel = generalLabel;
			applyLabel();
		}

		public void showEval(int eval) {
			setAttribute("eval", String.valueOf(eval));
			setAttribute("heated", String.valueOf(eval));
		}

		public void showExpected() {
			setAttribute("expected", "next");
		}

		public void showFinger(Finger finger) {
			setAttribute("finger", finger.toString());
		}

		public void showInSeq() {
			setAttribute("sequence", "next");
		}

		public void showPressed(int i) {
			setAttribute("pressed", String.valueOf(i));
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

	private Timer t;

	public KeyboardWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		keyboardBuilder = new KeyboardBuilder(keyArea);
	}

	@Override
	public void addKeyInputHandler(KeyInputHandler handler) {
		keyboardBuilder.addKeyInputHandler(handler);
	}

	@Override
	public void addNativeKeyInputHandler(NativeKeyInputHandler handler) {
		keyboardBuilder.addNativeKeyInputHandler(handler);
	}

	public KeyWidget getKeyWidget(Key key) {
		return keyWidgetMap.get(key);
	}

	public void setKeyboardDescriptor(KeyboardDescriptor keyboardDescriptor) {
		keyWidgetMap = keyboardBuilder.buildKeyboard(keyboardDescriptor);
	}

	@Override
	public void showAttempt(final Attempt attempt) {
		if (keyWidgetMap == null)
			return;
		Key[] keys = attempt.getActualNativeKey().getKeys();
		for (Key k : keys) {
			final KeyWidget kw = keyWidgetMap.get(k);
			if (kw != null) {
				kw.showPressed(attempt.getEval());
				Timer t = new Timer() {
					@Override
					public void run() {
						kw.resetPressed();
						kw.showEval(attempt.getEval());
						Timer tt = new Timer() {
							@Override
							public void run() {
								kw.resetEval();
							}
						};
						tt.schedule(150);
					}
				};
				t.schedule(150);
			}

		}
	}

	// WARN unused
	@SuppressWarnings("unused")
	private void showBlock(final List<Key> sequence, final int i) {
		if (keyWidgetMap==null) return;
		if (t != null)
			t.cancel();
		t = new Timer() {
			int n = i;
			KeyWidget kw;

			@Override
			public void run() {
				if (n < sequence.size()) {
					if (kw != null)
						kw.resetInSeq();
					kw = keyWidgetMap.get(sequence.get(n));
					kw.showInSeq();
					n++;
				} else if (n == sequence.size()) {
					if (kw != null)
						kw.resetInSeq();
					// this.cancel();
					n = i;
				}
			}
		};
		t.scheduleRepeating(200);
	}
	
	public void setGeneralLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		keyboardBuilder.setGeneralLabelDescriptor(labelDescriptor);
	}

	public void setAlternativeLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		keyboardBuilder.setAlternativeLabelDescriptor(labelDescriptor);
	}

	@Override
	public void showCurrentElement(Element element) {
		if (keyWidgetMap==null) return;
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
		// showBlock(sequence, 0);
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