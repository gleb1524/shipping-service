package ru.karaban.shippingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.karaban.shippingservice.service.impl.ImportExelDataService;
import ru.karaban.shippingservice.service.impl.ShipServiceImpl;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ShipServiceImpl excelDataService;

    @PostMapping("/import")
    public void importData(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = "D:\\program files\\projects\\shipping-service\\src\\main\\resources\\file.xlsx";
        file.transferTo(new File(filePath));
        excelDataService.importDataFromFile(filePath);
    }
}
