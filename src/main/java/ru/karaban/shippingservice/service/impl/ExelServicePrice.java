package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.processor.EntityProcessed;
import ru.karaban.shippingservice.repository.PriceRepository;
import ru.karaban.shippingservice.service.ExelService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExelServicePrice implements ExelService<Price> {

    private final PriceRepository priceRepository;
    private final EntityProcessed entityProcessedImpl;
    private final ExelToEntityBuilder exelToPriceBuilder;
    @Override
    public List<Price> saveEntityFromExel(XSSFSheet sheet, int startRow, int endRow) {

        List<Price> processedPrice = entityProcessedImpl.entityProcessed(sheet,startRow,endRow, exelToPriceBuilder);
        return priceRepository.saveAll(processedPrice);
    }
}
