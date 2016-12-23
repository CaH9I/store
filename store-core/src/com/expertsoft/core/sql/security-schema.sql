DROP TABLE IF EXISTS group_authorities;
DROP TABLE IF EXISTS group_members;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS groups_id_seq;
DROP SEQUENCE IF EXISTS user_id_seq;

CREATE SEQUENCE user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE users
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'),
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
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

CREATE SEQUENCE groups_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE groups
(
    id bigint NOT NULL DEFAULT nextval('groups_id_seq'),
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