-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-05-2022 a las 06:54:59
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestionproductos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `code` int(11) NOT NULL,
  `description` varchar(240) NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(60) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `users_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `article`
--

INSERT INTO `article` (`id`, `code`, `description`, `image`, `name`, `price`, `category_id`, `users_id`) VALUES
(42, 4, 'Balon del mundial', 'descarga-1652138241408.jpg', 'Ignacio', '3.00', 1, 12),
(43, 35362, 'Balon del mundial', '5068b980cf47495a6bda3aa70e22f982-1652138357820.jpg', 'DAM', '3.00', 1, 12),
(44, 4, 'Balon del mundial', 'depositphotos_26429241-stock-photo-desktop-computer-with-touchscreen-interface-1652138762884.jpg', 'DAM', '1500.00', 3, 12),
(47, 6548, 'pentium 3', 'depositphotos_26429241-stock-photo-desktop-computer-with-touchscreen-interface-1652144653427.jpg', 'Ordenador', '658.00', 3, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(60) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Deportes'),
(2, 'Música'),
(3, 'Tecnología'),
(4, 'Moda'),
(5, 'Libros'),
(6, 'Muebles');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `confirmation_token`
--

CREATE TABLE `confirmation_token` (
  `token_id` bigint(20) NOT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `confirmation_token`
--

INSERT INTO `confirmation_token` (`token_id`, `confirmation_token`, `created_date`, `id`) VALUES
(2, '08864c39-4d52-4665-b650-1b3e1d6d38a6', '2022-05-08 17:45:02', 1),
(4, 'ce8069fb-dfae-4403-bc1a-5ce77d630c75', '2022-05-08 17:45:35', 3),
(6, '1a9208c2-a5fe-4f46-a3c3-f0440cc08c3c', '2022-05-08 18:00:37', 5),
(8, 'f6962b70-226f-4e79-95bd-5faa80db8ea0', '2022-05-08 18:18:22', 7),
(10, '3aec6008-1041-470f-ab79-050c2281bc07', '2022-05-08 18:46:18', 9),
(13, '91182e4d-58ea-401a-81ab-9cd95b48cb65', '2022-05-08 18:52:53', 12),
(15, '3b2765b8-caa0-46b4-b4d9-96bb57bc75c3', '2022-05-09 08:50:50', 14),
(51, 'd758fba7-0bca-4c07-bfca-eef2a28a5daf', '2022-05-10 04:33:50', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(52);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `birthday` datetime NOT NULL,
  `dni` varchar(9) NOT NULL,
  `email` varchar(50) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `password` varchar(60) NOT NULL,
  `password_confirm` varchar(60) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `register_date` datetime DEFAULT NULL,
  `surname` varchar(90) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `balance`, `birthday`, `dni`, `email`, `enabled`, `name`, `password`, `password_confirm`, `telefono`, `register_date`, `surname`) VALUES
(12, NULL, '2022-02-09 23:00:00', '77454874D', 'luis@gmail.com', b'1', 'Luis', '$2a$10$gzdpEzA9fdlcnAQToTfcAOoeG8l9IGRGU9lOmBJSeRs8VgKv6FEgy', '$2a$10$CHWaWtN1..UoKC1xLnoUpec8m33Gady9g8OT9a9Ia4dWZcDKL5wnS', '645645598', '2022-05-08 18:52:52', 'Hernandez'),
(9, NULL, '2022-01-06 23:00:00', '76084955D', 'ignacastrisa@gmail.com', b'1', 'Ignacio', '$2a$10$DIGZI/trnIVbv9pf3jWm1uvvN4LI/fbo1WtlPdICv7/HTvJqh1wEa', '$2a$10$r1HpxhhJJrOxkfu5AC4bj.zV1uqrO3mO/e13B2GIZzeupcoOEjzNK', '601386781', NULL, 'Castrillon'),
(14, NULL, '2012-06-06 22:00:00', '74587454F', 'fernandoMagallane@gmail.com', b'0', 'Fernando', '$2a$10$y3WIaDKxMMQisPeo27cONuPUuwzJ1FCagyhN7URRizFJ6Z6b6pNPW', '$2a$10$eNsVHEO0VmEH673h.90WDONmtG01IAL9yaFRd0F8PRFt8uBVx6ZfS', '645897845', '2022-05-09 08:50:49', 'Magallanes'),
(50, NULL, '2016-01-29 23:00:00', '74574865T', 'froilan@gmail.com', b'0', 'Froilan', '$2a$10$cVGzkEBBFEo/gGNZ6mBhDO/gqXG1JJIHUOxUkva1FAMTO.NYmYCCC', '$2a$10$t9NLt7kDC831WWDAXuM2JOu2L5Bp2kKdvRU.QFXQ/UtTdIF4gloMu', '625458798', '2022-05-10 04:33:50', 'Borbon');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKy5kkohbk00g0w88fi05k2hcw` (`category_id`),
  ADD KEY `FK6qwjxued33ulih2djfjep0hva` (`users_id`);

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `confirmation_token`
--
ALTER TABLE `confirmation_token`
  ADD PRIMARY KEY (`token_id`),
  ADD KEY `FK9w4f2glynfjh5utjtbxpfgpgh` (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
