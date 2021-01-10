INSERT INTO client (first_name, last_name, telephone_number)
VALUES
('Peter', 'Anema', '12464'),
('Truus', 'Jansen', '13464'),
('Jan', 'Jansen', '14564');


INSERT into address (home_town, house_number, house_number_add, postal_code, street_name, client_id)
VALUES
('Bussum', '2', 'a', '1400 XX', 'Spijkerstraat', 1),
('Laren', '1','b' , '1400 XX', 'Bijlstraat', 2),
('Bussum', '2', 'a', '1400 XX', 'Hamerstraat', 3);

INSERT INTO car (number_plate, brand, model)
VALUES
('23-HG-35','Opel','Zafira'),
('12-HG-ZZ','Fiat','Multipla');