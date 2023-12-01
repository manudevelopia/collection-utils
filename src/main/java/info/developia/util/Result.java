package info.developia.util;

import java.util.function.Function;

public sealed interface Result<A> {

    default A get() {
        return ((Success<A>) this).value();
    }

    default A getOr(A fallbackValue) {
        if (this instanceof Success<A> success) {
            return success.value();
        }
        return fallbackValue;
    }

    default A getOrFailWith(RuntimeException exception) {
        if (this instanceof Success<A> success) {
            return success.value();
        }
        throw exception;
    }

    default Throwable fail() {
        if (this instanceof Result.Failure<A> e)
            return e.error();
        else return null;
    }

    default A getOrFailWith(Function<Throwable, RuntimeException> exception) {
        if (this instanceof Result.Failure<A> e) {
            throw exception.apply(e.error());
        }
        return this.get();
    }

    record Success<A>(A value) implements Result<A> {
    }

    record Failure<A>(Throwable error) implements Result<A> {
    }

    record None<A>() implements Result<A> {
    }
}
