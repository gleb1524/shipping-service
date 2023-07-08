package ru.karaban.shippingservice.processor.impl.cell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.processor.CellTypeProcessor;

@Component
public class NumericCellTypeProcessor implements CellTypeProcessor<Long> {
    @Override
    public Long getCellValue(Cell cell) {
        return (long) cell.getNumericCellValue();
    }

    @Override
    public boolean checkType(Cell cell) {
        return cell.getCellType().equals(CellType.NUMERIC);
    }
}
