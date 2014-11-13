package org.fingerfing.client.widget;

import java.util.Map;

import org.fingerfing.client.core.NativeKey;

public interface KeyboardLabelDescriptor {

		Map<NativeKey, String> getLabelTextMap();

		void setLabelTextMap(Map<NativeKey, String> map);
		
}
