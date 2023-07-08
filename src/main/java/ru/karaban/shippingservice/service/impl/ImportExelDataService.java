package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.processor.ExelDataProcessor;
import ru.karaban.shippingservice.service.ImportDataService;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;


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
    public void importDataFromFile(XSSFSheet sheet) {
        for (ExelDataProcessor processor : processors) {
            if (processor.checkType(sheet)) {
                processor.process(sheet, batchSize);
            }
        }
    }

    @Override
    @SneakyThrows
    @PostProxy
    public void importData() {
        InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream(filePath); //TODO дописать исключения
        if(fileInputStream != null){
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                importDataFromFile(sheet);
            }
            fileInputStream.close();
            workbook.close();
        }
    }
}
