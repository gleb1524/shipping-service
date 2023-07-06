package ru.karaban.shippingservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.karaban.shippingservice.model.PromoSign;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "actuals")
@Data
public class Actual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="CH3_ship_to_code", referencedColumnName="CH3_ship_to_code"),
            @JoinColumn(name="chain_name", referencedColumnName="chain_name")
    })
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_no")
    private Product product;

    @Column(name = "units")
    private Long units;

    @Column(name = "actual_sales")
    private BigDecimal actualSales;

    @Column(name = "date")
    private Date date;

    @Column(name = "promo_sign")
    private PromoSign promoSign;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
