package ru.karaban.shippingservice.builder.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.service.infrastructure.FormatDataExel;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExelToCustomerBuilder implements ExelToEntityBuilder<Customer> {

    private final FormatDataExel formatDataExel;

    @Override
    public Customer building(Map<Integer, Object> cellValues) {
        return Customer.builder()
                .id(Long.valueOf(formatDataExel.format((String) cellValues.get(0))))
                .address((String) cellValues.get(1))
                .chainName((String) cellValues.get(2))
                .build();
    }
}
