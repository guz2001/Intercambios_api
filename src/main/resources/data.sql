-- ============================================================
-- DATOS INICIALES: Lista de Intercambios Alimentarios
-- Fuente: Sistema de Intercambios para Planificación de Dietas
--         Escuela de Nutrición y Dietética - Universidad de Antioquia
-- ============================================================
-- Usa INSERT IGNORE para que sea idempotente: si ya existen los
-- registros (por un arranque anterior) simplemente se omiten.
-- Requiere: spring.sql.init.mode=always
--           spring.jpa.defer-datasource-initialization=true
-- ============================================================

-- ============================================================
-- 1. GRUPOS ALIMENTARIOS
-- ============================================================
INSERT IGNORE INTO grupo_alimento (id, nombre, descripcion) VALUES
(1, 'Cereales, raíces, tubérculos y plátanos',
    'Fuente principal de carbohidratos complejos y energía. Incluye cereales, sus derivados, raíces, tubérculos y plátanos.'),
(2, 'Verduras y hortalizas',
    'Fuente de vitaminas, minerales, fibra y agua. Se clasifican según su aporte calórico en lista A y lista B.'),
(3, 'Frutas',
    'Fuente de vitaminas, especialmente vitamina C, minerales, fibra y azúcares naturales.'),
(4, 'Leche y derivados lácteos',
    'Fuente principal de calcio y proteína de alto valor biológico. Se clasifica según el contenido de grasa.'),
(5, 'Carnes y sustitutos',
    'Fuente de proteína de alto valor biológico, hierro hemínico y vitamina B12. Se clasifica según el contenido de grasa.'),
(6, 'Leguminosas',
    'Fuente de proteína vegetal, carbohidratos complejos, fibra, hierro no hemínico y calcio.'),
(7, 'Grasas y aceites',
    'Fuente de ácidos grasos esenciales y vitaminas liposolubles A, D, E y K. Se clasifican según el tipo de ácido graso predominante.'),
(8, 'Azúcares y dulces',
    'Fuente de carbohidratos simples de rápida absorción. Deben consumirse con moderación.');

-- ============================================================
-- 2. SUBGRUPOS ALIMENTARIOS
-- ============================================================
INSERT IGNORE INTO subgrupo_alimento (id, grupo_id, nombre, poblacion, kcal_promedio) VALUES
-- Grupo 1: Cereales, raíces, tubérculos y plátanos
(1,  1, 'Cereales y productos de panadería',  'general', 80.00),
(2,  1, 'Tubérculos y raíces',                'general', 80.00),
(3,  1, 'Plátanos',                           'general', 80.00),
-- Grupo 2: Verduras y hortalizas
(4,  2, 'Verduras lista A — bajo aporte calórico',     'general', 25.00),
(5,  2, 'Verduras lista B — moderado aporte calórico', 'general', 50.00),
-- Grupo 3: Frutas
(6,  3, 'Frutas',                             'general', 60.00),
-- Grupo 4: Lácteos
(7,  4, 'Leche entera y derivados enteros',               'general', 150.00),
(8,  4, 'Leche semidescremada y derivados semidescremados','general', 120.00),
(9,  4, 'Leche descremada y derivados descremados',        'general',  80.00),
-- Grupo 5: Carnes (porción = 30 g / 1 oz)
(10, 5, 'Carnes muy magras',  'general',  35.00),
(11, 5, 'Carnes magras',      'general',  55.00),
(12, 5, 'Carnes semimagras',  'general',  75.00),
(13, 5, 'Carnes grasas',      'general', 100.00),
-- Grupo 6: Leguminosas
(14, 6, 'Leguminosas cocidas', 'general', 80.00),
-- Grupo 7: Grasas
(15, 7, 'Grasas monoinsaturadas', 'general', 45.00),
(16, 7, 'Grasas poliinsaturadas', 'general', 45.00),
(17, 7, 'Grasas saturadas',       'general', 45.00),
-- Grupo 8: Azúcares
(18, 8, 'Azúcares y mieles simples', 'general', 20.00),
(19, 8, 'Dulces y postres',          'general', 60.00);

-- ============================================================
-- 3. ALIMENTOS
-- ============================================================

