package com.postgresql.NetWorkers.controllers;

import com.postgresql.NetWorkers.entities.TableEntity;
import com.postgresql.NetWorkers.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:3333, http://localhost:5433, http://localhost:80")
public class TablesController {
    private final TableService tableService;

    @Autowired
    public TablesController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/tables")
    public ResponseEntity<List<TableEntity>> getAllTables() {
        List<TableEntity> records = tableService.getAll();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @PostMapping("/tables")
    public ResponseEntity<TableEntity> addTable(@RequestBody TableEntity newTable) {
        TableEntity savedTable = tableService.createTable(newTable);
        return new ResponseEntity<>(savedTable, HttpStatus.CREATED);
    }

    @PutMapping("/tables/{id}")
    public ResponseEntity<?> updateTable(@PathVariable String id, @RequestBody TableEntity updatedTable) {
        System.out.println("Received update request for table with ID: " + id);
        TableEntity table = tableService.updateTable(id, updatedTable);
        if (table != null) {
            return new ResponseEntity<>(table, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Table not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/tables/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable String id) {
        if (tableService.deleteTable(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/tables/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.DELETE).build();
    }

}
