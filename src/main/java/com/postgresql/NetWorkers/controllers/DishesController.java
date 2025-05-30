package com.postgresql.NetWorkers.controllers;

import com.postgresql.NetWorkers.entities.Dishes;
import com.postgresql.NetWorkers.entities.TableEntity;
import com.postgresql.NetWorkers.services.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:3333, http://localhost:5433, http://localhost:80")
public class DishesController {
    @GetMapping("/dishes")
    public ResponseEntity<List<Dishes>> getAllDishes() {
        List<Dishes> dishes = dishesService.getAll();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    private final com.postgresql.NetWorkers.services.DishesService dishesService;

    @Autowired
    public DishesController(DishesService dishesService) {
        this.dishesService  = dishesService;
    }

    @PostMapping("/dishes")
    public ResponseEntity<Dishes> prepareDish(@RequestBody Dishes newDish) {
        Dishes savedDish = dishesService.prepareDish(newDish);
        return new ResponseEntity<>(savedDish, HttpStatus.CREATED);
    }

    @PutMapping("/dishes/{id}")
    public ResponseEntity<?> updateDish(@PathVariable String id, @RequestBody Dishes updatedDish) {
        System.out.println("Received update request for table with ID: " + id);
        Dishes dish = dishesService.updateDish(id, updatedDish);
        if (dish != null) {
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Dish not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable String id) {
        if (dishesService.deleteDish(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/dishes/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.DELETE).build();
    }
}
