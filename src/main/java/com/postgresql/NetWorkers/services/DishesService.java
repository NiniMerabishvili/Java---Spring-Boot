package com.postgresql.NetWorkers.services;

import com.postgresql.NetWorkers.entities.Dishes;
import com.postgresql.NetWorkers.entities.TableEntity;
import com.postgresql.NetWorkers.repositories.DishesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishesService {
    private final DishesRepository dishesRepository;

    @Autowired
    public DishesService(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    public List<Dishes> getAll() {
        return dishesRepository.findAll();
    }

    public Dishes getById(String id) {
        return dishesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dish_NOT_FOUND"));
    }

    public Dishes prepareDish(Dishes newDish) {
        return dishesRepository.save(newDish);
    }

    @Transactional
    public Dishes updateDish(String id, Dishes updatedDish) {
        Optional<Dishes> existingDishOptional = dishesRepository.findById(id);

        return existingDishOptional.map(existingDish -> {
            existingDish.setType(updatedDish.getType());
            existingDish.setName(updatedDish.getName());
            existingDish.setSummary(updatedDish.getSummary());
            existingDish.setDescription(updatedDish.getDescription());
            existingDish.setPrice(updatedDish.getPrice());

            return existingDish;
        }).orElseThrow(() -> new ResourceNotFoundException("Dish_NOT_FOUND"));
    }

    @Transactional
    public Boolean deleteDish(String id) {
        Dishes dishes = getById(id);
        dishesRepository.delete(dishes);
        return true;
    }
}
