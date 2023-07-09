package ru.karaban.shippingservice.processor.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.processor.EntityProcessor;
import ru.karaban.shippingservice.service.cell.CellTypeService;
import ru.karaban.shippingservice.service.cell.impl.SetCellService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EntityProcessorImpl<T> implements EntityProcessor {

    private final List<CellTypeService> cellTypeServices;
    private final SetCellService setCellService;

    @Override
    public List<T> entityProcessed(XSSFSheet sheet, int startRow, int endRow, ExelToEntityBuilder exelToEntityBuilder) {
        List<T> processedProducts = new ArrayList<>();
        Map<Integer, Object> cellValues;
        for (int i = startRow; i < endRow; i++) {
            Row row = sheet.getRow(i);

            if (checkRowIsBlank(row)) {
                cellValues = setCellService.setCell(row, cellTypeServices);
                processedProducts.add((T) exelToEntityBuilder.building(cellValues));
            }
        }
        return processedProducts;
    }

    private boolean checkRowIsBlank(Row row) {
        boolean isBlank = true;
        if (row == null) {
            return false;
        }
        if (row.getRowNum() == 0) {
            return false;
        }
        for (Cell cell : row) {
            if (cell == null) {
                isBlank = false;
                break;
            }
        }
        return isBlank;
    }
}
