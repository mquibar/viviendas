-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 13, 2010 at 05:39 AM
-- Server version: 5.1.37
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `viviendas`
--

-- --------------------------------------------------------

--
-- Table structure for table `añoplan`
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
-- Dumping data for table `añoplan`
--


-- --------------------------------------------------------

--
-- Table structure for table `ciudad`
--

CREATE TABLE IF NOT EXISTS `ciudad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `desde` int(11) DEFAULT NULL,
  `hasta` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `ciudad`
--

INSERT INTO `ciudad` (`id`, `desde`, `hasta`, `nombre`) VALUES
(1, 0, 0, '10000'),
(2, 10, 10000, '25000'),
(3, 25, 25000, '1000000');

-- --------------------------------------------------------

--
-- Table structure for table `distribucionciudad`
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
-- Dumping data for table `distribucionciudad`
--


-- --------------------------------------------------------

--
-- Table structure for table `distribucionfinanciacion`
--

CREATE TABLE IF NOT EXISTS `distribucionfinanciacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentajeFinanciacion` int(11) NOT NULL,
  `loQueFinancio_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC4CAD07D316F6662` (`loQueFinancio_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `distribucionfinanciacion`
--


-- --------------------------------------------------------

--
-- Table structure for table `distribucionflujofondo`
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
  KEY `FK276388B1D3161B36` (`sectorEconomico_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `distribucionflujofondo`
--


-- --------------------------------------------------------

--
-- Table structure for table `distribucionoperatoria`
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
-- Dumping data for table `distribucionoperatoria`
--


-- --------------------------------------------------------

--
-- Table structure for table `distribucionprovincial`
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
-- Dumping data for table `distribucionprovincial`
--


-- --------------------------------------------------------

--
-- Table structure for table `distribucionsector`
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
-- Dumping data for table `distribucionsector`
--


-- --------------------------------------------------------

--
-- Table structure for table `financiacion`
--

CREATE TABLE IF NOT EXISTS `financiacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `cuidad_id` bigint(20) DEFAULT NULL,
  `distribucionFinanciacion_id` bigint(20) DEFAULT NULL,
  `operatoria_id` bigint(20) DEFAULT NULL,
  `sectorEconomico_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2259BFA8984BF89E` (`operatoria_id`),
  KEY `FK2259BFA81DB6F0B2` (`distribucionFinanciacion_id`),
  KEY `FK2259BFA8D3161B36` (`sectorEconomico_id`),
  KEY `FK2259BFA83CB1A886` (`cuidad_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `financiacion`
--


-- --------------------------------------------------------

--
-- Table structure for table `flujofondo`
--

CREATE TABLE IF NOT EXISTS `flujofondo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `flujofondo`
--


-- --------------------------------------------------------

--
-- Table structure for table `loquefinancio`
--

CREATE TABLE IF NOT EXISTS `loquefinancio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `loquefinancio`
--


-- --------------------------------------------------------

--
-- Table structure for table `operatoria`
--

CREATE TABLE IF NOT EXISTS `operatoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK771478DCA25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `operatoria`
--

INSERT INTO `operatoria` (`id`, `nombre`, `parametro_id`) VALUES
(1, 'OBRA PÚBLICA', 1),
(2, 'ADMINISTRACIÓN POR COOPERATIVA', 2),
(3, 'COOPERATIVA DE TRABAJO', 3),
(4, 'AUTOCONSTRUCTOR', 4),
(5, 'MUNICIPIOS', 5),
(6, 'COMPRA DE VIVIENDA NUEVA O USADA', 6);

-- --------------------------------------------------------

--
-- Table structure for table `parametrosplan`
--

CREATE TABLE IF NOT EXISTS `parametrosplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombreParametro` varchar(255) DEFAULT NULL,
  `porcenteaje` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `parametrosplan`
--

INSERT INTO `parametrosplan` (`id`, `nombreParametro`, `porcenteaje`) VALUES
(1, 'OPERATORIA_OBRA_PUBLICA', 0),
(2, 'OPERATORIA_ADM_COOPERATIVA', 0),
(3, 'OPERATORIA_COOPERATIVA_TRABAJO', 0),
(4, 'OPERATORIA_AUTOCONSTRUCTOR', 0),
(5, 'OPERATORIA_MUNICIPIOS', 0),
(6, 'OPERATORIA_COMPRA_VIVIENDA', 0);

-- --------------------------------------------------------

--
-- Table structure for table `plan`
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
-- Dumping data for table `plan`
--


-- --------------------------------------------------------

--
-- Table structure for table `provincia`
--

CREATE TABLE IF NOT EXISTS `provincia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `provincia`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `provincia_ciudad`
--

CREATE TABLE IF NOT EXISTS `provincia_ciudad` (
  `Provincia_id` bigint(20) NOT NULL,
  `listaCuidad_id` bigint(20) NOT NULL,
  KEY `FK4189016A393209B6` (`Provincia_id`),
  KEY `FK4189016AD6428863` (`listaCuidad_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `provincia_ciudad`
--


-- --------------------------------------------------------

--
-- Table structure for table `sectoreconomico`
--

CREATE TABLE IF NOT EXISTS `sectoreconomico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `sectoreconomico`
--

INSERT INTO `sectoreconomico` (`id`, `nombre`) VALUES
(1, 'INGRESOS ALTOS'),
(2, 'INGRESOS MEDIOS'),
(3, 'INGRESOS BAJOS'),
(4, 'INGRESOS MEDIOS BAJOS');

-- --------------------------------------------------------

--
-- Table structure for table `tipoplan`
--

CREATE TABLE IF NOT EXISTS `tipoplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `tipoplan`
--


-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contraseña` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `contraseña`, `usuario`) VALUES
(1, 'luis', 'luis');

-- --------------------------------------------------------

--
-- Table structure for table `valor`
--

CREATE TABLE IF NOT EXISTS `valor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentaje` double DEFAULT NULL,
  `cuidad_id` bigint(20) DEFAULT NULL,
  `financio_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4E9A0A43CB1A886` (`cuidad_id`),
  KEY `FK4E9A0A453E48380` (`financio_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `valor`
--

