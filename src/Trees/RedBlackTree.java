package Trees;

import jdk.jshell.spi.ExecutionControl;
import models.RedBlackNode;

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
}
