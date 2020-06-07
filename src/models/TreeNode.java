package models;

public class TreeNode<T> extends Node {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private TreeNode<T> parent;

    TreeNode(TreeNodeBuilder<T> builder) {
        super(builder);
        this.left = builder.left;
        this.right = builder.right;
        this.parent = builder.parent;
    }

    public static class TreeNodeBuilder<T> extends Node.Builder<T>{
        private TreeNode<T> left;
        private TreeNode<T> right;
        private TreeNode<T> parent = null;

        public TreeNodeBuilder<T> left(TreeNode<T> left) {
            this.left = left;
            return this;
        }

        public TreeNodeBuilder<T> right(TreeNode<T> right) {
            this.right = right;
            return this;
        }
        public TreeNodeBuilder<T> parent(TreeNode<T> parent) {
            this.parent = parent;
            return this;
        }

        @Override
        public TreeNode<T> build() {
            return new TreeNode(this);
        }

        @Override
        public TreeNodeBuilder<T> self() {
            return this;
        }
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }
}
