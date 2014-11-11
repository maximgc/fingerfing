package org.fingerfing.client.core;

import java.util.Iterator;
import java.util.List;

public class Exercise {

	private class ElementNavigator implements Iterator<Element> {

		private int cur;

		public ElementNavigator() {
			cur = -1;
		}

		@Override
		public boolean hasNext() {
			return cur + 1 < exerciseDescriptor.getSequence().size();
		}

		@Override
		public Element next() {
			cur++;
			return new Element(cur, exerciseDescriptor.getSequence().get(cur));
		}
	}

	private ExerciseDescriptor exerciseDescriptor;
	private ElementNavigator elementNavigator;
	private Element currentElement;

	public Exercise(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor == null
				|| exerciseDescriptor.getSequence() == null) {
			throw new FFRuntimeException(
					"ExerciseDescriptor doesn't define sequence");
		}
		this.exerciseDescriptor = exerciseDescriptor;
		this.elementNavigator = new ElementNavigator();
		currentElement = nextElement();
	}

	public Element getCurrentElement() {
		if (hasCurrentElement()) {
			return currentElement;
		}
		throw new FFRuntimeException("Current Element is undefined");
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

	public List<NativeKey> getSequence() {
		return exerciseDescriptor.getSequence();
	}

	public boolean hasCurrentElement() {
		return currentElement != null;
	}

	public Attempt makeElementAttempt(NativeKey nativeKey) {
		Attempt lastAttempt;
		if (!hasCurrentElement()) {
			throw new FFRuntimeException("Current Element is undefined");
		}
		if (currentElement.is(nativeKey)) {
			lastAttempt = new Attempt(currentElement, 0, nativeKey, 1);
			currentElement = nextElement();
		} else {
			lastAttempt = new Attempt(currentElement, 0, nativeKey, 2);
		}
		return lastAttempt;
	}

	private Element nextElement() {
		return (elementNavigator.hasNext()) ? elementNavigator.next() : null;
	}
}
