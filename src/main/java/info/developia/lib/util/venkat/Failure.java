package info.developia.lib.util.venkat;

public final class Failure<T> implements Tryable<T> {
    private final Throwable throwable;

    public Failure(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public T getResult() {
        return null;
    }

    @Override
    public Throwable getError() {
        return throwable;
    }
}
