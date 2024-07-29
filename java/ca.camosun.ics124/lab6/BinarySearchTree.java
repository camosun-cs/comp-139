package ca.camosun.ics124.lab6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A sorted collection backed by a binary tree. Stored values must implement the
 * {@link Comparable} interface, and will be sorted according to their natural
 * order. <code>null</code> data values are not supported. Duplicate values may
 * be added, and the collection will preserve their order (later added items
 * last).
 *
 * @author Maxwell Terpstra
 * @param <E> data stored in this collection
 */
public abstract class BinarySearchTree<E extends Comparable<? super E>> implements Iterable<E> {

    /**
     * Storage unit for the tree. Sub-classes of {@link BinarySearchTree} must
     * use this node type.
     */
    protected class Node {

        /**
         * Data stored in this node.
         */
        public E content;
        /**
         * Link to a node with a smaller value.
         */
        public Node leftChild;
        /**
         * Link to a node with an equal or greater value.
         */
        public Node rightChild;
        /**
         * Tree balance factor at this node. The actual value used is
         * implementation defined, and may not be meaningful if the tree
         * implementation is not balanced. Otherwise, this value will be used to
         * detect when re-balancing is required.
         */
        public int balance;

        /**
         * Construct an empty, unlinked node.
         */
        public Node() {
        }
    }
    /**
     * Base of the storage tree. This will be null if the tree is empty.
     */
    protected Node root;

    /**
     * Check whether or not this collection contains the given value. Equivalent
     * values are determined according to {@link Comparable#compareTo}.
     *
     * @param value the value to search for
     * @return true if the given value is present; false otherwise
     */
    public boolean has(E value) {
        Node current = root;
        while (current != null) {
            int cmp = value.compareTo(current.content);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return false;
    }

    /**
     * Add a value to the collection. Duplicates are allowed and will be
     * preserved in order of addition (oldest first).
     *
     * @param value the data value to add
     * @return true if the added value is unique within the collection; false if
     * a duplicate value is already present
     * @throws NullPointerException if a null value is provided
     */
    public abstract boolean add(E value) throws NullPointerException;

    /**
     * Remove a value from the collection. Returns false when called with a null
     * parameter, since null values cannot be stored in this collection.
     *
     * @param value the value to remove
     * @return true if the collection was modified; false if the given value was
     * not in the collection
     */
    public abstract boolean remove(E value);

    /**
     * Create an iterator over all stored values, in natural order. Duplicate
     * values will be provided in the order that they were added to the
     * collection. This is a fail-fast iterator; that is, changes to the
     * collection after the iterator is constructed will cause the iterator to
     * throw {@link java.util.ConcurrentModificationException} on any subsequent
     * action.
     *
     * @return an inorder iterator
     */
    public abstract Iterator<E> iterator();

    /**
     * Get the largest value in the collection. If the collection contains
     * duplicate maximum values, there are no guarantees about which specific
     * duplicate is returned.
     *
     * @return the maximum value
     * @throws NoSuchElementException if the collection is empty
     */
    public abstract E max() throws NoSuchElementException;

    /**
     * Get the smallest value in the collection. If the collection contains
     * duplicate minimum values, there are no guarantees about which specific
     * duplicate is returned.
     *
     * @return the minimum value
     * @throws NoSuchElementException if the collection is empty
     */
    public abstract E min();

    /**
     * Count the number of values contained within this collection, including
     * duplicates.
     *
     * @return number of stored values
     */
    public abstract int size();
}
