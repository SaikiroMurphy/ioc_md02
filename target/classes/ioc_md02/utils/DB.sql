--
-- PostgreSQL database dump
--

\restrict RTXKUO9sSkU960sf8UlC0Ixawjuxyst8T97nbEfC1MvQtfEKc9sQXUptAgaUXtn

-- Dumped from database version 18.1 (Debian 18.1-1.pgdg13+2)
-- Dumped by pg_dump version 18.1 (Debian 18.1-1.pgdg13+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;


--
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admins (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE public.admins OWNER TO postgres;

--
-- Name: admins_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.admins_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.admins_id_seq OWNER TO postgres;

--
-- Name: admins_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.admins_id_seq OWNED BY public.admins.id;


--
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    phone character varying(20),
    email character varying(100),
    address character varying(255)
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customers_id_seq OWNER TO postgres;

--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- Name: invoice_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice_details (
    id integer NOT NULL,
    invoice_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    unit_price numeric(12,2) NOT NULL
);


ALTER TABLE public.invoice_details OWNER TO postgres;

--
-- Name: invoice_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.invoice_details_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.invoice_details_id_seq OWNER TO postgres;

--
-- Name: invoice_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.invoice_details_id_seq OWNED BY public.invoice_details.id;


--
-- Name: invoices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoices (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    created_at date DEFAULT CURRENT_TIMESTAMP,
    total_amount numeric(12,2) NOT NULL
);


ALTER TABLE public.invoices OWNER TO postgres;

--
-- Name: invoices_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.invoices_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.invoices_id_seq OWNER TO postgres;

--
-- Name: invoices_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.invoices_id_seq OWNED BY public.invoices.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    brand character varying(50) NOT NULL,
    price numeric(12,2) NOT NULL,
    stock integer NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: admins id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admins ALTER COLUMN id SET DEFAULT nextval('public.admins_id_seq'::regclass);


--
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- Name: invoice_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_details ALTER COLUMN id SET DEFAULT nextval('public.invoice_details_id_seq'::regclass);


--
-- Name: invoices id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoices ALTER COLUMN id SET DEFAULT nextval('public.invoices_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admins (id, username, password) FROM stdin;
1	admin	$2a$10$JGPVog5fdHRpa/SRWeJCf.GsRcvcnE49u2ral3p1cBbPenZsTh/56
\.


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customers (id, name, phone, email, address) FROM stdin;
1	Nguyen Van A	0900000001	a@gmail.com	Ha Noi
2	Tran Thi B	0900000002	b@gmail.com	Hai Phong
3	Le Van C	0900000003	c@gmail.com	Da Nang
4	Pham Thi D	0900000004	d@gmail.com	HCM
5	Hoang Van E	0900000005	e@gmail.com	Can Tho
\.


--
-- Data for Name: invoice_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoice_details (id, invoice_id, product_id, quantity, unit_price) FROM stdin;
1	1	1	1	22000000.00
2	1	2	1	28000000.00
3	1	3	2	18000000.00
4	1	4	1	30000000.00
5	1	5	1	15000000.00
6	2	6	1	21000000.00
7	2	7	1	29000000.00
8	2	8	2	19000000.00
9	2	9	1	35000000.00
10	2	10	1	25000000.00
11	3	11	1	17000000.00
12	3	12	2	7000000.00
13	3	13	1	12000000.00
14	3	14	1	11000000.00
15	3	15	1	28000000.00
16	4	16	1	20000000.00
17	4	17	2	9000000.00
18	4	18	1	8000000.00
19	4	19	1	27000000.00
20	4	20	1	8500000.00
21	5	21	1	21000000.00
22	5	22	1	10000000.00
23	5	23	2	6500000.00
24	5	24	1	24000000.00
25	5	25	1	9500000.00
26	6	26	1	14000000.00
27	6	27	2	9000000.00
28	6	28	1	5000000.00
29	6	29	1	7000000.00
30	6	30	1	13000000.00
31	7	1	1	22000000.00
32	7	6	1	21000000.00
33	7	11	1	17000000.00
34	7	16	1	20000000.00
35	7	21	1	21000000.00
36	8	2	1	28000000.00
37	8	7	1	29000000.00
38	8	12	1	7000000.00
39	8	17	1	9000000.00
40	8	22	1	10000000.00
41	9	3	1	18000000.00
42	9	8	1	19000000.00
43	9	13	1	12000000.00
44	9	18	1	8000000.00
45	9	23	1	6500000.00
46	10	4	1	30000000.00
47	10	9	1	35000000.00
48	10	14	1	11000000.00
49	10	19	1	27000000.00
50	10	24	1	24000000.00
\.


--
-- Data for Name: invoices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoices (id, customer_id, created_at, total_amount) FROM stdin;
1	1	2026-03-10	131000000.00
2	1	2026-03-10	148000000.00
3	2	2026-03-10	82000000.00
4	2	2026-03-10	81500000.00
5	3	2026-03-10	77500000.00
6	3	2026-03-10	57000000.00
7	4	2026-03-10	101000000.00
8	4	2026-03-10	83000000.00
9	5	2026-03-10	63500000.00
10	5	2026-03-10	127000000.00
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, name, brand, price, stock) FROM stdin;
1	iPhone 15	Apple	22000000.00	50
2	iPhone 15 Pro	Apple	28000000.00	40
3	iPhone 14	Apple	18000000.00	60
4	iPhone 14 Pro Max	Apple	30000000.00	35
5	iPhone 13	Apple	15000000.00	70
6	Galaxy S24	Samsung	21000000.00	45
7	Galaxy S24 Ultra	Samsung	29000000.00	30
8	Galaxy S23	Samsung	19000000.00	50
9	Galaxy Z Fold 5	Samsung	35000000.00	20
10	Galaxy Z Flip 5	Samsung	25000000.00	25
11	Xiaomi 14	Xiaomi	17000000.00	60
12	Redmi Note 13	Xiaomi	7000000.00	100
13	Xiaomi 13T	Xiaomi	12000000.00	75
14	Redmi K60	Xiaomi	11000000.00	80
15	Xiaomi Mix Fold 3	Xiaomi	28000000.00	15
16	Oppo Find X6	Oppo	20000000.00	40
17	Oppo Reno 11	Oppo	9000000.00	70
18	Oppo A98	Oppo	8000000.00	90
19	Oppo Find N3	Oppo	27000000.00	20
20	Oppo Reno 10	Oppo	8500000.00	85
21	Vivo X100	Vivo	21000000.00	30
22	Vivo V29	Vivo	10000000.00	65
23	Vivo Y36	Vivo	6500000.00	95
24	Vivo X90 Pro	Vivo	24000000.00	25
25	Vivo V27	Vivo	9500000.00	60
26	Realme GT5	Realme	14000000.00	50
27	Realme 11 Pro+	Realme	9000000.00	75
28	Realme C55	Realme	5000000.00	120
29	Realme Narzo 60	Realme	7000000.00	85
30	Realme GT Neo 5	Realme	13000000.00	5
\.


--
-- Name: admins_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.admins_id_seq', 1, true);


--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 9, true);


--
-- Name: invoice_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.invoice_details_id_seq', 53, true);


--
-- Name: invoices_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.invoices_id_seq', 13, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 33, true);


--
-- Name: admins admins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (id);


--
-- Name: admins admins_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_username_key UNIQUE (username);


--
-- Name: customers customers_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_email_key UNIQUE (email);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: invoice_details invoice_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_details
    ADD CONSTRAINT invoice_details_pkey PRIMARY KEY (id);


--
-- Name: invoices invoices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: invoice_details invoice_details_invoice_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_details
    ADD CONSTRAINT invoice_details_invoice_id_fkey FOREIGN KEY (invoice_id) REFERENCES public.invoices(id);


--
-- Name: invoice_details invoice_details_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_details
    ADD CONSTRAINT invoice_details_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: invoices invoices_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- PostgreSQL database dump complete
--

\unrestrict RTXKUO9sSkU960sf8UlC0Ixawjuxyst8T97nbEfC1MvQtfEKc9sQXUptAgaUXtn

