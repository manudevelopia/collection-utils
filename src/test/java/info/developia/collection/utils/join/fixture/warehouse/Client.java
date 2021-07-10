package info.developia.collection.utils.join.fixture.warehouse;

import info.developia.collection.utils.join.fixture.shop.Order;

import java.util.List;

public class Client {
    private final List<Order> orders;

    public Client(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
