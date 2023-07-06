package ru.karaban.shippingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karaban.shippingservice.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
