package ru.karaban.shippingservice.service;

import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.model.AnalysisModel;

import java.time.LocalDate;
import java.util.List;

public interface ActualService<T, E> {

        AnalysisModel getShipByPromo(T priceId, E dateStart, E dateEnd);
}
