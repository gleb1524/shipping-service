package ru.karaban.shippingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.model.FinanceMessage;
import ru.karaban.shippingservice.service.PriceService;

@RestController
@RequestMapping("/finance")
@RequiredArgsConstructor
public class Finance {

    private final PriceService<FinanceMessage> priceService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FinanceMessage message) {
        priceService.savePrice(message);
        return ResponseEntity.ok().build();           //TODO добавить обработку не корректных запросов
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody FinanceMessage message) {
        priceService.savePrice(message);
        return ResponseEntity.ok().build();           //TODO добавить обработку не корректных запросов
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestBody FinanceMessage message) {
        Price price = priceService.finByPriceId(PriceId.builder().materialNo(message.getMaterialNo()).chainName(message.getChainName()).build());
        return ResponseEntity.ok(price);
    }
}
