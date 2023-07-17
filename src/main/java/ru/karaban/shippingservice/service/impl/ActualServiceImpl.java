package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.entity.Actual;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.Product;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.model.AnalysisModel;
import ru.karaban.shippingservice.repository.ActualRepository;
import ru.karaban.shippingservice.service.ActualService;
import ru.karaban.shippingservice.service.ProductService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActualServiceImpl implements ActualService<PriceId, AnalysisModel> {

    private final ActualRepository actualRepository;
    private final ProductService productService;

    @Override
    public AnalysisModel getFactByMonth(PriceId priceId, LocalDate dateStart, LocalDate dateEnd) {


        List<Actual> actualListByPriceAndDate =
                actualRepository.findAllByPriceAndDateBetween(Price.builder().priceId(priceId).build(), dateStart, dateEnd);

        Map<String, Double> units = calculateUnitsByPromoSing(actualListByPriceAndDate);

        return AnalysisModel.builder()
                .chainName(priceId.getChainName())
                .categoryCode(productService.findById(priceId.getMaterialNo()).getCategoryCode())
                .categoryName(productService.findById(priceId.getMaterialNo()).getBrand())
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .regularUnits(units.get("regularUnit"))
                .promoUnits(units.get("promoUnit"))
                .promoShare(units.get("promoShare"))
                .build();
    }

    @Override
    public List<AnalysisModel> getFactByPriceId(List<PriceId> priceIds) {
        List<Price> prices =
                priceIds.stream().map(priceId -> Price.builder().priceId(priceId).build()).toList();
        List<Actual> allByPriceInOrderByDate =
                actualRepository.findAllByPriceInOrderByDate(prices);
        Map<String, Double> units = calculateUnitsByPromoSing(allByPriceInOrderByDate);
        Map<Long, Product> products = new HashMap<>();
        List<Product> collect = priceIds.stream().map(p -> productService.findById(p.getMaterialNo())).collect(Collectors.toList());
        for (Product product : collect) {
            products.put(product.getCode(), product);
        }
        return allByPriceInOrderByDate.stream().map(actual ->
                AnalysisModel.builder()
                        .chainName(actual.getPrice().getPriceId().getChainName())
                        .categoryCode(products.get(actual.getPrice().getPriceId().getMaterialNo()).getCategoryCode())
                        .categoryName(products.get(actual.getPrice().getPriceId().getMaterialNo()).getBrand())
                        .dateStart(actual.getDate())
                        .regularUnits(units.get("regularUnit"))
                        .promoUnits(units.get("promoUnit"))
                        .promoShare(units.get("promoShare"))
                        .build()
        ).toList();
    }

    private Map<String, Double> calculateUnitsByPromoSing(List<Actual> actualList) {
        double totalUnit = 0;
        double promoUnit = 0;
        double regularUnit = 0;

        for (Actual actual : actualList) {
            totalUnit += actual.getUnits();
            if (actual.getPromoSign().equals("Promo")) {
                promoUnit += actual.getUnits();
            } else {
                regularUnit += actual.getUnits();
            }
        }
        Double promoShare = calculatePromoShare(totalUnit, promoUnit);
        Map<String, Double> unitsByPromo = new HashMap<>();
        unitsByPromo.put("promoUnit", totalUnit);
        unitsByPromo.put("regularUnit", regularUnit);
        unitsByPromo.put("promoShare", promoShare);
        return unitsByPromo;
    }

    private Double calculatePromoShare(double total, double promoUnit) {
        double promoShare = 0d;
        if (total != 0d) {
            promoShare = promoUnit / total;
        }
        return promoShare;
    }
}
