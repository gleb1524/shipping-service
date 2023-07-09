package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.entity.Actual;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.processor.EntityProcessed;
import ru.karaban.shippingservice.repository.ActualRepository;
import ru.karaban.shippingservice.repository.CustomerRepository;
import ru.karaban.shippingservice.service.ExelService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExelServiceActual implements ExelService<Actual> {

    private final ActualRepository actualRepository;
    private final EntityProcessed entityProcessedImpl;
    private final ExelToEntityBuilder exelToActualBuilder;
    @Override
    public List<Actual> saveEntityFromExel(XSSFSheet sheet, int startRow, int endRow) {
        List<Actual> customerList = entityProcessedImpl.entityProcessed(sheet,startRow,endRow, exelToActualBuilder);
        return actualRepository.saveAll(customerList);
    }
}
