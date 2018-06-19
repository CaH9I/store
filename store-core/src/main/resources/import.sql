INSERT INTO account(id, username, password) VALUES (1, 'admin', '$2a$04$TeWT/i9Iuht8jAgxMOaKTuHpdaHvrKpVwv9npt13g0BR0H7DPCweW');
INSERT INTO account(id, username, password) VALUES (2, 'user', '$2a$04$QP5sIhM6txQCD6B5Ujem1.oub.LaMiS9hu18hFmNYEx1zNebvmmZy');

INSERT INTO role(id, name) VALUES (1, 'ADMIN');
INSERT INTO role(id, name) VALUES (2, 'USER');

INSERT INTO account_role(account_id, role_id) VALUES (1, 1);
INSERT INTO account_role(account_id, role_id) VALUES (2, 2);

INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (1, 'Samsung galaxy SII', '4''''', '14mm', '56mm', 'black', 600, '12MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (2, 'Samsung galaxy SIII', '5''''', '16mm', '60mm', 'black', 650, '13MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (3, 'Samsung galaxy S6', '5.5''''', '17mm', '62mm', 'black', 750, '16MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (4, 'iPhone 5S', '4.0''''', '12mm', '50mm', 'space gray', 300, '10MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (5, 'iPhone SE', '4.0''''', '12mm', '50mm', 'space gray', 500, '12MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (6, 'iPhone 6S', '4.7''''', '15mm', '58mm', 'space gray', 700, '12MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (7, 'iPhone 6S+', '4.7''''', '15mm', '58mm', 'space gray', 800, '14MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (8, 'iPhone 7', '4.7''''', '15mm', '58mm', 'space gray', 850, '15MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (9, 'iPhone 7+', '4.7''''', '15mm', '58mm', 'space gray', 900, '16MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (10, 'iPhone 8', '5''''', '16mm', '60mm', 'space gray', 950, '17MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (11, 'iPhone 10', '5.2''''', '16mm', '60mm', 'space gray', 1000, '20MP');
INSERT INTO mobile_phone(id, model, display, length, width, color, price, camera) VALUES (12, 'iPhone X', '5.5''''', '17mm', '62mm', 'metallic', 1100, '25MP');

INSERT INTO store_order(first_name, last_name, address, phone_number, subtotal, delivery, total, additional_info, state, account_id) VALUES ('James', 'Smith', '135 Evergreen Ave. Wake Forest, NC 27587', '202-555-0118', 2850, 10, 2860, 'Do not delay delivery please', 'SUBMITTED', 2);
INSERT INTO store_order(first_name, last_name, address, phone_number, subtotal, delivery, total, additional_info, state, account_id) VALUES ('John', 'Johnson', '7716 Wagon St. Kingsport, TN 37660', '202-555-0137', 3400, 15, 3415, 'I''d like you to deliver till December 24', 'SUBMITTED', 1);
INSERT INTO store_order(first_name, last_name, address, phone_number, subtotal, delivery, total, state, account_id) VALUES ('Robert', 'Williams', '8668 East Snake Hill St. Yuba City, CA 95993', '+1-202-555-0106', 4000, 5, 4005, 'DELIVERED', 2);

INSERT INTO order_item(order_id, phone_id, price, quantity) VALUES (1, 1, 400, 2);
INSERT INTO order_item(order_id, phone_id, price, quantity) VALUES (1, 2, 500, 3);
INSERT INTO order_item(order_id, phone_id, price, quantity) VALUES (1, 3, 550, 1);
INSERT INTO order_item(order_id, phone_id, price, quantity) VALUES (2, 2, 550, 4);
INSERT INTO order_item(order_id, phone_id, price, quantity) VALUES (2, 3, 600, 2);
INSERT INTO order_item(order_id, phone_id, price, quantity) VALUES (3, 3, 500, 8);