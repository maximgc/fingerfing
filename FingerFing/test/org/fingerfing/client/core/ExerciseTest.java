package org.fingerfing.client.core;

import java.util.ArrayList;
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
		ArrayList<NativeKey> sequence = new ArrayList<NativeKey>();
		sequence.add(NativeKey.KEY_Q);
		sequence.add(NativeKey.KEY_W);
		sequence.add(NativeKey.KEY_E);
		ed = createExerciseDescriptor(sequence);
		e = new Exercise(ed);
	}

	private ExerciseDescriptor createExerciseDescriptor(
			ArrayList<NativeKey> sequence) {
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
		ee.makeElementAttempt(NativeKey.KEY_0);
		assertEquals(2, ee.getLastAttempt().getEval());
		ee.makeElementAttempt(NativeKey.KEY_Q);
		assertEquals(1, ee.getLastAttempt().getEval());
	}

	@Test
	public void testHasCurrentElement() {
		Exercise ee = new Exercise(ed);
		assertTrue(ee.hasCurrentElement());
	}

	@Test(expected = RuntimeException.class)
	public void testNullExerciseDescription() {
		Exercise ee = new Exercise(null);
		assertNotNull(ee);
	}

	@Test(expected = RuntimeException.class)
	public void testNullSequence() {
		Exercise ee = new Exercise(createExerciseDescriptor(null));
		assertNotNull(ee);
	}

	@Test
	public void testEmptySequence() {
		Exercise ee = new Exercise(
				createExerciseDescriptor(new ArrayList<NativeKey>()));
		assertNotNull(ee);
		assertNotNull(ee.getSequence());
		assertFalse(ee.hasCurrentElement());

	}

	@Test
	public void testPassAllElements() {
		Exercise ee = new Exercise(ed);
		while (ee.hasCurrentElement()) {
			ee.makeElementAttempt(ee.getCurrentElement().getNativeKey());
		}
		ee = new Exercise(createExerciseDescriptor(new ArrayList<NativeKey>()));
		while (ee.hasCurrentElement()) {
			ee.makeElementAttempt(ee.getCurrentElement().getNativeKey());
		}
	}

}
