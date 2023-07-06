create table products
(
    material_no              bigint primary key unique,
    material_desc_RUS        varchar not null unique,
    L3_product_category_code bigint  not null,
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
    material_no            bigint,
    chain_name             varchar not null,
    regular_price_per_unit decimal not null,
    created_at             timestamp default current_timestamp,
    updated_at             timestamp default current_timestamp,
    PRIMARY KEY (material_no, chain_name)
);

create table actuals
(
    id               bigint primary key unique,
    material_no      bigint,
    CH3_ship_to_code bigint  not null,
    chain_name       varchar not null,
    units            bigint,
    actual_sales     bigint,
    promo_sign       varchar not null,
    date             timestamp,
    created_at       timestamp default current_timestamp,
    updated_at       timestamp default current_timestamp
);

ALTER TABLE price
    ADD CONSTRAINT FK_MATERIAL_NO_ON_PR FOREIGN KEY (material_no) REFERENCES products (material_no);

ALTER TABLE actuals
    ADD CONSTRAINT FK_PRICE_ON_ACTUALS FOREIGN KEY (material_no, chain_name) REFERENCES price (material_no, chain_name);