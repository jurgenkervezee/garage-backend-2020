INSERT INTO status(name)
VALUES
('OPEN'),('INSPECTED'), ('REPAIRED'), ('REPAIR_DECLINED'), ('PAID_CLOSED');

INSERT INTO role(name)
VALUES
('ROLE_USER'), ('ROLE_RECEPTION'),('ROLE_MECHANIC'),('ROLE_WAREHOUSE'),('ROLE_CASHIER'),('ROLE_MODERATOR'),('ROLE_ADMIN');

INSERT INTO app_user(email, password, username)
VALUES
('reception@reception.com','$2a$10$ObwVYwkqoDejbGWWGL.NDO4wN9Fssla2R/StfaYYVxxudZW51p4AS','reception-test'),
('warehouse@warehouse.com','$2a$10$ObwVYwkqoDejbGWWGL.NDO4wN9Fssla2R/StfaYYVxxudZW51p4AS','warehouse-test'),
('mechanic@mechanic.com','$2a$10$ObwVYwkqoDejbGWWGL.NDO4wN9Fssla2R/StfaYYVxxudZW51p4AS','mechanic-test'),
('cashier@cashier.com','$2a$10$ObwVYwkqoDejbGWWGL.NDO4wN9Fssla2R/StfaYYVxxudZW51p4AS','cashier-test'),
('moderation@moderation.com','$2a$10$ObwVYwkqoDejbGWWGL.NDO4wN9Fssla2R/StfaYYVxxudZW51p4AS','moderator-test');
INSERT INTO user_role(user_id,role_id)
VALUES(1,2),(2,4),(3,3),(4,5),(5,6);

INSERT INTO client (first_name, last_name, telephone_number)
VALUES
('Peter', 'Anema', '06-12345678'),
('Truus', 'Jansen', '06-87654321'),
('Henk', 'Henksen', '06-12389738'),
('Jaap', 'Gerrit', '06-33763761'),
('Jan', 'Jansen', '06-12348765');

INSERT into address ( street_name, house_number, house_number_add, postal_code, home_town, client_id)
VALUES
('Spijkerstraat', '2', 'a', '1400 XX','Bussum', 1),
('Bijlstraat','1','b', '1400 XX', 'Laren', 2),
('ClaudiaGaarde', '3', null , '1400 XX', 'Weeps', 3),
('Bijlstraat', '12','b' , '1400 XX', 'Laren', 4),
('Hamerstraat', '2','a', '1400 XX', 'Amsterdam', 5);

INSERT INTO car (number_plate, brand, model, client_id)
VALUES
('23-HG-35','Opel','Zafira', 1),
('12-HG-ZZ','Fiat','Multipla', 2);

INSERT INTO carpart (description, price, stock_amount)
VALUES
('Stuurhuis', 700.50 , 3),
('Luchtfilter', 20.45 , 20),
('dikke rims', 120.85 , 16),
('Olie', 5.98 , 50),
('lamp neon', 38.65 , 8),
('Riem', 32.95 , 6);

INSERT INTO repairactivity (description, price)
VALUES
('Carinspection',45.00),
('Stuurhuis vervangen',200.00),
('Luchtfilter vervangen',50),
('Velgen vervangen',25),
('Olie aftappen en verversen',10.00),
('Lampjes vervangen',5),
('Koffiedrinken',1.50);

INSERT INTO carinspection(date_inspection, status,client_id)
VALUES
(TO_DATE('01/02/2021','DD/MM/YYYY'), 1,1),
(TO_DATE('15/02/2021','DD/MM/YYYY'), 1,3),
(TO_DATE('01/03/2021','DD/MM/YYYY'), 1,2),
(TO_DATE('01/03/2021','DD/MM/YYYY'), 1,4),
(TO_DATE('01/03/2021','DD/MM/YYYY'), 2,1),
(TO_DATE('01/02/2021','DD/MM/YYYY'), 3,1),
(TO_DATE('18/02/2021','DD/MM/YYYY'), 4,1);

INSERT INTO orderline(amount, description, price, carinspection_id, carpart_id, repairactivity_id)
VALUES
(1,'Olie aftappen en verversen',10.65,1,null,5),
(1,'Stuurhuis vervangen',200.76,1,null,2),
(1,'Luchtfilter vervangen',50.88,1,null,3),
(1,'Carinspection',45.00,1,null,1),
(1,'Lampjes vervangen',5.00,2,null,6),
(1,'Lampjes vervangen',5.00,4,null,6),
(2,'Velgen vervangen',25.09,1,null,4),
(2,'Carinspection',45.00,2,null,1),
(2,'Stuurhuis vervangen',200.87,2,null,2),
(1, 'Carinspection',45,4.99,null,1),
(2,'Stuurhuis vervangen',200.16,4,null,2);
