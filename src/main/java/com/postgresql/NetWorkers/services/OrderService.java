package com.postgresql.NetWorkers.services;

import com.postgresql.NetWorkers.entities.Dishes;
import com.postgresql.NetWorkers.entities.Order;
import com.postgresql.NetWorkers.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService (OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order_NOT_FOUND"));
    }

    public Order addOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Transactional
    public Order updateOrder(String id, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);

        return existingOrderOptional.map(existingDish -> {
            existingDish.setName(updatedOrder.getName());
            existingDish.setQuantity(updatedOrder.getQuantity());

            return existingDish;
        }).orElseThrow(() -> new ResourceNotFoundException("Dish_NOT_FOUND"));
    }

    @Transactional
    public Boolean deleteOrder(String id) {
        Order orders = getById(id);
        orderRepository.delete(orders);
        return true;

    }
}
