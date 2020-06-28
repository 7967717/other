package com.imperva.vendingmachine.repositories;

import com.imperva.vendingmachine.entities.Drink;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DrinkRepository extends PagingAndSortingRepository<Drink, Long> {
}
