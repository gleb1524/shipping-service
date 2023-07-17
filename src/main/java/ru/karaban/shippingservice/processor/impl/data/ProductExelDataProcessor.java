package ru.karaban.shippingservice.processor.impl.data;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.entity.Product;
import ru.karaban.shippingservice.processor.ExelDataProcessor;
import ru.karaban.shippingservice.processor.impl.Processor;
import ru.karaban.shippingservice.service.ExelService;

@Component
@Order(1)
@RequiredArgsConstructor
public class ProductExelDataProcessor implements ExelDataProcessor {

    private static final String PRODUCT_SHEET = "Products";
    private final ExelService<Product> exelServiceProduct;
    private final ThreadPoolTaskExecutor taskExecutor;
    private final Processor processor;

    @Override
    @SneakyThrows
    public void process(XSSFSheet sheet, int batchSize) {
        processor.process(sheet, batchSize, taskExecutor, exelServiceProduct);
    }

    @Override
    public String getType() {
        return PRODUCT_SHEET;
    }
}
