package models;

public class RedBlackNode<T extends Comparable> extends TreeNode<T> {
    private Color color;

    RedBlackNode (RedBlackNodeBuilder builder) {
        super(builder);
        this.color = builder.color;
    }
    public static class RedBlackNodeBuilder<T extends Comparable> extends TreeNodeBuilder<T> {
        private Color color;

        public RedBlackNodeBuilder<T> color(Color color) {
            this.color = color;
            return this;
        }

        @Override
        public RedBlackNode<T> build() {
            return new RedBlackNode<>(this);
        }

        @Override
        public RedBlackNodeBuilder<T> self() {
            return this;
        }
    }

    public Color getColor() {
        return color;
    }


    @Override
    public RedBlackNode getLeft() {
        return (RedBlackNode) super.getLeft();
    }

    @Override
    public RedBlackNode getRight() {
        return (RedBlackNode) super.getRight();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setParent(TreeNode node) {
        if (getKey() != null) {
            super.setParent(node);
        }
    }

    public RedBlackNode<T> getParent() {
        return (RedBlackNode) super.getParent();
    }


}
