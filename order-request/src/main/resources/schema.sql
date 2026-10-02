CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20),
    product VARCHAR(200),
    price DECIMAL(8, 2),
    observation VARCHAR(200)
);