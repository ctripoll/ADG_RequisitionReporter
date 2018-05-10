package adg.nonpostedreport.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the array list class.
 * 
 * @author Courtney Ripoll
 *
 */
public class ArrayBasedListTest {

	/** The ArrayList to test with */
	private ArrayBasedList<String> testArray = new ArrayBasedList<String>();

	/**
	 * Test method for ArrayBasedList.size()
	 */
	@Test
	public void testSize() {
		testArray = new ArrayBasedList<String>();
		assertEquals(0, testArray.size());
		testArray.add("hi");
		testArray.add("hey");
		testArray.add("hello");
		assertEquals(3, testArray.size());
	}

	/**
	 * Test method for ArrayBasedList.isEmpty()
	 */
	@Test
	public void testIsEmpty() {
		testArray = new ArrayBasedList<String>();
		assertTrue(testArray.isEmpty());
		testArray.add("hi");
		assertTrue(!testArray.isEmpty());
	}

	/**
	 * Test method for ArrayBasedList.addObject()
	 */
	@Test
	public void testAddObject() {
		testArray = new ArrayBasedList<String>();
		assertEquals(0, testArray.size());
		testArray.add("math");
		testArray.add("science");
		testArray.add("cs");
		assertEquals("math", testArray.get(0));
		assertEquals("science", testArray.get(1));
		assertEquals("cs", testArray.get(2));
		try {
			testArray.add(null);
			fail();
		} catch (Exception e) {
			// skip
		}
	}

	/**
	 * Test method for ArrayBasedList.get()
	 */
	@Test
	public void testGet() {
		testArray = new ArrayBasedList<String>();
		testArray.add("Hola");
		testArray.add("Como Estas");
		assertEquals("Hola", testArray.get(0));
		assertEquals("Como Estas", testArray.get(1));
		try {
			testArray.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}
	}

	/**
	 * Test method for ArrayList.indexOf()
	 */
	@Test
	public void testIndexOf() {
		testArray = new ArrayBasedList<String>();
		testArray.add("math");
		testArray.add("reading");
		testArray.add("science");
		testArray.add("social studies");
		assertEquals(0, testArray.indexOf("math"));
		assertEquals(1, testArray.indexOf("reading"));
		assertEquals(2, testArray.indexOf("science"));
		assertEquals(3, testArray.indexOf("social studies"));
		assertEquals(-1, testArray.indexOf("invalid"));
	}

	/**
	 * Test method for ArrayList.set()
	 */
	@Test
	public void testSetIntE() {
		ArrayBasedList<String> empty = new ArrayBasedList<String>();
		try {
			empty.set(0, "hey");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(empty.size(), 0);
		}
		ArrayBasedList<String> invalidList = new ArrayBasedList<String>();
		invalidList.add("hi");
		assertEquals(invalidList.size(), 1);
		try {
			invalidList.set(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(invalidList.get(0), "hi");
		}
		try {
			invalidList.set(-1, "hello");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(invalidList.get(0), "hi");
		}
		try {
			invalidList.set(2, "hello");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(invalidList.get(0), "hi");
		}
		ArrayBasedList<String> list = new ArrayBasedList<String>();
		assertEquals(0, list.size());
		list.add("hello");
		assertEquals(1, list.size());
		list.add("hi");
		assertEquals(2, list.size());
		list.add("hey");
		assertEquals(3, list.size());
		assertEquals(list.get(2), "hey");
		list.set(2, "whats up");
		assertEquals(list.get(2), "whats up");
	}

	/**
	 * Test ArrayList.contains()
	 */
	@Test
	public void testContains() {
		testArray = new ArrayBasedList<String>();
		assertEquals(0, testArray.size());
		testArray.add("hi");
		testArray.add("hey");
		testArray.add("hello");
		assertTrue(testArray.contains("hi"));
		assertTrue(!testArray.contains("whoo"));
	}

}
