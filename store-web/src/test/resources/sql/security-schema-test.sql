CREATE TABLE users
(
  id BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  username CHARACTER VARYING(50) NOT NULL UNIQUE,
  password CHARACTER VARYING(255) NOT NULL,
  enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities
(
  username CHARACTER VARYING(50) NOT NULL REFERENCES users(username),
  authority CHARACTER VARYING(50) NOT NULL,
  CONSTRAINT authorities_pkey PRIMARY KEY (authority, username)
);

CREATE TABLE groups
(
  id BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  group_name CHARACTER VARYING(50) NOT NULL
);

CREATE TABLE group_members
(
  group_id BIGINT NOT NULL REFERENCES groups(id),
  username CHARACTER VARYING(50) NOT NULL REFERENCES users(username),
  CONSTRAINT group_members_pkey PRIMARY KEY (group_id, username)
);

CREATE TABLE group_authorities
(
  group_id BIGINT NOT NULL REFERENCES groups(id),
  authority CHARACTER VARYING(50) NOT NULL,
  CONSTRAINT group_authorities_pkey PRIMARY KEY (authority, group_id)
);