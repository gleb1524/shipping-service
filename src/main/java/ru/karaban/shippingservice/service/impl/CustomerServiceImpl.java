package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.repository.CustomerRepository;
import ru.karaban.shippingservice.service.CustomerService;

@Component
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }
}
