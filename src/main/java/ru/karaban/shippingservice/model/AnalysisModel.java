package ru.karaban.shippingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisModel {

    private String chainName;
    private Long categoryCode;
    private String categoryName;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int promoUnits;
    private int regularUnits;
    private double promoShare;
}
