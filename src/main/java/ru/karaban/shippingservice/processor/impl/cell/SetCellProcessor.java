package ru.karaban.shippingservice.processor.impl.cell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.processor.CellTypeProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SetCellProcessor {
    public Map<Integer, Object> setCell(Row row, List<CellTypeProcessor> cellTypeProcessors){
        Map<Integer, Object> cellValues = new HashMap<>();
        for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
            for (CellTypeProcessor typeProcessor : cellTypeProcessors) {
                Cell cell = row.getCell(j);
                if (cell!=null && typeProcessor.checkType(cell)) {
                    cellValues.put(j, typeProcessor.getCellValue(cell));
                }
            }
        }
        return cellValues;
    }
}
