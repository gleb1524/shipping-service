package ru.karaban.shippingservice.service;

import ru.karaban.shippingservice.model.AnalysisModel;

import java.util.List;

public interface ActualService<T, E> {

        List<T> getShipByPromo(E e);
}
