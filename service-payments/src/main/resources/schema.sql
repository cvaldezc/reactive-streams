CREATE TABLE IF NOT EXISTS payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id VARCHAR(20),
    amount DECIMAL(10,2),
    status VARCHAR(20)
);