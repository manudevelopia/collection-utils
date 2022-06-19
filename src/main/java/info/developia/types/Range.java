package info.developia.types;

public class Range<T> {
    private final T from;
    private final T to;

    private Range(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public static <T> Range<T> of(T from, T to) {
        if (from.getClass() == to.getClass()) {
            return new Range<>(from, to);
        }
        throw new UnsupportedOperationException();
    }

    public T from() {
        return from;
    }

    public T to() {
        return to;
    }
}
