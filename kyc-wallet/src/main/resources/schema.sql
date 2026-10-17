CREATE TABLE IF NOT EXISTS customers (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  document_id BIGINT,
  email VARCHAR(50),
    wallet_id VARCHAR(50),
    status VARCHAR(20)
);