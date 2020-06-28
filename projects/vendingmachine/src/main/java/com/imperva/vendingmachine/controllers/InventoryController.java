package com.imperva.vendingmachine.controllers;

import com.imperva.vendingmachine.entities.Drink;
import com.imperva.vendingmachine.entities.Inventory;
import com.imperva.vendingmachine.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryRepository inventoryRepository;

    @GetMapping("/vending-machines/{id}/drinks")
    List<Drink> getDrinksByVendingMachine(@PathVariable Long id) {
        return inventoryRepository.findAll().stream()
                .filter(e -> e.getVendingMachine().getId().equals(id))
                .filter(e -> e.getQuantity() > 0)
                .map(Inventory::getDrink).collect(Collectors.toList());
    }

    @PatchMapping("/vending-machines/{id1}/drinks/{id2}")
    Drink getDrinkFromVendingMachine(@PathVariable Long id1, @PathVariable Long id2) {
        Inventory inventory = inventoryRepository.findAll().stream()
                .filter(e -> e.getVendingMachine().getId().equals(id1))
                .filter(e -> e.getDrink().getId().equals(id2))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No drink with id - " + id2));
        if (inventory.getQuantity() == 0) {
            throw new NoSuchElementException("No drink, stock is over for - " + inventory.getDrink().getName());
        }
        inventory.setQuantity(inventory.getQuantity() - 1);
        inventoryRepository.save(inventory);

        return inventory.getDrink();
    }

    @PutMapping("/vending-machines/{id1}/drinks/{id2}")
    Drink putDrinkInVendingMachine(@PathVariable Long id1, @PathVariable Long id2, @RequestParam("quantity") Integer toAdd) {
        Inventory inventory = inventoryRepository.findAll().stream()
                .filter(e -> e.getVendingMachine().getId().equals(id1))
                .filter(e -> e.getDrink().getId().equals(id2))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No drink with id - " + id2));
        if (inventory.getQuantity() + toAdd > inventory.getCapacity()) {
            throw new NoSuchElementException("Could not add more, stock is full for - " + inventory.getDrink().getName());
        }
        inventory.setQuantity(inventory.getQuantity() + toAdd);
        inventoryRepository.save(inventory);

        return inventory.getDrink();
    }

    @GetMapping("/inventories")
    List<Inventory> findAllInventories() {
        return inventoryRepository.findAll();
    }

    @PostMapping(value = "/inventories", consumes = MediaType.APPLICATION_JSON_VALUE)
    Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    //TODO add authorization
    @DeleteMapping("/inventories/{id}")
    String deleteInventory(@PathVariable Long id) {
        inventoryRepository.deleteById(id);
        return "The inventory - " + id + " was deleted";
    }

    @GetMapping("/vending-machines/{id}/inventories")
    List<Inventory> getInventoriesByVendingMachine(@PathVariable Long id) {
        return inventoryRepository.findAll().stream()
                .filter(e -> e.getVendingMachine().getId().equals(id)).collect(Collectors.toList());
    }

}
