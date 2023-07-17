package ru.karaban.shippingservice.service.cell.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.service.cell.CellTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SetCellService {
    public Map<Integer, Object> setCell(Row row, List<CellTypeService> cellTypeServices) {
        Map<Integer, Object> cellValues = new HashMap<>();
        for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
            for (CellTypeService typeProcessor : cellTypeServices) {
                Cell cell = row.getCell(j);
                if (cell != null && typeProcessor.checkType(cell)) {
                    cellValues.put(j, typeProcessor.getCellValue(cell));
                }
            }
        }
        return cellValues;
    }
}
