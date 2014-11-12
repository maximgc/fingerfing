package org.fingerfing.client.core;

import java.util.Iterator;
import java.util.List;

public class Exercise {

	private class ElementNavigator implements Iterator<Element> {

		private int curPos;
		private List<NativeKey> sequence;

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

	private void requireCurrentElement() {
		if (!hasCurrentElement()) {
			throw new CoreException("Current Element is undefined");
		}
	}

	public Element getCurrentElement() {
		requireCurrentElement();
		return currentElement;
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return exerciseDescriptor;
	}

	public List<NativeKey> getSequence() {
		return exerciseDescriptor.getSequence();
	}

	/**
	 * use instead isComplete()
	 * @author Max
	 */
	@Deprecated
	public boolean hasCurrentElement() {
		return currentElement != null;
	}

	public Attempt makeElementAttempt(NativeKey nativeKey) {
		Attempt lastAttempt;
		requireCurrentElement();
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

	public boolean isComplete() {
		return !hasCurrentElement();
	}
}
