package org.fingerfing.client.view;

import java.util.Map;

import org.fingerfing.client.domain.Key;

public interface KeyboardLabelDescriptor {

		Map<Key, String> getLabelMap();

		void setLabelMap(Map<Key, String> map);
		
}
