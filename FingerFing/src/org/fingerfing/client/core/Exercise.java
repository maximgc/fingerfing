package org.fingerfing.client.core;

import java.util.List;

public class Exercise {

	private final ExerciseDescriptor exerciseDescriptor;

	private Element currentElement;
	private Attempt lastAttempt;

	public Exercise(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor == null || exerciseDescriptor.getSequence()==null){
			throw new RuntimeException("ExerciseDescriptor doesn't define sequence");
		}
		this.exerciseDescriptor = exerciseDescriptor;
		this.currentElement = nextElement();
	}

	public Element getCurrentElement() {
		if (hasCurrentElement()) {
			return currentElement;
		}
		throw new RuntimeException("Current Element is undefined");
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

	public Attempt getLastAttempt() {
		if (lastAttempt != null) {
			return lastAttempt;
		}
		throw new RuntimeException("Last Attempt is undefined");
	}

	public List<NativeKey> getSequence() {
		return exerciseDescriptor.getSequence();
	}

	public boolean hasCurrentElement() {
		return currentElement != null;
	}

	public void makeElementAttempt(NativeKey nativeKey) {
		if (!hasCurrentElement()) {
			throw new RuntimeException("Current Element is undefined");
		}
		if (currentElement.getNativeKey().equals(nativeKey)) {
			lastAttempt = new Attempt(currentElement, 0, nativeKey, 1);
			currentElement = nextElement();
		} else {
			lastAttempt = new Attempt(currentElement, 0, nativeKey, 2);
		}
	}

	private Element getElement(int i) {
		if (hasElement(i)) {
			return new Element(i, getSequence().get(i));
		} else {
			throw new RuntimeException("Element not found");
		}

	}

	private boolean hasElement(int i) {
		return i < getSequence().size();
	}

	private Element nextElement() {
		if (hasCurrentElement() && hasElement(currentElement.getPos() + 1)) {
			return getElement(currentElement.getPos() + 1);
		} else if (!hasCurrentElement() && hasElement(0)) {
			return getElement(0);
		} else {
			return null;
		}
	}

}
