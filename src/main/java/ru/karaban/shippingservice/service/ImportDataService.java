package ru.karaban.shippingservice.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;


public interface ImportDataService {

    void importDataFromFile(XSSFSheet sheet);

    void importData();
}
