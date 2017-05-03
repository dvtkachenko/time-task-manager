--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.6.2

-- Started on 2017-05-03 17:13:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2123 (class 1262 OID 16386)
-- Name: timetaskmanager; Type: DATABASE; Schema: -; Owner: timetaskmanager
--

CREATE DATABASE timetaskmanager WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE timetaskmanager OWNER TO timetaskmanager;

\connect timetaskmanager

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12361)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2126 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 186 (class 1255 OID 16455)
-- Name: set_task_id(); Type: FUNCTION; Schema: public; Owner: timetaskmanager
--

CREATE FUNCTION set_task_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN 
 IF NEW.id IS NULL THEN
    NEW.id = nextval('tasks_seq');
 END IF;
 RETURN NEW;
END;
$$;


ALTER FUNCTION public.set_task_id() OWNER TO timetaskmanager;

--
-- TOC entry 185 (class 1255 OID 16433)
-- Name: set_user_id(); Type: FUNCTION; Schema: public; Owner: timetaskmanager
--

CREATE FUNCTION set_user_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN 
 IF NEW.id IS NULL THEN
    NEW.id = nextval('users_seq');
 END IF;
 RETURN NEW;
END;
$$;


ALTER FUNCTION public.set_user_id() OWNER TO timetaskmanager;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 183 (class 1259 OID 16435)
-- Name: tasks; Type: TABLE; Schema: public; Owner: timetaskmanager
--

CREATE TABLE tasks (
    id integer NOT NULL,
    userid integer NOT NULL,
    taskname character varying(255) NOT NULL,
    parenttaskid integer,
    creationtime character varying(255) NOT NULL,
    finishtime character varying(255),
    suggestedtaskduration character varying(255) NOT NULL,
    elapsedtaskduration character varying(255) NOT NULL,
    finished character varying(5) NOT NULL
);


ALTER TABLE tasks OWNER TO timetaskmanager;

--
-- TOC entry 184 (class 1259 OID 16453)
-- Name: tasks_seq; Type: SEQUENCE; Schema: public; Owner: timetaskmanager
--

CREATE SEQUENCE tasks_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tasks_seq OWNER TO timetaskmanager;

--
-- TOC entry 182 (class 1259 OID 16397)
-- Name: users; Type: TABLE; Schema: public; Owner: timetaskmanager
--

CREATE TABLE users (
    id integer NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    tasklistversion integer
);


ALTER TABLE users OWNER TO timetaskmanager;

--
-- TOC entry 181 (class 1259 OID 16395)
-- Name: users_seq; Type: SEQUENCE; Schema: public; Owner: timetaskmanager
--

CREATE SEQUENCE users_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_seq OWNER TO timetaskmanager;

--
-- TOC entry 2000 (class 2606 OID 16442)
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: timetaskmanager
--

ALTER TABLE ONLY tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- TOC entry 1996 (class 2606 OID 16408)
-- Name: users users_LOGIN_key; Type: CONSTRAINT; Schema: public; Owner: timetaskmanager
--

ALTER TABLE ONLY users
    ADD CONSTRAINT "users_LOGIN_key" UNIQUE (login);


--
-- TOC entry 1998 (class 2606 OID 16404)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: timetaskmanager
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2004 (class 2620 OID 16456)
-- Name: tasks bi_tasks; Type: TRIGGER; Schema: public; Owner: timetaskmanager
--

CREATE TRIGGER bi_tasks BEFORE INSERT ON tasks FOR EACH ROW EXECUTE PROCEDURE set_task_id();


--
-- TOC entry 2003 (class 2620 OID 16434)
-- Name: users bi_users; Type: TRIGGER; Schema: public; Owner: timetaskmanager
--

CREATE TRIGGER bi_users BEFORE INSERT ON users FOR EACH ROW EXECUTE PROCEDURE set_user_id();


--
-- TOC entry 2001 (class 2606 OID 16443)
-- Name: tasks tasks_fk; Type: FK CONSTRAINT; Schema: public; Owner: timetaskmanager
--

ALTER TABLE ONLY tasks
    ADD CONSTRAINT tasks_fk FOREIGN KEY (userid) REFERENCES users(id) ON DELETE CASCADE;


--
-- TOC entry 2002 (class 2606 OID 16448)
-- Name: tasks tasks_parent; Type: FK CONSTRAINT; Schema: public; Owner: timetaskmanager
--

ALTER TABLE ONLY tasks
    ADD CONSTRAINT tasks_parent FOREIGN KEY (parenttaskid) REFERENCES tasks(id) ON DELETE CASCADE;


--
-- TOC entry 2125 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-05-03 17:13:25

--
-- PostgreSQL database dump complete
--

