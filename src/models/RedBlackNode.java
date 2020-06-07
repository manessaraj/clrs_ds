package models;

public class RedBlackNode<T> extends TreeNode<T> {
    private Color color;

    RedBlackNode (RedBlackNodeBuilder builder) {
        super(builder);
        this.color = builder.color;
    }
    public static class RedBlackNodeBuilder<T> extends TreeNodeBuilder<T> {
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

    public void setColor(Color color) {
        this.color = color;
    }
}
