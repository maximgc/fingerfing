package org.fingerfing.client.resource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface KeyboardResource extends ClientBundle{
	
	public final KeyboardResource INST = GWT.create(KeyboardResource.class);
	
	@Source("KeyboardDescriptor1.json")
	public TextResource getKeyboardDescriptor1();

	@Source("KeyboardDescriptor2.json")
	public TextResource getKeyboardDescriptor2();

	@Source("KeyboardLabelDescriptorEN.json")
	public TextResource getKeyboardLabelDescriptorEN();
	
	@Source("KeyboardLabelDescriptorRU.json")
	public TextResource getKeyboardLabelDescriptorRU();
	
	
}
