--
-- PostgreSQL database cluster dump
--

-- Started on 2017-05-17 21:28:16

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE myyoutube;
ALTER ROLE myyoutube WITH NOSUPERUSER INHERIT NOCREATEROLE CREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'md50e3851061cd07560b9d72d95b308d792';
COMMENT ON ROLE myyoutube IS 'user for my test project';
CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md504c4ca51e5d2bd1f44263fb493e1a453';
CREATE ROLE timetaskmanager;
ALTER ROLE timetaskmanager WITH NOSUPERUSER INHERIT NOCREATEROLE CREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'md5787453154cabb3c429a5bf62bf7a457f';
COMMENT ON ROLE timetaskmanager IS 'user for mainacad project';


--
-- Role memberships
--

GRANT postgres TO myyoutube WITH ADMIN OPTION GRANTED BY postgres;




--
-- Database creation
--

CREATE DATABASE myyoutube WITH TEMPLATE = template0 OWNER = myyoutube;
REVOKE ALL ON DATABASE template1 FROM PUBLIC;
REVOKE ALL ON DATABASE template1 FROM postgres;
GRANT ALL ON DATABASE template1 TO postgres;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;
CREATE DATABASE timetaskmanager WITH TEMPLATE = template0 OWNER = timetaskmanager;


\connect myyoutube

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.6.2

-- Started on 2017-05-17 21:28:17

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
-- TOC entry 2143 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 184 (class 1259 OID 16482)
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: myyoutube
--

CREATE TABLE databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE databasechangelog OWNER TO myyoutube;

--
-- TOC entry 183 (class 1259 OID 16477)
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: myyoutube
--

CREATE TABLE databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE databasechangeloglock OWNER TO myyoutube;

--
-- TOC entry 186 (class 1259 OID 16490)
-- Name: people; Type: TABLE; Schema: public; Owner: myyoutube
--

CREATE TABLE people (
    id bigint NOT NULL,
    fullname character varying(255) NOT NULL,
    jobtitle character varying(255)
);


ALTER TABLE people OWNER TO myyoutube;

--
-- TOC entry 185 (class 1259 OID 16488)
-- Name: people_id_seq; Type: SEQUENCE; Schema: public; Owner: myyoutube
--

CREATE SEQUENCE people_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE people_id_seq OWNER TO myyoutube;

--
-- TOC entry 2144 (class 0 OID 0)
-- Dependencies: 185
-- Name: people_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: myyoutube
--

ALTER SEQUENCE people_id_seq OWNED BY people.id;


--
-- TOC entry 181 (class 1259 OID 16457)
-- Name: users; Type: TABLE; Schema: public; Owner: myyoutube
--

CREATE TABLE users (
    user_id integer NOT NULL,
    user_login character varying(30) NOT NULL,
    user_password character varying(30) NOT NULL,
    user_name character varying(100),
    comment character varying(255),
    user_email character varying(50)
);


ALTER TABLE users OWNER TO myyoutube;

--
-- TOC entry 182 (class 1259 OID 16464)
-- Name: users_video; Type: TABLE; Schema: public; Owner: myyoutube
--

CREATE TABLE users_video (
    video_id integer NOT NULL,
    user_id integer NOT NULL,
    video_head character varying(100) NOT NULL,
    video_description character varying(255),
    video_size integer,
    video_host character varying(255) NOT NULL,
    video_path_to character varying(255) NOT NULL,
    video_filename character varying(255) NOT NULL
);


ALTER TABLE users_video OWNER TO myyoutube;

--
-- TOC entry 2004 (class 2604 OID 16493)
-- Name: people id; Type: DEFAULT; Schema: public; Owner: myyoutube
--

ALTER TABLE ONLY people ALTER COLUMN id SET DEFAULT nextval('people_id_seq'::regclass);


--
-- TOC entry 2133 (class 0 OID 16482)
-- Dependencies: 184
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: myyoutube
--

COPY databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
1	codahale	migrations.xml	2017-05-09 00:48:43.70719	1	EXECUTED	7:466c815ef370d0ef6b1526a636507dab	createTable tableName=people		\N	3.5.3	\N	\N	4280123485
\.


--
-- TOC entry 2132 (class 0 OID 16477)
-- Dependencies: 183
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: myyoutube
--

COPY databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- TOC entry 2135 (class 0 OID 16490)
-- Dependencies: 186
-- Data for Name: people; Type: TABLE DATA; Schema: public; Owner: myyoutube
--

COPY people (id, fullname, jobtitle) FROM stdin;
1	Dmitriy Tkachenko	Developer
2	Andrey Haymin	builder
3	Ruslan Grigorenko	manager
4	Inkognito	Unknown
5	Inkognito	Unknown
\.


--
-- TOC entry 2145 (class 0 OID 0)
-- Dependencies: 185
-- Name: people_id_seq; Type: SEQUENCE SET; Schema: public; Owner: myyoutube
--

SELECT pg_catalog.setval('people_id_seq', 5, true);


--
-- TOC entry 2130 (class 0 OID 16457)
-- Dependencies: 181
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: myyoutube
--

COPY users (user_id, user_login, user_password, user_name, comment, user_email) FROM stdin;
\.


--
-- TOC entry 2131 (class 0 OID 16464)
-- Dependencies: 182
-- Data for Name: users_video; Type: TABLE DATA; Schema: public; Owner: myyoutube
--

COPY users_video (video_id, user_id, video_head, video_description, video_size, video_host, video_path_to, video_filename) FROM stdin;
\.


--
-- TOC entry 2012 (class 2606 OID 16481)
-- Name: databasechangeloglock pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: myyoutube
--

