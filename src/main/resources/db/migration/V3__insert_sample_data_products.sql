-- Birinci grup ürünler
INSERT INTO user_products (user_id, name, brand, product_type, opened_at, safety_score, category, ai_feedback) 
VALUES 
(1, 'Hydrating Cleanser', 'CeraVe', 'Temizleyici', '2026-03-15', 9.5, 'SKIN', 'Bu ürün kuru cildiniz için mükemmel bir nem dengesi sağlar.'),
(1, 'Moisturizing Cream', 'La Roche-Posay', 'Nemlendirici', '2026-04-01', 8.8, 'SKIN', 'İçeriğindeki seramidler bariyerinizi güçlendirecektir.'),
(2, 'Effaclar Gel', 'La Roche-Posay', 'Temizleyici', '2026-02-20', 8.2, 'SKIN', 'Yağ dengesini sağlamak için günde iki kez kullanıma uygundur.'),
(2, 'Oil-Free Sunscreen', 'Missha', 'Güneş Kremi', '2026-04-10', 9.0, 'SKIN', 'Hafif yapısı sayesinde gözeneklerinizi tıkamaz.'),
(3, 'Niacinamide Serum', 'The Ordinary', 'Serum', '2026-03-25', 7.5, 'SKIN', 'T bölgenizdeki parlamayı kontrol altına almanıza yardımcı olur.'),
(3, 'Water Gel Moisturizer', 'Neutrogena', 'Nemlendirici', '2026-04-05', 8.5, 'SKIN', 'Yanaklarınızdaki kuruluğu giderirken ağırlık yapmaz.');

-- İkinci grup ürünler (Hatalı olan kısım düzeltildi)
INSERT INTO user_products (user_id, name, brand, product_type, opened_at, category) 
VALUES 
(1, 'Salicylic Acid', 'Paulas Choice', 'Asit', '2026-03-10', 'SKIN'),
(1, 'Oil Control Matte Moisturizer', 'Bioderma', 'Nemlendirici', '2026-04-08','SKIN'),
(1, 'Argan Özlü Saç Yağı', 'Isana', 'Saç Yağı', '2026-03-08','HAIR'),
(1, 'BB Krem', 'Farmasi', 'Fondöten', '2026-03-08','MAKEUP');