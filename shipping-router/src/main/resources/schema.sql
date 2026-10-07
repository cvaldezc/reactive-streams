CREATE TABLE IF NOT EXISTS shipments (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  order_id VARCHAR(20),
    courier_Name VARCHAR(50),
    final_cost DECIMAL(10, 2)
);