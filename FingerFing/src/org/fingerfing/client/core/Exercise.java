package org.fingerfing.client.core;

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

	public Attempt makeAttempt(NativeKey key) {
		Attempt lastAttempt;
		requireIncomplete();
		//WARN ..
		if (currentElement.getKey().getNativeCode()==key.getNativeCode()) {
			lastAttempt = new Attempt(currentElement, 0, key, 1);
			currentElement = nextElement();
		} else {
			lastAttempt = new Attempt(currentElement, 0, key, 2);
		}
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
