CREATE SCHEMA IF NOT EXISTS rekrutacja;

CREATE TABLE rekrutacja.pracownicy (
    idp SERIAL PRIMARY KEY,
    nazwisko VARCHAR(50) NOT NULL,
    imie VARCHAR(25) NOT NULL,
    wiek INTEGER NOT NULL,
    doswiadczenie TEXT NOT NULL,
    zainteresowania TEXT NOT NULL
);
