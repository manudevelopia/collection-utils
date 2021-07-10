package info.developia.collection.utils.join.fixture.warehouse;

import info.developia.collection.utils.join.fixture.shop.Item;

import java.util.List;

public class Stock {
    private final List<Item> items;

    public Stock(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
