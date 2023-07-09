package ru.karaban.shippingservice.service;

import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;

public interface PriceService<T> {

    Price finByPriceId(PriceId priceId);
    Price savePrice(T price);
}
