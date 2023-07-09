package ru.karaban.shippingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.model.ActualMessage;
import ru.karaban.shippingservice.model.FinanceMessage;
import ru.karaban.shippingservice.service.ActualService;

@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class Analysis {

    private final ActualService actualService;

    @GetMapping
    public ResponseEntity<?> getShipByPromo(@RequestBody ActualMessage message) {
        PriceId priceId = PriceId.builder().chainName(message.getChainName()).materialNo(message.getMaterialNo()).build();
        return ResponseEntity.ok(actualService.getShipByPromo(priceId)) ;
    }

    public void getShipByDays() {

    }
}
