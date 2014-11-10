package org.fingerfing.client.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExerciseTest {

	private ExerciseDescriptor ed;
	private Exercise e;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<NativeKey> sequence = new ArrayList<NativeKey>();
		sequence.add(NativeKey.KEY_Q);
		sequence.add(NativeKey.KEY_W);
		sequence.add(NativeKey.KEY_E);
		ed = new ExerciseDescriptor(sequence);  
		e = new Exercise(ed);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExercise() {
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
		Exercise ee = new Exercise(new ExerciseDescriptor(null));
		assertNotNull(ee);
	}
	
	@Test
	public void testEmptySequence() {
		Exercise ee = new Exercise(new ExerciseDescriptor(new ArrayList<NativeKey>()));
		assertThat(ee, notNullValue());
		assertThat(ee.getSequence(), notNullValue());
		assertThat(ee.hasCurrentElement(), is(false));
	}
	
	@Test
	public void testPassAllElements() {
		Exercise ee = new Exercise(ed);
		while (ee.hasCurrentElement()){
			ee.makeElementAttempt(ee.getCurrentElement().getNativeKey());
		}
		ee = new Exercise(new ExerciseDescriptor(new ArrayList<NativeKey>()));
		while (ee.hasCurrentElement()){
			ee.makeElementAttempt(ee.getCurrentElement().getNativeKey());
		}
	}

}
