package models;

public abstract class Node<T> {
    private T key;

    abstract static class Builder<T> {
        T key;
        public Builder<T> key(T key) {
            this.key = key;
            return this;
        }

        abstract Node<T> build();
        abstract Builder<T> self();
    }

    Node (Builder<T> builder) {
        this.key = builder.key;
    }
}
