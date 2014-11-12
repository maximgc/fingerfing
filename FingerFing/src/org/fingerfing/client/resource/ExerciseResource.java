package org.fingerfing.client.resource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface ExerciseResource extends ClientBundle{
	
	public final ExerciseResource INST = GWT.create(ExerciseResource.class);
	
	@Source(value = "ExerciseDescriptor1.json")
	public TextResource getJsonExerciseDescriptor1(); 

}
