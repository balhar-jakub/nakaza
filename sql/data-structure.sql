--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- Name: plpgsql_call_handler(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION plpgsql_call_handler() RETURNS language_handler
    LANGUAGE c
    AS '/usr/lib64/pgsql/plpgsql.so', 'plpgsql_call_handler';


ALTER FUNCTION public.plpgsql_call_handler() OWNER TO postgres;

--
-- Name: plpgsql_validator(oid); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION plpgsql_validator(oid) RETURNS void
    LANGUAGE c
    AS '/usr/lib64/pgsql/plpgsql.so', 'plpgsql_validator';


ALTER FUNCTION public.plpgsql_validator(oid) OWNER TO postgres;

--
-- Name: nakaza_label_ids; Type: SEQUENCE; Schema: public; Owner: phgnyzcb
--

CREATE SEQUENCE nakaza_label_ids
    START WITH 0
    INCREMENT BY 1
    NO MAXVALUE
    MINVALUE 0
    CACHE 1;


ALTER TABLE public.nakaza_label_ids OWNER TO phgnyzcb;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: nakaza_label; Type: TABLE; Schema: public; Owner: phgnyzcb; Tablespace: 
--

CREATE TABLE nakaza_label (
    id integer DEFAULT nextval('nakaza_label_ids'::regclass) NOT NULL,
    name text NOT NULL,
    description text
);


ALTER TABLE public.nakaza_label OWNER TO phgnyzcb;

--
-- Name: nakaza_participant_ids; Type: SEQUENCE; Schema: public; Owner: phgnyzcb
--

CREATE SEQUENCE nakaza_participant_ids
    START WITH 0
    INCREMENT BY 1
    NO MAXVALUE
    MINVALUE 0
    CACHE 1;


ALTER TABLE public.nakaza_participant_ids OWNER TO phgnyzcb;

--
-- Name: nakaza_participant; Type: TABLE; Schema: public; Owner: phgnyzcb; Tablespace: 
--

CREATE TABLE nakaza_participant (
    id integer DEFAULT nextval('nakaza_participant_ids'::regclass) NOT NULL,
    "group" text,
    description_private text,
    story integer NOT NULL
);


ALTER TABLE public.nakaza_participant OWNER TO phgnyzcb;

--
-- Name: nakaza_participant_has_label; Type: TABLE; Schema: public; Owner: phgnyzcb; Tablespace: 
--

CREATE TABLE nakaza_participant_has_label (
    id_participant integer NOT NULL,
    id_label integer NOT NULL
);


ALTER TABLE public.nakaza_participant_has_label OWNER TO phgnyzcb;

--
-- Name: nakaza_story_ids; Type: SEQUENCE; Schema: public; Owner: phgnyzcb
--

CREATE SEQUENCE nakaza_story_ids
    START WITH 0
    INCREMENT BY 1
    NO MAXVALUE
    MINVALUE 0
    CACHE 1;


ALTER TABLE public.nakaza_story_ids OWNER TO phgnyzcb;

--
-- Name: nakaza_story; Type: TABLE; Schema: public; Owner: phgnyzcb; Tablespace: 
--

CREATE TABLE nakaza_story (
    id integer DEFAULT nextval('nakaza_story_ids'::regclass) NOT NULL,
    name text,
    description_public text,
    description_private text
);


ALTER TABLE public.nakaza_story OWNER TO phgnyzcb;

--
-- Name: nakaza_story_has_label; Type: TABLE; Schema: public; Owner: phgnyzcb; Tablespace: 
--

CREATE TABLE nakaza_story_has_label (
    id_story integer NOT NULL,
    id_label integer NOT NULL
);


ALTER TABLE public.nakaza_story_has_label OWNER TO phgnyzcb;

--
-- Name: nakaza_user_ids; Type: SEQUENCE; Schema: public; Owner: phgnyzcb
--

CREATE SEQUENCE nakaza_user_ids
    START WITH 0
    INCREMENT BY 1
    NO MAXVALUE
    MINVALUE 0
    CACHE 1;


ALTER TABLE public.nakaza_user_ids OWNER TO phgnyzcb;

--
-- Name: nakaza_user; Type: TABLE; Schema: public; Owner: phgnyzcb; Tablespace: 
--

CREATE TABLE nakaza_user (
    id integer DEFAULT nextval('nakaza_user_ids'::regclass) NOT NULL,
    email text NOT NULL,
    name text NOT NULL,
    date_of_brith timestamp without time zone NOT NULL,
    password text NOT NULL,
    image text,
    character_name text,
    character_age integer,
    character_group text,
    character_description text
);


ALTER TABLE public.nakaza_user OWNER TO phgnyzcb;

--
-- Name: nakaza_user_has_story; Type: TABLE; Schema: public; Owner: phgnyzcb; Tablespace: 
--

CREATE TABLE nakaza_user_has_story (
    id_user integer NOT NULL,
    id_story integer NOT NULL
);


ALTER TABLE public.nakaza_user_has_story OWNER TO phgnyzcb;

--
-- Name: nakaza_label_pkey; Type: CONSTRAINT; Schema: public; Owner: phgnyzcb; Tablespace: 
--

ALTER TABLE ONLY nakaza_label
    ADD CONSTRAINT nakaza_label_pkey PRIMARY KEY (id);


--
-- Name: nakaza_participant_has_label_pkey; Type: CONSTRAINT; Schema: public; Owner: phgnyzcb; Tablespace: 
--

ALTER TABLE ONLY nakaza_participant_has_label
    ADD CONSTRAINT nakaza_participant_has_label_pkey PRIMARY KEY (id_participant, id_label);


--
-- Name: nakaza_participant_pkey; Type: CONSTRAINT; Schema: public; Owner: phgnyzcb; Tablespace: 
--

ALTER TABLE ONLY nakaza_participant
    ADD CONSTRAINT nakaza_participant_pkey PRIMARY KEY (id);


--
-- Name: nakaza_story_has_label_pkey; Type: CONSTRAINT; Schema: public; Owner: phgnyzcb; Tablespace: 
--

ALTER TABLE ONLY nakaza_story_has_label
    ADD CONSTRAINT nakaza_story_has_label_pkey PRIMARY KEY (id_story, id_label);


--
-- Name: nakaza_story_pkey; Type: CONSTRAINT; Schema: public; Owner: phgnyzcb; Tablespace: 
--

ALTER TABLE ONLY nakaza_story
    ADD CONSTRAINT nakaza_story_pkey PRIMARY KEY (id);


--
-- Name: nakaza_user_has_story_pkey; Type: CONSTRAINT; Schema: public; Owner: phgnyzcb; Tablespace: 
--

ALTER TABLE ONLY nakaza_user_has_story
    ADD CONSTRAINT nakaza_user_has_story_pkey PRIMARY KEY (id_story, id_user);


--
-- Name: nakaza_user_pkey; Type: CONSTRAINT; Schema: public; Owner: phgnyzcb; Tablespace: 
--

ALTER TABLE ONLY nakaza_user
    ADD CONSTRAINT nakaza_user_pkey PRIMARY KEY (id);


--
-- Name: participant_has_label__label_fk; Type: FK CONSTRAINT; Schema: public; Owner: phgnyzcb
--

ALTER TABLE ONLY nakaza_participant_has_label
    ADD CONSTRAINT participant_has_label__label_fk FOREIGN KEY (id_participant) REFERENCES nakaza_participant(id) MATCH FULL;


--
-- Name: participant_has_label_participant_fk; Type: FK CONSTRAINT; Schema: public; Owner: phgnyzcb
--

ALTER TABLE ONLY nakaza_participant_has_label
    ADD CONSTRAINT participant_has_label_participant_fk FOREIGN KEY (id_label) REFERENCES nakaza_label(id) MATCH FULL;


--
-- Name: participant_story_fk; Type: FK CONSTRAINT; Schema: public; Owner: phgnyzcb
--

ALTER TABLE ONLY nakaza_participant
    ADD CONSTRAINT participant_story_fk FOREIGN KEY (story) REFERENCES nakaza_story(id) MATCH FULL;


--
-- Name: story_label_label_fk; Type: FK CONSTRAINT; Schema: public; Owner: phgnyzcb
--

ALTER TABLE ONLY nakaza_story_has_label
    ADD CONSTRAINT story_label_label_fk FOREIGN KEY (id_label) REFERENCES nakaza_label(id) MATCH FULL;


--
-- Name: story_label_story_fk; Type: FK CONSTRAINT; Schema: public; Owner: phgnyzcb
--

ALTER TABLE ONLY nakaza_story_has_label
    ADD CONSTRAINT story_label_story_fk FOREIGN KEY (id_story) REFERENCES nakaza_story(id) MATCH FULL;


--
-- Name: story_user_story_fk; Type: FK CONSTRAINT; Schema: public; Owner: phgnyzcb
--

ALTER TABLE ONLY nakaza_user_has_story
    ADD CONSTRAINT story_user_story_fk FOREIGN KEY (id_story) REFERENCES nakaza_story(id) MATCH FULL;


--
-- Name: story_user_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: phgnyzcb
--

ALTER TABLE ONLY nakaza_user_has_story
    ADD CONSTRAINT story_user_user_fk FOREIGN KEY (id_user) REFERENCES nakaza_user(id) MATCH FULL;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

