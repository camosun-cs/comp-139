package examples;
import java.util.NoSuchElementException;

public interface Deque<T> {
	/**
	 * Returns the number of available spaces left in the deque.
	 * @return how many additional items the deque can hold.
	 */
	int capacity();

	/**
	 * Checks whether any elements are currently waiting in the deque.
	 * @return true if the deque is empty; false otherwise.
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements currently waiting in the deque.
	 * @return how many items are in this collection
	 */
	int size();

	/**
	 * Queues an element at the front of the deque.
	 * @param e the element to add to this collection
	 * @throws IllegalStateException if the deque cannot hold additional items
	 */
	void addFirst(T e);

	/**
	 * Queues an element at the back of the deque.
	 * @param e the element to add to this collection
	 * @throws IllegalStateException if the deque cannot hold additional items
	 */
	void addLast(T e);

	/**
	 * Retrieves and removes the element at the front of the deque.
	 * @return the element waiting at the front of the deque.
	 * @throws NoSuchElementException if the queue is empty
	 */
	T removeFirst();

	/**
	 * Retrieves (but does not remove) the element at the front of the deque.
	 * @return the element waiting at the front of the deque.
	 * @throws NoSuchElementException if the queue is empty
	 */
	T peekFirst();

	/**
	 * Retrieves and removes the element at the back of the deque.
	 * @return the element waiting at the back of the deque.
	 * @throws NoSuchElementException if the queue is empty
	 */
	T removeLast();

	/**
	 * Retrieves (but does not remove) the element at the back of the deque.
	 * @return the element waiting at the back of the deque.
	 * @throws NoSuchElementException if the queue is empty
	 */
	T peekLast();
}
