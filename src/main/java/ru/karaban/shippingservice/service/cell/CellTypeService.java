package ru.karaban.shippingservice.service.cell;

import org.apache.poi.ss.usermodel.Cell;

public interface CellTypeService<T> {

    T getCellValue(Cell cell);

    boolean checkType(Cell cell);
}
