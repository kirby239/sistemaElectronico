-- datos en customers
INSERT INTO customers (id, email, name, created_at, updated_at)
VALUES (1, 'john.doe@example.com', 'John Doe', now(), now());

-- datos en products
INSERT INTO products (id, description, name, price, stock, created_at, updated_at)
VALUES (1, 'Smartphone de última generación', 'Smartphone', 999.99, 10, now(), now());
