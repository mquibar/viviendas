INSERT INTO `ciudad` (`id`, `desde`, `hasta`, `nombre`) VALUES
(1, 0, 0, '10000'),
(2, 10, 10000, '25000'),
(3, 25, 25000, '1000000');

INSERT INTO `operatoria` (`id`, `nombre`, `parametro_id`) VALUES
(1, 'OBRA PÚBLICA', 1),
(2, 'ADMINISTRACIÓN POR COOPERATIVA', 2),
(3, 'COOPERATIVA DE TRABAJO', 3),
(4, 'AUTOCONSTRUCTOR', 4),
(5, 'MUNICIPIOS', 5),
(6, 'COMPRA DE VIVIENDA NUEVA O USADA', 6);

INSERT INTO `parametrosplan` (`id`, `nombreParametro`, `porcenteaje`) VALUES
(1, 'OPERATORIA_OBRA_PUBLICA', 0),
(2, 'OPERATORIA_ADM_COOPERATIVA', 0),
(3, 'OPERATORIA_COOPERATIVA_TRABAJO', 0),
(4, 'OPERATORIA_AUTOCONSTRUCTOR', 0),
(5, 'OPERATORIA_MUNICIPIOS', 0),
(6, 'OPERATORIA_COMPRA_VIVIENDA', 0);

INSERT INTO `provincia` (`id`, `nombre`) VALUES
(1, 'SAN JUAN'),
(2, 'BUENOS AIRES'),
(3, 'TIERRA DEL FUEGO'),
(4, 'ENTRE RÍOS'),
(5, 'FORMOSA'),
(6, 'SANTIAGO DEL ESTERO'),
(7, 'CHACO'),
(8, 'MISIONES'),
(9, 'JUJUY'),
(10, 'CATAMARCA'),
(11, 'LA RIOJA'),
(12, 'MENDOZA'),
(13, 'NEUQUÉN'),
(14, 'CÓRDOBA'),
(15, 'LA PAMPA'),
(16, 'SANTA CRUZ'),
(17, 'RÍO NEGRO'),
(18, 'SALTA'),
(19, 'TUCUMÁN'),
(20, 'CHUBUT'),
(21, 'SAN LUIS'),
(22, 'CORRIENTES'),
(23, 'SANTA FE'),
(24, 'CAPITAL FEDERAL');


INSERT INTO `sectoreconomico` (`id`, `nombre`) VALUES
(1, 'INGRESOS ALTOS'),
(2, 'INGRESOS MEDIOS'),
(3, 'INGRESOS BAJOS'),
(4, 'INGRESOS MEDIOS BAJOS');