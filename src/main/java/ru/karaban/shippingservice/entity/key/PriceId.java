package ru.karaban.shippingservice.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.entity.Product;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PriceId implements Serializable {

    @Column(nullable = false, name="chain_name")
    private String chainName;

    @Column(nullable = false, name = "material_no")
    private Long materialNo;

}
