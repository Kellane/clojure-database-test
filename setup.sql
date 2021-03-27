CREATE DATABASE db2;

\c db2

CREATE ROLE clojure_user WITH PASSWORD '123456';
ALTER ROLE clojure_user WITH LOGIN;

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    eid INTEGER PRIMARY KEY NOT NULL,
    description TEXT
);

GRANT ALL PRIVILEGES ON product TO clojure_user;

