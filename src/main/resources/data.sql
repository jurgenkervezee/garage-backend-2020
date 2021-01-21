INSERT INTO client (first_name, last_name, telephone_number)
VALUES
('Peter', 'Anema', '06-12345678'),
('Truus', 'Jansen', '06-87654321'),
('Jan', 'Jansen', '06-12348765');

INSERT into address ( street_name, house_number, house_number_add, postal_code, home_town, client_id)
VALUES
('Spijkerstraat', '2', 'a', '1400 XX','Bussum', 1),
('Bijlstraat',  '1','b' , '1400 XX', 'Laren', 2),
('Hamerstraat', '2', 'a', '1400 XX', 'Bussum', 3);

INSERT INTO car (number_plate, brand, model, client_id)
VALUES
('23-HG-35','Opel','Zafira', 1),
('12-HG-ZZ','Fiat','Multipla', 2);

INSERT INTO car_part (description, price, stock_amount)
VALUES
('Stuurhuis', 700.50 , 3),
('Luchtfilter', 20.45 , 20),
('dikke rims', 120.85 , 16),
('Olie', 5.98 , 50),
('lamp neon', 38.65 , 8),
('Riem', 32.95 , 6);

INSERT INTO role(name)
VALUES('ROLE_USER');
INSERT INTO role(name)
VALUES('ROLE_MODERATOR');
INSERT INTO role(name)
VALUES('ROLE_ADMIN');