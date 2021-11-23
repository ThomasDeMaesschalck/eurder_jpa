package com.switchfully.eurder.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderline", schema = "eurder")
public class Orderline {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "amount")
    private int amount;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;


    public BigDecimal getOrderlineTotal() {
        return salePrice.multiply(BigDecimal.valueOf(amount));
    }
}
