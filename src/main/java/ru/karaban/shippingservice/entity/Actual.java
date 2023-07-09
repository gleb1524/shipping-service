package ru.karaban.shippingservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.karaban.shippingservice.model.PromoSign;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "actuals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Actual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CH3_ship_to_code", referencedColumnName="CH3_ship_to_code")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="material_no", referencedColumnName="material_no"),
            @JoinColumn(name="chain_name", referencedColumnName="chain_name")
    })
    private Price price;

    @Column(name = "units")
    private Long units;

    @Column(name = "actual_sales")
    private BigDecimal actualSales;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "promo_sign")
    @Enumerated(EnumType.STRING)
    private PromoSign promoSign;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
