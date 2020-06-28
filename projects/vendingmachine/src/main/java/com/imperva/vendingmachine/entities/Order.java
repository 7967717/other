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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private Drink drink;

    @NonNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod paidBy;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private VendingMachine vendingMachine;

}
