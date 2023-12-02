package info.developia.util;

import java.util.function.Function;

public interface None {
    record Nothing() implements None {
    }

    record Failure(Throwable error) implements None {
    }

    default void orFail(RuntimeException exception) {
        if (this instanceof Failure) {
            throw exception;
        }
    }

    default void orFailWith(Function<Throwable, RuntimeException> exception) {
        if (this instanceof Failure failure) {
            throw exception.apply(failure.error());
        }
    }
}
