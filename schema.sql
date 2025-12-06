CREATE TABLE product
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(255)   NOT NULL,
    price             FLOAT NOT NULL,
    creation_datetime TIMESTAMP
);

CREATE TABLE product_category
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(150),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES product(id)
);