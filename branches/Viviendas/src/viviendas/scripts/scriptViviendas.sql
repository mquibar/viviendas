-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 30, 2010 at 09:06 AM
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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `añoplan`
--

INSERT INTO `añoplan` (`id`, `año`, `cantViviendasAño`, `plan_id`) VALUES
(1, 2010, 1000, 1),
(2, 2011, 1000, 1),
(3, 2012, 1000, 1),
(4, 2013, 1000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ciudad`
--

CREATE TABLE IF NOT EXISTS `ciudad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `desde` int(11) DEFAULT NULL,
  `hasta` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK78530878A25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `ciudad`
--

INSERT INTO `ciudad` (`id`, `desde`, `hasta`, `nombre`, `parametro_id`, `vigente`) VALUES
(1, 0, 10000, 'RURAL', 8, b'1'),
(2, 10000, 20000, '10000-20000', 9, b'1'),
(3, 20000, 1000000, 'METROPOLI', 10, NULL),
(4, NULL, NULL, '0-10000', 39, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `detalledistribucionfinanciacion`
--

CREATE TABLE IF NOT EXISTS `detalledistribucionfinanciacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `porcentaje` double DEFAULT NULL,
  `distribucionFinanciacion_id` bigint(20) DEFAULT NULL,
  `fuenteFondo_id` bigint(20) DEFAULT NULL,
  `usoFondo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAB1790D4F6705932` (`usoFondo_id`),
  KEY `FKAB1790D41DB6F0B2` (`distribucionFinanciacion_id`),
  KEY `FKAB1790D466A7FD42` (`fuenteFondo_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `detalledistribucionfinanciacion`
--


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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `distribucionciudad`
--

INSERT INTO `distribucionciudad` (`id`, `porcentajeDistribucion`, `añoPlan_id`, `cuidad_id`, `distribucionProvincial_id`) VALUES
(1, 25, 1, 3, 2),
(2, 75, 1, 1, 2),
(3, 33.333, 2, 3, 6),
(4, 33.333, 2, 1, 6),
(5, 33.333, 3, 3, 10),
(6, 33.333, 3, 1, 10),
(7, 33.333, 4, 3, 14),
(8, 33.333, 4, 1, 14);

-- --------------------------------------------------------

--
-- Table structure for table `distribucionfinanciacion`
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
  KEY `FK276388B1D3161B36` (`sectorEconomico_id`),
  KEY `FK276388B1F6869A0D` (`flujoFondo_id`)
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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=193 ;

--
-- Dumping data for table `distribucionoperatoria`
--

INSERT INTO `distribucionoperatoria` (`id`, `porcentajeDistribucion`, `añoPlan_id`, `distribucionSector_id`, `operatoria_id`) VALUES
(1, 33.333, 1, 1, 1),
(2, 33.333, 1, 1, 2),
(3, 33.333, 1, 1, 3),
(4, 33.333, 1, 1, 4),
(5, 33.333, 1, 1, 5),
(6, 33.333, 1, 1, 6),
(7, 33.333, 1, 2, 1),
(8, 33.333, 1, 2, 2),
(9, 33.333, 1, 2, 3),
(10, 33.333, 1, 2, 4),
(11, 33.333, 1, 2, 5),
(12, 33.333, 1, 2, 6),
(13, 33.333, 1, 3, 1),
(14, 33.333, 1, 3, 2),
(15, 33.333, 1, 3, 3),
(16, 33.333, 1, 3, 4),
(17, 33.333, 1, 3, 5),
(18, 33.333, 1, 3, 6),
(19, 33.333, 1, 4, 1),
(20, 33.333, 1, 4, 2),
(21, 33.333, 1, 4, 3),
(22, 33.333, 1, 4, 4),
(23, 33.333, 1, 4, 5),
(24, 33.333, 1, 4, 6),
(25, 33.333, 1, 5, 1),
(26, 33.333, 1, 5, 2),
(27, 33.333, 1, 5, 3),
(28, 33.333, 1, 5, 4),
(29, 33.333, 1, 5, 5),
(30, 33.333, 1, 5, 6),
(31, 33.333, 1, 6, 1),
(32, 33.333, 1, 6, 2),
(33, 33.333, 1, 6, 3),
(34, 33.333, 1, 6, 4),
(35, 33.333, 1, 6, 5),
(36, 33.333, 1, 6, 6),
(37, 33.333, 1, 7, 1),
(38, 33.333, 1, 7, 2),
(39, 33.333, 1, 7, 3),
(40, 33.333, 1, 7, 4),
(41, 33.333, 1, 7, 5),
(42, 33.333, 1, 7, 6),
(43, 33.333, 1, 8, 1),
(44, 33.333, 1, 8, 2),
(45, 33.333, 1, 8, 3),
(46, 33.333, 1, 8, 4),
(47, 33.333, 1, 8, 5),
(48, 33.333, 1, 8, 6),
(49, 33.333, 2, 9, 1),
(50, 33.333, 2, 9, 2),
(51, 33.333, 2, 9, 3),
(52, 33.333, 2, 9, 4),
(53, 33.333, 2, 9, 5),
(54, 33.333, 2, 9, 6),
(55, 33.333, 2, 10, 1),
(56, 33.333, 2, 10, 2),
(57, 33.333, 2, 10, 3),
(58, 33.333, 2, 10, 4),
(59, 33.333, 2, 10, 5),
(60, 33.333, 2, 10, 6),
(61, 33.333, 2, 11, 1),
(62, 33.333, 2, 11, 2),
(63, 33.333, 2, 11, 3),
(64, 33.333, 2, 11, 4),
(65, 33.333, 2, 11, 5),
(66, 33.333, 2, 11, 6),
(67, 33.333, 2, 12, 1),
(68, 33.333, 2, 12, 2),
(69, 33.333, 2, 12, 3),
(70, 33.333, 2, 12, 4),
(71, 33.333, 2, 12, 5),
(72, 33.333, 2, 12, 6),
(73, 33.333, 2, 13, 1),
(74, 33.333, 2, 13, 2),
(75, 33.333, 2, 13, 3),
(76, 33.333, 2, 13, 4),
(77, 33.333, 2, 13, 5),
(78, 33.333, 2, 13, 6),
(79, 33.333, 2, 14, 1),
(80, 33.333, 2, 14, 2),
(81, 33.333, 2, 14, 3),
(82, 33.333, 2, 14, 4),
(83, 33.333, 2, 14, 5),
(84, 33.333, 2, 14, 6),
(85, 33.333, 2, 15, 1),
(86, 33.333, 2, 15, 2),
(87, 33.333, 2, 15, 3),
(88, 33.333, 2, 15, 4),
(89, 33.333, 2, 15, 5),
(90, 33.333, 2, 15, 6),
(91, 33.333, 2, 16, 1),
(92, 33.333, 2, 16, 2),
(93, 33.333, 2, 16, 3),
(94, 33.333, 2, 16, 4),
(95, 33.333, 2, 16, 5),
(96, 33.333, 2, 16, 6),
(97, 33.333, 3, 17, 1),
(98, 33.333, 3, 17, 2),
(99, 33.333, 3, 17, 3),
(100, 33.333, 3, 17, 4),
(101, 33.333, 3, 17, 5),
(102, 33.333, 3, 17, 6),
(103, 33.333, 3, 18, 1),
(104, 33.333, 3, 18, 2),
(105, 33.333, 3, 18, 3),
(106, 33.333, 3, 18, 4),
(107, 33.333, 3, 18, 5),
(108, 33.333, 3, 18, 6),
(109, 33.333, 3, 19, 1),
(110, 33.333, 3, 19, 2),
(111, 33.333, 3, 19, 3),
(112, 33.333, 3, 19, 4),
(113, 33.333, 3, 19, 5),
(114, 33.333, 3, 19, 6),
(115, 33.333, 3, 20, 1),
(116, 33.333, 3, 20, 2),
(117, 33.333, 3, 20, 3),
(118, 33.333, 3, 20, 4),
(119, 33.333, 3, 20, 5),
(120, 33.333, 3, 20, 6),
(121, 33.333, 3, 21, 1),
(122, 33.333, 3, 21, 2),
(123, 33.333, 3, 21, 3),
(124, 33.333, 3, 21, 4),
(125, 33.333, 3, 21, 5),
(126, 33.333, 3, 21, 6),
(127, 33.333, 3, 22, 1),
(128, 33.333, 3, 22, 2),
(129, 33.333, 3, 22, 3),
(130, 33.333, 3, 22, 4),
(131, 33.333, 3, 22, 5),
(132, 33.333, 3, 22, 6),
(133, 33.333, 3, 23, 1),
(134, 33.333, 3, 23, 2),
(135, 33.333, 3, 23, 3),
(136, 33.333, 3, 23, 4),
(137, 33.333, 3, 23, 5),
(138, 33.333, 3, 23, 6),
(139, 33.333, 3, 24, 1),
(140, 33.333, 3, 24, 2),
(141, 33.333, 3, 24, 3),
(142, 33.333, 3, 24, 4),
(143, 33.333, 3, 24, 5),
(144, 33.333, 3, 24, 6),
(145, 33.333, 4, 25, 1),
(146, 33.333, 4, 25, 2),
(147, 33.333, 4, 25, 3),
(148, 33.333, 4, 25, 4),
(149, 33.333, 4, 25, 5),
(150, 33.333, 4, 25, 6),
(151, 33.333, 4, 26, 1),
(152, 33.333, 4, 26, 2),
(153, 33.333, 4, 26, 3),
(154, 33.333, 4, 26, 4),
(155, 33.333, 4, 26, 5),
(156, 33.333, 4, 26, 6),
(157, 33.333, 4, 27, 1),
(158, 33.333, 4, 27, 2),
(159, 33.333, 4, 27, 3),
(160, 33.333, 4, 27, 4),
(161, 33.333, 4, 27, 5),
(162, 33.333, 4, 27, 6),
(163, 33.333, 4, 28, 1),
(164, 33.333, 4, 28, 2),
(165, 33.333, 4, 28, 3),
(166, 33.333, 4, 28, 4),
(167, 33.333, 4, 28, 5),
(168, 33.333, 4, 28, 6),
(169, 33.333, 4, 29, 1),
(170, 33.333, 4, 29, 2),
(171, 33.333, 4, 29, 3),
(172, 33.333, 4, 29, 4),
(173, 33.333, 4, 29, 5),
(174, 33.333, 4, 29, 6),
(175, 33.333, 4, 30, 1),
(176, 33.333, 4, 30, 2),
(177, 33.333, 4, 30, 3),
(178, 33.333, 4, 30, 4),
(179, 33.333, 4, 30, 5),
(180, 33.333, 4, 30, 6),
(181, 33.333, 4, 31, 1),
(182, 33.333, 4, 31, 2),
(183, 33.333, 4, 31, 3),
(184, 33.333, 4, 31, 4),
(185, 33.333, 4, 31, 5),
(186, 33.333, 4, 31, 6),
(187, 33.333, 4, 32, 1),
(188, 33.333, 4, 32, 2),
(189, 33.333, 4, 32, 3),
(190, 33.333, 4, 32, 4),
(191, 33.333, 4, 32, 5),
(192, 33.333, 4, 32, 6);

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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `distribucionprovincial`
--

INSERT INTO `distribucionprovincial` (`id`, `porcentajeDistribucion`, `añoPlan_id`, `provincia_id`) VALUES
(1, 25, 1, 1),
(2, 25, 1, 12),
(3, 25, 1, 11),
(4, 25, 1, 14),
(5, 25, 2, 1),
(6, 25, 2, 12),
(7, 25, 2, 11),
(8, 25, 2, 14),
(9, 25, 3, 1),
(10, 25, 3, 12),
(11, 25, 3, 11),
(12, 25, 3, 14),
(13, 25, 4, 1),
(14, 25, 4, 12),
(15, 25, 4, 11),
(16, 25, 4, 14);

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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `distribucionsector`
--

INSERT INTO `distribucionsector` (`id`, `porcentajeDistribucion`, `añoPlan_id`, `distribucionCiudad_id`, `sectorEconomico_id`) VALUES
(1, 33.333, 1, 1, 1),
(2, 33.333, 1, 1, 2),
(3, 33.333, 1, 1, 3),
(4, 33.333, 1, 1, 4),
(5, 33.333, 1, 2, 1),
(6, 33.333, 1, 2, 2),
(7, 33.333, 1, 2, 3),
(8, 33.333, 1, 2, 4),
(9, 33.333, 2, 3, 1),
(10, 33.333, 2, 3, 2),
(11, 33.333, 2, 3, 3),
(12, 33.333, 2, 3, 4),
(13, 33.333, 2, 4, 1),
(14, 33.333, 2, 4, 2),
(15, 33.333, 2, 4, 3),
(16, 33.333, 2, 4, 4),
(17, 33.333, 3, 5, 1),
(18, 33.333, 3, 5, 2),
(19, 33.333, 3, 5, 3),
(20, 33.333, 3, 5, 4),
(21, 33.333, 3, 6, 1),
(22, 33.333, 3, 6, 2),
(23, 33.333, 3, 6, 3),
(24, 33.333, 3, 6, 4),
(25, 33.333, 4, 7, 1),
(26, 33.333, 4, 7, 2),
(27, 33.333, 4, 7, 3),
(28, 33.333, 4, 7, 4),
(29, 33.333, 4, 8, 1),
(30, 33.333, 4, 8, 2),
(31, 33.333, 4, 8, 3),
(32, 33.333, 4, 8, 4);

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
  `distribucionOperatoria_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2259BFA8984BF89E` (`operatoria_id`),
  KEY `FK2259BFA81DB6F0B2` (`distribucionFinanciacion_id`),
  KEY `FK2259BFA8D3161B36` (`sectorEconomico_id`),
  KEY `FK2259BFA83CB1A886` (`cuidad_id`),
  KEY `FK2259BFA87D1F9E9E` (`distribucionOperatoria_id`)
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
-- Table structure for table `fuentefondo`
--

CREATE TABLE IF NOT EXISTS `fuentefondo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK26958347A25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `fuentefondo`
--

INSERT INTO `fuentefondo` (`id`, `nombre`, `vigente`, `parametro_id`) VALUES
(1, 'DEVOLUCION CREDITOS', b'1', 42),
(2, 'AHORRO PREVIO', b'1', 43),
(3, 'OTROS APORTES', b'1', 44),
(4, 'CREDITOS DEL ESTADO', b'1', 45),
(5, 'SUBSIDIOS DEL ESTADO', b'1', 46),
(6, 'APORTES PROVINCIALES', b'1', 47),
(7, 'APORTES MUNICIPALES', b'1', 48);

-- --------------------------------------------------------

--
-- Table structure for table `inversion`
--

CREATE TABLE IF NOT EXISTS `inversion` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `totalInversion` double DEFAULT NULL,
  `ciudad_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFF3A0BF3D8EE551E` (`ciudad_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `inversion`
--

INSERT INTO `inversion` (`DTYPE`, `id`, `totalInversion`, `ciudad_id`) VALUES
('IParam', 1, 0, NULL),
('IParam', 2, 80000, 1),
('IParam', 3, 80000, 2),
('IParam', 4, 80000, 4);

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
  `vigente` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK771478DCA25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `operatoria`
--

INSERT INTO `operatoria` (`id`, `nombre`, `parametro_id`, `vigente`) VALUES
(1, 'OBRA PÚBLICA', 1, b'1'),
(2, 'ADMINISTRACIÓN POR COOPERATIVA', 2, b'1'),
(3, 'COOPERATIVA DE TRABAJO', 3, NULL),
(4, 'AUTOCONSTRUCTOR', 4, b'1'),
(5, 'MUNICIPIOS', 5, b'1'),
(6, 'COMPRA DE VIVIENDA NUEVA O USADA', 6, NULL),
(8, 'COOP. TRABAJO', 40, b'1'),
(9, 'COMPRA VIVIENDA NUEVA O USADA', 41, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `parametrosplan`
--

CREATE TABLE IF NOT EXISTS `parametrosplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombreParametro` varchar(255) DEFAULT NULL,
  `porcenteaje` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Dumping data for table `parametrosplan`
--

INSERT INTO `parametrosplan` (`id`, `nombreParametro`, `porcenteaje`) VALUES
(1, 'OPERATORIA_OBRA_PUBLICA', 100),
(2, 'OPERATORIA_ADM_COOPERATIVA', 0),
(3, 'OPERATORIA_COOPERATIVA_TRABAJO', 0),
(4, 'OPERATORIA_AUTOCONSTRUCTOR', 0),
(5, 'OPERATORIA_MUNICIPIOS', 0),
(6, 'OPERATORIA_COMPRA_VIVIENDA', 10),
(7, 'OPERATORIA_OTRA', 10),
(41, 'OPERATORIA_COMPRA VIVIENDA NUEVA O USADA', 0),
(8, 'CIUDAD_0-10000', 0),
(9, 'CIUDAD_10000-20000', 0),
(10, 'CIUDAD_20000-1000000', 0),
(11, 'PROVINCIA_SAN JUAN', 0),
(12, 'PROVINCIA_BUENOS AIRES', 0),
(13, 'PROVINCIA_TIERRA DEL FUEGO', 0),
(14, 'PROVINCIA_ENTRE RÍOS', 0),
(15, 'PROVINCIA_FORMOSA', 90),
(16, 'PROVINCIA_SANTIAGO DEL ESTERO', 0),
(17, 'PROVINCIA_CHACO', 10),
(18, 'PROVINCIA_MISIONES', 0),
(19, 'PROVINCIA_JUJUY', 0),
(20, 'PROVINCIA_CATAMARCA', 0),
(21, 'PROVINCIA_LA RIOJA', 0),
(22, 'PROVINCIA_MENDOZA', 0),
(23, 'PROVINCIA_NEUQUÉN', 0),
(24, 'PROVINCIA_CORDOBA', 0),
(25, 'PROVINCIA_LA PAMPA', 0),
(26, 'PROVINCIA_SANTA CRUZ', 0),
(27, 'PROVINCIA_RÍO NEGRO', 0),
(28, 'PROVINCIA_SALTA', 0),
(29, 'PROVINCIA_TUCUMAN', 0),
(30, 'PROVINCIA_CHUBUT', 0),
(31, 'PROVINCIA_SAN LUIS', 0),
(32, 'PROVINCIA_CORRIENTES', 0),
(33, 'PROVINCIA_SANTA FE', 0),
(34, 'PROVINCIA_CAPITAL FEDERAL', 0),
(35, 'SECTOR-ECONOMICO_INGRESOS ALTOS', 100),
(36, 'SECTOR-ECONOMICO_MEDIOS', 0),
(37, 'SECTOR-ECONOMICO_BAJOS', 0),
(38, 'SECTOR-ECONOMICO_MEDIOS BAJOS', 0),
(39, 'CIUDAD_0-10000', 100),
(40, 'OPERATORIA_COOP. TRABAJO', 0),
(42, 'FUENTEFONDO_DEVOLUCION CREDITOS', 100),
(43, 'FUENTEFONDO_AHORRO PREVIO', 0),
(44, 'FUENTEFONDO_OTROS APORTES', 0),
(45, 'FUENTEFONDO_CREDITOS DEL ESTADO', 0),
(46, 'FUENTEFONDO_SUBSIDIOS DEL ESTADO', 0),
(47, 'FUENTEFONDO_APORTES PROVINCIALES', 0),
(48, 'FUENTEFONDO_APORTES MUNICIPALES', 0);

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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `plan`
--

INSERT INTO `plan` (`id`, `añosPlan`, `nombre`, `numeroViviendas`, `tipoPlan_id`) VALUES
(1, 4, 'PLAN_1', 4000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `plan_inversion`
--

CREATE TABLE IF NOT EXISTS `plan_inversion` (
  `Plan_id` bigint(20) NOT NULL,
  `listaInversion_id` bigint(20) NOT NULL,
  UNIQUE KEY `listaInversion_id` (`listaInversion_id`),
  KEY `FKBFA53E7DA0CBE07E` (`Plan_id`),
  KEY `FKBFA53E7D291A64AE` (`listaInversion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plan_inversion`
--


-- --------------------------------------------------------

--
-- Table structure for table `provincia`
--

CREATE TABLE IF NOT EXISTS `provincia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK56D35B8DA25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `provincia`
--

INSERT INTO `provincia` (`id`, `nombre`, `parametro_id`) VALUES
(1, 'SAN JUAN', 11),
(2, 'BUENOS AIRES', 12),
(3, 'TIERRA DEL FUEGO', 13),
(4, 'ENTRE RÍOS', 14),
(5, 'FORMOSA', 15),
(6, 'SANTIAGO DEL ESTERO', 16),
(7, 'CHACO', 17),
(8, 'MISIONES', 18),
(9, 'JUJUY', 19),
(10, 'CATAMARCA', 20),
(11, 'LA RIOJA', 21),
(12, 'MENDOZA', 22),
(13, 'NEUQUÉN', 23),
(14, 'CÓRDOBA', 24),
(15, 'LA PAMPA', 25),
(16, 'SANTA CRUZ', 26),
(17, 'RÍO NEGRO', 27),
(18, 'SALTA', 28),
(19, 'TUCUMÁN', 29),
(20, 'CHUBUT', 30),
(21, 'SAN LUIS', 31),
(22, 'CORRIENTES', 32),
(23, 'SANTA FE', 33),
(24, 'CAPITAL FEDERAL', 34);

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

INSERT INTO `provincia_ciudad` (`Provincia_id`, `listaCuidad_id`) VALUES
(12, 2),
(12, 1),
(12, 3),
(2, 3),
(6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sectoreconomico`
--

CREATE TABLE IF NOT EXISTS `sectoreconomico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK240AB234A25178E8` (`parametro_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `sectoreconomico`
--

INSERT INTO `sectoreconomico` (`id`, `nombre`, `parametro_id`, `vigente`) VALUES
(1, 'INGRESOS ALTOS', 35, b'1'),
(2, 'INGRESOS MEDIOS', 36, b'1'),
(3, 'INGRESOS BAJOS', 37, b'1'),
(4, 'INGRESOS MEDIOS BAJOS', 38, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `tipoplan`
--

CREATE TABLE IF NOT EXISTS `tipoplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `tipoplan`
--

INSERT INTO `tipoplan` (`id`, `nombre`) VALUES
(1, 'VIVIENDAS'),
(2, 'SOLUCIONES');

-- --------------------------------------------------------

--
-- Table structure for table `usofondo`
--

CREATE TABLE IF NOT EXISTS `usofondo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `importe` double DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `usofondo`
--

INSERT INTO `usofondo` (`id`, `importe`, `nombre`, `vigente`) VALUES
(1, 10000, 'VIVIENDA', b'1'),
(2, 50000, 'TERRENO', b'1'),
(3, 20000, 'URBANIZACION E INFRAESTRUCTURA', b'1');

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


-- --------------------------------------------------------

--
-- Table structure for table `valorinversion`
--

CREATE TABLE IF NOT EXISTS `valorinversion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `importe` double DEFAULT NULL,
  `financio_id` bigint(20) DEFAULT NULL,
  `inversion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD630E3CFA20233E2` (`inversion_id`),
  KEY `FKD630E3CFCF1C10C0` (`financio_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `valorinversion`
--

INSERT INTO `valorinversion` (`id`, `importe`, `financio_id`, `inversion_id`) VALUES
(1, 10000, 1, 2),
(2, 50000, 2, 2),
(3, 20000, 3, 2),
(4, 10000, 1, 3),
(5, 50000, 2, 3),
(6, 20000, 3, 3),
(7, 10000, 1, 4),
(8, 50000, 2, 4),
(9, 20000, 3, 4);
