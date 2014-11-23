package org.fingerfing.client.presenter.event;

import com.google.gwt.event.shared.GwtEvent;


public class ExerciseDescriptorChangeEvent extends GwtEvent<ExerciseDescriptorChangeEventHandler> {

	public static final Type<ExerciseDescriptorChangeEventHandler> TYPE = new Type<ExerciseDescriptorChangeEventHandler>();
	
	@Override
	public Type<ExerciseDescriptorChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExerciseDescriptorChangeEventHandler handler) {
		handler.onExerciseDescriptorChange(this);
	}

}
