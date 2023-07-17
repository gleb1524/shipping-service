package ru.karaban.shippingservice.processor.impl.data;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.entity.Actual;
import ru.karaban.shippingservice.processor.ExelDataProcessor;
import ru.karaban.shippingservice.processor.impl.Processor;
import ru.karaban.shippingservice.service.ExelService;

@Component
@Order(4)
@RequiredArgsConstructor
public class ActualExelDataProcessor implements ExelDataProcessor {

    private static final String ACTUAL_SHEET = "Actuals";
    private final ExelService<Actual> exelServiceActual;
    private final ThreadPoolTaskExecutor taskExecutor;
    private final Processor processor;

    @Override
    public void process(XSSFSheet sheet, int batchSize) {
        processor.process(sheet, batchSize, taskExecutor,exelServiceActual);
    }

    @Override
    public String getType() {
        return ACTUAL_SHEET;
    }
}
