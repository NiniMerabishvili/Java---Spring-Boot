package com.postgresql.NetWorkers.repositories;


import com.postgresql.NetWorkers.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Registration findByEmailAndPassword(String email, String password);
    Optional<Registration> findByName(String name);
}
