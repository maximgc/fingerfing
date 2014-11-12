package org.fingerfing.client.json;

import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

public class DescriptorManager {
	
	private DescriptorFactory df = DescriptorFactory.INST;
	
	public <T> T create(Class<T> clazz){
		return df.create(clazz).as();
	}
	
	public <T> T decodeFromJson(Class<T> clazz, String json) {
		return AutoBeanCodex.decode(df, clazz, json).as();
	}
	
	public <T> String encodeToJson(T b) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b)).getPayload();
	}

}
