package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.service.ImportDataService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class ImportExelDataService implements ImportDataService {

    private final ExelServiceProduct productService;
    private final ExelServicePrice priceService;

    @Override
    @SneakyThrows
    public void importDataFromFile(XSSFSheet sheet, String sheetName) {
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
//        XSSFSheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CountDownLatch latch = new CountDownLatch(4);


        for (int i = 0; i < 4; i++) {
            int startRow = i * totalRows / 4;
            int endRow = (i + 1) * totalRows / 4;
            executorService.execute(new DataReaderRunnable(sheet, startRow, endRow, latch, sheetName));
        }

        executorService.shutdown();
//        workbook.close();
//        fileInputStream.close();
    }

    private class DataReaderRunnable implements Runnable {
        private final XSSFSheet sheet;
        private final int startRow;
        private final int endRow;
        private final CountDownLatch latch;
        private final String sheetName;

        public DataReaderRunnable(XSSFSheet sheet, int startRow, int endRow, CountDownLatch latch, String sheetName) {
            this.sheet = sheet;
            this.startRow = startRow;
            this.endRow = endRow;
            this.latch = latch;
            this.sheetName = sheetName;
        }

        @Override
        public void run() {
//            for (int i = startRow; i < endRow; i++) {
//                Row row = sheet.getRow(i);
//
//                if (row == null || row.getRowNum() == 0) {
//                    continue;
//                }
//
//                Product product = Product.builder()
//                        .code((long) row.getCell(0).getNumericCellValue())
//                        .title(row.getCell(1).getStringCellValue())
//                        .categoryCode(Long.valueOf(row.getCell(2).getStringCellValue()))
//                        .brand(row.getCell(3).getStringCellValue())
//                        .build();
//
//                productRepository.save(product);
//            }
//            List<XSSFName> nameList = workbook.getAllNames();
//            String sheetName;
//            for (XSSFName name : nameList) {
//                sheetName = name.getSheetName();
//                System.out.println(sheetName);
                switch (sheetName) {
                    case "Products" -> productService.saveEntityFromExel(sheet, startRow, endRow);
                    case "Actuals" -> System.out.println("Actuals");
                    case "Customers" -> System.out.println("Customers!");
                    case "Price" -> priceService.saveEntityFromExel(sheet, startRow, endRow);
                    default -> System.out.println("Oooops, something wrong !");
//                }
            }

            latch.countDown();
        }
    }

}
