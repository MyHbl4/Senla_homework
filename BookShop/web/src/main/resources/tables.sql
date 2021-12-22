CREATE TABLE users
(
    username varchar(15),
    password varchar(100),
    enabled  smallint,
    PRIMARY KEY (username)
);

CREATE TABLE authorities
(
    username  varchar(15),
    authority varchar(25),
    FOREIGN KEY (username) references users (username)
);

INSERT INTO users (username, password, enabled)
VALUES ('ira', '{noop}ira', 1),
       ('mark', '{noop}mark', 1),
       ('lydia', '{noop}lydia', 1),
       ('moon', '{noop}moon', 1);

INSERT INTO authorities (username, authority)
VALUES ('ira', 'USER'),
       ('mark', 'USER'),
       ('lydia', 'USER'),
       ('moon', 'ADMIN');
