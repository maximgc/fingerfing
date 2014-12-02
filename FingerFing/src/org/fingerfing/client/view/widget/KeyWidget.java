package org.fingerfing.client.view.widget;

import java.util.LinkedList;
import java.util.Queue;

import org.fingerfing.client.domain.Finger;
import org.fingerfing.client.domain.Key;
import org.fingerfing.client.domain.NativeKey;
import org.fingerfing.client.view.widget.event.HasKeyInputHandler;
import org.fingerfing.client.view.widget.event.HasNativeKeyInputHandler;
import org.fingerfing.client.view.widget.event.KeyInputEvent;
import org.fingerfing.client.view.widget.event.KeyInputHandler;
import org.fingerfing.client.view.widget.event.NativeKeyInputEvent;
import org.fingerfing.client.view.widget.event.NativeKeyInputHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;

public class KeyWidget extends Button implements HasNativeKeyInputHandler,
		HasKeyInputHandler {

	public static final int LABEL_RIGHT_BOTTOM = 3;
	public static final int LABEL_LEFT_BOTTOM = 2;
	public static final int LABEL_RIGHT_TOP = 1;
	public static final int LABEL_LEFT_TOP = 0;

	private class DellayedEffect extends Timer {

		private KeyWidget.Effect prev;

		@Override
		public void run() {
			if (prev != null) {
				removeAttribute(prev.name);
			}
			KeyWidget.Effect e = effectQueue.poll();
			if (e != null) {
				setAttribute(e.name, e.value);
				this.scheduleRepeating(e.showMillis);
				prev = e;
			} else {
				this.cancel();
			}
		}
	}

	private static class Effect {
		private String name;
		private String value;
		private int showMillis;

		public Effect(String name, String value, int showMillis) {
			this.name = name;
			this.value = value;
			this.showMillis = showMillis;
		}
	}

	private static final String PRESSED = "pressed";
	private static final String FINGER = "finger";
	// private static final String HEATED = "heated";
	private static final String EVAL = "eval";
	private static final String EXPECTED = "expected";
	// private int left, top, width, height;

	private Key key;

	private Queue<KeyWidget.Effect> effectQueue = new LinkedList<KeyWidget.Effect>();

	private DellayedEffect showdellayedEffects = new DellayedEffect();

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

	private String[] labels = new String[] { "", "", "", "" };

	private void applyLabel() {
		this.setHTML("<table class=\"keyWidget_label\"><tr><td class=\"general\">"
				+ labels[LABEL_LEFT_TOP]
				+ "</td><td>"
				+ labels[LABEL_RIGHT_TOP]
				+ "</td></tr><tr><td>"
				+ labels[LABEL_LEFT_BOTTOM]
				+ "</td><td class=\"alternative\">"
				+ labels[LABEL_RIGHT_BOTTOM] + "</td></tr></table>");
	}

	private void putEffect(String name, String value, int showMillis) {
		effectQueue.offer(new Effect(name, value, showMillis));
		if (!showdellayedEffects.isRunning()) {
			showdellayedEffects.scheduleRepeating(10);
		}
	}

	private void removeAttribute(String name) {
		this.getElement().removeAttribute(name);
	}

	public void resetEval() {
		removeAttribute(EVAL);
	}

	public void resetExpected() {
		removeAttribute(EXPECTED);
	}

	public void resetFinger() {
		removeAttribute(FINGER);
	}

	public void resetPressed() {
		removeAttribute(PRESSED);
	}

	public void setLabel(String[] label) {
		for (int i = 0; i < 4; i++) {
			this.labels[i] = (label[i] == null) ? "" : label[i];
		}
		applyLabel();
	}

	public void setLabel(int pos, String label) {
		this.labels[pos] = (label == null) ? "" : label;
		applyLabel();
	}

	private void setAttribute(String name, String value) {
		this.getElement().setAttribute(name, value);
	}

	public void showDelayedEval(int eval, int showMillis) {
		putEffect(EVAL, String.valueOf(eval), showMillis);
	}

	public void showDelayedExpected(int showMillis) {
		putEffect(EXPECTED, "next", showMillis);
	}

	public void showDelayedPressed(int eval, int showMillis) {
		putEffect(PRESSED, String.valueOf(eval), showMillis);
	}

	public void showEval(int eval) {
		setAttribute(EVAL, String.valueOf(eval));
	}

	public void showExpected() {
		setAttribute(EXPECTED, "next");
	}

	public void showFinger(Finger finger) {
		setAttribute(FINGER, finger.toString());
	}

	public void showPressed(int eval) {
		setAttribute(PRESSED, String.valueOf(eval));
	}

}