-- ── Subgrupo 1: Cereales y productos de panadería (80 kcal) ──
INSERT IGNORE INTO alimento (id, subgrupo_id, nombre, porcion_gramos, unidad_medida, descripcion_medida) VALUES
(1,  1, 'Arroz blanco cocido',      75,  'taza',      '½ taza'),
(2,  1, 'Arroz integral cocido',    75,  'taza',      '½ taza'),
(3,  1, 'Pan blanco tajado',        25,  'tajada',    '1 tajada mediana'),
(4,  1, 'Pan integral tajado',      25,  'tajada',    '1 tajada mediana'),
(5,  1, 'Pasta cocida',             75,  'taza',      '½ taza'),
(6,  1, 'Avena en hojuelas',        20,  'taza',      '¼ taza'),
(7,  1, 'Arepa de maíz blanco',     50,  'unidad',    '1 arepa pequeña (50 g)'),
(8,  1, 'Arepa de maíz amarillo',   50,  'unidad',    '1 arepa pequeña (50 g)'),
(9,  1, 'Galletas de soda',         15,  'unidades',  '4 galletas'),
(10, 1, 'Maíz pira (crispetas)',    15,  'taza',      '1 taza sin mantequilla'),
(11, 1, 'Cebada perlada cocida',    75,  'taza',      '½ taza'),
(12, 1, 'Quinua cocida',            75,  'taza',      '½ taza'),
(13, 1, 'Granola sin azúcar',       20,  'cucharadas','2 cucharadas'),
(14, 1, 'Tostadas de pan',          20,  'unidades',  '2 tostadas pequeñas'),
(15, 1, 'Harina de maíz precocida', 20,  'cucharadas','3 cucharadas rasas'),
(16, 1, 'Maíz tierno cocido (mazorca)', 80, 'taza',  '½ taza de granos'),
(17, 1, 'Tapioca cocida',           75,  'taza',      '½ taza'),
(18, 1, 'Biscochos o pandebono',    30,  'unidades',  '2 unidades pequeñas'),

-- ── Subgrupo 2: Tubérculos y raíces (80 kcal) ──
(19, 2, 'Papa pastusa cocida',      100, 'unidad',    '1 papa mediana'),
(20, 2, 'Papa criolla cocida',      100, 'unidades',  '3–4 unidades medianas'),
(21, 2, 'Papa sabanera cocida',     100, 'unidad',    '1 papa mediana'),
(22, 2, 'Yuca cocida',              60,  'trozo',     '1 trozo pequeño'),
(23, 2, 'Ñame cocido',              80,  'trozo',     '1 trozo mediano'),
(24, 2, 'Batata (camote) cocida',   80,  'unidad',    '½ unidad mediana'),
(25, 2, 'Ahuyama cocida',           200, 'taza',      '1 taza picada'),
(26, 2, 'Racacha cocida',           80,  'trozo',     '1 trozo mediano'),
(27, 2, 'Arracacha cocida',         80,  'trozo',     '1 trozo mediano'),

-- ── Subgrupo 3: Plátanos (80 kcal) ──
(28, 3, 'Plátano verde cocido',    50,  'tajadas',    '3 tajadas medianas'),
(29, 3, 'Plátano maduro cocido',   60,  'tajadas',    '2 tajadas medianas'),
(30, 3, 'Plátano maduro asado',    55,  'tajadas',    '2 tajadas'),
(31, 3, 'Patacón (tostón)',        30,  'unidad',     '1 unidad mediana'),

