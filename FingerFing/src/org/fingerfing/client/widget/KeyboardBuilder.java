package org.fingerfing.client.widget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fingerfing.client.core.Key;
import org.fingerfing.client.widget.KeyboardDescriptor.KeyDescriptor;
import org.fingerfing.client.widget.KeyboardDescriptor.RowDescriptor;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

class KeyboardBuilder {

	private class KeyWidget extends Button {

		private int left, top, width, height;

		public KeyWidget(int left, int top, int width, int height) {
			this(left, top, width, height, null);
		}

		public KeyWidget(int left, int top, int width, int height, String label) {
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

	// WARN 1 key не больше 1 кнопки?
	private Map<Key, KeyWidget> keyWidgetMap = new HashMap<Key, KeyWidget>();

	public KeyboardBuilder(AbsolutePanel keyArea) {
		assert (keyArea != null) : "Const: keyArea is null";
		this.keyArea = keyArea;
	}

	public Map<Key, ? extends Widget> build(KeyboardDescriptor kd) {
		assert (keyArea != null) : "keyArea is null";
		buildBlock(kd.getBlock(), BLOCK_LEFT, BLOCK_TOP);
		return keyWidgetMap;
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
		for (Map.Entry<Key, KeyWidget> e : keyWidgetMap.entrySet()){
			String l = ld.getLabelMap().get(e.getKey());
			if (l!=null){
				KeyWidget keyWidget = e.getValue();
				int lLeft = (left < 0) ? keyWidget.left + keyWidget.width + left : keyWidget.left	+ left;
				int lTop = (top < 0) ? keyWidget.top + keyWidget.height + top : keyWidget.top + top;
				buildLabel(l, lLeft, lTop);
			}
		}
	}

	private void buildLabel(String text, int left, int top) {
		Label label = new Label(text);
		keyArea.add(label, left, top);
	}

	private void buildKey(Key key, int left, int top, int width,
			int height) {
		KeyWidget keyWidget = new KeyWidget(left, top, width, height); // , key.toText());
		keyArea.add(keyWidget, left, top);
		keyWidgetMap.put(key, keyWidget);
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
			buildKey(r.getKey(), left, top, width, height);
			left += width + KEY_SPACE_HORIZONTAL;
		}
	}
}