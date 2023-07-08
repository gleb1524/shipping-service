package ru.karaban.shippingservice.service;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;


import java.util.List;

public interface ExelService<T> {

    List<T> saveEntityFromExel(XSSFSheet sheet, int startRow, int endRow);
}
