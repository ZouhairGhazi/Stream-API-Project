package fr.codebusters.Stream.API.Project.services;

import fr.codebusters.Stream.API.Project.model.Order;
import fr.codebusters.Stream.API.Project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByProductCategory(String category) {
        return getAll().stream()
                .filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals(category)))
                .toList();
    }

    public List<Order> getKMostRecentOrders(int k) {
        return getAll().stream()
                .sorted(Comparator.comparing(Order::getOrderDate))
                .limit(3)
                .toList();
    }
}
