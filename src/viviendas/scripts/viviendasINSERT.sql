--
-- Volcar la base de datos para la tabla `ciudad`
--

INSERT INTO `ParametrosPlan` (`id`, `nombreParametro`, `porcenteaje`) VALUES
(7, 'Ejemplo 1', 0),
(8, 'Ejemplo 2', 0),
(9, 'Ejemplo 3', 0),
(10, 'Ejemplo 4', 0),
(11, 'Ejemplo 5', 0),
(12, 'Ejemplo 6', 0);
(13, 'Ejemplo 7', 0);

--
-- Volcar la base de datos para la tabla `fuentefondo`
--

INSERT INTO `fuentefondo` (`id`, `nombre`, `parametro_id`, `vigente`) VALUES
(1, 'OTROS APORTES', 46, b'1'),
(2, 'gdfgd', 47, b'0'),
(3, 'APORTES A', 54, b'1'),
(4, 'ZAPORTE', 55, b'1');

--
-- Volcar la base de datos para la tabla `operatoria`
--

INSERT INTO `operatoria` (`id`, `nombre`, `parametro_id`, `vigente`) VALUES
(14, 'OBRA PÚBLICA', 48, b'1'),
(15, 'ADMINISTRACIÓN POR COOPERATIVA', 49, b'1'),
(16, 'COOPERATIVA TRABAJO', 50, b'1'),
(17, 'AUTOCONSTRUCTOR', 51, b'1'),
(18, 'MUNICIPIOS', 52, b'0'),
(19, 'COMPRA DE VIVIENDA NUEVA O USADA', 53, b'1');

--
-- Volcar la base de datos para la tabla `parametrosplan`
--

INSERT INTO `parametrosplan` (`id`, `nombreParametro`, `porcenteaje`) VALUES
(1, 'OPERATORIA_OBRA_PUBLICA', 10),
(2, 'OPERATORIA_ADM_COOPERATIVA', 70),
(3, 'OPERATORIA_COOPERATIVA_TRABAJO', 10),
(4, 'OPERATORIA_AUTOCONSTRUCTOR', 0),
(5, 'OPERATORIA_MUNICIPIOS', 10),
(6, 'OPERATORIA_COMPRA_VIVIENDA', 0),
(9, 'OPERATORIA_DFSFS', 10),
(10, 'SECTOR-ECONOMICO_ALTOS', 15),
(11, 'SECTOR-ECONOMICO_MEDIOS', 0),
(12, 'SECTOR-ECONOMICO_BAJOS', 35),
(13, 'SECTOR-ECONOMICO_MEDIOS-BAJOS', 50),
(14, 'SECTOR-ECONOMICO_INGRESO X', 0),
(15, 'CIUDAD_0-10.000', 10),
(16, 'CIUDAD_10.000-25.000', 0),
(17, 'CIUDAD_25.000 - 1.000.000', 90),
(18, 'SECTOR-ECONOMICO_INGRESO Y', 0),
(19, 'CIUDAD_FGHGFH', 0),
(20, 'OPERATORIA_DFSFSF', 0),
(21, 'SECTOR-ECONOMICO_FSDFSFS', 10),
(22, 'PROVINCIA_SAN JUAN', 0),
(23, 'PROVINCIA_BUENOS AIRES', 0),
(24, 'PROVINCIA_TIERRA DEL FUEGO', 0),
(25, 'PROVINCIA_ENTRE RÍOS', 0),
(26, 'PROVINCIA_FORMOSA', 90),
(27, 'PROVINCIA_SANTIAGO DEL ESTERO', 0),
(28, 'PROVINCIA_CHACO', 10),
(29, 'PROVINCIA_MISIONES', 0),
(30, 'PROVINCIA_JUJUY', 0),
(31, 'PROVINCIA_CATAMARCA', 0),
(32, 'PROVINCIA_LA RIOJA', 0),
(33, 'PROVINCIA_MENDOZA', 0),
(34, 'PROVINCIA_NEUQUÉN', 0),
(35, 'PROVINCIA_CORDOBA', 0),
(36, 'PROVINCIA_LA PAMPA', 0),
(37, 'PROVINCIA_SANTA CRUZ', 0),
(38, 'PROVINCIA_RÍO NEGRO', 0),
(39, 'PROVINCIA_SALTA', 0),
(40, 'PROVINCIA_TUCUMAN', 0),
(41, 'PROVINCIA_CHUBUT', 0),
(42, 'PROVINCIA_SAN LUIS', 0),
(43, 'PROVINCIA_CORRIENTES', 0),
(44, 'PROVINCIA_SANTA FE', 0),
(45, 'PROVINCIA_CAPITAL FEDERAL', 0),
(46, 'FUENTEFONDOS_OTROSAPORTES', 100),
(47, 'FUENTEFONDO_gdfgd', 10),
(48, 'OPERATORIA_OBRA PÚBLICA', 100),
(49, 'OPERATORIA_ADMINISTRACIÓN POR COOPERATIVA', 0),
(50, 'OPERATORIA_COOPERATIVA TRABAJO', 0),
(51, 'OPERATORIA_AUTOCONSTRUCTOR', 0),
(52, 'OPERATORIA_MUNICIPIOS', 0),
(53, 'OPERATORIA_COMPRA DE VIVIENDA NUEVA O USADA', 0),
(54, 'FUENTEFONDO_APORTES A', 0),
(55, 'FUENTEFONDO_ZAPORTE', 0);


--
-- Volcar la base de datos para la tabla `provincia`
--

