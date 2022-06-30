--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS pjdb;
--
-- Name: pjdb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE pjdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';


ALTER DATABASE pjdb OWNER TO postgres;

\connect pjdb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: pjschema1; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA pjschema1;


ALTER SCHEMA pjschema1 OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: t_mst_employee; Type: TABLE; Schema: pjschema1; Owner: pjusr1
--

CREATE TABLE pjschema1.t_mst_employee (
    id integer,
    name character varying(40),
    sei character varying(40),
    nen integer,
    address character varying(40)
);


ALTER TABLE pjschema1.t_mst_employee OWNER TO pjusr1;

--
-- Name: t_trx_application_log; Type: TABLE; Schema: pjschema1; Owner: pjusr1
--

CREATE TABLE pjschema1.t_trx_application_log (
    insert_timestamp timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    thread_no bigint DEFAULT 0 NOT NULL,
    row_number integer DEFAULT 0 NOT NULL,
    log_type character varying(16) DEFAULT ''::character varying,
    intercept_point character varying(16) DEFAULT ''::character varying,
    user_id character varying(32) DEFAULT ''::character varying,
    session_id character varying(64) DEFAULT ''::character varying,
    process_name character varying(256) DEFAULT ''::character varying,
    process_return_type character varying(32) DEFAULT ''::character varying,
    argument_value character varying(128) DEFAULT ''::character varying,
    message character varying(1024) DEFAULT ''::character varying
);


ALTER TABLE pjschema1.t_trx_application_log OWNER TO pjusr1;

--
-- Name: t_trx_auth; Type: TABLE; Schema: pjschema1; Owner: pjusr1
--

CREATE TABLE pjschema1.t_trx_auth (
    id character varying(32) NOT NULL,
    password character varying(120) NOT NULL,
    authority character varying(32) NOT NULL,
    expiration_start date NOT NULL,
    expiration_end date NOT NULL,
    enabled boolean NOT NULL,
    insert_timestamp timestamp without time zone,
    insert_user character varying(32),
    update_timestamp timestamp without time zone,
    update_user character varying(32)
);


ALTER TABLE pjschema1.t_trx_auth OWNER TO pjusr1;

--
-- Name: t_mst_employee production; Type: CONSTRAINT; Schema: pjschema1; Owner: pjusr1
--

ALTER TABLE ONLY pjschema1.t_mst_employee
    ADD CONSTRAINT production UNIQUE (id);


--
-- Name: t_trx_auth t_trx_auth_pkey; Type: CONSTRAINT; Schema: pjschema1; Owner: pjusr1
--

ALTER TABLE ONLY pjschema1.t_trx_auth
    ADD CONSTRAINT t_trx_auth_pkey PRIMARY KEY (id);


--
-- Name: SCHEMA pjschema1; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA pjschema1 TO pjusr1;


--
-- PostgreSQL database dump complete
--

