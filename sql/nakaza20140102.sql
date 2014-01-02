--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: nakaza_label_ids; Type: SEQUENCE; Schema: public; Owner: pia
--

CREATE SEQUENCE nakaza_label_ids
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nakaza_label_ids OWNER TO pia;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: nakaza_label; Type: TABLE; Schema: public; Owner: pia; Tablespace: 
--

CREATE TABLE nakaza_label (
    id integer DEFAULT nextval('nakaza_label_ids'::regclass) NOT NULL,
    name text NOT NULL,
    description text
);


ALTER TABLE public.nakaza_label OWNER TO pia;

--
-- Name: nakaza_participant_ids; Type: SEQUENCE; Schema: public; Owner: pia
--

CREATE SEQUENCE nakaza_participant_ids
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nakaza_participant_ids OWNER TO pia;

--
-- Name: nakaza_participant; Type: TABLE; Schema: public; Owner: pia; Tablespace: 
--

CREATE TABLE nakaza_participant (
    id integer DEFAULT nextval('nakaza_participant_ids'::regclass) NOT NULL,
    group_id text,
    description_private text,
    story integer NOT NULL,
    id_user integer,
    description_public text,
    name text
);


ALTER TABLE public.nakaza_participant OWNER TO pia;

--
-- Name: nakaza_participant_has_label; Type: TABLE; Schema: public; Owner: pia; Tablespace: 
--

CREATE TABLE nakaza_participant_has_label (
    id_participant integer NOT NULL,
    id_label integer NOT NULL
);


ALTER TABLE public.nakaza_participant_has_label OWNER TO pia;

--
-- Name: nakaza_story_ids; Type: SEQUENCE; Schema: public; Owner: pia
--

CREATE SEQUENCE nakaza_story_ids
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nakaza_story_ids OWNER TO pia;

--
-- Name: nakaza_story; Type: TABLE; Schema: public; Owner: pia; Tablespace: 
--

CREATE TABLE nakaza_story (
    id integer DEFAULT nextval('nakaza_story_ids'::regclass) NOT NULL,
    name text,
    description_public text,
    description_private text,
    points integer,
    state boolean,
    created_by integer
);


ALTER TABLE public.nakaza_story OWNER TO pia;

--
-- Name: nakaza_story_has_label; Type: TABLE; Schema: public; Owner: pia; Tablespace: 
--

CREATE TABLE nakaza_story_has_label (
    id_story integer NOT NULL,
    id_label integer NOT NULL
);


ALTER TABLE public.nakaza_story_has_label OWNER TO pia;

--
-- Name: nakaza_user_ids; Type: SEQUENCE; Schema: public; Owner: pia
--

CREATE SEQUENCE nakaza_user_ids
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nakaza_user_ids OWNER TO pia;

--
-- Name: nakaza_user; Type: TABLE; Schema: public; Owner: pia; Tablespace: 
--

CREATE TABLE nakaza_user (
    id integer DEFAULT nextval('nakaza_user_ids'::regclass) NOT NULL,
    email text NOT NULL,
    name text NOT NULL,
    date_of_birth timestamp without time zone NOT NULL,
    password text NOT NULL,
    image text,
    character_name text,
    character_age integer,
    character_group text,
    character_description text,
    role integer,
    remaining_points integer
);


ALTER TABLE public.nakaza_user OWNER TO pia;

--
-- Name: nakaza_user_has_story; Type: TABLE; Schema: public; Owner: pia; Tablespace: 
--

CREATE TABLE nakaza_user_has_story (
    id_user integer NOT NULL,
    id_story integer NOT NULL
);


ALTER TABLE public.nakaza_user_has_story OWNER TO pia;

--
-- Data for Name: nakaza_label; Type: TABLE DATA; Schema: public; Owner: pia
--

