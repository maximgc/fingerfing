package org.fingerfing.client.core;

import java.util.List;

public interface ExerciseDescriptor {

	public abstract void setSequence(List<NativeKey> sequence);

	public abstract List<NativeKey> getSequence();

}