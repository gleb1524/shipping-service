package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.entity.Actual;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.model.AnalysisModel;
import ru.karaban.shippingservice.repository.ActualRepository;
import ru.karaban.shippingservice.service.ActualService;
import ru.karaban.shippingservice.service.ProductService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActualServiceImpl implements ActualService<PriceId, LocalDate> {

    private final ActualRepository actualRepository;
    private final ProductService productService;

    @Override
    public AnalysisModel getShipByPromo(PriceId priceId, LocalDate dateStart, LocalDate dateEnd) {


        List<Actual> actualListByPriceAndDate =
                actualRepository.findAllByPriceAndDateBetween(Price.builder().priceId(priceId).build(), dateStart, dateEnd);
        double totalUnit = 0;
        double promoUnit = 0;
        double regularUnit = 0;

        for (Actual actual : actualListByPriceAndDate) {
            totalUnit += actual.getUnits();
            if (actual.getPromoSign().equals("Promo")) {
                promoUnit += actual.getUnits();
            } else {
                regularUnit += actual.getUnits();
            }
        }
        Double promoShare = calculatePromoShare(totalUnit, promoUnit);
        return AnalysisModel.builder()
                .chainName(priceId.getChainName())
                .categoryCode(productService.findById(priceId.getMaterialNo()).getCategoryCode())
                .categoryName(productService.findById(priceId.getMaterialNo()).getBrand())
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .regularUnits((int) regularUnit)
                .promoUnits((int) promoUnit)
                .promoShare(promoShare)
                .build();
    }

    private Double calculatePromoShare(double total, double promoUnit) {
        double promoShare = 0d;
        if (total != 0d) {
            promoShare = promoUnit / total;
        }
        return promoShare;
    }
}
