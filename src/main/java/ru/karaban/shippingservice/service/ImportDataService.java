package ru.karaban.shippingservice.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface ImportDataService {

    void importDataFromFile(XSSFSheet sheet);

    void importData();
}
