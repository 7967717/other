package com.imperva.vendingmachine;

import com.imperva.vendingmachine.entities.Inventory;
import com.imperva.vendingmachine.entities.Drink;
import com.imperva.vendingmachine.entities.VendingMachine;
import com.imperva.vendingmachine.repositories.DrinkRepository;
import com.imperva.vendingmachine.repositories.InventoryRepository;
import com.imperva.vendingmachine.repositories.VendingMachineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
public class VendingmachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendingmachineApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase2(VendingMachineRepository vendingMachineRepository,
                                    InventoryRepository inventoryRepository,
                                    DrinkRepository drinkRepository) {
        VendingMachine machine1 = new VendingMachine(BigDecimal.ZERO, "First floor");
        VendingMachine machine2 = new VendingMachine(BigDecimal.ZERO, "Second floor");
        vendingMachineRepository.save(machine1);
        vendingMachineRepository.save(machine2);

        Drink cola = new Drink("Cola", BigDecimal.ONE, "Regular cola");
        Drink pepsi = new Drink("Pepsi", BigDecimal.ONE, "Regular pepsi");
        Drink water = new Drink("Water", BigDecimal.ONE, "Just water");
        drinkRepository.save(cola);
        drinkRepository.save(pepsi);
        drinkRepository.save(water);

        return args -> {
            log.info("Preloading " + inventoryRepository.save(new Inventory(cola, 1, 3, machine1)));
            log.info("Preloading " + inventoryRepository.save(new Inventory(pepsi, 1, 3, machine1)));
            log.info("Preloading " + inventoryRepository.save(new Inventory(water, 1, 3, machine1)));
            log.info("Preloading " + inventoryRepository.save(new Inventory(cola, 2, 2, machine2)));
            log.info("Preloading " + inventoryRepository.save(new Inventory(pepsi, 2, 2, machine2)));
        };
    }
}
