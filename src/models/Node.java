package models;

public abstract class Node<T> {
    private T key;

    public abstract static class Builder<T> {
        T key;
        public Builder<T> key(T key) {
            this.key = key;
            return this;
        }

        public abstract Node<T> build();
        public abstract Builder<T> self();
    }

    Node (Builder<T> builder) {
        this.key = builder.key;
    }

    public T getKey() {
        return key;
    }
}
