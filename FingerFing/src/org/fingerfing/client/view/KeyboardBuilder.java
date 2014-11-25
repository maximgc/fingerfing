package org.fingerfing.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fingerfing.client.domain.Key;
import org.fingerfing.client.view.KeyboardDescriptor.KeyDescriptor;
import org.fingerfing.client.view.KeyboardDescriptor.RowDescriptor;
import org.fingerfing.client.view.KeyboardWidget.KeyWidget;
import org.fingerfing.client.view.event.KeyInputHandler;
import org.fingerfing.client.view.event.NativeKeyInputHandler;

import com.google.gwt.user.client.ui.AbsolutePanel;

class KeyboardBuilder {

	private static final String KEY_WIDGET_STYLE = "keyWidget";

	private static final int BLOCK_LEFT = 5;
	private static final int BLOCK_TOP = 0;

	private static final int KEY_WIDTH = 45;
	private static final int KEY_HEIGHT = 45;
	private static final int KEY_SPACE_VERTICAL = 5;
	private static final int KEY_SPACE_HORIZONTAL = 5;

	private AbsolutePanel keyArea;

	private Map<Key, KeyWidget> keyWidgetMap = new HashMap<Key, KeyWidget>();

	private Map<Key, String> generalLabelMap;

	private Map<Key, String> alternativeLabelMap;

	private NativeKeyInputHandler nativeKeyInputHandler;

	private KeyInputHandler keyInputHandler;

	public KeyboardBuilder(AbsolutePanel keyPanel) {
		assert (keyPanel != null) : "keyArea is null";
		this.keyArea = keyPanel;
	}

	public Map<Key, KeyWidget> buildKeyboard(
			KeyboardDescriptor keyboardDescriptor) {
		keyArea.clear();
		buildBlock(keyboardDescriptor.getBlock(), BLOCK_LEFT, BLOCK_TOP);
		return keyWidgetMap;
	}

	public void setGeneralLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		this.generalLabelMap = labelDescriptor.getLabelMap();
		if (keyWidgetMap != null)
			buildGeneralLabelBlock();
	}

	public void setAlternativeLabelDescriptor(
			KeyboardLabelDescriptor labelDescriptor) {
		this.alternativeLabelMap = labelDescriptor.getLabelMap();
		if (keyWidgetMap != null)
			buildAlternativeLabelBlock();
	}

	private void buildBlock(List<RowDescriptor> rows, int left, int top) {
		for (KeyboardDescriptor.RowDescriptor kr : rows) {
			buildRow(kr.getRow(), left, top, KEY_HEIGHT);
			top += KEY_HEIGHT + KEY_SPACE_VERTICAL;
		}
	}

	private void buildAlternativeLabelBlock() {
		assert (alternativeLabelMap != null) : "alternative label descriptor null";
		for (Map.Entry<Key, KeyWidget> e : keyWidgetMap.entrySet()) {
			buildAlternativeLabelKey(e.getKey(), e.getValue());
		}
	}

	private void buildAlternativeLabelKey(Key key, KeyWidget keyWidget) {
		String label = alternativeLabelMap.get(key);
		if (label != null) {
			keyWidget.setAlternativeLabel(label);
		}
	}

	private void buildGeneralLabelBlock() {
		assert (generalLabelMap != null) : "general label descriptor null";
		for (Map.Entry<Key, KeyWidget> e : keyWidgetMap.entrySet()) {
			buildGeneralLabelKey(e.getKey(), e.getValue());
		}
	}

	private void buildGeneralLabelKey(Key key, KeyWidget keyWidget) {
		String label = generalLabelMap.get(key);
		if (label != null) {
			keyWidget.setGeneralLabel(label);
		}
	}

	private void buildKey(Key key, int left, int top, int width, int height) {
		KeyWidget keyWidget = new KeyWidget(key, left, top, width, height);
		keyWidget.setStyleName(KEY_WIDGET_STYLE);
		keyArea.add(keyWidget, left, top);
		keyWidgetMap.put(key, keyWidget);

		if (generalLabelMap != null)
			buildGeneralLabelKey(key, keyWidget);
		if (alternativeLabelMap != null)
			buildAlternativeLabelKey(key, keyWidget);

	}

	private void buildRow(List<KeyDescriptor> keys, int left, int top,
			int height) {
		for (KeyDescriptor keyDescriptor : keys) {
			int width;
			if (keyDescriptor.getWidth() == null) {
				width = KEY_WIDTH;
			} else {
				width = Integer.parseInt(keyDescriptor.getWidth());
			}
			buildKey(keyDescriptor.getKey(), left, top, width, height);
			left += width + KEY_SPACE_HORIZONTAL;
		}
	}

	public void addKeyInputHandler(KeyInputHandler handler) {
		this.keyInputHandler = handler;
		if (keyWidgetMap == null)
			return;
		for (KeyWidget kw : keyWidgetMap.values()) {
			kw.addKeyInputHandler(handler);
		}
	}

	public void addNativeKeyInputHandler(NativeKeyInputHandler handler) {
		this.nativeKeyInputHandler = handler;
		if (keyWidgetMap == null)
			return;
		for (KeyWidget kw : keyWidgetMap.values()) {
			kw.addNativeKeyInputHandler(handler);
		}
	}
}