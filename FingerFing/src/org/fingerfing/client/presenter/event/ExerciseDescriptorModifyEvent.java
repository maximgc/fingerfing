package org.fingerfing.client.presenter.event;

import com.google.gwt.event.shared.GwtEvent;


public class ExerciseDescriptorModifyEvent extends GwtEvent<ExerciseDescriptorModifyEventHandler> {

	public static final Type<ExerciseDescriptorModifyEventHandler> TYPE = new Type<ExerciseDescriptorModifyEventHandler>();
	
	@Override
	public Type<ExerciseDescriptorModifyEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExerciseDescriptorModifyEventHandler handler) {
		handler.onExerciseDescriptorChange(this);
	}

}
