package org.fingerfing.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fingerfing.client.domain.Key;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.resource.KeyboardResource;
import org.fingerfing.client.view.KeyboardDescriptor.KeyDescriptor;
import org.fingerfing.client.view.KeyboardDescriptor.RowDescriptor;
import org.fingerfing.client.view.KeyboardWidget.KeyWidget;

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
	
	private DescriptorManager dm = new DescriptorManager();

	public KeyboardBuilder(AbsolutePanel keyArea) {
		assert (keyArea != null) : "keyArea is null";
		this.keyArea = keyArea;
	}

	public Map<Key, KeyWidget> build() {
		KeyboardDescriptor kd = dm.decodeFromJson(KeyboardDescriptor.class,
				KeyboardResource.INST.getKeyboardDescriptor1().getText());
		assert (kd != null) : "KeyboardDescriptor is null";
		KeyboardLabelDescriptor kldEN = dm.decodeFromJson(
				KeyboardLabelDescriptor.class, KeyboardResource.INST
						.getKeyboardLabelDescriptorEN().getText());
		KeyboardLabelDescriptor kldRU = dm.decodeFromJson(
				KeyboardLabelDescriptor.class, KeyboardResource.INST
						.getKeyboardLabelDescriptorRU().getText());

		buildBlock(kd.getBlock(), BLOCK_LEFT, BLOCK_TOP);
		buildGeneralLabel(kldEN);
		buildAlternativeLabel(kldRU);
		return keyWidgetMap;
	}

	private void buildBlock(List<RowDescriptor> rows, int left, int top) {
		for (KeyboardDescriptor.RowDescriptor kr : rows) {
			buildRow(kr.getRow(), left, top, KEY_HEIGHT);
			top += KEY_HEIGHT + KEY_SPACE_VERTICAL;
		}
	}

	private void buildAlternativeLabel(KeyboardLabelDescriptor ld) {
		assert (ld != null) : "alternative label descriptor null";
		for (Map.Entry<Key, KeyWidget> e : keyWidgetMap.entrySet()) {
			String l = ld.getLabelMap().get(e.getKey());
			if (l != null) {
				KeyWidget keyWidget = e.getValue();
				keyWidget.setAlternativeLabel(l);
			}
		}
	}

	private void buildGeneralLabel(KeyboardLabelDescriptor ld) {
		assert (ld != null) : "general label descriptor null";
		for (Map.Entry<Key, KeyWidget> e : keyWidgetMap.entrySet()) {
			String l = ld.getLabelMap().get(e.getKey());
			if (l != null) {
				KeyWidget keyWidget = e.getValue();
				keyWidget.setGeneralLabel(l);
			}
		}
	}

	// WARN пробег по мапе 3 раза?
	private void buildKey(Key key, int left, int top, int width, int height) {
		KeyWidget keyWidget = new KeyWidget(key, left, top, width, height);
		keyWidget.setStyleName(KEY_WIDGET_STYLE);
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