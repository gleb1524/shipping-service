package ru.karaban.shippingservice.processor.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.builder.ExelToEntityBuilder;
import ru.karaban.shippingservice.entity.Product;
import ru.karaban.shippingservice.processor.CellTypeProcessor;
import ru.karaban.shippingservice.processor.EntityProcessed;
import ru.karaban.shippingservice.processor.impl.cell.SetCellProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EntityProcessedImpl<T> implements EntityProcessed {

    private final List<CellTypeProcessor> cellTypeProcessors;
    private final SetCellProcessor setCellProcessor;

    @Override
    public List<T> entityProcessed(XSSFSheet sheet, int startRow, int endRow, ExelToEntityBuilder exelToEntityBuilder) {
        List<T> processedProducts = new ArrayList<>();
        Map<Integer, Object> cellValues;
        for (int i = startRow; i < endRow; i++) {
            Row row = sheet.getRow(i);

            if(checkRowIsBlank(row)){
                cellValues = setCellProcessor.setCell(row, cellTypeProcessors);
                processedProducts.add((T) exelToEntityBuilder.building(cellValues));
            }
        }
        return processedProducts;
    }

    private boolean checkRowIsBlank(Row row) {
        boolean isBlank = true;
        if(row == null){
            return false;
        }
        if(row.getRowNum() == 0){
            return false;
        }
        for (Cell cell : row) {
            if(cell == null) {
                isBlank = false;
                break;
            }
        }
        return isBlank;
    }
}
