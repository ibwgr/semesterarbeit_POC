DROP table if exists calculator.reise;


CREATE table if not exists calculator.reise

(
    nr int not null AUTO_INCREMENT primary key,
    destination VARCHAR(50),
    preis DOUBLE (19,2),
    datum DATE
);


INSERT into calculator.reise

(destination, preis, datum)

VALUES

('Bern', 127.00, '2019-12-19'),
('Zürich', 81.90, '2019-04-08'),
('Zürich', 81.90, '2019-02-22'),
('Bern', 127.00, '2019-08-04'),
('Olten', 116.70, '2019-01-01'),
('Olten', 116.70, '2019-12-15'),
('Bern', 127.00, '2019-11-06')