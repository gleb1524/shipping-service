package ru.karaban.shippingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karaban.shippingservice.entity.Price;
import ru.karaban.shippingservice.entity.key.PriceId;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {
}
