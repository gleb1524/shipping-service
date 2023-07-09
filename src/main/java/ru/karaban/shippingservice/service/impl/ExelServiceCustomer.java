package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.processor.EntityProcessed;
import ru.karaban.shippingservice.repository.CustomerRepository;
import ru.karaban.shippingservice.service.ExelService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExelServiceCustomer implements ExelService<Customer> {

    private final CustomerRepository customerRepository;
    private final EntityProcessed entityProcessedImpl;
    private final ExelToEntityBuilder exelToCustomerBuilder;
    @Override
    public List<Customer> saveEntityFromExel(XSSFSheet sheet, int startRow, int endRow) {
        List<Customer> customerList = entityProcessedImpl.entityProcessed(sheet,startRow,endRow, exelToCustomerBuilder);
        return customerRepository.saveAll(customerList);
    }
}
