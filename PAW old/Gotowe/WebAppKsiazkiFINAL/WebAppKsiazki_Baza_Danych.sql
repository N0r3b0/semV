CREATE SCHEMA ksiazki;

CREATE TABLE ksiazki.kategoria (
    idk integer NOT NULL,
    opis text
);
CREATE SEQUENCE ksiazki.kategoria_idk_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE ksiazki.kategoria_idk_seq OWNED BY ksiazki.kategoria.idk;

CREATE TABLE ksiazki.ksiazka (
    idk integer NOT NULL,
    tytul text,
    rok_wydania date,
    idwyd integer,
    idkat integer
);

CREATE SEQUENCE ksiazki.ksiazka_idk_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE ksiazki.ksiazka_idk_seq OWNED BY ksiazki.ksiazka.idk;
CREATE TABLE ksiazki.wydawnictwo (
    idwyd integer NOT NULL,
    nazwa text,
    miasto text,
    panstwo text
);

CREATE SEQUENCE ksiazki.wydawnictwo_idw_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE ksiazki.wydawnictwo_idw_seq OWNED BY ksiazki.wydawnictwo.idwyd;

ALTER TABLE ONLY ksiazki.kategoria ALTER COLUMN idk SET DEFAULT nextval('ksiazki.kategoria_idk_seq'::regclass);

ALTER TABLE ONLY ksiazki.ksiazka ALTER COLUMN idk SET DEFAULT nextval('ksiazki.ksiazka_idk_seq'::regclass);


ALTER TABLE ONLY ksiazki.wydawnictwo ALTER COLUMN idwyd SET DEFAULT nextval('ksiazki.wydawnictwo_idw_seq'::regclass);


ALTER TABLE ONLY ksiazki.kategoria
   
ADD CONSTRAINT kategoria_pkey PRIMARY KEY (idk);
	
ALTER TABLE ONLY ksiazki.ksiazka
ADD CONSTRAINT ksiazka_pkey PRIMARY KEY (idk);
	
ALTER TABLE ONLY ksiazki.wydawnictwo
ADD CONSTRAINT wydawnictwo_pkey PRIMARY KEY (idwyd);

ALTER TABLE ONLY ksiazki.ksiazka
    ADD CONSTRAINT ksiazka_idkat_fkey FOREIGN KEY (idkat) REFERENCES ksiazki.kategoria(idk) ON DELETE CASCADE;
ALTER TABLE ONLY ksiazki.ksiazka
    ADD CONSTRAINT ksiazka_idwyd_fkey FOREIGN KEY (idwyd) REFERENCES ksiazki.wydawnictwo(idwyd) ON DELETE CASCADE;

INSERT INTO ksiazki.kategoria (opis) VALUES ('Fikcja');
INSERT INTO ksiazki.kategoria (opis) VALUES ('Literatura faktu');

-- Przykładowe dane dla tabeli ksiazki.wydawnictwo
INSERT INTO ksiazki.wydawnictwo (nazwa, miasto, panstwo) VALUES ('Wydawca A', 'Miasto A', 'Kraj A');
INSERT INTO ksiazki.wydawnictwo (nazwa, miasto, panstwo) VALUES ('Wydawca B', 'Miasto B', 'Kraj B');

-- Przykładowe dane dla tabeli ksiazki.ksiazka
INSERT INTO ksiazki.ksiazka (tytul, rok_wydania, idwyd, idkat) VALUES ('Książka 1', '2022-01-01', 1, 1);
INSERT INTO ksiazki.ksiazka (tytul, rok_wydania, idwyd, idkat) VALUES ('Książka 2', '2021-05-15', 2, 2);