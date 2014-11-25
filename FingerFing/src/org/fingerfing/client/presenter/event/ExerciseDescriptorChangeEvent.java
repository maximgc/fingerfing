package org.fingerfing.client.presenter.event;

import com.google.gwt.event.shared.GwtEvent;


public class ExerciseDescriptorChangeEvent extends GwtEvent<ExerciseDescriptorChangeHandler> {

	public static final Type<ExerciseDescriptorChangeHandler> TYPE = new Type<ExerciseDescriptorChangeHandler>();
	
	@Override
	public Type<ExerciseDescriptorChangeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExerciseDescriptorChangeHandler handler) {
		handler.onExerciseDescriptorChange(this);
	}

}
