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
		ed = createExerciseDescriptor(NativeKey.KEY_Q, NativeKey.KEY_W,
				NativeKey.KEY_E);
		e = new Exercise(ed);
	}

	private ExerciseDescriptor createExerciseDescriptor(NativeKey... sequence) {
		return createExerciseDescriptor(Arrays.asList(sequence));
	}

	private ExerciseDescriptor createExerciseDescriptor(List<NativeKey> sequence) {
		class ExerciseDescriptorImpl implements ExerciseDescriptor {

			private List<NativeKey> sequence;

			public ExerciseDescriptorImpl(List<NativeKey> sequence) {
				this.sequence = sequence;
			}

			@Override
			public void setSequence(List<NativeKey> sequence) {
				this.sequence = sequence;
			}

			@Override
			public List<NativeKey> getSequence() {
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
		assertEquals(NativeKey.KEY_Q, e.getCurrentElement().getNativeKey());
	}

	@Test
	public void testMakeAndGetLastAttempt() {
		Exercise ee = new Exercise(ed);
		Attempt a;
		a = ee.makeElementAttempt(NativeKey.KEY_0);
		assertEquals(2, a.getEval());
		a = ee.makeElementAttempt(NativeKey.KEY_Q);
		assertEquals(1, a.getEval());
	}

	@Test
	public void testHasCurrentElement() {
		Exercise ee = new Exercise(ed);
		assertTrue(ee.hasCurrentElement());
	}

	@Test(expected = CoreException.class)
	public void testNullExerciseDescription() {
		new Exercise(null);
	}

	@Test(expected = CoreException.class)
	public void testNullSequence() {
		new Exercise(createExerciseDescriptor((List<NativeKey>) null));
	}

	@Test
	public void testEmptySequence() {
		Exercise ee = new Exercise(createExerciseDescriptor());
		assertNotNull(ee);
		assertNotNull(ee.getSequence());
		assertFalse(ee.hasCurrentElement());
	}
	
	@Test(expected = CoreException.class)
	public void testEmptySequence2() {
		Exercise ee = new Exercise(createExerciseDescriptor());
		ee.getCurrentElement();
	}

	@Test
	public void testPassAllElements() {
		Exercise ee = new Exercise(ed);
		while (ee.hasCurrentElement()) {
			ee.makeElementAttempt(ee.getCurrentElement().getNativeKey());
		}
		ee = new Exercise(createExerciseDescriptor());
		while (ee.hasCurrentElement()) {
			ee.makeElementAttempt(ee.getCurrentElement().getNativeKey());
		}
	}

}
