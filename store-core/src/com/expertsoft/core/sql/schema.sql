CREATE SEQUENCE mobile_phone_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE mobile_phone
(
    id bigint NOT NULL DEFAULT nextval('mobile_phone_id_seq'::regclass),
    model character varying(50) NOT NULL,
    display character varying(50) NOT NULL,
    length character varying(50) NOT NULL,
    width character varying(50) NOT NULL,
    color character varying(50) NOT NULL,
    price double precision NOT NULL,
    camera character varying(50) NOT NULL,
    CONSTRAINT mobile_phone_pkey PRIMARY KEY (id)
);