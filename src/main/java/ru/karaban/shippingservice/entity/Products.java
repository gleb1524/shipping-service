package ru.karaban.shippingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Products {

    @Id
    @Column(name = "material_no")
    private Long code;

    @Column(name = "material_desc_RUS")
    private String title;

    @Column(name = "L3_product_category_code")
    private Long categoryCode;

    @Column(name = "L3_product_category_name")
    private String brand;
}
