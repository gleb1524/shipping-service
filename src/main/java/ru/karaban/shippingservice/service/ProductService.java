package ru.karaban.shippingservice.service;

import ru.karaban.shippingservice.entity.Product;

public interface ProductService {

    Product findById(Long id);
}