-- ── Subgrupo 4: Verduras lista A (25 kcal) ──
-- Porción: 1 taza cruda o ½ taza cocida salvo indicación contraria
(32, 4, 'Acelga',             150, 'taza', '1 taza cruda / ½ taza cocida'),
(33, 4, 'Apio',               150, 'taza', '1 taza cruda picado'),
(34, 4, 'Berenjena',          150, 'taza', '1 taza cruda picada'),
(35, 4, 'Brócoli',            150, 'taza', '1 taza cruda'),
(36, 4, 'Cebolla cabezona',   100, 'taza', '1 taza cruda picada'),
(37, 4, 'Cebolla larga',      100, 'taza', '1 taza cruda picada'),
(38, 4, 'Coliflor',           150, 'taza', '1 taza cruda'),
(39, 4, 'Espinaca',           150, 'taza', '1 taza cruda'),
(40, 4, 'Lechuga',            200, 'taza', '2 tazas crudas'),
(41, 4, 'Pepino cohombro',    150, 'taza', '1 taza cruda picada'),
(42, 4, 'Pimentón',           150, 'taza', '1 taza cruda picado'),
(43, 4, 'Repollo',            150, 'taza', '1 taza cruda'),
(44, 4, 'Tomate chonto',      150, 'taza', '1 taza cruda picado'),
(45, 4, 'Zanahoria cruda',     75, 'unidad','1 zanahoria mediana'),
(46, 4, 'Rábano',             100, 'taza', '1 taza cruda'),
(47, 4, 'Cilantro',           150, 'taza', '1 taza fresca'),
(48, 4, 'Espárragos',         150, 'taza', '1 taza cruda'),
(49, 4, 'Champiñones',        150, 'taza', '1 taza crudos'),
(50, 4, 'Pepino de rellenar', 150, 'taza', '1 taza cruda picado'),
(51, 4, 'Calabacín (zuchini)',150, 'taza', '1 taza cruda picado'),

-- ── Subgrupo 5: Verduras lista B (50 kcal) ──
(52, 5, 'Arveja verde cocida',   80,  'taza',      '½ taza cocida'),
(53, 5, 'Habichuela cocida',    150,  'taza',      '1 taza cocida'),
(54, 5, 'Remolacha cocida',     100,  'taza',      '½ taza cocida'),
(55, 5, 'Maíz tierno en lata',   80,  'cucharadas','3 cucharadas'),
(56, 5, 'Zanahoria cocida',     100,  'taza',      '½ taza cocida'),

-- ── Subgrupo 6: Frutas (60 kcal) ──
(57,  6, 'Banano guineo',       70,  'unidad',    '1 banano pequeño'),
(58,  6, 'Manzana',            100,  'unidad',    '1 manzana pequeña'),
(59,  6, 'Naranja',            120,  'unidad',    '1 naranja mediana'),
(60,  6, 'Papaya',             150,  'tajada',    '1 tajada mediana'),
(61,  6, 'Mango tommy',         80,  'unidad',    '½ mango mediano'),
(62,  6, 'Fresa',              150,  'taza',      '1 taza'),
(63,  6, 'Uvas',                90,  'unidades',  '15 uvas'),
(64,  6, 'Pera',               100,  'unidad',    '1 pera pequeña'),
(65,  6, 'Melón',              200,  'taza',      '1 taza picado'),
(66,  6, 'Patilla (sandía)',   250,  'taza',      '1 taza picada'),
(67,  6, 'Piña',               140,  'tajada',    '1 tajada mediana'),
(68,  6, 'Mandarina',          110,  'unidad',    '1 mandarina mediana'),
(69,  6, 'Guayaba',             90,  'unidades',  '2 guayabas medianas'),
(70,  6, 'Mango pequeño',       70,  'unidad',    '1 mango pequeño'),
(71,  6, 'Lulo',               150,  'unidades',  '3 lulos medianos'),
(72,  6, 'Maracuyá (pulpa)',    50,  'cucharadas','4 cucharadas de pulpa'),
(73,  6, 'Tomate de árbol',    100,  'unidades',  '2 unidades medianas'),
(74,  6, 'Granadilla',         130,  'unidades',  '2 unidades medianas'),
(75,  6, 'Curuba',             150,  'unidades',  '3 curubas medianas'),
(76,  6, 'Mora',               150,  'taza',      '1 taza'),
(77,  6, 'Durazno',            100,  'unidad',    '1 durazno mediano'),
(78,  6, 'Ciruela',             80,  'unidades',  '3 ciruelas medianas'),
(79,  6, 'Higo',                80,  'unidades',  '2 higos medianos'),
(80,  6, 'Zapote',              80,  'trozo',     '1 trozo mediano'),
(81,  6, 'Mamoncillo',         120,  'unidades',  '10–12 unidades'),
(82,  6, 'Nispero',            100,  'unidades',  '2 unidades medianas'),
(83,  6, 'Feijoa',             120,  'unidades',  '3 unidades medianas'),
(84,  6, 'Chirimoya',           80,  'trozo',     '1 trozo pequeño'),
(85,  6, 'Carambolo',          200,  'unidades',  '2 unidades medianas'),
(86,  6, 'Coco (pulpa fresca)', 20,  'cucharadas','2 cucharadas'),

