package examples;

/**
 * A node in a binary tree. Each non-leaf ("internal") node stores a single
 * element and links to left, right, and parent nodes. Leaf nodes do not store
 * content or have left or right links. Implementers may choose to represent
 * leaf nodes using {@code null} references, or as normal TreeNode instances.
 * 
 * @author {@code Maxwell Terpstra <C0380979@intra.camosun.bc.ca>}
 * @param <E> type of stored elements
 * @param <T> TreeNode type that is valid to link to
 */
public interface TreeNode<E, T extends TreeNode<E,?>> {
    
    /**
     * Get the value stored in this node.
     * @return the stored content
     * @throws UnsupportedOperationException (optional) if this is a leaf node
     */
    E getContent();
    
    /**
     * Change the value stored in this node.
     * @param value new content to store
     * @throws UnsupportedOperationException if this is a leaf node
     */
    void setContent(E value);
    
    /**
     * Get the left child node.
     * @return the left child, or null
     * @throws UnsupportedOperationException (optional) if this is a leaf node
     */
    T getLeft();
    
    /**
     * Change the left child to the given node.
     * @param child new left child
     * @throws UnsupportedOperationException if this is a leaf node
     */
    void setLeft(T child);

    /**
     * Get the right child node.
     * @return the right child, or null
     * @throws UnsupportedOperationException (optional) if this is a leaf node
     */
    T getRight();
    
    /**
     * Change the right child to the given node.
     * @param child new right node
     * @throws UnsupportedOperationException if this is a leaf node
     */
    void setRight(T child);

    /**
     * Get the parent of this node. The parent of a leaf node is always a
     * non-leaf node.
     * @return the parent node
     */
    T getParent();
    
    /**
     * Change the parent of this node.
     * @param parent the new parent node
     * @throws UnsupportedOperationException if this is a leaf node
     */
    void setParent(T parent);
    
    /**
     * Returns true if this is a leaf node. Leaf nodes do not hold content and
     * cannot have children. Implementers may choose to represent leaf nodes
     * as {@code null} references, in which case this method will always return
     * false.
     * @return true if this is a leaf node; false otherwise
     */
    boolean isLeaf();
    
}
