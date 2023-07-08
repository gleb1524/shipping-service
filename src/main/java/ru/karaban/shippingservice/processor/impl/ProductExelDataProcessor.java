package ru.karaban.shippingservice.processor.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.entity.Product;
import ru.karaban.shippingservice.processor.ExelDataProcessor;
import ru.karaban.shippingservice.service.ExelService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
public class ProductExelDataProcessor implements ExelDataProcessor {

    private static final String PRODUCT_SHEET = "Products";

    private final ExelService exelServiceProduct;

    private final ThreadPoolTaskExecutor taskExecutor;
    @Override
    @SneakyThrows
    public void process(XSSFSheet sheet, int batchSize) {
        int start = 0;
        List<Product> processedRow = new ArrayList<>();
        do {
            final int startRow = start;
            final int endRow = start + batchSize;
            Future<List<Product>> result = taskExecutor.submit(() -> exelServiceProduct.saveEntityFromExel(sheet, startRow, endRow));
            start += batchSize;
            processedRow.addAll(result.get());
        } while (start < sheet.getPhysicalNumberOfRows() && processedRow.size() != sheet.getPhysicalNumberOfRows());
    }

    @Override
    public boolean checkType(final XSSFSheet sheet) {
        return PRODUCT_SHEET.equals(sheet.getSheetName());
    }
}