package com.postgresql.NetWorkers.controllers;

import com.postgresql.NetWorkers.entities.Booking;
import com.postgresql.NetWorkers.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3333, http://localhost:5433, http://localhost:80")
public class BookingsController {
    private final BookingsService bookingsService;

    @Autowired
    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> records = bookingsService.getAll();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> addTable(@RequestBody Booking newBooking) {
        Booking savedBooking = bookingsService.createBooking(newBooking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

//    @PutMapping("/tables/{id}")
//    public ResponseEntity<?> updateTable(@PathVariable Long id, @RequestBody TableEntity updatedTable) {
//        System.out.println("Received update request for table with ID: " + id);
//        TableEntity table = tableService.updateTable(id, updatedTable);
//        if (table != null) {
//            return new ResponseEntity<>(table, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Table not found", HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//    @DeleteMapping("/tables/{id}")
//    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
//        if (tableService.deleteTable(id)) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @RequestMapping(value = "/tables/{id}", method = RequestMethod.OPTIONS)
//    public ResponseEntity<?> handleOptions() {
//        return ResponseEntity.ok().allow(HttpMethod.DELETE).build();
//    }
//    public List<String> getBookedTablesByDate(@RequestParam Date date) {
//        return bookingsService.getBookedTablesByDate(date);
//    }
}
