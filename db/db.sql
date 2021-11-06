DROP DATABASE book_shop;

CREATE DATABASE book_shop ENCODING 'UTF-8';

\c book_shop;

CREATE TYPE status_enum AS ENUM ('NEW', 'COMPLETED', 'CANCELED');
CREATE TABLE IF NOT EXISTS orders(
id SMALLSERIAL PRIMARY KEY NOT NULL,
customerName VARCHAR(50) NOT NULL,
orderStatus status_enum DEFAULT 'NEW',
execution DATE DEFAULT '0001-01-01'
);

CREATE TYPE availability_enum AS ENUM ('IN_STOCK', 'OUT_OF_STOCK');
CREATE TABLE IF NOT EXISTS books (
id SMALLSERIAL PRIMARY KEY NOT NULL,
title VARCHAR(50) NOT NULL,
author VARCHAR(50) NOT NULL,
price SMALLINT NOT NULL,
availability availability_enum DEFAULT 'IN_STOCK',
publication SMALLINT NOT NULL,
deliveryDate DATE DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS requests(
id SMALLSERIAL PRIMARY KEY NOT NULL,
count SMALLINT DEFAULT 1,
book_id SMALLINT NOT NULL,
book_title VARCHAR(50) NOT NULL,
FOREIGN KEY (book_id) REFERENCES books(id)
);

CREATE TABLE IF NOT EXISTS order_books(
id SMALLSERIAL PRIMARY KEY NOT NULL,
order_id SMALLINT NOT NULL,
book_id SMALLINT NOT NULL,
FOREIGN KEY (order_id) REFERENCES orders(id),
FOREIGN KEY (book_id) REFERENCES books(id)
);

insert into orders (customerName, orderStatus, execution) values ('Cordi Strang', 'COMPLETED', '2021-02-13');
insert into orders (customerName, orderStatus, execution) values ('Hermione MacIlraith', 'COMPLETED', '2021-04-29');
insert into orders (customerName, orderStatus, execution) values ('Alec Mordaunt', 'COMPLETED', '2021-06-11');
insert into orders (customerName, orderStatus, execution) values ('Rhianon Benech', 'COMPLETED', '2021-06-11');
insert into orders (customerName, orderStatus, execution) values ('Brigitte Edmund', 'COMPLETED', '2021-02-21');
insert into orders (customerName, orderStatus, execution) values ('Anny Fenwick', 'NEW', '0001-01-01');

insert into books (title, author, price, availability, publication, deliverydate) values ('Curly Sue', 'Ciro Cinavas', 184, 'OUT_OF_STOCK', 2013, '2021-08-23');
insert into books (title, author, price, availability, publication, deliverydate) values ('Love and Other Catastrophes', 'Alberto Kidgell', 96, 'OUT_OF_STOCK', 1904, '2021-05-13');
insert into books (title, author, price, availability, publication, deliverydate) values ('Aakrosh', 'Bobbie Simmance', 159, 'OUT_OF_STOCK', 1921, '2021-01-20');
insert into books (title, author, price, availability, publication, deliverydate) values ('Assassination Tango', 'Mei Karpinski', 183, 'OUT_OF_STOCK', 1957, '2021-02-24');
insert into books (title, author, price, availability, publication, deliverydate) values ('Yearling', 'Lacee Akid', 77, 'OUT_OF_STOCK', 1905, '2020-10-05');
insert into books (title, author, price, availability, publication, deliverydate) values ('Dragon Ball The Curse Of The Blood Rubies', 'Estelle Huskisson', 76, 'IN_STOCK', 2007, '2021-04-25');
insert into books (title, author, price, availability, publication, deliverydate) values ('The Green Years', 'Purcell Howlings', 158, 'IN_STOCK', 1967, '2021-04-07');
insert into books (title, author, price, availability, publication, deliverydate) values ('Sweet Liberty', 'Melanie Burle', 245, 'IN_STOCK', 1967, '2021-01-01');
insert into books (title, author, price, availability, publication, deliverydate) values ('Nowhere in Africa', 'Kimbell Merington', 103, 'IN_STOCK', 1968, '2021-09-15');
insert into books (title, author, price, availability, publication, deliverydate) values ('Pavement: Slow Century', 'Ella Killock', 13, 'IN_STOCK', 1947, '2021-04-24');
insert into books (title, author, price, availability, publication, deliverydate) values ('As Long as You', 'Harwilll Schrinel', 104, 'IN_STOCK', 1921, '2020-12-31');
insert into books (title, author, price, availability, publication, deliverydate) values ('The Eagle and the Hawk', 'Kyrstin Neeves', 235, 'IN_STOCK', 1986, '2021-10-19');
insert into books (title, author, price, availability, publication, deliverydate) values ('Dark Lurking, The', 'Vinita Randalston', 261, 'IN_STOCK', 1927, '2020-11-19');
insert into books (title, author, price, availability, publication, deliverydate) values ('Royal Wedding', 'Delia MacFadyen', 227, 'IN_STOCK', 1928, '2020-12-16');
insert into books (title, author, price, availability, publication, deliverydate) values ('Trembling Before God', 'Lurlene Blaw', 246, 'IN_STOCK', 1988, '2020-11-18');
insert into books (title, author, price, availability, publication, deliverydate) values ('Koruto wa ore no pasupooto', 'Rockey Clayill', 69, 'IN_STOCK', 2013, '2021-03-01');
insert into books (title, author, price, availability, publication, deliverydate) values ('MR 73', 'Laverna Long', 162, 'IN_STOCK', 1945, '2020-10-07');
insert into books (title, author, price, availability, publication, deliverydate) values ('Lethal Weapon', 'Galvan Paolicchi', 194, 'IN_STOCK', 1915, '2021-08-27');
insert into books (title, author, price, availability, publication, deliverydate) values ('Romeo and Juliet', 'Gottfried Whimper', 74, 'IN_STOCK', 1918, '2021-06-30');

insert into requests (book_id, book_title) values (1, 'Curly Sue');

insert into order_books (order_id, book_id) values (1,1);
insert into order_books (order_id, book_id) values (2,2);
insert into order_books (order_id, book_id) values (3,3);
insert into order_books (order_id, book_id) values (4,4);
insert into order_books (order_id, book_id) values (5,5);
insert into order_books (order_id, book_id) values (6,1);

