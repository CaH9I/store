CREATE TABLE mobile_phone
(
    id bigint GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    display VARCHAR(50) NOT NULL,
    length VARCHAR(50) NOT NULL,
    width VARCHAR(50) NOT NULL,
    color VARCHAR(50) NOT NULL,
    price double precision NOT NULL,
    camera VARCHAR(50) NOT NULL
);

CREATE TABLE store_order
(
    id bigint GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    address character varying(255) NOT NULL,
    phone character varying(50) NOT NULL,
    subtotal double precision NOT NULL,
    delivery_amount double precision NOT NULL,
    total double precision NOT NULL,
    additional_info character varying(255),
    state character varying(50) NOT NULL,
    CONSTRAINT store_order_state_check CHECK (state IN ('SUBMITTED', 'DELIVERED'))
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

CREATE TABLE users
(
    id bigint GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    enabled boolean NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_username_key UNIQUE (username)
);

CREATE TABLE authorities
(
    username character varying(50) NOT NULL,
    authority character varying(50) NOT NULL,
    CONSTRAINT authorities_pkey PRIMARY KEY (authority, username),
    CONSTRAINT authorities_username_fkey FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE groups
(
    id bigint GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    group_name character varying(50) NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
);

CREATE TABLE group_members
(
    group_id bigint NOT NULL,
    username character varying(50) NOT NULL,
    CONSTRAINT group_members_pkey PRIMARY KEY (group_id, username),
    CONSTRAINT group_members_group_id_fkey FOREIGN KEY (group_id) REFERENCES groups (id),
    CONSTRAINT group_members_username_fkey FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE group_authorities
(
    group_id bigint NOT NULL,
    authority character varying(50) NOT NULL,
    CONSTRAINT group_authorities_pkey PRIMARY KEY (authority, group_id),
    CONSTRAINT group_authorities_group_id_fkey FOREIGN KEY (group_id) REFERENCES groups (id)
);