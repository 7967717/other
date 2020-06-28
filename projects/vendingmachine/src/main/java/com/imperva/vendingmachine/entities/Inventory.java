package com.imperva.vendingmachine.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private Drink drink;

    @NonNull
    private int quantity;

    @NonNull
    private int capacity;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private VendingMachine vendingMachine;

}
