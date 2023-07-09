package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.processor.ExelDataProcessor;
import ru.karaban.shippingservice.service.ImportDataService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class ImportExelDataService implements ImportDataService {
    private final List<ExelDataProcessor> processors;

    @Value("${shipping.batchSize}")
    private Integer batchSize;
    @Value("${shipping.filePath}")
    private String filePath;


    @Override
    @SneakyThrows
    @PostProxy
    public void importData() {
        InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream(filePath); //TODO дописать исключения
        if (fileInputStream != null) {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            int numberOfSheets = workbook.getNumberOfSheets();
            Map<String, XSSFSheet> mapSheet = new HashMap<>();
            for (int i = 0; i < numberOfSheets; i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                mapSheet.put(sheet.getSheetName(), sheet);
            }
            importDataFromSheets(mapSheet);
            fileInputStream.close();
            workbook.close();
        }
    }

    private void importDataFromSheets(Map<String, XSSFSheet> mapSheet) {
        for (ExelDataProcessor processor : processors) {
            processor.process(mapSheet.get(processor.getType()), batchSize);
        }
    }
}
