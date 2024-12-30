-- Crear tabla customers
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT PRIMARY KEY,
    email VARCHAR(255),
    name VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Crear tabla products
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255),
    price NUMERIC(38, 2),
    stock INTEGER,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);