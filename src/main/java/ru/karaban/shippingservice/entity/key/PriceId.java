package ru.karaban.shippingservice.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.entity.Product;

import java.io.Serializable;

@Data
@Embeddable
public class PriceId implements Serializable {

    @JoinColumn(name = "chain_name", referencedColumnName = "chain_name", nullable = false)
    @OneToOne
    private Customer customer;

    @Column(nullable = false)
    private Long materialNo;

}
