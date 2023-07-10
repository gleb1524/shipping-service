package ru.karaban.shippingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.model.ActualMessage;
import ru.karaban.shippingservice.service.ActualService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class Analysis {

    private final ActualService actualService;

    @PostMapping
    public ResponseEntity<?> getShipByPromo(@RequestBody ActualMessage message) {
        PriceId priceId = PriceId.builder().chainName(message.getChainName()).materialNo(message.getMaterialNo()).build();
        return ResponseEntity.ok(actualService.getFactByMonth(priceId, message.getStart(), message.getEnd()));
    }

    @PostMapping("/days")
    public ResponseEntity<?> getShipByDays(@RequestBody ActualMessage message) {
        Map<String, Long> map = message.getPriceIds();
        List<PriceId> priceIds = new ArrayList<>();
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            priceIds.add(PriceId.builder().materialNo(entry.getValue()).chainName(entry.getKey()).build());
        }
        return ResponseEntity.ok(actualService.getFactByPriceId(priceIds));
    }
}