-- ── Subgrupo 7: Leche entera y derivados (150 kcal) ──
(87,  7, 'Leche entera líquida',         200, 'vaso',      '1 vaso (200 mL)'),
(88,  7, 'Yogur entero natural',         150, 'taza',      '¾ taza'),
(89,  7, 'Queso doble crema',             35, 'cucharadas','2 cucharadas rasas'),
(90,  7, 'Queso campesino entero',        40, 'rebanadas', '2 rebanadas delgadas'),

-- ── Subgrupo 8: Leche semidescremada (120 kcal) ──
(91,  8, 'Leche semidescremada líquida', 200, 'vaso',  '1 vaso (200 mL)'),
(92,  8, 'Yogur semidescremado',         150, 'taza',  '¾ taza'),

-- ── Subgrupo 9: Leche descremada y derivados (80 kcal) ──
(93,  9, 'Leche descremada líquida',     200, 'vaso',      '1 vaso (200 mL)'),
(94,  9, 'Yogur descremado',             150, 'taza',      '¾ taza'),
(95,  9, 'Queso cottage',                100, 'cucharadas','4 cucharadas'),
(96,  9, 'Suero costeño bajo en grasa',   30, 'cucharadas','2 cucharadas'),

-- ── Subgrupo 10: Carnes muy magras — 35 kcal / 30 g ──
(97,  10, 'Clara de huevo cocida',              45, 'unidades',    '2 claras de huevo'),
(98,  10, 'Pechuga de pollo sin piel cocida',   30, 'gramos',      '1 onza'),
(99,  10, 'Mojarra (tilapia) cocida',           30, 'gramos',      '1 onza'),
(100, 10, 'Corvina cocida',                     30, 'gramos',      '1 onza'),
(101, 10, 'Bagre cocido',                       30, 'gramos',      '1 onza'),
(102, 10, 'Atún en agua escurrido',             30, 'cucharadas',  '3 cucharadas'),
(103, 10, 'Langostinos cocidos',                30, 'gramos',      '1 onza'),
(104, 10, 'Queso blanco bajo en grasa',         30, 'rebanada',    '1 rebanada delgada'),

-- ── Subgrupo 11: Carnes magras — 55 kcal / 30 g ──
(105, 11, 'Carne de res magra cocida',          30, 'gramos',  '1 onza'),
(106, 11, 'Carne de cerdo lomo cocido',         30, 'gramos',  '1 onza'),
(107, 11, 'Muslo de pollo sin piel cocido',     30, 'gramos',  '1 onza'),
(108, 11, 'Huevo entero cocido',                50, 'unidad',  '1 unidad mediana'),
(109, 11, 'Sardina en agua',                    30, 'gramos',  '1 onza'),
(110, 11, 'Salmón cocido',                      30, 'gramos',  '1 onza'),
(111, 11, 'Trucha cocida',                      30, 'gramos',  '1 onza'),
(112, 11, 'Cangrejo cocido',                    30, 'gramos',  '1 onza'),

-- ── Subgrupo 12: Carnes semimagras — 75 kcal / 30 g ──
(113, 12, 'Carne molida semi-magra cocida',     30, 'gramos',  '1 onza'),
(114, 12, 'Costilla de cerdo cocida',           30, 'gramos',  '1 onza'),
(115, 12, 'Pollo entero con piel cocido',       30, 'gramos',  '1 onza'),
(116, 12, 'Atún en aceite escurrido',           30, 'gramos',  '1 onza'),
(117, 12, 'Carne de res costilla cocida',       30, 'gramos',  '1 onza'),

