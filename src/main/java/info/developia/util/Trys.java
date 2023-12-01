package info.developia.util;

import java.util.function.Supplier;

public class Trys {

    public static <T> Result<T> of(final Runnable o) {
        try {
            o.run();
            return new Result.None<>();
        } catch (Throwable e) {
            return new Result.Failure<>(e);
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

    //    public static <T> TryWrap ofs(final Supplier<T> s) {
//        try {
//            final var value = s.get();
//            return new TryWrap(new Try.Success(value));
//        } catch (Throwable e) {
//            return new TryWrap(new Try.Failure(e));
//        }
//    }
//
//    public static <T> Try<T> of(final Supplier<T> s) {
//
//        try {
//            final var value = s.get();
//            return new Try.Success<>(value);
//        } catch (Throwable e) {
//            return new Try.Failure<>(e);
//        }
//    }
//
//    public static <T> Try<T> failed(final Throwable error) {
//        return new Try.Failure<>(error);
//    }
//
//    private static <T, U> Try<U> executeF(
//            final Function<? super T, ? extends Try<U>> f,
//            final T value) {
//        try {
//            return f.apply(value);
//        } catch (Throwable e) {
//            return failed(e);
//        }
//    }
//
//    public static <T> Try<T> recoverWith(
//            Try<T> from,
//            Function<? super Throwable, ? extends Try<T>> f) {
//
//        if (from instanceof Try.Success<T> s)
//            return s;
////        else if (from instanceof Try.Failure<T>)
//        return executeF(f, null);
//
////        return switch (from) {
////            case Try.Success<T> s -> s;
////            case Try.Failure<T>(var e) -> executeF(f, e);
////        };
//    }
//
//    public static <T> Try<T> recover(
//            Try<T> from,
//            Function<? super Throwable, ? extends T> f) {
//        return recoverWith(from, (e) -> of(() -> f.apply(e)));
//    }
//
////    public static <T, U> Try<U> map(
////            Try<T> from,
////            Function<? super T, ? extends U> f) {
////
////        return flatMap(from, s -> of(() -> f.apply(s)));
////    }
//
////    public static <T, U> Try<U> flatMap(
////            Try<T> from,
////            Function<? super T, ? extends Try<U>> f) {
////
////        return switch (from) {
////            case Try.Success<T>(var value) -> executeF(f, value);
////            case Try.Failure<T>(var e) -> failed(e);
////        };
////    }
//
    public static class ResultWrap<T> {

        private final Result<T> tryValue;

        private ResultWrap(final Result<T> tryValue) {
            this.tryValue = tryValue;
        }

        public T get() {
            return ((Result.Success<T>) tryValue).value();
        }


//
////        public <U> TryWrap<U> flatMap(
////                Function<? super T, ? extends Try<U>> f) {
////            return new TryWrap<>(Trys.flatMap(tryValue, f));
////        }
////
////        public <U> TryWrap<U> map(
////                Function<? super T, ? extends U> f) {
////            return new TryWrap<>(Trys.map(tryValue, f));
////        }
//
//        public TryWrap<T> recoverWith(
//                Function<? super Throwable, ? extends Try<T>> f) {
//            return new TryWrap<>(Trys.recoverWith(tryValue, f));
//        }
//
//        public TryWrap<T> recover(
//                Function<? super Throwable, ? extends T> f) {
//            return new TryWrap<>(Trys.recover(tryValue, f));
//        }
    }
}
