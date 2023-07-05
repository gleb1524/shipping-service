package ru.karaban.shippingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customers {

    @Id
    @Column(name = "CH3_ship_to_code")
    private Long id;

    @Column(name = "CH3_ship_to_name")
    private String address;

    @Column(name = "chain_name")
    private String chainName;
}
