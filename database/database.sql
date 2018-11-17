
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;



CREATE TABLE public."Foodplace" (
    id SERIAL NOT NULL,
    name text NOT NULL,
    "coordX" real NOT NULL,
    "coordY" real NOT NULL
);

ALTER TABLE public."Foodplace" OWNER TO postgres;



CREATE TABLE public."OpenHour" (
    id SERIAL NOT NULL,
    day text NOT NULL,
    "placeId" integer NOT NULL,
    "start" time without time zone,
    "end" time without time zone
);

ALTER TABLE public."OpenHour" OWNER TO postgres;



CREATE TABLE public."Vote" (
    id SERIAL NOT NULL,
    "placeId" integer NOT NULL,
    "voteTime" timestamp NOT NULL,
    "open" boolean NOT NULL
);

ALTER TABLE public."Vote" OWNER TO postgres;



INSERT INTO public."Foodplace" (name, "coordX", "coordY") VALUES ('lua', 1.20000005, 2.29999995);
INSERT INTO public."Foodplace" (name, "coordX", "coordY") VALUES ('awiteks', 2.5, 10);

INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('tuesday', 1, '8:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('wednesday', 1, '12:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('thursday', 1, '13:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('friday', 1, '14:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('saturday', 1, '15:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('sunday', 1, '16:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('monday', 1, '08:00:00', NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('monday', 2, NULL, NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('tuesday', 2, NULL, NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('wednesday', 2, NULL, '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('thursday', 2, '10:00:00', NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('friday', 2, NULL, NULL);
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('saturday', 2, '11:00:00', '20:00:00');
INSERT INTO public."OpenHour" (day, "placeId", start, "end") VALUES ('sunday', 2, '12:00:00', '13:00:00');

INSERT INTO public."Vote" ("placeId", "voteTime", "open") VALUES (1, TIMESTAMP '2018-11-13 20:07:00.000', 'TRUE');
INSERT INTO public."Vote" ("placeId", "voteTime", "open") VALUES (1, TIMESTAMP '2018-11-13 19:44:00.000', 'TRUE');
INSERT INTO public."Vote" ("placeId", "voteTime", "open") VALUES (1, TIMESTAMP '2018-11-13 18:50:00.000', 'FALSE');
INSERT INTO public."Vote" ("placeId", "voteTime", "open") VALUES (2, TIMESTAMP '2018-11-13 20:01:00.000', 'TRUE');


ALTER TABLE ONLY public."OpenHour"
    ADD CONSTRAINT "OpenHours_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY public."Foodplace"
    ADD CONSTRAINT "Foodplace_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY public."Vote"
    ADD CONSTRAINT "Vote_pkey" PRIMARY KEY (id);



CREATE INDEX "fki_placeIdfFk" ON public."OpenHour" USING btree ("placeId");



ALTER TABLE ONLY public."OpenHour"
    ADD CONSTRAINT "placeIdfFk" FOREIGN KEY ("placeId") REFERENCES public."Foodplace"(id);


ALTER TABLE ONLY public."Vote"
    ADD CONSTRAINT "placeIdfFK" FOREIGN KEY ("placeId") REFERENCES public."Foodplace"(id);
