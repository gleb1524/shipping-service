package ru.karaban.shippingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karaban.shippingservice.entity.Actual;

@Repository
public interface ActualRepository extends JpaRepository<Actual, Long> {
}
