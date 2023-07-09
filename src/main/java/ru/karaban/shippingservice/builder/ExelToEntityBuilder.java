package ru.karaban.shippingservice.builder;

import java.util.Map;


public interface ExelToEntityBuilder<T> {

    T building(Map<Integer, Object> cellValues);
}
