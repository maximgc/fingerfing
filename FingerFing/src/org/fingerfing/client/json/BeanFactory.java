package org.fingerfing.client.json;

import org.fingerfing.client.core.ExerciseDescriptor;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface BeanFactory extends AutoBeanFactory {
	
	AutoBean<ExerciseDescriptor> createExerciseDescriptor();

}
