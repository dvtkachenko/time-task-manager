-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- ����: localhost
-- ����� ��������: ��� 05 2017 �., 22:13
-- ������ �������: 5.7.18-0ubuntu0.16.04.1
-- ������ PHP: 7.0.15-0ubuntu0.16.04.4

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- ���� ������: `timetaskmanager`
--

CREATE DATABASE IF NOT EXISTS `timetaskmanager` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `timetaskmanager`;

-- --------------------------------------------------------

--
-- ��������� ������� `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `taskname` varchar(255) NOT NULL,
  `parenttaskid` int(11) DEFAULT NULL,
  `creationtime` varchar(255) NOT NULL,
  `finishtime` varchar(255) DEFAULT NULL,
  `suggestedtaskduration` varchar(255) NOT NULL,
  `elapsedtaskduration` varchar(255) NOT NULL,
  `finished` varchar(5) NOT NULL,
  `comments` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- ���� ������ ������� `tasks`
--

INSERT INTO `tasks` (`id`, `userid`, `taskname`, `parenttaskid`, `creationtime`, `finishtime`, `suggestedtaskduration`, `elapsedtaskduration`, `finished`,'comments') VALUES
(-1, -1, 'zero task', NULL, '0', '0', '0', '0', 'true', NULL);

-- --------------------------------------------------------

--
-- ��������� ������� `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tasklistversion` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- ���� ������ ������� `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `tasklistversion`) VALUES
(-1, 'zerouser', 'zerouser', '0');

--
-- ������� ���������� ������
--

--
-- ������� ������� `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userid` (`userid`),
  ADD KEY `parenttaskid` (`parenttaskid`);

--
-- ������� ������� `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_login_key` (`login`);

--
-- AUTO_INCREMENT ��� ���������� ������
--

--
-- AUTO_INCREMENT ��� ������� `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT ��� ������� `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- ����������� �������� ����� ����������� ������
--

--
-- ����������� �������� ����� ������� `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `tasks_ibfk_2` FOREIGN KEY (`parenttaskid`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
SET FOREIGN_KEY_CHECKS=1;
COMMIT;
