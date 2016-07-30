-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:8889
-- Tiempo de generación: 30-07-2016 a las 02:15:04
-- Versión del servidor: 5.5.42
-- Versión de PHP: 5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `productsAndroid`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `HISTORICO`
--

DROP TABLE IF EXISTS `HISTORICO`;
CREATE TABLE `HISTORICO` (
  `ID` int(11) NOT NULL,
  `TITTLE` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `IMGURL` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `FECHA` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `HISTORICO`
--

INSERT INTO `HISTORICO` (`ID`, `TITTLE`, `IMGURL`, `FECHA`) VALUES
(1, 'CDHYF - El Mundo de Hielo y Fuego', '/img/MundoHieloFuego.jpg', '2016-07-29 15:05:32'),
(2, 'CDHYF - El Mundo de Hielo y Fuego', '/img/MundoHieloFuego.jpg', '2016-07-29 15:08:32'),
(3, 'CDHYF I - Juego de Tronos [Lujo]', '/img/JuegoTronos.jpg', '2016-07-29 18:04:57'),
(4, 'CDHYF V - Danza de Dragones [Lujo]', '/img/DanzaDragones.jpg', '2016-07-29 18:05:55'),
(5, 'CDHYF III - Tormentas de Espadas [Lujo]', '/img/TormentaSpadas.jpg', '2016-07-29 18:06:35'),
(6, 'CDHYF - El Mundo de Hielo y Fuego', '/img/MundoHieloFuego.jpg', '2016-07-30 00:10:19'),
(67, 'CDHYF III - Tormentas de Espadas [Lujo]', '/img/TormentaSpadas.jpg', '2016-07-30 00:10:31');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `HISTORICO`
--
ALTER TABLE `HISTORICO`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `HISTORICO`
--
ALTER TABLE `HISTORICO`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
