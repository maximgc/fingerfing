package org.fingerfing.client.core;

import java.util.List;

public class Exercise {

	private final ExerciseDescriptor exerciseDescriptor;
	private Element curElem;

	private Attempt lastAttempt;

	public Exercise(ExerciseDescriptor exerciseDescriptor) {
		assert (exerciseDescriptor != null);
		assert (exerciseDescriptor.getSequence() != null);
		this.exerciseDescriptor = exerciseDescriptor;
		this.curElem = getElement(0);
	}

	public Element getCurElement() {
		return curElem;
	}

	public Attempt getLastAttempt() {
		return lastAttempt;
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

	public List<NativeKey> getSequence() {
		return exerciseDescriptor.getSequence();
	}

	public void makeAttempt(NativeKey nativeKey) {
		assert (curElem != null);
		if (curElem.getNativeKey().equals(nativeKey)) {
			lastAttempt = new Attempt(curElem, 0, nativeKey, 1);
			if (hasNextElement()) {
				curElem = nextElement();
			} else {
				curElem = null;
			}
		} else {
			lastAttempt = new Attempt(curElem, 0, nativeKey, 2);
		}
	}

	private Element getElement(int i) {
		// WARN
		if (i < exerciseDescriptor.getSequence().size()) {
			return new Element(i, exerciseDescriptor.getSequence().get(i));
		} else {
			throw new RuntimeException("Element not found");
		}

	}

	private boolean hasNextElement() {
		// WARN
		return curElem.getPos() + 1 < exerciseDescriptor.getSequence().size();
	}

	private Element nextElement() {
		if (hasNextElement()) {
			return getElement(curElem.getPos() + 1);
		} else {
			throw new RuntimeException("next Element not found");
		}
	}

}
