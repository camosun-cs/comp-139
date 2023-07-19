package ca.camosun.ics124.lab6;

/**
 * A node in a binary tree. Each node stores a single element and links to left,
 * right, and parent nodes.
 * 
 * @author Maxwell Terpstra {@code <terpstrama@camosun.ca>}
 * @param <E> type of stored elements
 * @param <T> TreeNode type that is valid to link to
 */
public interface TreeNode<E, T extends TreeNode<E,?>> {
    
    /**
     * Get the value stored in this node.
     * @return the stored content
     */
    E getContent();
    
    /**
     * Change the value stored in this node.
     * @param value new content to store
     */
    void setContent(E value);
    
    /**
     * Get the left child node.
     * @return the left child, or null
     */
    T getLeft();
    
    /**
     * Change the left child to the given node.
     * @param child new left child
     */
    void setLeft(T child);

    /**
     * Get the right child node.
     * @return the right child, or null
     */
    T getRight();
    
    /**
     * Change the right child to the given node.
     * @param child new right node
     */
    void setRight(T child);

    /**
     * Get the parent of this node.
     * @return the parent node
     */
    T getParent();
    
}
