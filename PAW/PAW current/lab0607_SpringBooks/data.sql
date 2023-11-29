insert into books.publisher(idp,name,address) values (1,'Helion','Gliwice, Polska');
insert into books.publisher(idp,name,address) values (2,'PWN','Warszawa, Polska');
insert into books.publisher(idp,name,address) values (3,'OREILLY','Boston, USA');

insert into books.category(idc,description) values (1,'WWW');
insert into books.category(idc,description) values (2,'HTML');
insert into books.category(idc,description) values (3,'JavaScript');
insert into books.category(idc,description) values (4,'Java');

insert into books.book(title,publisher_idp,category_idc) values ('Java. Podstawy',1,4);
insert into books.book(title,publisher_idp,category_idc) values ('Projektownie serwisów WWW. Standardy sieciowe',1,1);
insert into books.book(title,publisher_idp,category_idc) values ('Zrozumieć JavaScript',1,3);
insert into books.book(title,publisher_idp,category_idc) values ('Head first Java',3,4);
insert into books.book(title,publisher_idp,category_idc) values ('HTML5. Komponenty',2,2);
insert into books.book(title,publisher_idp,category_idc) values ('Wydajny JavaScript',2,3);