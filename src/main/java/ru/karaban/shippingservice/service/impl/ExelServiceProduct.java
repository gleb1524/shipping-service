package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.entity.Product;
import ru.karaban.shippingservice.repository.ProductRepository;
import ru.karaban.shippingservice.service.ExelService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExelServiceProduct implements ExelService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> saveEntityFromExel(XSSFSheet sheet, int startRow, int endRow) {

        List<Product> processedProducts = new ArrayList<>();
        for (int i = startRow; i < endRow; i++) {
            Row row = sheet.getRow(i);

            if (row == null || row.getRowNum() == 0) {
                continue;
            }
            Product product = Product.builder()
                    .code((long) row.getCell(0).getNumericCellValue())
                    .title(row.getCell(1).getStringCellValue())
                    .categoryCode(Long.valueOf(row.getCell(2).getStringCellValue()))
                    .brand(row.getCell(3).getStringCellValue())
                    .build();
            processedProducts.add(product);
        }
        return productRepository.saveAll(processedProducts);
    }
}
