DROP table if exists calculator.reise;


CREATE table if not exists calculator.reise

(
    nr int not null AUTO_INCREMENT primary key,
    destination VARCHAR(50),
    preis int,
    datum DATE
);


INSERT into calculator.reise

(destination, preis, datum)

VALUES

('Bern', 100, '2019-12-19'),
('Zürich', 50, '2019-04-08'),
('Zürich', 50, '2019-02-22'),
('Bern', 100, '2019-08-04'),
('Olten', 70, '2019-01-01'),
('Olten', 70, '2019-12-15'),
('Bern', 100, '2019-11-06')