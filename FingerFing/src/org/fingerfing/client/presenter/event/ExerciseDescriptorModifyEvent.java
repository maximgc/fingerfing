package org.fingerfing.client.presenter.event;

import com.google.gwt.event.shared.GwtEvent;


public class ExerciseDescriptorModifyEvent extends GwtEvent<ExerciseDescriptorModifyHandler> {

	public static final Type<ExerciseDescriptorModifyHandler> TYPE = new Type<ExerciseDescriptorModifyHandler>();
	
	@Override
	public Type<ExerciseDescriptorModifyHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExerciseDescriptorModifyHandler handler) {
		handler.onExerciseDescriptorModify(this);
	}

}
