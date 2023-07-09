package ru.karaban.shippingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.karaban.shippingservice.entity.Actual;
import ru.karaban.shippingservice.entity.Customer;
import ru.karaban.shippingservice.entity.Price;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActualRepository extends JpaRepository<Actual, Long> {

    List<Actual> findAllByPrice(Price price);
    List<Actual> findAllByPriceAndPromoSign(Price price, String promo);
    List<Actual> findAllByPriceAndDateBetween(Price price, LocalDate start, LocalDate end);
}
