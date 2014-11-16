package org.fingerfing.client.widget;

import java.util.List;
import java.util.Map;

import org.fingerfing.client.core.Attempt;
import org.fingerfing.client.core.Element;
import org.fingerfing.client.core.Key;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.resource.KeyboardResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;

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
		}

		public void resetExpected() {
			removeAttribute("expected");
		}

		public void setAlternativeLabel(String alternativeLabel) {
			this.alternativeLabel = alternativeLabel;
			applyLabel();
		}

		public void setGeneralLabel(String generalLabel) {
			this.generalLabel = generalLabel;
			applyLabel();
		}

		public void showAttempt(int eval) {
			setAttribute("eval", String.valueOf(eval));
			Timer t = new Timer() {
				@Override
				public void run() {
					removeAttribute("eval");
				}
			};
			t.schedule(500);
		}

		public void showExpected() {
			setAttribute("expected", "true");
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

	}

	private static KeyboardUiBinder uiBinder = GWT
			.create(KeyboardUiBinder.class);

	@UiField
	AbsolutePanel keyArea;

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
		keyWidgetMap = keyBuilder.build(keyboardDescriptor);
		keyBuilder.buildGeneralLabel(generalLabelDescriptor);
		keyBuilder.buildAlternativeLabel(alternativeLabelDescriptor);
	}

	@Override
	public void showAttempt(Attempt attempt) {
		Key[] keys = attempt.getActualNativeKey().getKeys();
		for (Key k : keys) {
			KeyWidget kw = keyWidgetMap.get(k);
			if (kw != null)
				kw.showAttempt(attempt.getEval());
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
		// kw.resetExpected();
		curElement = element;
	}

	@Override
	public void showSequence(List<Key> sequence) {

	}

}
