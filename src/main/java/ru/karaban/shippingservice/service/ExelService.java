package ru.karaban.shippingservice.service;


import org.apache.poi.xssf.usermodel.XSSFSheet;


import java.util.List;

public interface ExelService<T> {

    List<T> saveEntityFromExel(XSSFSheet sheet, int startRow, int endRow);
}
