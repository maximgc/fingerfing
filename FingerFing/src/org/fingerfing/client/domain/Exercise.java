package org.fingerfing.client.domain;

import java.util.Iterator;
import java.util.List;

public class Exercise {

	private class ElementNavigator implements Iterator<Element> {

		private int curPos;
		private List<Key> sequence;

		public ElementNavigator() {
			curPos = -1;
			sequence = exerciseDescriptor.getSequence();
		}

		@Override
		public boolean hasNext() {

			return curPos + 1 < sequence.size();
		}

		@Override
		public Element next() {
			curPos++;
			return new Element(curPos, sequence.get(curPos));
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}

	}

	private ExerciseDescriptor exerciseDescriptor;
	private ElementNavigator elementNavigator;
	private Element currentElement;

	public Exercise(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor == null
				|| exerciseDescriptor.getSequence() == null) {
			throw new CoreException(
					"ExerciseDescriptor doesn't define sequence");
		}
		this.exerciseDescriptor = exerciseDescriptor;
		this.elementNavigator = new ElementNavigator();
		currentElement = nextElement();
	}

	public Element getCurrentElement() {
		requireIncomplete();
		return currentElement;
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

	public List<Key> getSequence() {
		return exerciseDescriptor.getSequence();
	}

	public boolean isComplete() {
		return currentElement == null;
	}

	public Attempt makeAttempt(NativeKey nativeKey) {
		requireIncomplete();
		Attempt lastAttempt = new Attempt(currentElement, nativeKey);
		if (lastAttempt.isSuccess())
			currentElement = nextElement();
		return lastAttempt;
	}

	private Element nextElement() {
		return (elementNavigator.hasNext()) ? elementNavigator.next() : null;
	}

	private void requireIncomplete() {
		if (isComplete()) {
			throw new CoreException("Exercise is completed");
		}
	}
}
