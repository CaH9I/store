INSERT INTO mobile_phone(model, display, length, width, color, price, camera) VALUES ('Samsung galaxy SII', '4''''', '14mm', '56mm', 'black', 600, '12MP');
INSERT INTO mobile_phone(model, display, length, width, color, price, camera) VALUES ('Samsung galaxy SIII', '5''''', '16mm', '60mm', 'black', 650, '13MP');
INSERT INTO mobile_phone(model, display, length, width, color, price, camera) VALUES ('Samsung galaxy S6', '5.5''''', '17mm', '62mm', 'black', 750, '16MP');

INSERT INTO fixed_delivery_price(amount) VALUES(5);

INSERT INTO store_order(first_name, last_name, address, phone, subtotal, delivery_amount, total, additional_info, state) VALUES 
    ('James', 'Smith', '135 Evergreen Ave. Wake Forest, NC 27587', '202-555-0118', 2850, 10, 2860, 'Do not delay delivery please', 'SUBMITTED');
INSERT INTO store_order(first_name, last_name, address, phone, subtotal, delivery_amount, total, additional_info, state) VALUES
    ('John', 'Johnson', '7716 Wagon St. Kingsport, TN 37660', '202-555-0137', 3400, 15, 3415, 'I''d like you to deliver till December 24', 'SUBMITTED');
INSERT INTO store_order(first_name, last_name, address, phone, subtotal, delivery_amount, total, state) VALUES
    ('Robert', 'Williams', '8668 East Snake Hill St. Yuba City, CA 95993', '+1-202-555-0106', 4000, 5, 4005, 'DELIVERED');

INSERT INTO commerce_item(order_id, product_id, price, quantity) VALUES (1, 1, 400, 2);
INSERT INTO commerce_item(order_id, product_id, price, quantity) VALUES (1, 2, 500, 3);
INSERT INTO commerce_item(order_id, product_id, price, quantity) VALUES (1, 3, 550, 1);
INSERT INTO commerce_item(order_id, product_id, price, quantity) VALUES (2, 2, 550, 4);
INSERT INTO commerce_item(order_id, product_id, price, quantity) VALUES (2, 3, 600, 2);
INSERT INTO commerce_item(order_id, product_id, price, quantity) VALUES (3, 3, 500, 8);