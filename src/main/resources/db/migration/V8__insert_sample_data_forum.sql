INSERT INTO community_posts (user_id, title, content, created_at) VALUES
(1, 'Best Skincare Routine', 'What is your daily skincare routine for glowing skin?', '2026-05-01 09:15:23'),
(2, 'Makeup for Beginners', 'What are the must-have products for someone new to makeup?', '2026-05-01 14:42:10'),
(3, 'Acne Solutions', 'What products helped you get rid of acne?', '2026-05-02 11:05:47'),
(4, 'Favorite Lipsticks', 'Which lipstick brands and shades do you recommend?', '2026-05-03 18:27:55'),
(5, 'Hair Care Tips', 'How do you keep your hair healthy and shiny?', '2026-05-04 08:33:12');


-- Post 1 → 3 yorum
INSERT INTO community_comments (post_id, user_id, content, created_at) VALUES
(1, 2, 'I always double cleanse and use a good moisturizer.', '2026-05-01 10:02:11'),
(1, 3, 'Sunscreen is a must every single day!', '2026-05-01 10:45:33'),
(1, 4, 'Vitamin C serum really helped my skin glow.', '2026-05-01 12:20:05');

-- Post 2 → 2 yorum
INSERT INTO community_comments (post_id, user_id, content, created_at) VALUES
(2, 1, 'Start with a good foundation and mascara.', '2026-05-01 15:10:44'),
(2, 5, 'A basic nude palette is essential.', '2026-05-01 16:55:21');

-- Post 3 → 4 yorum
INSERT INTO community_comments (post_id, user_id, content, created_at) VALUES
(3, 2, 'Salicylic acid products worked for me.', '2026-05-02 12:30:18'),
(3, 4, 'Avoid touching your face too much.', '2026-05-02 13:05:49'),
(3, 5, 'Drink lots of water and keep your skin clean.', '2026-05-02 14:22:37'),
(3, 1, 'Retinol helped reduce my acne over time.', '2026-05-02 16:40:03');

-- Post 4 → 1 yorum
INSERT INTO community_comments (post_id, user_id, content, created_at) VALUES
(4, 3, 'I love matte lipsticks from MAC.', '2026-05-03 19:15:27');

-- Post 5 → 3 yorum
INSERT INTO community_comments (post_id, user_id, content, created_at) VALUES
(5, 1, 'I use argan oil once a week.', '2026-05-04 09:05:11'),
(5, 2, 'Avoid too much heat styling.', '2026-05-04 10:48:56'),
(5, 3, 'Regular trims make a big difference.', '2026-05-04 12:33:42');