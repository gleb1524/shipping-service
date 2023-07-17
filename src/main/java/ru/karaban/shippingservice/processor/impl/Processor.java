package ru.karaban.shippingservice.processor.impl;

import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.entity.Actual;
import ru.karaban.shippingservice.service.ExelService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
@Component
public class Processor {

    @SneakyThrows
    public void process(XSSFSheet sheet, int batchSize, ThreadPoolTaskExecutor taskExecutor, ExelService exelService){
        int start = 0;
        List<Actual> processedRow = new ArrayList<>();
        do {
            final int startRow = start;
            final int endRow = start + batchSize;
            Future<List<Actual>> result = taskExecutor.submit(() -> exelService.saveEntityFromExel(sheet, startRow, endRow));
            start += batchSize;
            processedRow.addAll(result.get());
        } while (start < sheet.getPhysicalNumberOfRows() && processedRow.size() != sheet.getPhysicalNumberOfRows());
    }
}
