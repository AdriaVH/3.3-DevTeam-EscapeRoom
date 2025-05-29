-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-05-2025 a las 11:12:14
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `scaperoom`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clue`
--

CREATE TABLE `clue` (
  `id` int(11) NOT NULL,
  `enigma_id` int(11) DEFAULT NULL,
  `item_id` int(11) NOT NULL,
  `theme` enum('MEDIEVAL_CASTLE','ABANDONED_SPACESHIP','HAUNTED_MANSION','JUNGLE_ADVENTURE','MURDER_MYSTERY') NOT NULL,
  `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `decorationitem`
--

CREATE TABLE `decorationitem` (
  `id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `material` enum('WOOD','METAL','PLASTIC','PAPER','CRYSTAL') NOT NULL,
  `theme` enum('MEDIEVAL_CASTLE','ABANDONED_SPACESHIP','HAUNTED_MANSION','JUNGLE_ADVENTURE','MURDER_MYSTERY') NOT NULL,
  `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enigma`
--

CREATE TABLE `enigma` (
  `id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `theme` enum('MEDIEVAL_CASTLE','ABANDONED_SPACESHIP','HAUNTED_MANSION','JUNGLE_ADVENTURE','MURDER_MYSTERY') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `scape_room_id` int(11) NOT NULL,
  `difficult_level` enum('EASY','MEDIUM','HARD') NOT NULL,
  `theme` enum('MEDIEVAL_CASTLE','ABANDONED_SPACESHIP','HAUNTED_MANSION','JUNGLE_ADVENTURE','MURDER_MYSTERY') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `scaperoom`
--

CREATE TABLE `scaperoom` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clue`
--
ALTER TABLE `clue`
  ADD PRIMARY KEY (`id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `enigma_id` (`enigma_id`);

--
-- Indices de la tabla `decorationitem`
--
ALTER TABLE `decorationitem`
  ADD PRIMARY KEY (`id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indices de la tabla `enigma`
--
ALTER TABLE `enigma`
  ADD PRIMARY KEY (`id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indices de la tabla `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indices de la tabla `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `scape_room_id` (`scape_room_id`);

--
-- Indices de la tabla `scaperoom`
--
ALTER TABLE `scaperoom`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clue`
--
ALTER TABLE `clue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `decorationitem`
--
ALTER TABLE `decorationitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `enigma`
--
ALTER TABLE `enigma`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `scaperoom`
--
ALTER TABLE `scaperoom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clue`
--
ALTER TABLE `clue`
  ADD CONSTRAINT `clue_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  ADD CONSTRAINT `clue_ibfk_2` FOREIGN KEY (`enigma_id`) REFERENCES `enigma` (`id`);

--
-- Filtros para la tabla `decorationitem`
--
ALTER TABLE `decorationitem`
  ADD CONSTRAINT `decorationitem_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`);

--
-- Filtros para la tabla `enigma`
--
ALTER TABLE `enigma`
  ADD CONSTRAINT `enigma_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`);

--
-- Filtros para la tabla `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`);

--
-- Filtros para la tabla `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`scape_room_id`) REFERENCES `scaperoom` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
