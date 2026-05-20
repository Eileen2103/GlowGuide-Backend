CREATE TABLE comment_likes (
    id BIGINT AUTO_INCREMENT,          -- MySQL için otomatik artan ID yapısı
    user_id BIGINT NOT NULL,           
    comment_id BIGINT NOT NULL,        
    
    -- Kısıtlamalar (Constraints)
    CONSTRAINT pk_comment_like PRIMARY KEY (id),
    
    -- Foreign Key tanımlamaları
    CONSTRAINT fk_like_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_like_comment FOREIGN KEY (comment_id) REFERENCES community_comments(id) ON DELETE CASCADE,
    
    -- Unique kısıtlaması
    CONSTRAINT unique_user_comment UNIQUE (user_id, comment_id)
);