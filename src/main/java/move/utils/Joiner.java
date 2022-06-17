package move.utils;

import info.developia.collection.utils.fixture.shop.Item;
import info.developia.collection.utils.fixture.shop.Order;
import info.developia.collection.utils.fixture.warehouse.Stock;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Joiner {

    public static List<Item> common1(Order list, Stock filter, Function<Item, Long> by) {
        Set<Long> dict = filter.getItems().stream().map(by).collect(Collectors.toSet());
        return list.getItems().stream().filter(item -> dict.contains(by.apply(item))).collect(Collectors.toList());
    }

    public static <T, G, F> List<G> common2(List<G> list, List<G> filter, Function<T, F> by) {
        Set<F> dict = filter.stream().map(item -> by.apply((T) item)).collect(Collectors.toSet());
        return list.stream().filter(item -> dict.contains(by.apply((T) item))).collect(Collectors.toList());
    }

    public static <T, G> List<G> fromArrayToList(T[] a, Function<T, G> mapperFunction) {
        return Arrays.stream(a)
                .map(mapperFunction)
                .collect(Collectors.toList());
    }
}
