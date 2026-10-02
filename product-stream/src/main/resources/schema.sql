CREATE TABLE IF NOT EXISTS productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(200) NOT NULL,
    price_usd DECIMAL(8, 2),
    price_pen DECIMAL(8, 2),
    rate DECIMAL(8, 2),
    is_active_category BOOLEAN
);