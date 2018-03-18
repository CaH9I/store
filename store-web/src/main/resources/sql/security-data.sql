DELETE FROM authorities;
DELETE FROM users;

INSERT INTO users(username, password, enabled) VALUES ('admin', '2cf3a06c822820fe36a664a97f58312aaf29322920bc5b6c71ae042c9ebb16918ebad06a561e0c15', true);
INSERT INTO users(username, password, enabled) VALUES ('user', 'd158396b7bdcbd92a7fbc2ff9fdf48c3dce85b670365d4f7e62bf8579154d7c6650fdf3313de0f9f', true);
INSERT INTO authorities(username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities(username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES ('user', 'ROLE_USER');