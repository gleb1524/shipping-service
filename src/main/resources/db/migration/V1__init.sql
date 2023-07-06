create table products
(
    material_no              bigint primary key unique,
    material_desc_RUS        varchar not null unique,
    L3_product_category_code bigint  not null unique,
    L3_product_category_name varchar not null,
    created_at               timestamp default current_timestamp,
    updated_at               timestamp default current_timestamp,
    CONSTRAINT products_id UNIQUE (material_no)
);

create table customers
(
    CH3_ship_to_code bigint primary key unique,
    CH3_ship_to_name varchar not null,
    chain_name       varchar not null,
    created_at       timestamp default current_timestamp,
    updated_at       timestamp default current_timestamp,
    CONSTRAINT customers_id UNIQUE (CH3_ship_to_code)
);

create table price
(
    id                     bigint primary key unique,
    material_no            bigint,
    customers_id           bigint,
    chain_name             varchar not null,
    regular_price_per_unit decimal not null,
    created_at             timestamp default current_timestamp,
    updated_at             timestamp default current_timestamp,
    CONSTRAINT price_id UNIQUE (id)
);

ALTER TABLE price
    ADD CONSTRAINT FK_MATERIAL_NO_ON_PR FOREIGN KEY (material_no) REFERENCES products (material_no);
ALTER TABLE price
    ADD CONSTRAINT FK_CHAIN_NAME_ON_CUR FOREIGN KEY (customers_id) REFERENCES customers (CH3_ship_to_code);
SELECT pc.*, cs.chain_name
FROM price pc
         JOIN customers cs ON pc.customers_id = cs.CH3_ship_to_code