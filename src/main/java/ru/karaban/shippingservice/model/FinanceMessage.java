package ru.karaban.shippingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceMessage {


    private String chainName;
    private Long materialNo;
    private BigDecimal pricePerUnit;
}
