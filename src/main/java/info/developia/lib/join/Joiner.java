package info.developia.lib.join;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Joiner {

    public static <C, T, F> List<T> common(List<T> list, List<T> filter, Function<C, F> by) {
        Set<F> dictionary = filter.stream().map(item -> by.apply((C) item)).collect(Collectors.toSet());
        return list.stream().filter(item -> dictionary.contains(by.apply((C) item))).toList();
    }

    public static <T, G> List<G> fromArrayToList(T[] a, Function<T, G> mapperFunction) {
        return Arrays.stream(a)
                .map(mapperFunction)
                .collect(Collectors.toList());
    }
}
