package ca.camosun.ics124.lab6;

/**
 * A height-balanced binary search tree. This interface does not introduce any
 * new methods beyond BinaryTree, but its use indicates that the implementing
 * tree class is self-balancing. As a result, all methods of the tree are
 * expected to run in O(logn) time.
 * @author Maxwell Terpstra {@code <terpstrama@camosun.ca>}
 * @param <E> type of data stored by this tree
 */
public interface BalancedBTree<E extends Comparable<? super E>> extends BinaryTree<E> {}
