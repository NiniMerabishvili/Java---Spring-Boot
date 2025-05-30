package com.postgresql.NetWorkers.services;

import com.postgresql.NetWorkers.entities.Booking;
import com.postgresql.NetWorkers.repositories.BookingsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingsService {
    private final BookingsRepository bookingsRepository;

    @Autowired
    public BookingsService(BookingsRepository bookingsRepository) {
        this.bookingsRepository = bookingsRepository;
    }

    public List<Booking> getAll() {
        return bookingsRepository.findAll();
    }

    public Booking getById(Long id) {
        return bookingsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TABLE_NOT_FOUND"));
    }

    @Transactional
    public Booking createBooking(Booking newBooking) {
        // Check if the table is available for the specified date
        if (isTableAvailable(newBooking.getDate(), newBooking.getId())) {
            // Set the table name
            newBooking.setTable_name("Table " + newBooking.getTable_name());
            // Save the booking
            return bookingsRepository.save(newBooking);
        } else {
            // Handle case where the table is not available
            throw new IllegalArgumentException("Table is not available for the specified date");
        }
    }

    private boolean isTableAvailable(Date bookingDate, String tableId) {
        // Get the list of booked table IDs for the specified date
        List<String> bookedTableIds = bookingsRepository.findTableIdsByDate(bookingDate);

        // Check if the selected table ID is not in the list of booked table IDs
        return !bookedTableIds.contains(tableId);
    }



    @Transactional
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Optional<Booking> existingBookingOptional = bookingsRepository.findById(id);

        return existingBookingOptional.map(existingBooking -> {
            existingBooking.setDate(updatedBooking.getDate());
            existingBooking.setTable_name(updatedBooking.getTable_name());
            return existingBooking;
        }).orElseThrow(() -> new ResourceNotFoundException("BOOKING_NOT_FOUND"));
    }

    @Transactional
    public Boolean deleteBooking(Long id) {
        Booking booking = getById(id);
        bookingsRepository.delete(booking);
        return true;
    }


}
