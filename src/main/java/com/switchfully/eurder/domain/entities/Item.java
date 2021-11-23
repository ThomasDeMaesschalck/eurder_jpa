package com.switchfully.eurder.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item", schema = "eurder")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "amount_in_stock")
    private int amountInStock;

    public void takeItemsFromStock(int amountOrdered) {
        if (amountOrdered > this.amountInStock) {
            this.amountInStock = 0;
        } else {
            this.amountInStock = this.amountInStock - amountOrdered;
        }
    }
}
