package ru.karaban.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karaban.shippingservice.entity.Product;
import ru.karaban.shippingservice.repository.ProductRepository;
import ru.karaban.shippingservice.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
