package examples;

/**
 * A collection implemented as a binary search tree. Stored values must
 * implement the {@link java.lang.Comparable} interface, and will be stored
 * in the tree according to their natural ordering.
 * 
 * @author {@code Maxwell Terpstra <C0380979@intra.camosun.bc.ca>}
 * @param <E> type of data stored by this tree
 */
public interface BinaryTree<E extends Comparable<? super E>> {
    /**
     * Get the node at the base of this tree.
     * @return the root node, or null if the collection is currently empty
     */
    TreeNode<E> rootNode();
    
    /**
     * Check whether or not the tree contains the given element.
     * @param element the value to search for
     * @return true if element is in this collection; false otherwise
     */
    boolean has(E element);
    
    /**
     * Find the smallest value stored in the tree.
     * @return the minimum value in this collection
     */
    E min();

    /**
     * Find the largest value stored in the tree.
     * @return the maximum value in this collection
     */
    E max();
    
    /**
     * Add a value to the tree. Duplicates are ignored.
     * @param element new value to add
     * @return false if the collection already contained the given element;
     *     true otherwise
     */
    boolean add(E element);

    /**
     * Remove a value from the tree.
     * @param element value to remove
     * @return true if the collection contained the given element; false otherwise
     */
    boolean remove(E element);
    
    /**
     * Count the number of distinct values stored in the tree.
     * @return number of elements stored in the collection
     */
    int size();
    
    /**
     * Measure the height of the tree.
     * @return the number of steps from the root to the furthest leaf node
     */
    int height();
}
