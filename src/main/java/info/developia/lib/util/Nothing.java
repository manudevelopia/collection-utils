package info.developia.lib.util;

import java.util.function.Function;

public sealed interface Nothing {
    record None() implements Nothing {
    }

    record Failure(Throwable error) implements Nothing {
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
