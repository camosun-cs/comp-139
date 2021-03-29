package ca.camosun.comp139.lab6;

/**
 * A node for storing data in a binary tree structure. Each node stores a single
 * data element and links to left and right child nodes (either of which may be
 * null).
 *
 * @author {@code Maxwell Terpstra <terpstrama@camosun.ca>}
 * @param <E> type of stored data elements
 * @param <T> TreeNode type that is valid to link to
 */
public interface TreeNode<E, T extends TreeNode<E,?>> {

    /**
     * Get the value stored in this node.
     * @return the stored content
     */
    E getContent();

    /**
     * Get the left child node.
     * @return the left child, or {@code null} if this node does not have a left child
     */
    T getLeft();

    /**
     * Get the right child node.
     * @return the right child, or {@code null} if this node does not have a right child
     */
    T getRight();

}
