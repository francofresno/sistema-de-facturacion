INSERT INTO regions (id, r_name) VALUES (1, 'Sudamérica');
INSERT INTO regions (id, r_name) VALUES (2, 'Centroamérica');
INSERT INTO regions (id, r_name) VALUES (3, 'Norteamérica');
INSERT INTO regions (id, r_name) VALUES (4, 'Europa');
INSERT INTO regions (id, r_name) VALUES (5, 'Asia');
INSERT INTO regions (id, r_name) VALUES (6, 'África');
INSERT INTO regions (id, r_name) VALUES (7, 'Oceanía');
INSERT INTO regions (id, r_name) VALUES (8, 'Antártida');

INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(2, 'Franco', 'Fresno', 'example@gmail.com', '2020-01-02');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(3, 'John', 'Doe', 'john.doe@gmail.com', '2020-01-03');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(4, 'Martina', 'Gonzalez', 'martina.gonzalez@gmail.com', '2020-01-04');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(5, 'Eric', 'Perez', 'eric.perez@gmail.com', '2020-02-01');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(6, 'Ricardo', 'Gutierrez', 'richard@gmail.com', '2020-02-10');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(7, 'Berry', 'Allen', 'the.flash@gmail.com', '2020-02-18');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(8, 'Oliver', 'Queen', 'green.arrow@gmail.com', '2020-02-28');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(1, 'Juliana', 'Santos', 'juli@gmail.com', '2020-03-03');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(2, 'Mariana', 'Ramirez', 'mariana.ramirez@gmail.com', '2020-03-04');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(3, 'Jessica', 'Fernandez', 'jess.fernandez@gmail.com', '2020-03-05');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(8, 'Rolando', 'Lopez', 'rolopez@gmail.com', '2020-03-06');
INSERT INTO clients (region_id, first_name, last_name, email, create_at) VALUES(1, 'Geralt', 'De Rivia', 'geralt@gmail.com', '2020-01-01');

INSERT INTO users (username, u_password, enabled, first_name, last_name, email) VALUES ('franco', '$2a$10$.4S3VWGN8yOD86igSbWxEuvVmER1l//dRPNv3Zitvvnb9BYC6yMfq', 1, 'Franco', 'Fresno', 'f@gmail.com');
INSERT INTO users (username, u_password, enabled, first_name, last_name, email) VALUES ('admin', '$2a$10$yujIBSI8Ghpr3lA9blyyNeVjOvhw.Nkx/s2xYW3766xB4v4t4rEPq', 1, 'Jhon', 'Salchi', 'salchi@gmail.com');

INSERT INTO roles (r_name) VALUES ('ROLE_USER');
INSERT INTO roles (r_name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authorities (user_id, role_id) VALUES (1,1);
INSERT INTO user_authorities (user_id, role_id) VALUES (2,2);
INSERT INTO user_authorities (user_id, role_id) VALUES (2,1);

INSERT INTO products (p_name, price, create_at) VALUES ('Panasonic Pantalla LCD', 30000, NOW());
INSERT INTO products (p_name, price, create_at) VALUES ('Sony Camara Digital', 7000, NOW());
INSERT INTO products (p_name, price, create_at) VALUES ('Apple iPhone', 56000, NOW());
INSERT INTO products (p_name, price, create_at) VALUES ('Sony Notebook A', 49000, NOW());
INSERT INTO products (p_name, price, create_at) VALUES ('Hewlett Packard FF2', 30500, NOW());
INSERT INTO products (p_name, price, create_at) VALUES ('Benq 24p', 26000, NOW());
INSERT INTO products (p_name, price, create_at) VALUES ('Exo Teclado', 500, NOW());
INSERT INTO products (p_name, price, create_at) VALUES ('Exo Mouse', 450, NOW());

INSERT INTO invoices (i_description, observation, client_id, create_at) VALUES ('Equipos de oficina', null, 1, NOW());
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES (1,1,1);
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES (2,1,4);
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES (1,1,5);
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES (1,1,7);

INSERT INTO invoices (i_description, observation, client_id, create_at) VALUES ('iPhone', 'Nota importante', 1, NOW());
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES (2,2,3);

INSERT INTO invoices (i_description, observation, client_id, create_at) VALUES ('Combo Exo', 'Nota importante !!', 2, NOW());
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES (1,3,7);
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES (1,3,8);