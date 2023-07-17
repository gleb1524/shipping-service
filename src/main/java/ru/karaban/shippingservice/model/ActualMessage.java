package ru.karaban.shippingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActualMessage {

    private String chainName;
    private Long materialNo;
    private LocalDate start;
    private LocalDate end;
    private Map<String, Long> priceIds;
}
