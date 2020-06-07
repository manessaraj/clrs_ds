package Trees;

import jdk.jshell.spi.ExecutionControl;
import models.RedBlackNode;
import models.TreeNode;

/*
* @author: Saraj Singh Manes
* Implementation of Chap 13, CLRS.
* */

public class RedBlackTree<T> extends Tree<T> implements TreeRotations{
    /**
     * Binary Serach trees ensure that all operations such as MINIMM, MAXIMUM, SUCCESSOR, PREDECESSOR runs in O(h)
     * time, where h is height of tree along its longest path. So if tree is not height balanced, it might be as worst as
     * Linked List. Red Black Trees makes sure that h <= 2 *lg (n) where n is number of nodes, thus run time for all
     * operations will be O(lg(n)), i.e. Best case run time for Height balanced Binary Search Trees.
     * */

    public static RedBlackNode<T> SENTINEL_NODE = new RedBlackNode.RedBlackNodeBuilder().key(null).build();



    private RedBlackNode<T> root;

    /*
    *
    * */
    public int blackHeight() {
        return blackHeight(root);
    }

    /*
    * Root is black too, and black height is # of nodes except root in any path
    * */
    public int getNumberOfBlackNodes() {
        return 1 + blackHeight();
    }


    /*
    * black-height bh(x) = number of balck nodes from, but not including x, down to a leaf.
    *
    * */
    public int blackHeight(RedBlackNode<T> node) {
        throw new UnsupportedOperationException("Method needs implementation");
    }

    @Override
    public TreeNode rotateLeft(TreeNode x) {
        assert (x.getRight() != null);
        TreeNode y = x.getRight();
        TreeNode subtreeParent = x.getParent();
        x.setRight(y.getLeft()); // move beta.
        if (x.getRight() != null) {
            x.getRight().setParent(x);
        }
        //swap x and y
        y.setLeft(x);
        x.setParent(y);

        y.setParent(subtreeParent);

        //update parent's pointers
        updateChildOfParent(subtreeParent, x, y);
        return y;
    }

    private void updateChildOfParent(TreeNode parent, TreeNode oldChild, TreeNode newChild) {
        if (parent == null) {
            // x was root
            this.root = (RedBlackNode<T>) newChild;
        } else if (parent.getRight() == oldChild) {
            parent.setRight(newChild);
        } else {
            parent.setLeft(newChild);
        }
    }

    @Override
    public TreeNode<T> rotateRight(TreeNode y) {
        assert (y.getLeft() != null);
        TreeNode subTreeParent = y.getParent();
        TreeNode x = y.getLeft();
        y.setLeft(x.getRight()); // move beta
        if (y.getLeft() != null) {
            y.getLeft().setParent(y);
        }
        //swap x and y
        x.setRight(y);
        y.setParent(x);

        x.setParent(subTreeParent);
        this.updateChildOfParent(subTreeParent, y, x);
        return x;
    }
}
