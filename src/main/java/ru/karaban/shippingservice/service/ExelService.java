package ru.karaban.shippingservice.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface ExelService {

    void saveEntityFromExel(XSSFSheet sheet, int startRow, int endRow);
}
