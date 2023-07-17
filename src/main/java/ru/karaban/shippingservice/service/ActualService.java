package ru.karaban.shippingservice.service;

import java.time.LocalDate;
import java.util.List;

public interface ActualService<T, E> {

    E getFactByMonth(T priceId, LocalDate dateStart, LocalDate dateEnd);

    List<E> getFactByPriceId(List<T> price);
}
