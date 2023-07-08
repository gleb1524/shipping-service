package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;
import ru.karaban.shippingservice.repository.PriceRepository;
import ru.karaban.shippingservice.service.ExelService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExelServicePrice implements ExelService<Price> {

    private final PriceRepository priceRepository;
    @Override
    public List<Price> saveEntityFromExel(XSSFSheet sheet, int startRow, int endRow) {

        List<Price> processedPrice = new ArrayList<>();
        for (int i = startRow; i < endRow; i++) {
            Row row = sheet.getRow(i);

            if (row == null || row.getRowNum() == 0) {
                continue;
            }
            Price price = Price.builder()
                    .priceId(PriceId.builder()
                            .chainName(row.getCell(0).getStringCellValue())
                            .materialNo(Long.valueOf(row.getCell(1).getStringCellValue()))
                            .build())
                    .pricePerUnit(BigDecimal.valueOf(row.getCell(2).getNumericCellValue()))
                    .build();
            processedPrice.add(price);
        }
        return priceRepository.saveAll(processedPrice);
    }
}
