INSERT INTO ingredients (
canonical_name,
description,
comedogenic_rating,
irritation_level,
acne_score,
oily_skin_score,
dry_skin_score,
sensitive_skin_score,
normal_skin_score,
combination_skin_score,
fungal_acne_safe
)
VALUES

-- ACTIVES
('Niacinamide', 'Sebum dengeler, bariyer güçlendirir', 0, 'LOW', 9, 10, 8, 8, 9, 9, TRUE),
('Salicylic Acid', 'BHA exfoliant, gözenek temizler', 1, 'MEDIUM', 10, 10, 4, 3, 7, 9, TRUE),
('Glycolic Acid', 'AHA peeling etkili asit', 1, 'MEDIUM', 9, 9, 4, 3, 6, 8, TRUE),
('Retinol', 'Anti-aging vitamin A türevi', 1, 'HIGH', 10, 7, 3, 2, 5, 7, TRUE),
('Vitamin C', 'Cilt aydınlatıcı antioksidan', 0, 'MEDIUM', 8, 8, 7, 6, 8, 8, TRUE),
('Hyaluronic Acid', 'Nem tutucu molekül', 0, 'LOW', 8, 7, 10, 9, 10, 9, TRUE),
('Panthenol', 'B5 vitamini, yatıştırıcı', 0, 'LOW', 8, 8, 9, 9, 9, 9, TRUE),
('Ceramides', 'Cilt bariyer lipitleri', 0, 'LOW', 7, 7, 10, 10, 10, 10, TRUE),
('Zinc PCA', 'Sebum kontrol ve akne karşıtı', 0, 'LOW', 9, 10, 7, 8, 9, 9, TRUE),
('Azelaic Acid', 'Akne ve kızarıklık karşıtı', 0, 'MEDIUM', 9, 9, 7, 6, 8, 8, TRUE),

-- OILS / EMOLLIENTS
('Coconut Oil', 'Yüksek komedojenik yağ', 5, 'LOW', 1, 1, 8, 5, 4, 2, FALSE),
('Jojoba Oil', 'Cilde yakın yapıda yağ', 2, 'LOW', 6, 6, 8, 7, 7, 7, TRUE),
('Shea Butter', 'Yoğun nemlendirici yağ', 3, 'LOW', 5, 5, 9, 8, 7, 6, TRUE),
('Argan Oil', 'Antioksidan yağ', 2, 'LOW', 6, 6, 8, 7, 7, 7, TRUE),
('Squalane', 'Hafif yağ, non-comedogenic', 0, 'LOW', 8, 8, 9, 9, 9, 9, TRUE),
('Mineral Oil', 'Occlusive yağ', 0, 'LOW', 7, 7, 9, 8, 8, 8, TRUE),

-- ALCOHOLS
('Alcohol Denat', 'Kurutucu alkol türü', 2, 'HIGH', 2, 3, 1, 1, 2, 2, FALSE),
('Cetyl Alcohol', 'Yumuşatıcı yağ alkolleri', 1, 'LOW', 7, 7, 9, 8, 8, 8, TRUE),
('Stearyl Alcohol', 'Emollient alcohol', 1, 'LOW', 7, 7, 9, 8, 8, 8, TRUE),

-- SILICONES
('Dimethicone', 'Koruyucu silikon bariyer', 0, 'LOW', 7, 7, 9, 9, 8, 8, TRUE),
('Cyclopentasiloxane', 'Hafif silikon', 0, 'LOW', 7, 7, 8, 8, 8, 8, TRUE),

-- PRESERVATIVES
('Phenoxyethanol', 'Koruyucu madde', 1, 'MEDIUM', 5, 5, 6, 6, 6, 6, TRUE),
('Parabens', 'Klasik koruyucular', 1, 'MEDIUM', 5, 5, 6, 6, 6, 6, TRUE),

-- FRAGRANCE
('Fragrance', 'Koku verici karışım', 2, 'HIGH', 3, 3, 4, 2, 3, 3, FALSE),
('Linalool', 'Parfüm bileşeni', 2, 'HIGH', 3, 3, 4, 2, 3, 3, FALSE),
('Limonene', 'Parfüm bileşeni', 2, 'HIGH', 3, 3, 4, 2, 3, 3, FALSE),

-- SURFACTANTS
('Sodium Laureth Sulfate', 'Temizleyici surfactant', 3, 'MEDIUM', 4, 4, 3, 3, 4, 4, FALSE),
('Cocamidopropyl Betaine', 'Hafif temizleyici', 1, 'LOW', 6, 6, 7, 7, 7, 7, TRUE),

-- SUNSCREEN FILTERS
('Zinc Oxide', 'Mineral SPF filtresi', 0, 'LOW', 8, 8, 10, 10, 10, 10, TRUE),
('Titanium Dioxide', 'Mineral UV filtresi', 0, 'LOW', 8, 8, 10, 10, 10, 10, TRUE),
('Avobenzone', 'Kimyasal SPF filtresi', 1, 'MEDIUM', 7, 7, 8, 7, 8, 8, TRUE),

-- MAKEUP
('Mica', 'Işıltı verici mineral', 0, 'LOW', 8, 8, 9, 9, 9, 9, TRUE),
('Talc', 'Makyaj pudrası', 1, 'LOW', 6, 6, 8, 7, 7, 7, TRUE),
('Iron Oxides', 'Pigmentler', 0, 'LOW', 8, 8, 9, 9, 9, 9, TRUE);