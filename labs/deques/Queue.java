package examples;
import java.util.NoSuchElementException;

public interface Queue<T> {
	/**
	 * Returns the number of available spaces left in the queue.
	 * @return how many additional items the queue can hold.
	 */
	int capacity();

	/**
	 * Adds an element to the end of the queue.
	 * @param e the element to add to this collection
	 * @throws IllegalStateException if the queue cannot hold additional items
	 */
	void enqueue(T e);

	/**
	 * Retrieves and removes the element at the head of the queue.
	 * @return the least-recently added element.
	 * @throws NoSuchElementException if the queue is empty
	 */
	T dequeue();

	/**
	 * Retrieves (but does not remove) the element at the head of the queue.
	 * @return the least-recently added element.
	 * @throws NoSuchElementException if the queue is empty
	 */
	T peek();

	/**
	 * Remove all items from the queue and reset the capacity to the initial
	 * maximum amount.
	 */
	void reset();
}
