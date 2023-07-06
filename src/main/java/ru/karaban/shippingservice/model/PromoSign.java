package ru.karaban.shippingservice.model;

public enum PromoSign {
    REGULAR("Regular"),
    PROMO("Promo");

    private String title;

    PromoSign(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
