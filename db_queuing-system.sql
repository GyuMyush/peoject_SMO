-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Янв 08 2023 г., 20:17
-- Версия сервера: 8.0.30
-- Версия PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `db_queuing-system`
--

-- --------------------------------------------------------

--
-- Структура таблицы `manager`
--

CREATE TABLE `manager` (
  `id_manager` int NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `online_status` tinyint(1) NOT NULL DEFAULT '0',
  `roles` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `manager`
--

INSERT INTO `manager` (`id_manager`, `first_name`, `last_name`, `middle_name`, `login`, `password`, `online_status`, `roles`) VALUES
(1, 'Моисеев', 'Петр', 'Степанович', 'p.moiseev', '698d51a19d8a121ce581499d7b701668', 0, 'manager'),
(2, 'kiosk', 'kiosk', 'kiosk', 'kiosk', 'kiosk01', 0, 'kiosk'),
(3, 'artem', 'gyumyush', 'ivanovich', 'root', 'root', 0, 'root'),
(4, 'manager1', 'manager11', 'manager111', 'manager1', 'manager1', 0, 'manager'),
(5, 'Менеджер', 'Менеджеров', 'Менеджерович', 'manager2', 'manager2', 0, 'manager');

-- --------------------------------------------------------

--
-- Структура таблицы `service`
--

CREATE TABLE `service` (
  `id_service` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `service`
--

INSERT INTO `service` (`id_service`, `title`) VALUES
(1, 'Выдача паспорта'),
(2, 'консультация'),
(3, 'оформление договора'),
(4, 'услуга4'),
(5, 'услуга5'),
(6, 'услуга6'),
(7, 'услуга7'),
(8, 'услуга8'),
(9, 'услуга9');

-- --------------------------------------------------------

--
-- Структура таблицы `talon`
--

CREATE TABLE `talon` (
  `id_talon` int NOT NULL,
  `id_service` int NOT NULL,
  `time_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_manager` int DEFAULT NULL,
  `talon_status` set('waiting','processing','done') NOT NULL DEFAULT 'waiting'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `talon`
--

INSERT INTO `talon` (`id_talon`, `id_service`, `time_create`, `id_manager`, `talon_status`) VALUES
(1, 1, '2022-12-10 22:18:13', 1, ''),
(2, 3, '2023-01-02 21:19:59', NULL, 'waiting'),
(3, 1, '2023-01-05 14:29:31', NULL, 'waiting'),
(4, 4, '2023-01-05 14:29:43', NULL, 'waiting'),
(5, 7, '2023-01-05 14:29:47', NULL, 'waiting'),
(6, 9, '2023-01-05 19:46:29', NULL, 'waiting'),
(7, 7, '2023-01-06 11:59:42', NULL, 'waiting'),
(8, 1, '2023-01-06 12:06:23', NULL, 'waiting'),
(9, 9, '2023-01-06 12:10:02', NULL, 'waiting'),
(10, 2, '2023-01-06 12:13:25', NULL, 'waiting'),
(11, 5, '2023-01-06 12:17:24', NULL, 'waiting'),
(12, 4, '2023-01-06 12:23:02', NULL, 'waiting'),
(13, 7, '2023-01-06 12:23:14', NULL, 'waiting'),
(14, 2, '2023-01-06 12:30:54', NULL, 'waiting'),
(15, 2, '2023-01-06 12:42:17', NULL, 'waiting'),
(16, 3, '2023-01-06 14:34:35', NULL, 'waiting'),
(17, 4, '2023-01-06 14:39:02', NULL, 'waiting'),
(18, 5, '2023-01-06 14:39:12', NULL, 'waiting');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id_manager`),
  ADD KEY `id_manager` (`id_manager`,`first_name`,`last_name`,`middle_name`,`login`,`password`,`online_status`),
  ADD KEY `roles` (`roles`);

--
-- Индексы таблицы `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id_service`),
  ADD KEY `id_service` (`id_service`,`title`);

--
-- Индексы таблицы `talon`
--
ALTER TABLE `talon`
  ADD PRIMARY KEY (`id_talon`),
  ADD KEY `id_talon` (`id_talon`,`id_service`,`time_create`),
  ADD KEY `id_service` (`id_service`),
  ADD KEY `id_manager` (`id_manager`),
  ADD KEY `talon_status` (`talon_status`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `manager`
--
ALTER TABLE `manager`
  MODIFY `id_manager` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `service`
--
ALTER TABLE `service`
  MODIFY `id_service` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT для таблицы `talon`
--
ALTER TABLE `talon`
  MODIFY `id_talon` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `talon`
--
ALTER TABLE `talon`
  ADD CONSTRAINT `talon_ibfk_1` FOREIGN KEY (`id_service`) REFERENCES `service` (`id_service`),
  ADD CONSTRAINT `talon_ibfk_2` FOREIGN KEY (`id_manager`) REFERENCES `manager` (`id_manager`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
