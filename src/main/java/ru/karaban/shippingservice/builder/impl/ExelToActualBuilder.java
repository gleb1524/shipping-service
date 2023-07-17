package ru.karaban.shippingservice.builder.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.entity.Actual;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.service.infrastructure.FormatDataExel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExelToActualBuilder implements ExelToEntityBuilder<Actual> {

    //    private final PriceService priceService;
//    private final CustomerService customerService;
    private final FormatDataExel formatDataExel;

    @Override
    public Actual building(Map<Integer, Object> cellValues) {
        BigDecimal actualSales = BigDecimal.valueOf((long) cellValues.get(5));
        Long units = (Long) cellValues.get(4);
        PriceId priceId = PriceId.builder()
                .chainName((String) cellValues.get(3))
                .materialNo(Long.valueOf(formatDataExel.format((String) cellValues.get(1))))
                .build();
//        Price price = priceService.finByPriceId(priceId);
//        Customer customer = customerService.findById(Long.valueOf(formatDataExel.format ((String) cellValues.get(2))));
//        BigDecimal pricePerUnit = price.getPricePerUnit();
//        PromoSign promoSign = checkPromoSign(actualSales, units, pricePerUnit);

        return Actual.builder()
                .date(LocalDate.parse((String) cellValues.get(0)))
                .customer(Customer.builder().id(Long.valueOf(formatDataExel.format((String) cellValues.get(2)))).build())
                .price(Price.builder().priceId(priceId).build())
                .units(units)
                .actualSales(actualSales)
//                .promoSign(PromoSign.PROMO)
                .build();
    }

//    private PromoSign checkPromoSign(BigDecimal actualSales,Long units, BigDecimal pricePerUnit) {
//        double actual = actualSales.doubleValue();
//        double price = pricePerUnit.doubleValue();
//        if(actual/units<price) {
//            return PromoSign.PROMO;
//        } else {
//            return PromoSign.REGULAR;
//        }
//    }
}
