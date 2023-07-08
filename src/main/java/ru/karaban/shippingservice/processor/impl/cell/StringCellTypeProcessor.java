package ru.karaban.shippingservice.processor.impl.cell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.processor.CellTypeProcessor;

@Component
public class StringCellTypeProcessor implements CellTypeProcessor<String> {
    @Override
    public String getCellValue(Cell cell) {
        return cell.getStringCellValue();
    }

    @Override
    public boolean checkType(Cell cell) {
        return cell.getCellType().equals(CellType.STRING);
    }
}
