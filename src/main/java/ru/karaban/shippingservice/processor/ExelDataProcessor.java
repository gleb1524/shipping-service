package ru.karaban.shippingservice.processor;


import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface ExelDataProcessor {


    void process(XSSFSheet sheet, int batchSize);
    String getType();
}
