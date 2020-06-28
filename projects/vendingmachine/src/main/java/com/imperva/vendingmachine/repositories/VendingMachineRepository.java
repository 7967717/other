package com.imperva.vendingmachine.repositories;

import com.imperva.vendingmachine.entities.VendingMachine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface VendingMachineRepository extends PagingAndSortingRepository<VendingMachine, Long> {

    Optional<VendingMachine> findById(Long id);

}
