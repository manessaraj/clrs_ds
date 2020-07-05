package models;

public abstract class Node<T extends Comparable<T>> {
    private T key;

    public abstract static class Builder<T extends Comparable> {
        T key;
        public Builder<T> key(T key) {
            this.key = key;
            return this;
        }

        public abstract Node build();
        public abstract Builder<T> self();
    }

    Node (Builder<T> builder) {
        this.key = builder.key;
    }

    public T getKey() {
        return key;
    }
}
