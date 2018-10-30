--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)
-- Dumped by pg_dump version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)

-- Started on 2018-10-30 11:20:49 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 13041)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16394)
-- Name: Foodplace; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Foodplace" (
    id SERIAL NOT NULL,
    name text NOT NULL,
    "coordX" real NOT NULL,
    "coordY" real NOT NULL
);


ALTER TABLE public."Foodplace" OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16402)
-- Name: OpenHour; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."OpenHour" (
    id SERIAL NOT NULL,
    day text NOT NULL,
    "placeId" integer NOT NULL,
    start time without time zone,
    "end" time without time zone
);


ALTER TABLE public."OpenHour" OWNER TO postgres;

--
-- TOC entry 2917 (class 0 OID 16394)
-- Dependencies: 196
-- Data for Name: Foodplace; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Foodplace" (name, "coordX", "coordY") VALUES ('lua', 1.20000005, 2.29999995);
INSERT INTO public."Foodplace" (name, "coordX", "coordY") VALUES ('awiteks', 2.5, 10);


--
-- TOC entry 2918 (class 0 OID 16402)
-- Dependencies: 197
-- Data for Name: OpenHour; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('tuesday', 1, '11:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('wednesday', 1, '12:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('thursday', 1, '13:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('friday', 1, '14:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('saturday', 1, '15:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('sunday', 1, '16:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('monday', 1, '10:00:00', NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('monday', 2, NULL, NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('tuesday', 2, NULL, NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('wednesday', 2, NULL, '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('thursday', 2, '10:00:00', NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('friday', 2, NULL, NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('saturday', 2, '11:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('sunday', 2, '12:00:00', '13:00:00');


--
-- TOC entry 2793 (class 2606 OID 16406)
-- Name: OpenHour OpenHours_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."OpenHour"
    ADD CONSTRAINT "OpenHours_pkey" PRIMARY KEY (id);


--
-- TOC entry 2791 (class 2606 OID 16401)
-- Name: Foodplace eatery_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Foodplace"
    ADD CONSTRAINT eatery_pkey PRIMARY KEY (id);


--
-- TOC entry 2794 (class 1259 OID 16415)
-- Name: fki_placeIdfFk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_placeIdfFk" ON public."OpenHour" USING btree ("placeId");


--
-- TOC entry 2795 (class 2606 OID 16410)
-- Name: OpenHour placeIdfFk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."OpenHour"
    ADD CONSTRAINT "placeIdfFk" FOREIGN KEY ("placeId") REFERENCES public."Foodplace"(id);


-- Completed on 2018-10-30 11:20:49 CET

--
-- PostgreSQL database dump complete
--

