package org.fingerfing.client.json;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

public class BeanManager {
	
	BeanFactory bf = GWT.create(BeanFactory.class);
	
	public <T> T create(Class<T> clazz){
		return bf.create(clazz).as();
	}
	
	public <T> T decode(Class<T> clazz, String json) {
		return AutoBeanCodex.decode(bf, clazz, json).as();
	}
	
	public <T> String encode(T b) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b)).getPayload();
	}

}
