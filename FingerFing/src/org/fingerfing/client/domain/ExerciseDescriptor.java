package org.fingerfing.client.domain;

import java.util.List;

public interface ExerciseDescriptor {

	public abstract void setSequence(List<Key> sequence);

	public abstract List<Key> getSequence();

}