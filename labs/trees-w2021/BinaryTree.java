package ca.camosun.comp139.lab6;

import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A sorted collection implemented as a binary search tree. Stored values will
 * be ordered using the tree's {@link #setComparator(Comparator) current Comparator}.
 * Implementations should require a {@code Comparator<? super E>} instance as a
 * parameter to all constructors to ensure that {@link #add(Object) add(E)} will function
 * correctly for all newly created trees.
 *
 * @author {@code Maxwell Terpstra <terpstrama@camosun.ca>}
 * @param <E> type of data stored by this tree
 */
public interface BinaryTree<E> extends Iterable<E> {
    /**
     * Get the node at the base of this tree. Used by TreeVisualizer to
     * inspect the internal structure of the collection.
     * @return the root node, or null if the collection is currently empty
     */
    TreeNode<E,?> rootNode();

    /**
     * Check whether or not the tree contains the given element.
     * @param element the value to search for
     * @return true if element is in this collection; false otherwise
     */
    boolean has(E element);

    /**
     * Get the first ("smallest") value stored in the tree. The order of
     * values is determined by the {@link #setComparator(Comparator) current Comparator}.
     * @return the minimum value in this collection
     * @throws NoSuchElementException if the tree is empty
     */
    E min() throws NoSuchElementException;

    /**
     * Get the last ("largest") value stored in the tree. The order of
     * values is determined by the {@link #setComparator(Comparator) current Comparator}.
     * @return the maximum value in this collection
     * @throws NoSuchElementException if the tree is empty
     */
    E max() throws NoSuchElementException;

    /**
     * Add a value to the tree.
     * @param element new value to add
     */
    void add(E element);

    /**
     * Remove a value from the tree.
     * @param element value to remove
     * @return true if the collection contained the given element; false otherwise
     */
    boolean remove(E element);

    /**
     * Count the number of values stored in the tree.
     * @return number of elements stored in the collection
     */
    int size();

    /**
     * Measure the height of the tree. A tree with a {@link #size()} of
     * <samp>0</samp> will have a height of <samp>0</samp>, and a tree with a
     * size of <samp>1</samp> will have a height of <samp>1</samp>.
     * Otherwise, the height will be an implementation defined value between
     * <samp>1</samp> and its size.
     * @return the length of the longest branch within the tree
     */
    int height();

    /**
     * Set the <em>current Comparator</em> used for ordering values within this
     * tree. The {@link #add(Object) add(E)}, {@link #min()}, {@link #max()},
     * and {@link #iterator()} methods make use of this Comparator to determine
     * the total ordering of all added elements. When the current Comparator is
     * changed, the resulting order of equivalent elements is implementation
     * defined (that is, previous orderings are not necessarily stable).
     * @param comp new <em>current Comparator</em> instance to use for future
     *     ordering.
     */
    void setComparator(Comparator<? super E> comp);

    /**
     * Returns an inorder iterator over the elements in this tree.
     * The iterator created by this method is <em>fail-safe</em>; that is, it
     * captures a snapshot of the current tree contents when initialized and
     * will not be affected by later changes to the tree. The
     * {@link Iterator#remove()} method must be supported, functioning equivalently
     * to calling {@link #remove(Object) remove(E)} on the underlying tree.
     * Elements are returned in the order specified by the
     * {@link #setComparator(Comparator) current Comparator} at time of
     * construction (using an "inorder" tree traversal).
     * @return a fail-safe inorder iterator over this tree's content
     */
    @Override
    Iterator<E> iterator();
}
