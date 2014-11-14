package org.fingerfing.client.widget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fingerfing.client.core.NativeKey;
import org.fingerfing.client.widget.KeyboardDescriptor.KeyDescriptor;
import org.fingerfing.client.widget.KeyboardDescriptor.RowDescriptor;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

class KeyboardBuilder {

	private class Key extends Button {

		private int left, top, width, height;

		public Key(int left, int top, int width, int height) {
			this(left, top, width, height, null);
		}

		public Key(int left, int top, int width, int height, String label) {
			super(label);
			this.left = left;
			this.top = top;
			this.width = width;
			this.height = height;
			super.setWidth(width + "px");
			super.setHeight(height + "px");
		}
	}

	private static final int BLOCK_LEFT = 5;
	private static final int BLOCK_TOP = 0;

	private static final int KEY_WIDTH = 45;
	private static final int KEY_HEIGHT = 45;
	private static final int KEY_SPACE_VERTICAL = 5;
	private static final int KEY_SPACE_HORIZONTAL = 5;
	
	private static final int LABEL_LEFT = 5;
	private static final int LABEL_TOP = 0;
	private static final int ALT_LABEL_BOTTOM = -23;
	private static final int ALT_LABEL_RIGHT = -18; // 5;

	private AbsolutePanel keyArea;

	// WARN 1 nativeKey не больше 1 кнопки?
	private Map<NativeKey, Key> keyMap = new HashMap<NativeKey, Key>();

	public KeyboardBuilder(AbsolutePanel keyArea) {
		assert (keyArea != null) : "Const: keyArea is null";
		this.keyArea = keyArea;
	}

	public Map<NativeKey, ? extends Widget> build(KeyboardDescriptor kd) {
		assert (keyArea != null) : "keyArea is null";
		buildBlock(kd.getBlock(), BLOCK_LEFT, BLOCK_TOP);
		return keyMap;
	}

	private void buildBlock(List<RowDescriptor> rows, int left, int top) {
		for (KeyboardDescriptor.RowDescriptor kr : rows) {
			buildRow(kr.getRow(), left, top, KEY_HEIGHT);
			top += KEY_HEIGHT + KEY_SPACE_VERTICAL;
		}
	}

	public void buildAlternativeLabel(KeyboardLabelDescriptor ld) {
		assert (ld != null) : "alternative label descriptor null";
		buildBlockLabel(ld, ALT_LABEL_RIGHT, ALT_LABEL_BOTTOM);
	}

	public void buildGeneralLabel(KeyboardLabelDescriptor ld) {
		assert (ld != null) : "general label descriptor null";
		buildBlockLabel(ld, LABEL_LEFT, LABEL_TOP);
	}

	private void buildBlockLabel(KeyboardLabelDescriptor ld, int left, int top) {
		for (Map.Entry<NativeKey, Key> e : keyMap.entrySet()){
			String l = ld.getLabelMap().get(e.getKey());
			if (l!=null){
				Key key = e.getValue();
				int lLeft = (left < 0) ? key.left + key.width + left : key.left	+ left;
				int lTop = (top < 0) ? key.top + key.height + top : key.top + top;
				buildLabel(l, lLeft, lTop);
			}
		}
	}

	private void buildLabel(String text, int left, int top) {
		Label label = new Label(text);
		keyArea.add(label, left, top);
	}

	private void buildKey(NativeKey nativeKey, int left, int top, int width,
			int height) {
		Key key = new Key(left, top, width, height); // , nativeKey.toText());
		keyArea.add(key, left, top);
		keyMap.put(nativeKey, key);
	}

	private void buildRow(List<KeyDescriptor> keys, int left, int top,
			int height) {
		for (KeyDescriptor r : keys) {
			int width;
			if (r.getWidth() == null) {
				width = KEY_WIDTH;
			} else {
				width = Integer.parseInt(r.getWidth());
			}
			buildKey(r.getNativeKey(), left, top, width, height);
			left += width + KEY_SPACE_HORIZONTAL;
		}
	}
}