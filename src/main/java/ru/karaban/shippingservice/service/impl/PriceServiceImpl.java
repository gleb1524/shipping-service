package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.model.FinanceMessage;
import ru.karaban.shippingservice.repository.PriceRepository;
import ru.karaban.shippingservice.service.PriceService;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService<FinanceMessage> {

    private final PriceRepository priceRepository;

    @Override
    public Price finByPriceId(PriceId priceId) {
        return priceRepository.findById(priceId).orElseThrow();
    }


    @Override
    public Price savePrice(FinanceMessage message) {
        return priceRepository.save(Price.builder()
                .priceId(PriceId.builder().chainName(message.getChainName()).materialNo(message.getMaterialNo()).build())
                .pricePerUnit(message.getPricePerUnit())
                .build());
    }
}
