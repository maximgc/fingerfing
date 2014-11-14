package org.fingerfing.client.widget;

import java.util.Map;

import org.fingerfing.client.core.Key;

public interface KeyboardLabelDescriptor {

		Map<Key, String> getLabelMap();

		void setLabelMap(Map<Key, String> map);
		
}
