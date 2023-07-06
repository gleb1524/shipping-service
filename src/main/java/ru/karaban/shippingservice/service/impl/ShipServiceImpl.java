package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.service.ShipService;

import java.io.FileInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {

    private final ImportExelDataService importExelDataService;
    @Override
    @SneakyThrows
    public void importDataFromFile(String filePath) {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            importExelDataService.importDataFromFile(sheet, sheetName);
        }
        fileInputStream.close();
        workbook.close();
    }
}
