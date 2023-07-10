package ru.karaban.shippingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisModel {

    private String chainName;
    private Long categoryCode;
    private String categoryName;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Double promoUnits;
    private Double regularUnits;
    private Double promoShare;
}
