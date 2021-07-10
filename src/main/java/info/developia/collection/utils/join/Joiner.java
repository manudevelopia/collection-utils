package info.developia.collection.utils.join;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Joiner {

    public static <T, G, F> List<G> common(List<G> list, List<G> filter, Function<T, F> by) {
        Set<F> dict = filter.stream().map(item -> by.apply((T) item)).collect(Collectors.toSet());
        return list.stream().filter(item -> dict.contains(by.apply((T) item))).collect(Collectors.toList());
    }

}
