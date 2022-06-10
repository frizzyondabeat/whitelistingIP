package com.frizzycode.whitelist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Stock")
@Table(name = "stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stock {
    @Id
    @SequenceGenerator(
            name = "stock_sequence",
            sequenceName = "stock_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "stock_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "amount",
            nullable = false,
            precision = 2
    )
    private Double amount;

    @Column(
            name = "last_update",
            nullable = false
    )
    private Timestamp lastUpdate;

    public Stock(String name, Double amount, Timestamp lastUpdate) {
        this.name = name;
        this.amount = amount;
        this.lastUpdate = lastUpdate;
    }
}
