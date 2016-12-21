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
    additional_info character varying(255),
    CONSTRAINT order_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE commerce_item_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807;
    
CREATE TABLE commerce_item
(
    id bigint NOT NULL DEFAULT nextval('commerce_item_id_seq'),
    product_id bigint NOT NULL,
    price double precision NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT commerce_item_pkey PRIMARY KEY (id),
    CONSTRAINT commerce_item_product_id_fkey FOREIGN KEY (product_id) REFERENCES mobile_phone (id)
);

CREATE TABLE order_commerce_item
(
    order_id bigint NOT NULL,
    commerce_item_id bigint NOT NULL,
    CONSTRAINT order_commerce_item_pkey PRIMARY KEY (commerce_item_id, order_id),
    CONSTRAINT order_commerce_item_commerce_item_id_fkey FOREIGN KEY (commerce_item_id) REFERENCES commerce_item (id),
    CONSTRAINT order_commerce_item_order_id_fkey FOREIGN KEY (order_id) REFERENCES store_order (id)
);