package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.entity.Product;
import ru.karaban.shippingservice.processor.EntityProcessed;
import ru.karaban.shippingservice.repository.ProductRepository;
import ru.karaban.shippingservice.service.ExelService;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ExelServiceProduct implements ExelService {

    private final ProductRepository productRepository;
    private final EntityProcessed entityProcessedImpl;
    private final ExelToEntityBuilder exelToProductBuilder;

    @Override
    public List<Product> saveEntityFromExel(XSSFSheet sheet, int startRow, int endRow) {

        List<Product> processedProducts = entityProcessedImpl.entityProcessed(sheet,startRow,endRow, exelToProductBuilder);
        return productRepository.saveAll(processedProducts);
    }
}
