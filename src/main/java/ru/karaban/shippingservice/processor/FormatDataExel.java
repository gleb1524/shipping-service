package ru.karaban.shippingservice.processor;

import org.springframework.stereotype.Component;

@Component
public class FormatDataExel {

    public String format(String str) {
        if(str == null){
            return "null";
        }
        return str.replaceAll("[^0-9]", "");
    }
}
