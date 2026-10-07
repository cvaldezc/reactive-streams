CREATE TABLE IF NOT EXISTS reservations (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  event_id VARCHAR(20),
  user_id VARCHAR(20),
  total_amount DECIMAL(10, 2)
);