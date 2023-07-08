package ru.karaban.shippingservice.processor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CellTypeProcessor<T> {

    T getCellValue(Cell cell);
    boolean checkType(Cell cell);
}
