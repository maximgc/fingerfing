package org.fingerfing.client.json;

import org.fingerfing.client.core.ExerciseDescriptor;
import org.fingerfing.client.widget.KeyboardDescriptor;
import org.fingerfing.client.widget.KeyboardLabelDescriptor;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface DescriptorFactory extends AutoBeanFactory {
	
	DescriptorFactory INST = GWT.create(DescriptorFactory.class);

	AutoBean<ExerciseDescriptor> createExerciseDescriptor();

	AutoBean<KeyboardDescriptor> createKeyboardDescriptor();
	
	AutoBean<KeyboardLabelDescriptor> createKeyboardLabelDescriptor();
	
}
