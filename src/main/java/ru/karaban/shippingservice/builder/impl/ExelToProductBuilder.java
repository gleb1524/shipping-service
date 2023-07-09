package ru.karaban.shippingservice.builder.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.entity.Product;
import ru.karaban.shippingservice.processor.FormatDataExel;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExelToProductBuilder implements ExelToEntityBuilder<Product> {

    private final FormatDataExel formatDataExel;
    @Override
    public Product building(Map<Integer, Object> cellValues) {
        return Product.builder()
                .code((Long) cellValues.get(0))
                .title((String) cellValues.get(1))
                .categoryCode(Long.valueOf(formatDataExel.format ((String) cellValues.get(2))))
                .brand((String) cellValues.get(3))
                .build();
    }
}
