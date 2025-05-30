package com.postgresql.NetWorkers.repositories;

import com.postgresql.NetWorkers.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByDate(Date date);

    @Query("SELECT b.id FROM Booking b WHERE b.date = :date")
    List<String> findTableIdsByDate(@Param("date") Date date);
}
