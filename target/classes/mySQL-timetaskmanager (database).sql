CREATE USER 'timetaskmanager'@'%' IDENTIFIED WITH mysql_native_password AS '***';GRANT ALL PRIVILEGES ON *.* TO 'timetaskmanager'@'%' 
REQUIRE NONE WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;

CREATE DATABASE IF NOT EXISTS `timetaskmanager`;GRANT ALL PRIVILEGES ON `timetaskmanager`.* TO 'timetaskmanager'@'%';
