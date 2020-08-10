package examples;

/**
 * A node in a binary tree. Each node stores a single element and links
 * to left, right, and parent nodes.
 * 
 * <p>Note that TreeNodes have a {@link #getParent()} method, but no
 * corresponding {@code setParent}. The parent of a node should only ever be
 * changed indirectly by invoking the parent node's {@link #setLeft(TreeNode)}
 * or {@link #setRight(TreeNode)} method.</p>
 * 
 * @author {@code Maxwell Terpstra <C0380979@intra.camosun.bc.ca>}
 * @param <E> type of stored element
 */
public interface TreeNode<E> {
    
    /**
     * Get the value stored in this node.
     * @return the stored content
     */
    E getContent();
    
    /**
     * Change the value stored in this node.
     */
    void setContent();
    
    /**
     * Get the left child node.
     * @return the left child, or null
     */
    TreeNode<E> getLeft();
    
    /**
     * Change the left child to the given node. Also updates the child's
     * parent link to refer back to this node.
     * @param child new left child
     */
    void setLeft(TreeNode<E> child);

    /**
     * Get the right child node.
     * @return the right child, or null
     */
    TreeNode<E> getRight();
    
    /**
     * Change the right child to the given node. Also updates the child's parent
     * link to refer back to this node.
     * @param child new right node
     */
    void setRight(TreeNode<E> child);

    /**
     * Get the parent of this node.
     * @return the parent node, or null
     */
    TreeNode<E> getParent();
    
    /**
     * Returns true if this is a leaf node. A leaf node has no left or right
     * child
     * @return false if this node has a left or right child; true otherwise
     */
    boolean isLeaf();
    
}
