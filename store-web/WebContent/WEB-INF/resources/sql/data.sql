INSERT INTO mobile_phone(model, display, length, width, color, price, camera) VALUES
    ('Samsung galaxy SII', '4''''', '14mm', '56mm', 'black', 600, '12MP'),
    ('Samsung galaxy SIII', '5''''', '16mm', '60mm', 'black', 650, '13MP'),
    ('Samsung galaxy S6', '5.5''''', '17mm', '62mm', 'black', 750, '16MP');

INSERT INTO fixed_delivery_price(amount) VALUES(5);

INSERT INTO users(username, password, enabled) VALUES
    ('admin', '2cf3a06c822820fe36a664a97f58312aaf29322920bc5b6c71ae042c9ebb16918ebad06a561e0c15', true),
    ('user', 'd158396b7bdcbd92a7fbc2ff9fdf48c3dce85b670365d4f7e62bf8579154d7c6650fdf3313de0f9f', true);
INSERT INTO authorities(username, authority) VALUES
    ('admin', 'ROLE_ADMIN'),
    ('admin', 'ROLE_USER'),
    ('user', 'ROLE_USER');
    
INSERT INTO store_order(first_name, last_name, address, phone, subtotal, delivery_amount, total, additional_info) VALUES
    ('James', 'Smith', '135 Evergreen Ave. Wake Forest, NC 27587', '202-555-0118', 2850, 10, 2860, 'Do not delay delivery please'),
    ('John', 'Johnson', '7716 Wagon St. Kingsport, TN 37660', '202-555-0137', 3400, 15, 3415, 'I''d like you to deliver till December 24');
INSERT INTO store_order(first_name, last_name, address, phone, subtotal, delivery_amount, total) VALUES
    ('Robert', 'Williams', '8668 East Snake Hill St. Yuba City, CA 95993', '+1-202-555-0106', 4000, 5, 4005);
    
INSERT INTO commerce_item(order_id, product_id, price, quantity) VALUES
    (1, 1, 400, 2),
    (1, 2, 500, 3),
    (1, 3, 550, 1),
    (2, 2, 550, 4),
    (2, 3, 600, 2),
    (3, 3, 500, 8);