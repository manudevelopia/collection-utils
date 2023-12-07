package info.developia.lib.util.venkat;

import java.util.concurrent.Callable;
import java.util.function.Function;

public sealed interface Tryable<T> permits Success, Failure {
    T getResult();

    Throwable getError();

    static <T> Tryable<T> of(Callable<T> code) {
        try {
            return new Success<>(code.call());
        } catch (Throwable throwable) {
            return new Failure<>(throwable);
        }
    }

    default <R> Tryable<R> map(Function<T, R> mapper) {
        if (this instanceof Success<T>) {
            return of(() -> mapper.apply(getResult()));
        } else {
            return new Failure<>(getError());
        }

    }
}
