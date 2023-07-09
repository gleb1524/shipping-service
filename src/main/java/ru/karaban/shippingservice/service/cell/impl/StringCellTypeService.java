package ru.karaban.shippingservice.service.cell.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.service.cell.CellTypeService;

@Component
public class StringCellTypeService implements CellTypeService<String> {
    @Override
    public String getCellValue(Cell cell) {
        return cell.getStringCellValue();
    }

    @Override
    public boolean checkType(Cell cell) {
        return cell.getCellType().equals(CellType.STRING);
    }
}
