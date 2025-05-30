package com.postgresql.NetWorkers.repositories;

import com.postgresql.NetWorkers.entities.Registration;
import com.postgresql.NetWorkers.entities.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TableRepository extends JpaRepository<TableEntity, String> {
    // Other methods...
}


