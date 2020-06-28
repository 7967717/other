package com.imperva.vendingmachine.repositories;

import com.imperva.vendingmachine.entities.Inventory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long> {

    List<Inventory> findAll();
}
