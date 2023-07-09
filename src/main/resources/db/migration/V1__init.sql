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
    id bigint GENERATED ALWAYS AS IDENTITY,
    material_no      bigint,
    CH3_ship_to_code bigint  not null,
    chain_name       varchar not null,
    units            bigint,
    actual_sales     bigint,
    promo_sign       varchar,
    date             timestamp,
    created_at       timestamp default current_timestamp,
    updated_at       timestamp default current_timestamp
);
-- CREATE SEQUENCE actuals_id_seq;
--
-- ALTER TABLE actuals ALTER COLUMN id SET DEFAULT nextval('actuals_id_seq');

ALTER TABLE price
    ADD CONSTRAINT FK_MATERIAL_NO_ON_PR FOREIGN KEY (material_no) REFERENCES products (material_no);

ALTER TABLE actuals
    ADD CONSTRAINT FK_PRICE_ON_ACTUALS FOREIGN KEY (material_no, chain_name) REFERENCES price (material_no, chain_name);

CREATE OR REPLACE FUNCTION update_promo()
  RETURNS TRIGGER AS
$$
BEGIN
  IF NEW.actual_sales/NEW.units < (SELECT regular_price_per_unit FROM price WHERE material_no = NEW.material_no AND chain_name = NEW.chain_name) THEN
    NEW.promo_sign := 'Promo';
  ELSE
    NEW.promo_sign := 'Regular';
  END IF;

  RETURN NEW;
END;
$$
LANGUAGE plpgsql;


CREATE TRIGGER update_promo_trigger
    BEFORE INSERT ON actuals
    FOR EACH ROW
    EXECUTE FUNCTION update_promo();

