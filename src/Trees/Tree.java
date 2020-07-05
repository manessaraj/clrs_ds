package Trees;

import models.TreeNode;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    public void treeInsert(TreeNode<T> node, TreeNode<T> nullNode) {
        TreeNode<T> parent = nullNode;
        TreeNode<T> iterNode = this.root;
        while (iterNode != nullNode) {
            parent = iterNode;
            if (node.getKey().compareTo(iterNode.getKey()) < 0) {
                iterNode = iterNode.getLeft();
            } else {
                iterNode = iterNode.getRight();
            }
        }

        node.setParent(parent);

        if (parent == nullNode) {
            this.root = node;
        } else if (node.getKey().compareTo(parent.getKey()) < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
    }


    /*
     * Helper method for Node delete
     * Given a node Z and node Y. Node Z is getting deleted and Y is going to replace it
     */
    void transplantNode(TreeNode nodeTodelete, TreeNode replacementNode, TreeNode nullNode) {
        assert nodeTodelete != nullNode;
        if (nodeTodelete.getParent() == nullNode) {
            // root Node
            this.root = replacementNode;
        } else if (nodeTodelete.getParent().getLeft() == nodeTodelete) {
            nodeTodelete.getParent().setLeft(replacementNode);
        } else {
            nodeTodelete.getParent().setRight(replacementNode);
        }

        if (replacementNode != nullNode) {
            replacementNode.setParent(nodeTodelete.getParent());
        }
    }

    public void transplantNode(TreeNode nodeTodelete, TreeNode replacementNode) {
        this.transplantNode(nodeTodelete, replacementNode, null);
    }

    /**
     * Deletion of a node can be regarded as replacement with left or right child. This means, if a parent node is getting
     * deleted either right or left child replaces that node in tree. We will stick with CLRS notion and use right child
     * as replacement.
     * */

    public void deleteNode(TreeNode node) {
        if (node != null) {
            /* One child situation */
            if (node.getLeft() == null) {
                transplantNode(node, node.getRight());
            } else if (node.getRight() == null) {
                transplantNode(node, node.getLeft());
            } else {
                // Node has two children, so replacement is not that simple.
                TreeNode replacement = getMinimumNodeInSubTree(node.getRight());
                if (replacement.getParent() != node) {
                    transplantNode(replacement, replacement.getRight());
                    replacement.setRight(node.getRight());
                    replacement.getRight().setParent(replacement);
                }
                transplantNode(node, replacement);
                replacement.setLeft(node.getLeft());
                replacement.getLeft().setParent(replacement);
            }
        }
    }


    TreeNode getMinimumNodeInSubTree(TreeNode node) {
        TreeNode itr = null;
        while (node != null) {
            itr = node;
            node = node.getLeft();
        }
        return itr;
    }




}
