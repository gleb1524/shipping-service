package ru.karaban.shippingservice.processor;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;

import java.util.List;

@Component
public interface EntityProcessed<T> {

     List<T> entityProcessed(XSSFSheet sheet, int startRow, int endRow, ExelToEntityBuilder exelToEntityBuilder);
}
