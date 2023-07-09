package ru.karaban.shippingservice.service;

import ru.karaban.shippingservice.entity.Customer;

public interface CustomerService {

    Customer findById(Long id);
}
