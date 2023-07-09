package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.entity.Actual;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.model.AnalysisModel;
import ru.karaban.shippingservice.repository.ActualRepository;
import ru.karaban.shippingservice.service.ActualService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActualServiceImpl implements ActualService<AnalysisModel, PriceId> {

    private final ActualRepository actualRepository;

    @Override
    public List<AnalysisModel> getShipByPromo(PriceId priceId) {

        Price price = Price.builder().priceId(priceId).build();
        List<Actual> actuals = actualRepository.findAllByPrice(price);

        Long total = (long) actuals.size();

        Long unitWithPromo = (long) actualRepository.findAllByPriceAndPromoSign(price, "Promo").size();

        Long promoShare = calculatePromoShare(total, unitWithPromo);
        return actuals.stream().map(a ->
                AnalysisModel.builder()
                        .date(a.getDate())
                        .categoryCode(a.getCustomer().getId())
                        .units(unitWithPromo)
                        .chainName(a.getCustomer().getChainName())
                        .promoShare(promoShare)
                        .build()
        ).collect(Collectors.toList());
    }

    private Long calculatePromoShare(Long total, Long unitWithPromo) {
        return unitWithPromo/total;
    }

}