INSERT INTO nakaza_label VALUES (8, 'Akční', 'Jedná se o štítek pro roli, která má být silně akční, to znamená hodně střílení nebo pohybu.');
INSERT INTO nakaza_label VALUES (9, 'Pátrání', 'Jedná se o štítek, kde bude daná osoba pátrat po věci nebo osobě.');
INSERT INTO nakaza_label VALUES (10, 'Dramatický', 'Osoba, které se to týká bude řešit drama z minulosti.');
INSERT INTO nakaza_label VALUES (11, 'Smrt', 'Osoba, které se to týká pravděpodobně v průběhu příběhu zemře.');
INSERT INTO nakaza_label VALUES (12, 'Vrah', 'Osoba, která zabije nebo zabila..');
INSERT INTO nakaza_label VALUES (13, 'Ztracený', 'Osoba, která byla ztracena.');
INSERT INTO nakaza_label VALUES (14, 'Informátor', 'Osoba, která má informace ohledně toho co se stalo.');


--
-- Name: nakaza_label_ids; Type: SEQUENCE SET; Schema: public; Owner: pia
--

SELECT pg_catalog.setval('nakaza_label_ids', 14, true);


--
-- Data for Name: nakaza_participant; Type: TABLE DATA; Schema: public; Owner: pia
--

INSERT INTO nakaza_participant VALUES (17, '1', '<p>Zamiloval se do tebe voj&aacute;k a d&iacute;ky jeho ochotě ti pomoci zat&iacute;m přež&iacute;v&aacute;&scaron;, zač&iacute;n&aacute;&scaron; na sobě ale pozorovat n&aacute;znaky n&aacute;kazy. Je nějak&aacute; cesta?</p>', 15, NULL, '<p>D&iacute;vka, do kter&eacute; se zamiloval</p>', 'Dívka');
INSERT INTO nakaza_participant VALUES (16, '2', '<p>Zamiloval ses do jedn&eacute; z přeživ&scaron;&iacute;ch. Nen&iacute; ti &uacute;plně jasn&eacute;, jak se ti to stalo, ale přece se stalo.&nbsp;</p>', 15, 15, '<p>Voj&aacute;k, kter&eacute;ho se i za těchto okolnost&iacute; dotkne l&aacute;ska.</p>', 'Voják');
INSERT INTO nakaza_participant VALUES (15, '1', '<p>Ztracen&aacute; dcera / syn. Tvůj otec se tě pokusil sežrat, podařilo se ti v&scaron;ak d&iacute;kybohu ut&eacute;ci.&nbsp;</p>', 14, NULL, '<p>Postava, kter&aacute; v&iacute; jak to bylo s dcerou.&nbsp;</p>', 'Vědoucí');
INSERT INTO nakaza_participant VALUES (14, '0', '<p>Pot&eacute; co ses proměnil, byl jsi na cestě domů, pamatuje&scaron; si je&scaron;tě dom&aacute;c&iacute; plot, v domě byla i tvoje dcera, nev&iacute;&scaron; co se s n&iacute; stalo. Douf&aacute;&scaron;, že to přežila.</p>', 14, 14, '<p>Muž, kter&yacute; p&aacute;tr&aacute; po ztracen&eacute; dceře</p>', 'Muž hledající');


--
-- Data for Name: nakaza_participant_has_label; Type: TABLE DATA; Schema: public; Owner: pia
--



--
-- Name: nakaza_participant_ids; Type: SEQUENCE SET; Schema: public; Owner: pia
--

SELECT pg_catalog.setval('nakaza_participant_ids', 17, true);


--
-- Data for Name: nakaza_story; Type: TABLE DATA; Schema: public; Owner: pia
--

INSERT INTO nakaza_story VALUES (15, 'Voják a láska', '<p>I zelen&yacute; mozek se může zamilovat.&nbsp;</p>', '<p>Voj&aacute;k se zamiluje do jedn&eacute; z přeživ&scaron;&iacute;ch.&nbsp;</p>', 5, true, 15);
INSERT INTO nakaza_story VALUES (14, 'Muž hledá dceru', '<p>Zombie p&aacute;tr&aacute; po tom co se stalo s jeho / jej&iacute; dcerou</p>', '<p>Muž , kter&yacute; možn&aacute; sežral svoji dceru, chce zjistit zda ji sežral a pokud ne, pak co se s n&iacute; stalo a zda je&scaron;tě žije.&nbsp;</p>', 10, true, 14);


--
-- Data for Name: nakaza_story_has_label; Type: TABLE DATA; Schema: public; Owner: pia
--

