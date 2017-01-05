DROP TABLE IF EXISTS fixed_delivery_price;
DROP TABLE IF EXISTS commerce_item;
DROP TABLE IF EXISTS store_order;
DROP TABLE IF EXISTS mobile_phone;
DROP SEQUENCE IF EXISTS order_id_seq;
DROP SEQUENCE IF EXISTS mobile_phone_id_seq;

CREATE SEQUENCE mobile_phone_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807;

CREATE TABLE mobile_phone
(
    id bigint NOT NULL DEFAULT nextval('mobile_phone_id_seq'),
    model character varying(50) NOT NULL,
    display character varying(50) NOT NULL,
    length character varying(50) NOT NULL,
    width character varying(50) NOT NULL,
    color character varying(50) NOT NULL,
    price double precision NOT NULL,
    camera character varying(50) NOT NULL,
    CONSTRAINT mobile_phone_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE order_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807;

CREATE TABLE store_order
(
    id bigint NOT NULL DEFAULT nextval('order_id_seq'),
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    address character varying(255) NOT NULL,
    phone character varying(50) NOT NULL,
    subtotal double precision NOT NULL,
    delivery_amount double precision NOT NULL,
    total double precision NOT NULL,
    additional_info character varying(255),
    CONSTRAINT order_pkey PRIMARY KEY (id)
);

CREATE TABLE commerce_item
(
    order_id bigint NOT NULL,
    product_id bigint NOT NULL,
    price double precision NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT commerce_item_pkey PRIMARY KEY (order_id, product_id),
    CONSTRAINT commerce_item_order_id_fkey FOREIGN KEY (order_id) REFERENCES store_order (id),
    CONSTRAINT commerce_item_product_id_fkey FOREIGN KEY (product_id) REFERENCES mobile_phone (id)
);

CREATE TABLE fixed_delivery_price
(
    amount double precision NOT NULL
);