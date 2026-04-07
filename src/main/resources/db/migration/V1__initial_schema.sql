-- 1. Users Tablosu
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    user_name VARCHAR(150) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    skin_type VARCHAR(255),
    birthday DATE,
    avatar_url VARCHAR(255)
);

-- 2. User Products Tablosu
CREATE TABLE user_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(255),
    brand VARCHAR(255),
    product_type VARCHAR(255),
    opened_at DATE,
    safety_score DOUBLE,
    category VARCHAR(50),
    ai_feedback TEXT,
    CONSTRAINT fk_user_product FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 3. User Routines Tablosu
CREATE TABLE user_routines (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    type VARCHAR(50),
    day_of_week INT,
    description VARCHAR(255),
    completed BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_user_routine FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 4. Community Posts Tablosu
CREATE TABLE community_posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255),
    content TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 5. Community Comments Tablosu
CREATE TABLE community_comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES community_posts(id),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES users(id)
);