package Trees;

import models.TreeNode;

public class Tree<T> {
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
}
