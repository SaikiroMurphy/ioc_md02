CREATE DATABASE phone_store;

DROP TABLE IF EXISTS invoice_details;
DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    price DECIMAL(12, 2) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    address VARCHAR(255)
);

CREATE TABLE admins (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE invoices (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

DROP TABLE IF EXISTS invoice_details;

CREATE TABLE invoice_details (
    id SERIAL PRIMARY KEY,
    invoice_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (invoice_id) REFERENCES invoices(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO products (name, brand, price, stock) VALUES
-- Apple
('iPhone 15', 'Apple', 22000000, 50),
('iPhone 15 Pro', 'Apple', 28000000, 40),
('iPhone 14', 'Apple', 18000000, 60),
('iPhone 14 Pro Max', 'Apple', 30000000, 35),
('iPhone 13', 'Apple', 15000000, 70),

-- Samsung
('Galaxy S24', 'Samsung', 21000000, 45),
('Galaxy S24 Ultra', 'Samsung', 29000000, 30),
('Galaxy S23', 'Samsung', 19000000, 50),
('Galaxy Z Fold 5', 'Samsung', 35000000, 20),
('Galaxy Z Flip 5', 'Samsung', 25000000, 25),

-- Xiaomi
('Xiaomi 14', 'Xiaomi', 17000000, 60),
('Redmi Note 13', 'Xiaomi', 7000000, 100),
('Xiaomi 13T', 'Xiaomi', 12000000, 75),
('Redmi K60', 'Xiaomi', 11000000, 80),
('Xiaomi Mix Fold 3', 'Xiaomi', 28000000, 15),

-- Oppo
('Oppo Find X6', 'Oppo', 20000000, 40),
('Oppo Reno 11', 'Oppo', 9000000, 70),
('Oppo A98', 'Oppo', 8000000, 90),
('Oppo Find N3', 'Oppo', 27000000, 20),
('Oppo Reno 10', 'Oppo', 8500000, 85),

-- Vivo
('Vivo X100', 'Vivo', 21000000, 30),
('Vivo V29', 'Vivo', 10000000, 65),
('Vivo Y36', 'Vivo', 6500000, 95),
('Vivo X90 Pro', 'Vivo', 24000000, 25),
('Vivo V27', 'Vivo', 9500000, 60),

-- Realme
('Realme GT5', 'Realme', 14000000, 50),
('Realme 11 Pro+', 'Realme', 9000000, 75),
('Realme C55', 'Realme', 5000000, 120),
('Realme Narzo 60', 'Realme', 7000000, 85),
('Realme GT Neo 5', 'Realme', 13000000, 55);

INSERT INTO customers (name, phone, email, address) VALUES
('Nguyen Van A', '0900000001', 'a@gmail.com', 'Ha Noi'),
('Tran Thi B', '0900000002', 'b@gmail.com', 'Hai Phong'),
('Le Van C', '0900000003', 'c@gmail.com', 'Da Nang'),
('Pham Thi D', '0900000004', 'd@gmail.com', 'HCM'),
('Hoang Van E', '0900000005', 'e@gmail.com', 'Can Tho');

INSERT INTO invoices (customer_id, created_at, total_amount) VALUES
(1, NOW(), 0),
(1, NOW(), 0),
(2, NOW(), 0),
(2, NOW(), 0),
(3, NOW(), 0),
(3, NOW(), 0),
(4, NOW(), 0),
(4, NOW(), 0),
(5, NOW(), 0),
(5, NOW(), 0);

INSERT INTO invoice_details (invoice_id, product_id, quantity, unit_price) VALUES
-- Invoice 1
(1,1,1,22000000),(1,2,1,28000000),(1,3,2,18000000),(1,4,1,30000000),(1,5,1,15000000),

-- Invoice 2
(2,6,1,21000000),(2,7,1,29000000),(2,8,2,19000000),(2,9,1,35000000),(2,10,1,25000000),

-- Invoice 3
(3,11,1,17000000),(3,12,2,7000000),(3,13,1,12000000),(3,14,1,11000000),(3,15,1,28000000),

-- Invoice 4
(4,16,1,20000000),(4,17,2,9000000),(4,18,1,8000000),(4,19,1,27000000),(4,20,1,8500000),

-- Invoice 5
(5,21,1,21000000),(5,22,1,10000000),(5,23,2,6500000),(5,24,1,24000000),(5,25,1,9500000),

-- Invoice 6
(6,26,1,14000000),(6,27,2,9000000),(6,28,1,5000000),(6,29,1,7000000),(6,30,1,13000000),

-- Invoice 7
(7,1,1,22000000),(7,6,1,21000000),(7,11,1,17000000),(7,16,1,20000000),(7,21,1,21000000),

-- Invoice 8
(8,2,1,28000000),(8,7,1,29000000),(8,12,1,7000000),(8,17,1,9000000),(8,22,1,10000000),

-- Invoice 9
(9,3,1,18000000),(9,8,1,19000000),(9,13,1,12000000),(9,18,1,8000000),(9,23,1,6500000),

-- Invoice 10
(10,4,1,30000000),(10,9,1,35000000),(10,14,1,11000000),(10,19,1,27000000),(10,24,1,24000000);

UPDATE invoices i
SET total_amount = (
    SELECT SUM(quantity * unit_price)
    FROM invoice_details d
    WHERE d.invoice_id = i.id
);