ALTER TABLE ONLY databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- TOC entry 2014 (class 2606 OID 16498)
-- Name: people pk_people; Type: CONSTRAINT; Schema: public; Owner: myyoutube
--

ALTER TABLE ONLY people
    ADD CONSTRAINT pk_people PRIMARY KEY (id);


--
-- TOC entry 2006 (class 2606 OID 16463)
-- Name: users user_login_unique; Type: CONSTRAINT; Schema: public; Owner: myyoutube
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_login_unique UNIQUE (user_login);


--
-- TOC entry 2008 (class 2606 OID 16461)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: myyoutube
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2010 (class 2606 OID 16471)
-- Name: users_video users_video_pkey; Type: CONSTRAINT; Schema: public; Owner: myyoutube
--

ALTER TABLE ONLY users_video
    ADD CONSTRAINT users_video_pkey PRIMARY KEY (video_id);


--
-- TOC entry 2015 (class 2606 OID 16472)
-- Name: users_video user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: myyoutube
--

ALTER TABLE ONLY users_video
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;


--
-- TOC entry 2142 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-05-17 21:28:23

--
-- PostgreSQL database dump complete
--

\connect postgres

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.6.2

-- Started on 2017-05-17 21:28:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2098 (class 1262 OID 12379)
-- Dependencies: 2097
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 1 (class 3079 OID 12361)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-05-17 21:28:26

--
-- PostgreSQL database dump complete
--

\connect template1

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.6.2

-- Started on 2017-05-17 21:28:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2098 (class 1262 OID 1)
-- Dependencies: 2097
-- Name: template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- TOC entry 1 (class 3079 OID 12361)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-05-17 21:28:30

--
-- PostgreSQL database dump complete
--

\connect timetaskmanager

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.6.2

-- Started on 2017-05-17 21:28:30

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
-- TOC entry 2130 (class 0 OID 0)
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
    finished character varying(5) NOT NULL,
    comments character varying(255)
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
-- TOC entry 2121 (class 0 OID 16435)
-- Dependencies: 183
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: timetaskmanager
--

COPY tasks (id, userid, taskname, parenttaskid, creationtime, finishtime, suggestedtaskduration, elapsedtaskduration, finished, comments) FROM stdin;
-1	-1	zero task	\N	0	0	0	0	true	\N
16	18	task1	-1	2017-05-06T13:37:33.490	\N	PT240H	PT0S	false	\N
17	18	task2	-1	2017-05-06T13:37:33.558	\N	PT288H	PT0S	false	\N
18	18	task3	17	2017-05-06T13:37:33.559	\N	PT96H	PT0S	false	\N
19	18	task4	17	2017-05-06T13:37:33.561	\N	PT192H	PT0S	false	\N
20	18	task1	-1	2017-05-10T21:09:48.932	\N	PT240H	PT0S	false	\N
21	18	task2	-1	2017-05-10T21:09:48.968	\N	PT288H	PT0S	false	\N
22	18	task3	21	2017-05-10T21:09:48.970	\N	PT96H	PT0S	false	\N
23	18	task4	21	2017-05-10T21:09:48.972	\N	PT192H	PT0S	false	\N
\.


--
-- TOC entry 2131 (class 0 OID 0)
-- Dependencies: 184
-- Name: tasks_seq; Type: SEQUENCE SET; Schema: public; Owner: timetaskmanager
--

SELECT pg_catalog.setval('tasks_seq', 23, true);


--
-- TOC entry 2120 (class 0 OID 16397)
-- Dependencies: 182
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: timetaskmanager
--

COPY users (id, login, password, tasklistversion) FROM stdin;
18	qwerty	qwerty	0
19	Anton	12345	\N
22	User0	Pass0	\N
21	User1	Pass1	\N
23	abc	123	\N
24	User17	Pass17	\N
25	User18	Pass18	\N
26	User19	Pass19	\N
27	User20	Pass20	\N
28	User21	Pass21	\N
29	User22	Pass22	\N
30	User23	Pass23	\N
31	User24	Pass24	\N
32	User25	Pass25	\N
33	User26	Pass26	\N
34	User27	Pass27	\N
35	User28	Pass28	\N
36	User29	Pass29	\N
37	User30	Pass30	\N
38	User31	Pass31	\N
39	User32	Pass32	\N
40	User33	Pass33	\N
41	User34	Pass34	\N
42	User35	Pass35	\N
43	User36	Pass36	\N
44	User37	Pass37	\N
45	User38	Pass38	\N
46	User39	Pass39	\N
47	User40	Pass40	\N
48	User41	Pass41	\N
49	User42	Pass42	\N
50	User43	Pass43	\N
51	User44	Pass44	\N
52	User45	Pass45	\N
53	User46	Pass46	\N
54	User47	Pass47	\N
55	User48	Pass48	\N
56	User49	Pass49	\N
57	User50	Pass50	\N
58	User51	Pass51	\N
59	User52	Pass52	\N
60	John	12345	\N
61	Amadeus	12345	\N
-1	zerouser	12345	0
20	User2	12345	5
\.


--
-- TOC entry 2132 (class 0 OID 0)
-- Dependencies: 181
-- Name: users_seq; Type: SEQUENCE SET; Schema: public; Owner: timetaskmanager
--

SELECT pg_catalog.setval('users_seq', 61, true);


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
-- TOC entry 2129 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-05-17 21:28:35

--
-- PostgreSQL database dump complete
--

-- Completed on 2017-05-17 21:28:35

--
-- PostgreSQL database cluster dump complete
--

