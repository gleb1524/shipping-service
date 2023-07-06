package ru.karaban.shippingservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.karaban.shippingservice.entity.key.PriceId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
@Data
public class Price {

//    @Id
//    @Column(name = "chain")
//    private String chainName;
//
//    @Id
//    @Column(name = "material_no")
//    private Long materialNo;
    @EmbeddedId
    private PriceId priceId;

//    @MapsId("materialNo")
//    @OneToOne
//    private Product product;
//
//
//
//    @JoinColumn(name = "chain_name", referencedColumnName = "chain_name")
//    @OneToOne
//    private Customer customer;

    @Column(name = "regular_price_per_unit")
    private BigDecimal pricePerUnit;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
