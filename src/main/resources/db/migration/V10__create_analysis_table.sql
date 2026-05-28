-- ANA içerikler tablosu
CREATE TABLE ingredients (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
canonical_name VARCHAR(191) UNIQUE,
description TEXT,
comedogenic_rating INT,
irritation_level VARCHAR(50),
acne_score INT,
oily_skin_score INT,
dry_skin_score INT,
sensitive_skin_score INT,
normal_skin_score INT,
combination_skin_score INT,
fungal_acne_safe BOOLEAN,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- aynı ürüne ait farklı isimlendirmler için
CREATE TABLE ingredient_aliases(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
ingredient_id BIGINT,
alias_name VARCHAR(191) UNIQUE,
FOREIGN KEY (ingredient_id) REFERENCES ingredients(id) ON DELETE CASCADE
);

-- okutulan ürünler
CREATE TABLE scanned_products(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT,
product_name VARCHAR(191),
image_url TEXT,
overall_score INT,
risk_level VARCHAR(50),
analysis_result TEXT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE scanned_product_ingredients (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
scanned_product_id BIGINT,
ingredient_id BIGINT,
detected_name VARCHAR(191),
ingredient_order INT NOT NULL,

FOREIGN KEY (scanned_product_id)
REFERENCES scanned_products(id)
ON DELETE CASCADE,

FOREIGN KEY (ingredient_id)
REFERENCES ingredients(id)
ON DELETE CASCADE
);