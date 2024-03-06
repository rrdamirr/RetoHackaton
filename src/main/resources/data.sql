INSERT INTO customer (name, email, birth_date, dni) VALUES
('Alex Perez','alex@bananamint.com','1985-03-06','12345678L'),
('Alexia Osuna','alexia@bananamint.com','1972-06-01','12345678L'),
('Miquel Alemany','miquel@bananamint.com','1990-07-11','12345678L'),
('Jordi Sempere','jordi@bananamint.com','1973-01-12','12345678L'),
('David Cano','david@bananamint.com','1985-12-08','12345678L'),
('Maria Ortiz','maria@bananamint.com','1966-11-08','12345678L');

INSERT INTO goal (name, description, target_amount, status, target_date, customer_id) VALUES
('School','Gastos escolarización niños',100.00,'Finalizada', '2024-03-01', 2),
('Sports','Gastos Gimnasio',80.00,'Pendiente', '2024-03-01', 2),
('Home','Gastos comida',500.00,'Finalizada', '2024-03-01', 4),
('Home','Gastos luz, agua, gas',200.00,'Pendiente', '2024-03-01', 4),
('Party','Gastos ocio nocturo',300.00,'Finalizada', '2024-03-01', 1),
('Others','Gasto extra del mes',100.00,'Pendiente', '2024-03-01', 1);


