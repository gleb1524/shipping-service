package ru.karaban.shippingservice.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PriceId implements Serializable {

    @Column(nullable = false, name = "chain_name")
    private String chainName;

    @Column(nullable = false, name = "material_no")
    private Long materialNo;

}
