package ca.camosun.comp139.lab6;

/**
 * A height-balanced binary search tree. This is a <em>marker interface</em>
 * that does not introduce any new methods; its use indicates that the
 * implementing tree class is self-balancing.
 * @author {@code Maxwell Terpstra <terpstrama@camosun.ca>}
 * @param <E> type of data stored by this tree
 */
public interface BalancedBTree<E> extends BinaryTree<E> {}
