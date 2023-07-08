package ru.karaban.shippingservice.builder.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.processor.FormatDataExel;

import java.math.BigDecimal;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExelToPriceBuilder implements ExelToEntityBuilder<Price> {

    private final FormatDataExel formatDataExel;
    @Override
    public Price building(Map<Integer, Object> cellValues) {
        return Price.builder()
                .priceId(PriceId.builder()
                        .chainName((String) cellValues.get(0))
                        .materialNo(Long.valueOf(formatDataExel.format ((String) cellValues.get(1))))
                        .build())
                .pricePerUnit(BigDecimal.valueOf((Long) cellValues.get(2)))
                .build();
    }

    @Override
    public boolean checkType(Price price) {
        return price.getClass().equals(Price.class);
    }
}
