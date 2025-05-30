package com.postgresql.NetWorkers.repositories;

import com.postgresql.NetWorkers.entities.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, String> {
}
