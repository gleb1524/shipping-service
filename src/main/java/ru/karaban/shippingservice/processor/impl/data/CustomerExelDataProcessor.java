package ru.karaban.shippingservice.processor.impl.data;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.processor.ExelDataProcessor;
import ru.karaban.shippingservice.processor.impl.Processor;
import ru.karaban.shippingservice.service.ExelService;


@Component
@Order(2)
@RequiredArgsConstructor
public class CustomerExelDataProcessor implements ExelDataProcessor {

    private static final String CUSTOMER_SHEET = "Customers";
    private final ExelService<Customer> exelServiceCustomer;
    private final ThreadPoolTaskExecutor taskExecutor;
    private final Processor processor;

    @Override
    public void process(XSSFSheet sheet, int batchSize) {
        processor.process(sheet, batchSize, taskExecutor, exelServiceCustomer);
    }

    @Override
    public String getType() {
        return CUSTOMER_SHEET;
    }
}
