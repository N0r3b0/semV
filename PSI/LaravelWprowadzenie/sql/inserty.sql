insert into laravel_ksiazki.kategoria(opis) values ('WWW');
insert into laravel_ksiazki.kategoria(opis) values ('HTML');
insert into laravel_ksiazki.kategoria(opis) values ('JavaScript');
insert into laravel_ksiazki.kategoria(opis) values ('Java');

insert into laravel_ksiazki.wydawnictwo(nazwa,adres) values ('Helion','Gliwice, Polska');
insert into laravel_ksiazki.wydawnictwo(nazwa,adres) values ('PWN','Warszawa, Polska');
insert into laravel_ksiazki.wydawnictwo(nazwa,adres) values ('OREILLY','Boston, USA');

insert into laravel_ksiazki.ksiazka(tytul,idwyd,idkat) values ('Java. Podstawy',1,4);
insert into laravel_ksiazki.ksiazka(tytul,idwyd,idkat) values ('Projektownie serwisów WWW. Standardy sieciowe',1,1);
insert into laravel_ksiazki.ksiazka(tytul,idwyd,idkat) values ('Zrozumieć JavaScript',1,3);
insert into laravel_ksiazki.ksiazka(tytul,idwyd,idkat) values ('Head first Java',3,4);
insert into laravel_ksiazki.ksiazka(tytul,idwyd,idkat) values ('HTML5. Komponenty',2,2);
insert into laravel_ksiazki.ksiazka(tytul,idwyd,idkat) values ('Wydajny JavaScript',2,3);
