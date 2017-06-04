--
-- Структура таблицы `tasks`
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
);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tasklistversion` varchar(5) DEFAULT NULL
) ;

--
-- This is the data for the correct operation of the program
--

INSERT INTO tasks (id, userid, taskname, parenttaskid, creationtime, finishtime, suggestedtaskduration, elapsedtaskduration, finished,comments) 
VALUES  (-1, -1, 'zero task', NULL, '0', '0', '0', '0', 'true', NULL);

INSERT INTO users (id, login, password, tasklistversion) VALUES
(-1, 'zerouser', 'zerouser', '0');


--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userid` (`userid`),
  ADD KEY `parenttaskid` (`parenttaskid`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_login_key` (`login`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `tasks_ibfk_2` FOREIGN KEY (`parenttaskid`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
SET FOREIGN_KEY_CHECKS=1;


