CREATE USER timetaskmanager WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  CREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD '12345';
  COMMENT ON ROLE timetaskmanager IS 'user for mainacad project';