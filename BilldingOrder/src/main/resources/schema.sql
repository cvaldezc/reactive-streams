CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    product VARCHAR(50),
    price DECIMAL(15, 2),
    status VARCHAR(50),
    observation VARCHAR(100)
);