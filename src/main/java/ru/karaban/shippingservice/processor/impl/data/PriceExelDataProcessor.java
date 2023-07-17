package ru.karaban.shippingservice.processor.impl.data;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.processor.ExelDataProcessor;
import ru.karaban.shippingservice.processor.impl.Processor;
import ru.karaban.shippingservice.service.ExelService;

@Component
@Order(3)
@RequiredArgsConstructor
public class PriceExelDataProcessor implements ExelDataProcessor {

    private static final String PRICE_SHEET = "Price";
    private final ExelService<Price> exelServicePrice;
    private final ThreadPoolTaskExecutor taskExecutor;
    private final Processor processor;

    @Override
    @SneakyThrows
    public void process(XSSFSheet sheet, int batchSize) {
        processor.process(sheet, batchSize, taskExecutor, exelServicePrice);
    }

    @Override
    public String getType() {
        return PRICE_SHEET;
    }
}
