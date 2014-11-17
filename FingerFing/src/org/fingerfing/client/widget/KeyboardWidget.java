package org.fingerfing.client.widget;

import java.util.List;
import java.util.Map;

import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.Finger;
import org.fingerfing.client.core.Key;
import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.resource.KeyboardResource;
import org.fingerfing.client.widget.event.ElementInputEvent;
import org.fingerfing.client.widget.event.ElementInputHandler;

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

//кнопка:
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

public class KeyboardWidget extends Composite implements ExerciseWidget {

	interface KeyboardUiBinder extends UiBinder<Widget, KeyboardWidget> {
	}

	static class KeyWidget extends Button {
		// private int left, top, width, height;
		private String generalLabel = "";
		private String alternativeLabel = "";

		public KeyWidget(int left, int top, int width, int height) {
			this(left, top, width, height, null);
		}

		public KeyWidget(int left, int top, int width, int height, String label) {
			super(label);
			// this.left = left;
			// this.top = top;
			// this.width = width;
			// this.height = height;
			super.setWidth(width + "px");
			super.setHeight(height + "px");
			setAttribute("finger", "l1");
		}

		public void setAlternativeLabel(String alternativeLabel) {
			this.alternativeLabel = alternativeLabel;
			applyLabel();
		}

		public void setGeneralLabel(String generalLabel) {
			this.generalLabel = generalLabel;
			applyLabel();
		}

		public void showEval(int eval) {
			setAttribute("eval", String.valueOf(eval));
			setAttribute("heated", String.valueOf(eval));
		}

		public void resetEval() {
			removeAttribute("eval");
			removeAttribute("heated");
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

		private void setAttribute(String name, String value) {
			this.getElement().setAttribute(name, value);
		}

		public void resetExpected() {
			removeAttribute("expected");
		}

		public void showExpected() {
			setAttribute("expected", "next");
		}

		public void showPressed(int i) {
			setAttribute("pressed", String.valueOf(i));
		}

		public void resetPressed() {
			removeAttribute("pressed");
		}

		public void showInSeq() {
			setAttribute("sequence", "next");
		}

		public void resetInSeq() {
			removeAttribute("sequence");
		}
		
		public void showFinger(Finger finger) {
			setAttribute("finger", finger.toString());
		}
		
		public void resetFinger() {
			removeAttribute("finger");
		}

	}

	private static KeyboardUiBinder uiBinder = GWT
			.create(KeyboardUiBinder.class);

	@UiField
	AbsolutePanel keyArea;

	@UiField
	FocusPanel focusPanel;

	private DescriptorManager dm = new DescriptorManager();

	private Map<Key, KeyWidget> keyWidgetMap;

	private Element curElement;

	public KeyboardWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		KeyboardDescriptor kd = dm.decodeFromJson(KeyboardDescriptor.class,
				KeyboardResource.INST.getKeyboardDescriptor1().getText());
		KeyboardLabelDescriptor kldEN = dm.decodeFromJson(
				KeyboardLabelDescriptor.class, KeyboardResource.INST
						.getKeyboardLabelDescriptorEN().getText());
		KeyboardLabelDescriptor kldRU = dm.decodeFromJson(
				KeyboardLabelDescriptor.class, KeyboardResource.INST
						.getKeyboardLabelDescriptorRU().getText());
		this.setDescriptor(kd, kldEN, kldRU);
	}

	public void setDescriptor(KeyboardDescriptor keyboardDescriptor,
			KeyboardLabelDescriptor generalLabelDescriptor,
			KeyboardLabelDescriptor alternativeLabelDescriptor) {
		KeyboardBuilder keyBuilder = new KeyboardBuilder(keyArea);
		keyWidgetMap = keyBuilder.build(keyboardDescriptor,
				generalLabelDescriptor, alternativeLabelDescriptor);
	}

	@Override
	public void showAttempt(final Attempt attempt) {
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

	@Override
	public void showCurrentElement(Element element) {
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
//		 showBlock(sequence, 0);
	}
	
	public KeyWidget getKeyWidget(Key key){
		return keyWidgetMap.get(key);
	}

	private Timer t;

	private void showBlock(final List<Key> sequence, final int i) {
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

	@Override
	public void setElementInputHandler(ElementInputHandler handler) {
		for (Map.Entry<Key, KeyWidget> e : keyWidgetMap.entrySet()) {
			addElementInpurHandlerToKeyWidget(e.getValue(), handler, e.getKey());
		}
	}

	//WARN что то не то (ответсвенность процедуры?)
	private void addElementInpurHandlerToKeyWidget(KeyWidget kw,
			final ElementInputHandler handler, Key key) {
		final NativeKey nativeKey = NativeKey.getByNativeCode(key
				.getNativeCode());
		kw.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handler.onElementInput(new ElementInputEvent(nativeKey));
			}
		});
	}

}
