INSERT INTO `Ciudad` (`id`, `desde`, `hasta`, `nombre`,vigente) VALUES
(1, 0, 0, '10000',true),
(2, 10, 10000, '25000',true),
(3, 25, 25000, '1000000',true);

INSERT INTO `ParametrosPlan` (`id`, `nombreParametro`, `porcenteaje`) VALUES
(7, 'Ejemplo 1', 0),
(8, 'Ejemplo 2', 0),
(9, 'Ejemplo 3', 0),
(10, 'Ejemplo 4', 0),
(11, 'Ejemplo 5', 0),
(12, 'Ejemplo 6', 0);
(13, 'Ejemplo 7', 0);

INSERT INTO `Operatoria` (`id`, `nombre`, `parametro_id`,vigente) VALUES
(1, 'OBRA PÚBLICA', 1,true),
(2, 'ADMINISTRACIÓN POR COOPERATIVA', 2,true),
(3, 'COOPERATIVA DE TRABAJO', 3,true),
(4, 'AUTOCONSTRUCTOR', 4,true),
(5, 'MUNICIPIOS', 5,true),
(6, 'COMPRA DE VIVIENDA NUEVA O USADA', 6,true);



INSERT INTO `Provincia` (`id`, `nombre`) VALUES
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


INSERT INTO `SectorEconomico` (`id`, `nombre`,vigente) VALUES
(1, 'INGRESOS ALTOS',true),
(2, 'INGRESOS MEDIOS',true),
(3, 'INGRESOS BAJOS',true),
(4, 'INGRESOS MEDIOS BAJOS',true);

    INSERT INTO viviendas.Usuario (`contraseña`, usuario) VALUES ('luis', 'luis');


--ultimos
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('OPERATORIA_OBRA_PUBLICA', 10.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('OPERATORIA_ADM_COOPERATIVA', 40.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('OPERATORIA_COOPERATIVA_TRABAJO', 10.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('OPERATORIA_AUTOCONSTRUCTOR', 10.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('OPERATORIA_MUNICIPIOS', 20.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('OPERATORIA_COMPRA_VIVIENDA', 10.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Segmento_Bajo', 30.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Segmento_MedioBajo', 15.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Segmento_Medio', 15.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Segmento_Alto', 40.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Ciudad_Chica', 30.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Ciudad_Mediana', 30.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Ciudad_Alta', 40.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Fuente_Fuente1', 10.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Fuente_Fuente2', 40.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Fuente_Fuente3', 50.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Uso_Uso1', 20.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Uso_Uso2', 40.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Uso_Uso3', 40.0);
INSERT INTO viviendas.ParametrosPlan (nombreParametro, porcenteaje) VALUES ('Fuente_OtrosAportes', 0.0);



INSERT INTO viviendas.Ciudad (desde, hasta, nombre, vigente, parametro_id) VALUES (0, 0, '10000', true, 11);
INSERT INTO viviendas.Ciudad (desde, hasta, nombre, vigente, parametro_id) VALUES (10, 10000, '25000', true, 12);
INSERT INTO viviendas.Ciudad (desde, hasta, nombre, vigente, parametro_id) VALUES (25, 25000, '1000000', true, 13);

INSERT INTO viviendas.FuenteFondo (nombre, vigente, parametro_id) VALUES ('FUENTE 1', true, 14);
INSERT INTO viviendas.FuenteFondo (nombre, vigente, parametro_id) VALUES ('FUENTE 2', true, 15);
INSERT INTO viviendas.FuenteFondo (nombre, vigente, parametro_id) VALUES ('FUENTE 3', true, 16);
INSERT INTO viviendas.FuenteFondo (nombre, vigente, parametro_id) VALUES ('OTROS APORTES', true, 20);

INSERT INTO viviendas.Operatoria (nombre, vigente, parametro_id) VALUES ('OBRA PÚBLICA', true, 1);
INSERT INTO viviendas.Operatoria (nombre, vigente, parametro_id) VALUES ('ADMINISTRACIÓN POR COOPERATIVA', true, 2);
INSERT INTO viviendas.Operatoria (nombre, vigente, parametro_id) VALUES ('COOPERATIVA DE TRABAJO', true, 3);
INSERT INTO viviendas.Operatoria (nombre, vigente, parametro_id) VALUES ('AUTOCONSTRUCTOR', true, 4);
INSERT INTO viviendas.Operatoria (nombre, vigente, parametro_id) VALUES ('MUNICIPIOS', true, 5);
INSERT INTO viviendas.Operatoria (nombre, vigente, parametro_id) VALUES ('COMPRA DE VIVIENDA NUEVA O USADA', true, 6);


INSERT INTO viviendas.SectorEconomico (nombre, vigente, parametro_id) VALUES ('INGRESOS ALTOS', true, 10);
INSERT INTO viviendas.SectorEconomico (nombre, vigente, parametro_id) VALUES ('INGRESOS MEDIOS', true, 9);
INSERT INTO viviendas.SectorEconomico (nombre, vigente, parametro_id) VALUES ('INGRESOS BAJOS', true, 7);
INSERT INTO viviendas.SectorEconomico (nombre, vigente, parametro_id) VALUES ('INGRESOS MEDIOS BAJOS', true, 8);

INSERT INTO viviendas.TipoPlan (nombre) VALUES ('VIVIENDAS');
INSERT INTO viviendas.TipoPlan (nombre) VALUES ('SOLUCIONES');

INSERT INTO viviendas.UsoFondo (importe, nombre, vigente) VALUES (NULL, 'USO 1', true);
INSERT INTO viviendas.UsoFondo (importe, nombre, vigente) VALUES (NULL, 'USO 2', true);
INSERT INTO viviendas.UsoFondo (importe, nombre, vigente) VALUES (NULL, 'USO 3', true);

INSERT INTO `Provincia` (`id`, `nombre`) VALUES
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



