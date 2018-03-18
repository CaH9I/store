CREATE SEQUENCE IF NOT EXISTS user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS users
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'),
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    enabled boolean NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_username_key UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS authorities
(
    username character varying(50) NOT NULL,
    authority character varying(50) NOT NULL,
    CONSTRAINT authorities_pkey PRIMARY KEY (authority, username),
    CONSTRAINT authorities_username_fkey FOREIGN KEY (username) REFERENCES users (username)
);

CREATE SEQUENCE IF NOT EXISTS groups_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS groups
(
    id bigint NOT NULL DEFAULT nextval('groups_id_seq'),
    group_name character varying(50) NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS group_members
(
    group_id bigint NOT NULL,
    username character varying(50) NOT NULL,
    CONSTRAINT group_members_pkey PRIMARY KEY (group_id, username),
    CONSTRAINT group_members_group_id_fkey FOREIGN KEY (group_id) REFERENCES groups (id),
    CONSTRAINT group_members_username_fkey FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE IF NOT EXISTS group_authorities
(
    group_id bigint NOT NULL,
    authority character varying(50) NOT NULL,
    CONSTRAINT group_authorities_pkey PRIMARY KEY (authority, group_id),
    CONSTRAINT group_authorities_group_id_fkey FOREIGN KEY (group_id) REFERENCES groups (id)
);