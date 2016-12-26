INSERT INTO mobile_phone(model, display, length, width, color, price, camera) VALUES
    ('Samsung galaxy SII', '4''''', '14mm', '56mm', 'black', 600, '12MP'),
    ('Samsung galaxy SIII', '5''''', '16mm', '60mm', 'black', 650, '13MP'),
    ('Samsung galaxy S6', '5.5''''', '17mm', '62mm', 'black', 750, '16MP');

INSERT INTO fixed_delivery_price(amount) VALUES(5.0);

INSERT INTO users(username, password, enabled) VALUES
    ('admin', '2cf3a06c822820fe36a664a97f58312aaf29322920bc5b6c71ae042c9ebb16918ebad06a561e0c15', true),
    ('user', 'd158396b7bdcbd92a7fbc2ff9fdf48c3dce85b670365d4f7e62bf8579154d7c6650fdf3313de0f9f', true);
INSERT INTO authorities(username, authority) VALUES
    ('admin', 'ROLE_ADMIN'),
    ('admin', 'ROLE_USER'),
    ('user', 'ROLE_USER');