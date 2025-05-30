package com.postgresql.NetWorkers.controllers;

import com.postgresql.NetWorkers.entities.Dishes;
import com.postgresql.NetWorkers.entities.Order;
import com.postgresql.NetWorkers.services.DishesService;
import com.postgresql.NetWorkers.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:3333, http://localhost:5433, http://localhost:80")
public class OrderController {
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = ordersService.getAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    private final com.postgresql.NetWorkers.services.OrderService ordersService;

    @Autowired
    public OrderController(OrderService ordersService) {
        this.ordersService  = ordersService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody Order newOrder) {
        Order savedOrder = ordersService.addOrder(newOrder);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable String id, @RequestBody Order updatedOrder) {
        System.out.println("Received update request for order with ID: " + id);
        Order order = ordersService.updateOrder(id, updatedOrder);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        if (ordersService.deleteOrder(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/orders/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.DELETE).build();
    }
}
