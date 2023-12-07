package info.developia.lib.util.venkat;

public final class Success<T> implements Tryable<T> {
    private final T result;

    public Success(T result) {
        this.result = result;
    }

    @Override
    public T getResult() {
        return result;
    }

    @Override
    public Throwable getError() {
        return null;
    }
}
