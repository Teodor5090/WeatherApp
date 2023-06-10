--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-06-10 18:52:59

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16399)
-- Name: Locations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Locations" (
    id_location integer NOT NULL,
    name text,
    latitude text,
    longtitude text
);


ALTER TABLE public."Locations" OWNER TO postgres;

--
-- TOC entry 3316 (class 0 OID 16399)
-- Dependencies: 214
-- Data for Name: Locations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Locations" (id_location, name, latitude, longtitude) FROM stdin;
1	Bitola	41.0297	21.3292
2	Skopje	41.9981	21.4254
3	Thessaloniki	40.6401	22.9444
4	Athens	37.9838	23.7275
5	Rome	41.9028	12.4964
\.


--
-- TOC entry 3173 (class 2606 OID 16405)
-- Name: Locations Locations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Locations"
    ADD CONSTRAINT "Locations_pkey" PRIMARY KEY (id_location);


-- Completed on 2023-06-10 18:53:00

--
-- PostgreSQL database dump complete
--

