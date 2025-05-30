package com.postgresql.NetWorkers.services;

import com.postgresql.NetWorkers.entities.TableEntity;
import com.postgresql.NetWorkers.repositories.TableRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {
    private final TableRepository tableRepository;

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<TableEntity> getAll() {
        return tableRepository.findAll();
    }

    public TableEntity getById(String id) {
        return tableRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TABLE_NOT_FOUND"));
    }
    public TableEntity createTable(TableEntity newTable) {
        return tableRepository.save(newTable);
    }

    @Transactional
    public TableEntity updateTable(String id, TableEntity updatedTable) {
        Optional<TableEntity> existingTableOptional = tableRepository.findById(id);

        return existingTableOptional.map(existingTable -> {
            existingTable.setNumber(updatedTable.getNumber());
            existingTable.setCapacity(updatedTable.getCapacity());
            existingTable.setType(updatedTable.getType());
            return existingTable;
        }).orElseThrow(() -> new ResourceNotFoundException("TABLE_NOT_FOUND"));
    }


    @Transactional
    public Boolean deleteTable(String id) {
        TableEntity tableEntity = getById(id);
        tableRepository.delete(tableEntity);
        return true;
    }
}
