package info.developia.lib.util;

import java.util.function.Supplier;

public class Try {

    public static Nothing of(final Runnable o) {
        try {
            o.run();
            return new Nothing.None();
        } catch (Throwable e) {
            return new Nothing.Failure(e);
        }
    }

    public static <T> Result<T> of(final Supplier<T> o) {
        try {
            final var value = o.get();
            return new Result.Success<>(value);
        } catch (Throwable error) {
            return new Result.Failure<>(error);
        }
    }
}