INSERT INTO `provincia` (`id`, `nombre`, `parametro_id`) VALUES
(1, 'SAN JUAN', 22),
(2, 'BUENOS AIRES', 23),
(3, 'TIERRA DEL FUEGO', 24),
(4, 'ENTRE RÍOS', 25),
(5, 'FORMOSA', 26),
(6, 'SANTIAGO DEL ESTERO', 27),
(7, 'CHACO', 28),
(8, 'MISIONES', 29),
(9, 'JUJUY', 30),
(10, 'CATAMARCA', 31),
(11, 'LA RIOJA', 32),
(12, 'MENDOZA', 33),
(13, 'NEUQUÉN', 34),
(14, 'CÓRDOBA', 35),
(15, 'LA PAMPA', 36),
(16, 'SANTA CRUZ', 37),
(17, 'RÍO NEGRO', 38),
(18, 'SALTA', 39),
(19, 'TUCUMÁN', 40),
(20, 'CHUBUT', 41),
(21, 'SAN LUIS', 42),
(22, 'CORRIENTES', 43),
(23, 'SANTA FE', 44),
(24, 'CAPITAL FEDERAL', 45);

INSERT INTO `provincia_ciudad` (`Provincia_id`, `listaCuidad_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(4, 6),
(5, 1),
(5, 2),
(5, 3),
(5, 4),
(5, 5),
(5, 6),
(6, 1),
(6, 2),
(6, 3),
(6, 4),
(6, 5),
(6, 6),
(7, 1),
(7, 2),
(7, 3),
(7, 4),
(7, 5),
(7, 6),
(8, 1),
(8, 2),
(8, 3),
(8, 4),
(8, 5),
(8, 6),
(9, 1),
(9, 2),
(9, 3),
(9, 4),
(9, 5),
(9, 6),
(10, 1),
(10, 2),
(10, 3),
(10, 4),
(10, 5),
(10, 6),
(11, 1),
(11, 2),
(11, 3),
(11, 4),
(11, 5),
(11, 6),
(12, 1),
(12, 2),
(12, 3),
(12, 4),
(12, 5),
(12, 6),
(13, 1),
(13, 2),
(13, 3),
(13, 4),
(13, 5),
(13, 6),
(14, 1),
(14, 2),
(14, 3),
(14, 4),
(14, 5),
(14, 6),
(15, 1),
(15, 2),
(15, 3),
(15, 4),
(15, 5),
(15, 6),
(16, 1),
(16, 2),
(16, 3),
(16, 4),
(16, 5),
(16, 6),
(17, 1),
(17, 2),
(17, 3),
(17, 4),
(17, 5),
(17, 6),
(18, 1),
(18, 2),
(18, 3),
(18, 4),
(18, 5),
(18, 6),
(19, 1),
(19, 2),
(19, 3),
(19, 4),
(19, 5),
(19, 6),
(20, 1),
(20, 2),
(20, 3),
(20, 4),
(20, 5),
(20, 6),
(21, 1),
(21, 2),
(21, 3),
(21, 4),
(21, 5),
(21, 6),
(22, 1),
(22, 2),
(22, 3),
(22, 4),
(22, 5),
(22, 6),
(23, 1),
(23, 2),
(23, 3),
(23, 4),
(23, 5),
(23, 6),
(24, 1),
(24, 2),
(24, 3),
(24, 4),
(24, 5),
(24, 6);

--
-- Volcar la base de datos para la tabla `sectoreconomico`
--

INSERT INTO `sectoreconomico` (`id`, `nombre`, `parametro_id`, `vigente`) VALUES
(1, 'INGRESOS ALTOS', 10, b'1'),
(2, 'INGRESOS MEDIOS', 11, b'1'),
(3, 'INGRESOS BAJOS', 12, b'1'),
(4, 'INGRESOS MEDIOS BAJOS', 13, b'1');

--
-- Volcar la base de datos para la tabla `usofondo`
--

INSERT INTO `usofondo` (`id`, `nombre`, `vigente`, `importe`) VALUES
(1, 'TERRENO', b'1', 10000),
(2, 'OBRA E INFRAESTRUCTURA', b'1', 8000),
(3, 'CONSTRUCCIÓN', b'1', 11000),
(4, 'VIVIENDA', b'1', 25000);

--
-- Volcar la base de datos para la tabla `valorinversion`
--

INSERT INTO `valorinversion` (`id`, `financio_id`, `inversion_id`, `importe`) VALUES
(10, 1, 8, 5),
(11, 2, 8, 10),
(12, 3, 8, 11000),
(13, 4, 8, 25000),
(29, 4, 12, 25000),
(28, 3, 12, 11000),
(27, 2, 12, 8000),
(26, 1, 12, 10000),
(18, 1, 10, 10000),
(19, 2, 10, 8),
(20, 3, 10, 11000),
(21, 4, 10, 25000),
(22, 1, 11, 10000),
(23, 2, 11, 8000),
(24, 3, 11, 11000),
(25, 4, 11, 25000);

--
-- Volcar la base de datos para la tabla `inversion`
--

INSERT INTO `inversion` (`DTYPE`, `id`, `totalInversion`, `ciudad_id`) VALUES
('IParam', 10, 46008, 2),
('IParam', 8, 36015, 1),
('IParam', 11, 54000, 3);

--
-- Volcar la base de datos para la tabla `valorinversion`
--

INSERT INTO `valorinversion` (`id`, `financio_id`, `inversion_id`, `importe`) VALUES
(10, 1, 8, 5),
(11, 2, 8, 10),
(12, 3, 8, 11000),
(13, 4, 8, 25000),
(18, 1, 10, 10000),
(19, 2, 10, 8),
(20, 3, 10, 11000),
(21, 4, 10, 25000),
(22, 1, 11, 10000),
(23, 2, 11, 8000),
(24, 3, 11, 11000),
(25, 4, 11, 25000);
