package examples;

/**
 * A height-balanced binary search tree. This is a <em>marker interface</em>
 * that does not introduce any new methods; its use indicates that the
 * implementing tree class is self-balancing.
 * @author Maxwell Terpstra
 * @param <E> type of data stored by this tree
 */
public interface BalancedBTree<E extends Comparable<? super E>> extends BinaryTree<E> {}