-- ── Subgrupo 13: Carnes grasas — 100 kcal / 30 g ──
(118, 13, 'Salchicha',                          30, 'unidad',     '1 salchicha mediana'),
(119, 13, 'Chorizo',                            30, 'gramos',     '1 onza'),
(120, 13, 'Mortadela',                          30, 'rebanadas',  '2 rebanadas'),
(121, 13, 'Tocineta (tocino)',                  20, 'tiras',      '2 tiras'),
(122, 13, 'Morcilla',                           30, 'gramos',     '1 onza'),

-- ── Subgrupo 14: Leguminosas cocidas — 80 kcal / ½ taza ──
(123, 14, 'Frijoles cocidos',                   80, 'taza',  '½ taza'),
(124, 14, 'Lentejas cocidas',                   80, 'taza',  '½ taza'),
(125, 14, 'Garbanzos cocidos',                  80, 'taza',  '½ taza'),
(126, 14, 'Arvejas secas cocidas',              80, 'taza',  '½ taza'),
(127, 14, 'Habas cocidas',                      80, 'taza',  '½ taza'),
(128, 14, 'Soya cocida',                        65, 'taza',  '½ taza'),

-- ── Subgrupo 15: Grasas monoinsaturadas — 45 kcal ──
(129, 15, 'Aguacate',                           30, 'cucharadas',  '2 cucharadas'),
(130, 15, 'Aceite de oliva',                     5, 'cucharadita', '1 cucharadita'),
(131, 15, 'Aceite de canola',                    5, 'cucharadita', '1 cucharadita'),
(132, 15, 'Maní sin sal tostado',               10, 'cucharada',   '1 cucharada'),
(133, 15, 'Almendras',                          10, 'unidades',    '6 unidades'),
(134, 15, 'Aceitunas',                          45, 'unidades',    '8 aceitunas'),

-- ── Subgrupo 16: Grasas poliinsaturadas — 45 kcal ──
(135, 16, 'Aceite de girasol',                   5, 'cucharadita', '1 cucharadita'),
(136, 16, 'Aceite de maíz',                      5, 'cucharadita', '1 cucharadita'),
(137, 16, 'Aceite de soya',                      5, 'cucharadita', '1 cucharadita'),
(138, 16, 'Nueces',                             10, 'unidades',    '2 mitades'),
(139, 16, 'Semillas de girasol',                10, 'cucharada',   '1 cucharada'),
(140, 16, 'Semillas de linaza molida',          10, 'cucharada',   '1 cucharada'),

-- ── Subgrupo 17: Grasas saturadas — 45 kcal ──
(141, 17, 'Mantequilla',                         5, 'cucharadita', '1 cucharadita'),
(142, 17, 'Margarina',                           5, 'cucharadita', '1 cucharadita'),
(143, 17, 'Mayonesa',                            7, 'cucharadita', '1 cucharadita'),
(144, 17, 'Crema de leche',                     30, 'cucharadas',  '2 cucharadas'),
(145, 17, 'Queso crema (Philadelphia)',          15, 'cucharada',   '1 cucharada'),
(146, 17, 'Aceite de palma',                     5, 'cucharadita', '1 cucharadita'),
(147, 17, 'Aceite de coco',                      5, 'cucharadita', '1 cucharadita'),

-- ── Subgrupo 18: Azúcares y mieles simples — 20 kcal ──
(148, 18, 'Azúcar blanca',     5, 'cucharadita', '1 cucharadita'),
(149, 18, 'Azúcar morena',     5, 'cucharadita', '1 cucharadita'),
(150, 18, 'Panela rallada',    5, 'cucharadita', '1 cucharadita'),
(151, 18, 'Miel de abejas',    7, 'cucharadita', '1 cucharadita'),
(152, 18, 'Mermelada',         7, 'cucharadita', '1 cucharadita'),
(153, 18, 'Jarabe de maíz',    7, 'cucharadita', '1 cucharadita'),

-- ── Subgrupo 19: Dulces y postres — 60 kcal ──
(154, 19, 'Bocadillo de guayaba', 25, 'trozo',      '1 trozo pequeño'),
(155, 19, 'Arequipe',            20, 'cucharada',   '1 cucharada'),
(156, 19, 'Helado de crema',     65, 'cucharadas',  '3 cucharadas'),
(157, 19, 'Chocolate de mesa',   15, 'gramos',      '½ pastilla');
