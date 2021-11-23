package com.switchfully.eurder.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order", schema = "eurder")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id_fk")
    private User customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<Orderline> orderlines;


    public BigDecimal getTotalPriceOfOrder() {
        return orderlines.stream()
                .map(Orderline::getOrderlineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
