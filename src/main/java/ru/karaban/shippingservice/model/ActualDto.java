package ru.karaban.shippingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActualDto {

    private Long customerId;
    private PriceId priceId;
    private Long units;
    private BigDecimal actualSales;
    private LocalDate date;
    private PromoSign promoSign;
}
