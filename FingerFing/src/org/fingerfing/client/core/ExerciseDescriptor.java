package org.fingerfing.client.core;

import java.util.List;

public class ExerciseDescriptor {
	
	List<NativeKey> sequence;

	public ExerciseDescriptor(List<NativeKey> sequence) {
		this.sequence = sequence;
	}

	public List<NativeKey> getSequence() {
		return sequence;
	}

	
}
