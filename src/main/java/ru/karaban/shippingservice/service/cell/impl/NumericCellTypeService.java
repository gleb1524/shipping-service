package ru.karaban.shippingservice.service.cell.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.service.cell.CellTypeService;

@Component
public class NumericCellTypeService implements CellTypeService<Long> {
    @Override
    public Long getCellValue(Cell cell) {
        return (long) cell.getNumericCellValue();
    }

    @Override
    public boolean checkType(Cell cell) {
        return cell.getCellType().equals(CellType.NUMERIC);
    }
}