INSERT INTO nakaza_story_has_label VALUES (15, 10);
INSERT INTO nakaza_story_has_label VALUES (14, 8);
INSERT INTO nakaza_story_has_label VALUES (14, 10);


--
-- Name: nakaza_story_ids; Type: SEQUENCE SET; Schema: public; Owner: pia
--

SELECT pg_catalog.setval('nakaza_story_ids', 15, true);


--
-- Data for Name: nakaza_user; Type: TABLE DATA; Schema: public; Owner: pia
--

INSERT INTO nakaza_user VALUES (12, 'ilien22@seznam.cz', '1', '1996-01-12 00:03:00', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'img/question_icon.png', 'Test', 55, '0', '<p>Abcd</p>', 1, 20);
INSERT INTO nakaza_user VALUES (13, 'finmer@pilirionos.org', 'Lukáš Beran', '1986-01-29 00:08:00', 'dee15c78a98729ac7d631b83f6ff4ff2cfb61c3d', 'img/question_icon.png', NULL, NULL, NULL, NULL, 1, 20);
INSERT INTO nakaza_user VALUES (16, 'lenka.za@seznam.cz', 'Lenka Zalová', '1956-01-10 00:05:00', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea', 'img/question_icon.png', 'Tonička Bolavá', 17, '1', '<p>Přežila v&scaron;echny možn&eacute; strasti, kter&eacute; na n&iacute; čekali když se jej&iacute; rodiče stali zombie. Je takřka neuvěřiteln&eacute;, že zvl&aacute;dla přež&iacute;t, ale d&iacute;kybohu měla nějak&eacute; množstv&iacute; pomoci.&nbsp;</p>', 1, 20);
INSERT INTO nakaza_user VALUES (15, 'jakub.balhar@centrum.cz', 'Tomáš Novák', '2000-01-12 00:05:00', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea', 'img/question_icon.png', 'Jiří Petrů', 40, '2', '<p>Voj&aacute;k, cel&yacute; život voj&aacute;k a nic než voj&aacute;k. Absolutně typick&aacute; zelen&aacute; hlava. Jedin&eacute; co opravdu um&iacute;, je poslouchat rozkazy. Rozkazy, rozkazy a rozkazy jsou jeho život.</p>', 1, 20);
INSERT INTO nakaza_user VALUES (14, 'balhar.jakub@gmail.com', 'Jakub Balhar', '1988-01-14 00:04:00', 'd15e7cd9ece6c245eb496943dec911653ed85376', 'img/question_icon.png', 'Jan Novotný', 25, '0', '<p>Odhodlan&yacute; pracovn&iacute;k tov&aacute;rny. Tedy vlastně dř&iacute;ve pracovn&iacute;k tov&aacute;rny, aktu&aacute;lně sp&iacute;&scaron;e odhodlan&yacute; zombie, kter&yacute; se snaž&iacute; dostat k co nejv&iacute;ce j&iacute;dlu. V tom co dělal se vždy snažil b&yacute;t co nejlep&scaron;&iacute;.</p><p>V tomto př&iacute;padě mu pak jde o to, aby z&aacute;sobov&aacute;n&iacute; poř&aacute;dně &scaron;lapalo.&nbsp;</p>', 3, 20);


--
-- Data for Name: nakaza_user_has_story; Type: TABLE DATA; Schema: public; Owner: pia
--

INSERT INTO nakaza_user_has_story VALUES (14, 14);
INSERT INTO nakaza_user_has_story VALUES (15, 15);


--
-- Name: nakaza_user_ids; Type: SEQUENCE SET; Schema: public; Owner: pia
--

SELECT pg_catalog.setval('nakaza_user_ids', 16, true);


--
-- Name: nakaza_label_pkey; Type: CONSTRAINT; Schema: public; Owner: pia; Tablespace: 
--

ALTER TABLE ONLY nakaza_label
    ADD CONSTRAINT nakaza_label_pkey PRIMARY KEY (id);


--
-- Name: nakaza_participant_has_label_pkey; Type: CONSTRAINT; Schema: public; Owner: pia; Tablespace: 
--

ALTER TABLE ONLY nakaza_participant_has_label
    ADD CONSTRAINT nakaza_participant_has_label_pkey PRIMARY KEY (id_participant, id_label);


--
-- Name: nakaza_participant_pkey; Type: CONSTRAINT; Schema: public; Owner: pia; Tablespace: 
--

ALTER TABLE ONLY nakaza_participant
    ADD CONSTRAINT nakaza_participant_pkey PRIMARY KEY (id);


--
-- Name: nakaza_story_has_label_pkey; Type: CONSTRAINT; Schema: public; Owner: pia; Tablespace: 
--

ALTER TABLE ONLY nakaza_story_has_label
    ADD CONSTRAINT nakaza_story_has_label_pkey PRIMARY KEY (id_story, id_label);


--
-- Name: nakaza_story_pkey; Type: CONSTRAINT; Schema: public; Owner: pia; Tablespace: 
--

ALTER TABLE ONLY nakaza_story
    ADD CONSTRAINT nakaza_story_pkey PRIMARY KEY (id);


--
-- Name: nakaza_user_has_story_pkey; Type: CONSTRAINT; Schema: public; Owner: pia; Tablespace: 
--

ALTER TABLE ONLY nakaza_user_has_story
    ADD CONSTRAINT nakaza_user_has_story_pkey PRIMARY KEY (id_story, id_user);


--
-- Name: nakaza_user_pkey; Type: CONSTRAINT; Schema: public; Owner: pia; Tablespace: 
--

ALTER TABLE ONLY nakaza_user
    ADD CONSTRAINT nakaza_user_pkey PRIMARY KEY (id);


--
-- Name: nakaza_participant_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: pia
--

ALTER TABLE ONLY nakaza_participant
    ADD CONSTRAINT nakaza_participant_user_fk FOREIGN KEY (id_user) REFERENCES nakaza_user(id);


--
-- Name: nakaza_story_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: pia
--

ALTER TABLE ONLY nakaza_story
    ADD CONSTRAINT nakaza_story_user_fk FOREIGN KEY (created_by) REFERENCES nakaza_user(id);


--
-- Name: participant_has_label__label_fk; Type: FK CONSTRAINT; Schema: public; Owner: pia
--

ALTER TABLE ONLY nakaza_participant_has_label
    ADD CONSTRAINT participant_has_label__label_fk FOREIGN KEY (id_participant) REFERENCES nakaza_participant(id) MATCH FULL;


--
-- Name: participant_has_label_participant_fk; Type: FK CONSTRAINT; Schema: public; Owner: pia
--

ALTER TABLE ONLY nakaza_participant_has_label
    ADD CONSTRAINT participant_has_label_participant_fk FOREIGN KEY (id_label) REFERENCES nakaza_label(id) MATCH FULL;


--
-- Name: participant_story_fk; Type: FK CONSTRAINT; Schema: public; Owner: pia
--

ALTER TABLE ONLY nakaza_participant
    ADD CONSTRAINT participant_story_fk FOREIGN KEY (story) REFERENCES nakaza_story(id) MATCH FULL;


--
-- Name: story_label_label_fk; Type: FK CONSTRAINT; Schema: public; Owner: pia
--

ALTER TABLE ONLY nakaza_story_has_label
    ADD CONSTRAINT story_label_label_fk FOREIGN KEY (id_label) REFERENCES nakaza_label(id) MATCH FULL;


--
-- Name: story_label_story_fk; Type: FK CONSTRAINT; Schema: public; Owner: pia
--

ALTER TABLE ONLY nakaza_story_has_label
    ADD CONSTRAINT story_label_story_fk FOREIGN KEY (id_story) REFERENCES nakaza_story(id) MATCH FULL;


--
-- Name: story_user_story_fk; Type: FK CONSTRAINT; Schema: public; Owner: pia
--

ALTER TABLE ONLY nakaza_user_has_story
    ADD CONSTRAINT story_user_story_fk FOREIGN KEY (id_story) REFERENCES nakaza_story(id) MATCH FULL;


--
-- Name: story_user_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: pia
--

ALTER TABLE ONLY nakaza_user_has_story
    ADD CONSTRAINT story_user_user_fk FOREIGN KEY (id_user) REFERENCES nakaza_user(id) MATCH FULL;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--


--
-- PostgreSQL database dump complete
--

