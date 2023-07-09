package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.repository.PriceRepository;
import ru.karaban.shippingservice.service.PriceService;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    @Override
    public Price finByPriceId(PriceId priceId) {
       return priceRepository.findById(priceId).orElseThrow();
    }
}
