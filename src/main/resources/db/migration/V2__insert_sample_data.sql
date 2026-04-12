-- 1. KULLANICI EKLEME (Selin)
-- Önce ana tablo olan users dolmalı
INSERT INTO users (name, surname, user_name, email, password, skin_type, birthday, avatar_url)
VALUES ('Selin', 'Türkyılmaz', 'selinyilmaz', 'selin@test.com', '123456', 'Kuru/Hassas', '1998-05-15', 'https://res.cloudinary.com/dkfxalhew/image/upload/v1775930267/grace1_lioycx.jpg');

-- 2. Örnek Kullanıcı: Yağlı/Akneye Eğilimli Cilt Testi İçin
INSERT INTO users (name, surname, user_name, email, password, skin_type, birthday, avatar_url)
VALUES ('Aylin', 'Öztürk', 'eileen21', 'eileen@test.com', 'qwerty123', 'Yağlı/Akneye Eğilimli', '2001-09-22', 
'https://res.cloudinary.com/dkfxalhew/image/upload/v1775931232/WhatsApp_Image_2026-04-11_at_21.11.24_r4qgdr.jpg');

-- 3. Örnek Kullanıcı: Karma Cilt Testi İçin
INSERT INTO users (name, surname, user_name, email, password, skin_type, birthday, avatar_url)
VALUES ('Melis', 'Aydın', 'melisaydn', 'melis@test.com', 'melis456', 'Karma', '1995-12-03', 
'https://res.cloudinary.com/dkfxalhew/image/upload/v1775931204/Jill_Valentine_2_md6rzc.jpg');