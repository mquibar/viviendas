-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 28-12-2010 a las 12:50:15
-- Versión del servidor: 5.1.37
-- Versión de PHP: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `viviendas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `añoplan`
--

CREATE TABLE IF NOT EXISTS `añoplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `año` int(11) DEFAULT NULL,
  `cantViviendasAño` int(11) DEFAULT NULL,
  `plan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFF72968A0CBE07E` (`plan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `añoplan`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE IF NOT EXISTS `ciudad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `desde` int(11) DEFAULT NULL,
  `hasta` int(11) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK78530878A25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcar la base de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`id`, `nombre`, `desde`, `hasta`, `parametro_id`, `vigente`) VALUES
(1, '0-10.000', 0, 10000, 15, b'1'),
(2, '10.000-25.000', 10000, 25000, 16, b'1'),
(3, '25.000 - 1.000.000', 25000, 1000000, 17, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalledistribucionfinanciacion`
--

CREATE TABLE IF NOT EXISTS `detalledistribucionfinanciacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentaje` double DEFAULT NULL,
  `distribucionFinanciacion_id` bigint(20) DEFAULT NULL,
  `flujoFondo_id` bigint(20) DEFAULT NULL,
  `usoFondo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAB1790D4F6869A0D` (`flujoFondo_id`),
  KEY `FKAB1790D4F6705932` (`usoFondo_id`),
  KEY `FKAB1790D41DB6F0B2` (`distribucionFinanciacion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `detalledistribucionfinanciacion`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distribucionciudad`
--

CREATE TABLE IF NOT EXISTS `distribucionciudad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentajeDistribucion` double DEFAULT NULL,
  `añoPlan_id` bigint(20) DEFAULT NULL,
  `cuidad_id` bigint(20) DEFAULT NULL,
  `distribucionProvincial_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK45C29D0DD1E897BE` (`distribucionProvincial_id`),
  KEY `FK45C29D0D3CB1A886` (`cuidad_id`),
  KEY `FK45C29D0DBCB2F6F6` (`añoPlan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `distribucionciudad`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distribucionfinanciacion`
--

CREATE TABLE IF NOT EXISTS `distribucionfinanciacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentajeFinanciacion` int(11) NOT NULL,
  `loQueFinancio_id` bigint(20) DEFAULT NULL,
  `financiacion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC4CAD07D316F6662` (`loQueFinancio_id`),
  KEY `FKC4CAD07DB7FF08B2` (`financiacion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `distribucionfinanciacion`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distribucionflujofondo`
--

CREATE TABLE IF NOT EXISTS `distribucionflujofondo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentajeDistribucion` double DEFAULT NULL,
  `flujoFondo_id` bigint(20) DEFAULT NULL,
  `operatoria_id` bigint(20) DEFAULT NULL,
  `sectorEconomico_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK276388B1984BF89E` (`operatoria_id`),
  KEY `FK276388B1BF736E12` (`flujoFondo_id`),
  KEY `FK276388B1D3161B36` (`sectorEconomico_id`),
  KEY `FK276388B1F6869A0D` (`flujoFondo_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `distribucionflujofondo`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distribucionoperatoria`
--

CREATE TABLE IF NOT EXISTS `distribucionoperatoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentajeDistribucion` double DEFAULT NULL,
  `añoPlan_id` bigint(20) DEFAULT NULL,
  `distribucionSector_id` bigint(20) DEFAULT NULL,
  `operatoria_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22CDBAF1984BF89E` (`operatoria_id`),
  KEY `FK22CDBAF1BCB2F6F6` (`añoPlan_id`),
  KEY `FK22CDBAF1F93D785E` (`distribucionSector_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `distribucionoperatoria`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distribucionprovincial`
--

CREATE TABLE IF NOT EXISTS `distribucionprovincial` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentajeDistribucion` double DEFAULT NULL,
  `añoPlan_id` bigint(20) DEFAULT NULL,
  `provincia_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2F515894393209B6` (`provincia_id`),
  KEY `FK2F515894BCB2F6F6` (`añoPlan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `distribucionprovincial`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distribucionsector`
--

CREATE TABLE IF NOT EXISTS `distribucionsector` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentajeDistribucion` double DEFAULT NULL,
  `añoPlan_id` bigint(20) DEFAULT NULL,
  `distribucionCiudad_id` bigint(20) DEFAULT NULL,
  `sectorEconomico_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60CFD81BD584FB1E` (`distribucionCiudad_id`),
  KEY `FK60CFD81BD3161B36` (`sectorEconomico_id`),
  KEY `FK60CFD81BBCB2F6F6` (`añoPlan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `distribucionsector`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `financiacion`
--

CREATE TABLE IF NOT EXISTS `financiacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `cuidad_id` bigint(20) DEFAULT NULL,
  `distribucionFinanciacion_id` bigint(20) DEFAULT NULL,
  `operatoria_id` bigint(20) DEFAULT NULL,
  `sectorEconomico_id` bigint(20) DEFAULT NULL,
  `distribucionOperatoria_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2259BFA8984BF89E` (`operatoria_id`),
  KEY `FK2259BFA81DB6F0B2` (`distribucionFinanciacion_id`),
  KEY `FK2259BFA8D3161B36` (`sectorEconomico_id`),
  KEY `FK2259BFA83CB1A886` (`cuidad_id`),
  KEY `FK2259BFA87D1F9E9E` (`distribucionOperatoria_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `financiacion`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flujofondo`
--

CREATE TABLE IF NOT EXISTS `flujofondo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `flujofondo`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fuentefondo`
--

CREATE TABLE IF NOT EXISTS `fuentefondo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK26958347A25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcar la base de datos para la tabla `fuentefondo`
--

INSERT INTO `fuentefondo` (`id`, `nombre`, `parametro_id`, `vigente`) VALUES
(1, 'OTROS APORTES', 46, b'1'),
(2, 'gdfgd', 47, b'0'),
(3, 'APORTES A', 54, b'1'),
(4, 'ZAPORTE', 55, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inversion`
--

CREATE TABLE IF NOT EXISTS `inversion` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `totalInversion` double DEFAULT NULL,
  `ciudad_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFF3A0BF3D8EE551E` (`ciudad_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Volcar la base de datos para la tabla `inversion`
--

INSERT INTO `inversion` (`DTYPE`, `id`, `totalInversion`, `ciudad_id`) VALUES
('IParam', 10, 46008, 2),
('IParam', 8, 36015, 1),
('IParam', 11, 54000, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `loquefinancio`
--

CREATE TABLE IF NOT EXISTS `loquefinancio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `loquefinancio`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operatoria`
--

CREATE TABLE IF NOT EXISTS `operatoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK771478DCA25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametrosplan`
--

CREATE TABLE IF NOT EXISTS `parametrosplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombreParametro` varchar(255) DEFAULT NULL,
  `porcenteaje` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=56 ;

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan`
--

CREATE TABLE IF NOT EXISTS `plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `añosPlan` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `numeroViviendas` int(11) DEFAULT NULL,
  `tipoPlan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK25FF49D076061E` (`tipoPlan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `plan`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan_inversion`
--

CREATE TABLE IF NOT EXISTS `plan_inversion` (
  `Plan_id` bigint(20) NOT NULL,
  `listaInversion_id` bigint(20) NOT NULL,
  UNIQUE KEY `listaInversion_id` (`listaInversion_id`),
  KEY `FKBFA53E7DA0CBE07E` (`Plan_id`),
  KEY `FKBFA53E7D291A64AE` (`listaInversion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `plan_inversion`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

CREATE TABLE IF NOT EXISTS `provincia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK56D35B8DA25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia_ciudad`
--

CREATE TABLE IF NOT EXISTS `provincia_ciudad` (
  `Provincia_id` bigint(20) NOT NULL,
  `listaCuidad_id` bigint(20) NOT NULL,
  KEY `FK4189016A393209B6` (`Provincia_id`),
  KEY `FK4189016AD6428863` (`listaCuidad_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `provincia_ciudad`
--

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sectoreconomico`
--

CREATE TABLE IF NOT EXISTS `sectoreconomico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK240AB234A25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcar la base de datos para la tabla `sectoreconomico`
--

INSERT INTO `sectoreconomico` (`id`, `nombre`, `parametro_id`, `vigente`) VALUES
(1, 'INGRESOS ALTOS', 10, b'1'),
(2, 'INGRESOS MEDIOS', 11, b'1'),
(3, 'INGRESOS BAJOS', 12, b'1'),
(4, 'INGRESOS MEDIOS BAJOS', 13, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoplan`
--

CREATE TABLE IF NOT EXISTS `tipoplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `tipoplan`
--

INSERT INTO `tipoplan` (`id`, `nombre`) VALUES
(1, 'VIVIENDAS'),
(2, 'SOLUCIONES');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usofondo`
--

CREATE TABLE IF NOT EXISTS `usofondo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  `importe` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcar la base de datos para la tabla `usofondo`
--

INSERT INTO `usofondo` (`id`, `nombre`, `vigente`, `importe`) VALUES
(1, 'TERRENO', b'1', 10000),
(2, 'OBRA E INFRAESTRUCTURA', b'1', 8000),
(3, 'CONSTRUCCIÓN', b'1', 11000),
(4, 'VIVIENDA', b'1', 25000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contraseña` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcar la base de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `contraseña`, `usuario`) VALUES
(1, 'luis', 'luis');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valor`
--

CREATE TABLE IF NOT EXISTS `valor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentaje` double DEFAULT NULL,
  `cuidad_id` bigint(20) DEFAULT NULL,
  `financio_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4E9A0A43CB1A886` (`cuidad_id`),
  KEY `FK4E9A0A453E48380` (`financio_id`),
  KEY `FK4E9A0A4CF1C10C0` (`financio_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `valor`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valorinversion`
--

CREATE TABLE IF NOT EXISTS `valorinversion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `financio_id` bigint(20) DEFAULT NULL,
  `inversion_id` bigint(20) DEFAULT NULL,
  `importe` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD630E3CFA20233E2` (`inversion_id`),
  KEY `FKD630E3CFCF1C10C0` (`financio_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

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
