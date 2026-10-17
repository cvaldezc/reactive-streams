CREATE TABLE IF NOT EXISTS orders(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(20),
    product_id varchar(20),
    quantity BIGINT,
    status VARCHAR(50)
);