package org.fingerfing.client.core;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ExerciseTest {

	private ExerciseDescriptor ed;
	private Exercise e;

	@Before
	public void setUp() throws Exception {
		ed = createExerciseDescriptor(Key.KEY_Q, Key.KEY_W, Key.KEY_E);
		e = new Exercise(ed);
	}

	private ExerciseDescriptor createExerciseDescriptor(Key... sequence) {
		return createExerciseDescriptor(Arrays.asList(sequence));
	}

	private ExerciseDescriptor createExerciseDescriptor(List<Key> sequence) {
		class ExerciseDescriptorImpl implements ExerciseDescriptor {

			private List<Key> sequence;

			public ExerciseDescriptorImpl(List<Key> sequence) {
				this.sequence = sequence;
			}

			@Override
			public void setSequence(List<Key> sequence) {
				this.sequence = sequence;
			}

			@Override
			public List<Key> getSequence() {
				return this.sequence;
			}

		}
		return new ExerciseDescriptorImpl(sequence);
	}

	@Test
	public void testExercise() {
		assertNotNull(ed);
		Exercise ee = new Exercise(ed);
		assertEquals(3, ee.getSequence().size());
	}

	@Test
	public void testGetCurrentElement() {
		assertEquals(Key.KEY_Q, e.getCurrentElement().getKey());
	}

	@Test
	public void testMakeAndGetLastAttempt() {
		Exercise ee = new Exercise(ed);
		Attempt a;
		a = ee.makeAttempt(NativeKey.KEY_0);
		assertEquals(2, a.getEval());
		a = ee.makeAttempt(NativeKey.KEY_Q);
		assertEquals(1, a.getEval());
	}

	@Test
	public void testHasCurrentElement() {
		Exercise ee = new Exercise(ed);
		assertFalse(ee.isComplete());
	}

	@Test(expected = CoreException.class)
	public void testNullExerciseDescription() {
		new Exercise(null);
	}

	@Test(expected = CoreException.class)
	public void testNullSequence() {
		new Exercise(createExerciseDescriptor((List<Key>) null));
	}

	@Test
	public void testEmptySequence() {
		Exercise ee = new Exercise(createExerciseDescriptor());
		assertNotNull(ee);
		assertNotNull(ee.getSequence());
		assertTrue(ee.isComplete());
	}

	@Test(expected = CoreException.class)
	public void testEmptySequence2() {
		Exercise ee = new Exercise(createExerciseDescriptor());
		ee.getCurrentElement();
	}

	@Test
	public void testPassAllElements() {
		Exercise ee = new Exercise(ed);
		while (!ee.isComplete()) {
			NativeKey nk = NativeKey.getByNativeCode(ee.getCurrentElement()
					.getKey().getNativeCode());
			ee.makeAttempt(nk);
		}
		ee = new Exercise(createExerciseDescriptor());
		while (!ee.isComplete()) {
			NativeKey nk = NativeKey.getByNativeCode(ee.getCurrentElement()
					.getKey().getNativeCode());
			ee.makeAttempt(nk);
		}
	}

}
