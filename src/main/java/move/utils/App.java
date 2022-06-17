package move.utils;

import info.developia.collection.utils.fixture.shop.Item;
import info.developia.collection.utils.fixture.shop.Order;
import info.developia.collection.utils.fixture.warehouse.Client;
import info.developia.collection.utils.fixture.warehouse.Stock;

import java.util.List;
import java.util.function.Function;

public class App {

    public static void main(String[] args) {
        Client client = new Client(List.of(new Order(
                List.of(new Item(3, "article 3"),
                        new Item(4, "article 4"))
        )));
        Order order = new Order(List.of(
                new Item(1, "article 1"),
                new Item(2, "article 2"),
                new Item(3, "article 3"),
                new Item(4, "article 4"),
                new Item(5, "article 5")
        ));
        Stock stock = new Stock(List.of(
                new Item(1, "article 1"),
                new Item(3, "article 3"),
                new Item(5, "article 5")
        ));

        Function<Item, Long> by = (i) -> ((Item) i).getId();
        List<Item> commonList1 = Joiner.common1(order, stock, by);

        List<Item> commonList2 = Joiner.common2(order.getItems(), stock.getItems(), by);
        List<Item> commonList2a = Joiner.common2(order.getItems(), stock.getItems(), (i) -> ((Item) i).getId());
    }
}
