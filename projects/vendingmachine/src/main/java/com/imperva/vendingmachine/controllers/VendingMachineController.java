package com.imperva.vendingmachine.controllers;

import com.imperva.vendingmachine.entities.VendingMachine;
import com.imperva.vendingmachine.repositories.VendingMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/vending-machines")
@RequiredArgsConstructor
public class VendingMachineController {
    private final VendingMachineRepository vendingMachineRepository;

    @GetMapping
    Iterable<VendingMachine> getAllVendingMachines() {
        return vendingMachineRepository.findAll();
    }

    @GetMapping("/{id}")
    VendingMachine getVendingMachineById(@PathVariable Long id) {
        return vendingMachineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Vending Machine with id - " + id));
    }

}
