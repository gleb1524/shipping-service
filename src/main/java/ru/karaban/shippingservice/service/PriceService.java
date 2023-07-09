package ru.karaban.shippingservice.service;

import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;

public interface PriceService {

    Price finByPriceId(PriceId priceId);
}
