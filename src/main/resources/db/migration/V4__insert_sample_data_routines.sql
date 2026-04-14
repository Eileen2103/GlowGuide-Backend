INSERT INTO user_routines (user_id, type, day_of_week, description, completed) 
VALUES 
-- User 1 (Selin - Kuru Cilt)
(1, 'MORNING', 0, 'Temizleyici', FALSE),
(1, 'MORNING', 0, 'Tonik', FALSE),
(1, 'MORNING', 0, 'Nemlendirici', FALSE),
(1, 'NIGHT', 0, 'Temizleyici pad', FALSE),
(1, 'NIGHT', 0, 'Yüz yağı', FALSE),
(1, 'WEEKLY', 7, 'Pazar günü yoğun nem maskesi yap', FALSE),

-- User 2 (Berk - Yağlı Cilt)
(2, 'MORNING', 0, 'Salisilik asitli temizleyici', FALSE),
(2, 'MORNING', 0, 'Güneş kremi', FALSE),
(2, 'NIGHT', 0, 'Gözenek sıkılaştırıcı tonik', FALSE),
(2, 'WEEKLY', 3, 'Çarşamba akşamı kil maskesi uygula', FALSE),

-- User 3 (Aylin - Karma Cilt)
(3, 'MORNING', 0, 'C vitamini serumu', FALSE),
(3, 'NIGHT', 0, 'Çift aşamalı temizlik', FALSE),
(3, 'WEEKLY', 6, 'Cumartesi günü peeling (exfoliation) yap', FALSE),
(3, 'WEEKLY', 1, 'Pazartesi günü leke karşıtı serum kürü', FALSE);