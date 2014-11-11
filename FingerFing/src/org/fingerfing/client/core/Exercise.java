package org.fingerfing.client.core;

import java.util.List;

public class Exercise {

	private class ElementNavigator {

		private final ExerciseDescriptor exerciseDescriptor;
		
		public ElementNavigator(ExerciseDescriptor exerciseDescriptor) {
			this.exerciseDescriptor = exerciseDescriptor;
		}

		public Element getElement(int i) {
			if (hasElement(i)) {
				return new Element(i, getSequence().get(i));
			} else {
				throw new RuntimeException("Element not found");
			}
		}

		public ExerciseDescriptor getExerciseDescriptor() {
			return exerciseDescriptor;
		}

		public List<NativeKey> getSequence() {
			return exerciseDescriptor.getSequence();
		}

		public boolean hasElement(int i) {
			return i < getSequence().size();
		}

		public Element nextElement(Element element) {
			if (element == null && hasElement(0)) {
				return getElement(0);
			} else if (hasElement(element.getPos() + 1)) {
				return getElement(element.getPos() + 1);
			} else {
				return null;
			}
		}
	}
	
	

	private ElementNavigator elementNavigator;
	private Element currentElement;

	private Attempt lastAttempt;

	public Exercise(ExerciseDescriptor exerciseDescriptor) {
		if (exerciseDescriptor == null || exerciseDescriptor.getSequence()==null){
			throw new RuntimeException("ExerciseDescriptor doesn't define sequence");
		}
		this.elementNavigator = new ElementNavigator(exerciseDescriptor);
		this.currentElement = elementNavigator.nextElement(null);
	}

	public Element getCurrentElement() {
		if (hasCurrentElement()) {
			return currentElement;
		}
		throw new RuntimeException("Current Element is undefined");
	}

	public ExerciseDescriptor getExerciseDescriptor() {
		return elementNavigator.getExerciseDescriptor();
	}

	public Attempt getLastAttempt() {
		if (lastAttempt != null) {
			return lastAttempt;
		}
		throw new RuntimeException("Last Attempt is undefined");
	}

	public List<NativeKey> getSequence() {
		return elementNavigator.getSequence();
	}

	public boolean hasCurrentElement() {
		return currentElement != null;
	}
	
	
	public void makeElementAttempt(NativeKey nativeKey) {
		if (!hasCurrentElement()) {
			throw new RuntimeException("Current Element is undefined");
		}
		if (currentElement.is(nativeKey)) {
			lastAttempt = new Attempt(currentElement, 0, nativeKey, 1);
			currentElement = elementNavigator.nextElement(currentElement);
		} else {
			lastAttempt = new Attempt(currentElement, 0, nativeKey, 2);
		}
	}
}
