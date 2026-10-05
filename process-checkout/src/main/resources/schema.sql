CREATE TABLE IF NOT EXISTS checkout (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(20),
    product_id VARCHAR(20),
    amount_paid DECIMAL(10, 2)
);