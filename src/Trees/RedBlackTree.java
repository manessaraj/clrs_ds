package Trees;

import jdk.jshell.spi.ExecutionControl;
import models.Color;
import models.RedBlackNode;
import models.TreeNode;

/*
* @author: Saraj Singh Manes
* Implementation of Chap 13, CLRS.
* */

public class RedBlackTree<T extends Comparable<T>> extends Tree<T> implements TreeRotations{
    /**
     * Binary Serach trees ensure that all operations such as MINIMUM, MAXIMUM, SUCCESSOR, PREDECESSOR runs in O(h)
     * time, where h is height of tree along its longest path. So if tree is not height balanced, it might be as worst as
     * Linked List. Red Black Trees makes sure that h <= 2 *lg (n) where n is number of nodes, thus run time for all
     * operations will be O(lg(n)), i.e. Best case run time for Height balanced Binary Search Trees.
     *
     * 5 Properties to maintain to keep a tree red black tree and hence h = O(log(n))
     * 1. Every Node is either Red or Black
     * 2. Root is always black
     * 3. Every leaf is black (the Sentinel nodes, or null nodes)
     * 4. If a node is red, then both of its children are black
     * 5. From each Node all the paths to leaf (Sentinel Nodes) nodes contain same number of black nodes.
     * */


    public RedBlackNode<T> SENTINEL_NODE = (RedBlackNode<T>) new RedBlackNode.RedBlackNodeBuilder().key(null).self().color(Color.BLACK).build();



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

    /*
    * Only property 2 and 4 can be violated.
    * But the loop invariant in following method maintains that only property can be violated
    * at max and node is always red.
    * See diagrams 13.5 and 13.6 on CLRS for more clarity.
    * */
    private void rbInsertFixup(RedBlackNode node) {
        RedBlackNode parentNode = (RedBlackNode)node.getParent();
        if (root == node) {
            // Only property 2 is violated.
            node.setColor(Color.BLACK);
        } else if (parentNode.getColor()  == Color.RED){
            // Only property 4 is violated, i.e. A red node has red children.
            // it also implies node.parent != null, thus node.parent.parent also exists (because node.parent != root, because it is red).
            // here decisions are based on color of uncle node of node'.
            RedBlackNode grandParentNode = (RedBlackNode)node.getParent().getParent();
            RedBlackNode uncleNode;
            if (grandParentNode.getLeft() == parentNode) {
                uncleNode = (RedBlackNode) grandParentNode.getRight();
            } else {
                uncleNode = (RedBlackNode) grandParentNode.getLeft();
            }

            // case 1: If uncle is a red Neck Node, like papa
            if (uncleNode.getColor() == Color.RED) {
                // make grandpa RED and both uncle and papa as Black Node. Let grandpa deal with him with his own
                // parents
                grandParentNode.setColor(Color.RED);
                uncleNode.setColor(Color.BLACK);
                parentNode.setColor(Color.BLACK);
                rbInsertFixup(grandParentNode);
            } else {
                // uncle is black
                if ((node == parentNode.getRight() && grandParentNode.getLeft() == parentNode) ||
                        (node == parentNode.getLeft() && grandParentNode.getRight() == parentNode)) {
                    // i.e. if parent and child were different on side., i.e. Zig Zag, make it Zig Zig

                    if (node == parentNode.getRight()) {
                        parentNode = (RedBlackNode) this.rotateLeft(parentNode); // x and y has been swapped as well
                        node = parentNode.getLeft();
                    } else {
                        parentNode = (RedBlackNode) this.rotateRight(parentNode); // x and y has been swapped as well
                        node = parentNode.getRight();
                    }
                    grandParentNode = (RedBlackNode) parentNode.getParent();
                    // now it is same as case 3
                }


                //Case 3:  uncle is black and node is left node
                parentNode.setColor(Color.BLACK);
                grandParentNode.setColor(Color.RED);
                // Now with right rotation, parent level up to grand parent, and grandparent becomes child.
                if (parentNode == grandParentNode.getRight()) {
                    parentNode = (RedBlackNode) this.rotateLeft(grandParentNode);
                    node = parentNode.getRight();
                } else {
                    parentNode = (RedBlackNode) this.rotateRight(grandParentNode);
                    node = node.getLeft();
                }
                this.rbInsertFixup(node);
            }
        }
    }


    public void treeInsert(RedBlackNode node) {
        node.setRight(SENTINEL_NODE);
        node.setLeft(SENTINEL_NODE);
        node.setColor(Color.RED);
        this.treeInsert(node, SENTINEL_NODE);
        this.rbInsertFixup(node);
    }


    @Override
    public void transplantNode(TreeNode nodeTodelete, TreeNode replacementNode) {

    }
}
