package ru.karaban.shippingservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "shipping")
@Data
public class Actuals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chain_name")
    @JoinColumn(name = "CH3_ship_to_code")
    private Customers customers;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_no")
    private Products products;

    @Column(name = "units")
    private Long units;

    @Column(name = "actual_sales")
    private BigDecimal actualSales;

    @Column(name = "date")
    private Date date;

    @Column(name = "promo")
    private String promo;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
