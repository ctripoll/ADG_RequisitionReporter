package adg.nonpostedreport.util;

/**
 * A class for an array list. Stores each components in its own cell, creating a
 * list to access.
 * 
 * @author Courtney Ripoll
 *
 * @param <E>
 *            generic type
 */
public class ArrayBasedList<E> {
	/** Increment for increasing the capacity of the array */
	private static final int RESIZE = 100;
	/** List of items */
	private E[] list;
	/** Size of the list */
	private int size;

	/**
	 * Constructs the ArrayList
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList() {
		list = (E[]) new Object[RESIZE];
	}

	/**
	 * Returns the size of the list
	 *
	 * @return the size of the list (number of E in the list)
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns true if the list has zero objects.
	 *
	 * @return true if the list has zero objects
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the element at the index
	 *
	 * @param index
	 *            index of the E to return
	 * @return the E at the index
	 */
	public E get(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * Adds an item at a specified index in the list
	 *
	 * @param e
	 *            the element to add
	 * @return true if element is added
	 */
	@SuppressWarnings("unchecked")
	public boolean add(E e) {
		if (size == list.length) {
			E[] temp = (E[]) (new Object[list.length * 2]);
			for (int i = 0; i < list.length; i++) {
				temp[i] = list[i];
			}
			list = temp;
		}
		if (e == null) {
			throw new NullPointerException();
		}
		list[size++] = e;
		return true;
	}

	/**
	 * Returns the index of the specified E
	 *
	 * @param o
	 *            the E to look for
	 * @return the index of the E in the list, or -1 if this list does not
	 *         contain the element
	 */
	public int indexOf(E o) {
		for (int i = 0; i < size; i++) {
			if (list[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Replaces an object in the list with another
	 * 
	 * @param index
	 *            the index of the object to replace
	 * @param o
	 *            the new object to replace with
	 * @return the replaced object
	 */
	public Object set(int index, E o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Object old = list[index];
		list[index] = o;
		return old;
	}

	/**
	 * Checks that the array list contains an object
	 * 
	 * @param o
	 *            element to check
	 * @return true if the list has the element
	 */
	public boolean contains(E o) {
		return indexOf(o) != -1;
	}
}
