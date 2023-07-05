package ru.karaban.shippingservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "price")
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chain_name")
    private Customers customers;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_no")
    private Products products;

    @Column(name = "regular_price_per_unit")
    private BigDecimal pricePerUnit;
}
