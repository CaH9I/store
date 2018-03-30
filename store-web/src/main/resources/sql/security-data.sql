DELETE FROM authorities;
DELETE FROM users;

INSERT INTO users(username, password, enabled) VALUES ('admin', '$2a$04$TeWT/i9Iuht8jAgxMOaKTuHpdaHvrKpVwv9npt13g0BR0H7DPCweW', true);
INSERT INTO users(username, password, enabled) VALUES ('user', '$2a$04$QP5sIhM6txQCD6B5Ujem1.oub.LaMiS9hu18hFmNYEx1zNebvmmZy', true);
INSERT INTO authorities(username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities(username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES ('user', 'ROLE_USER');