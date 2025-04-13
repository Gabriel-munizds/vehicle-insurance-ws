-- Inserções para a tabela models (modelos)
INSERT INTO models (id, fipe_code, description, manufacturer, fuel_type, model_year, fipe_value, last_update_fipe_value)
VALUES (1001, '001001-1', 'Corolla', 'Toyota', 1, 2023, 150000.00, '2023-10-15 12:00:00'),
       (1002, '001002-1', 'Civic', 'Honda', 1, 2023, 145000.00, '2023-10-15 12:00:00'),
       (1003, '002001-1', 'Onix', 'Chevrolet', 2, 2023, 80000.00, '2023-10-15 12:00:00'),
       (1004, '003001-1', 'Golf', 'Volkswagen', 3, 2023, 130000.00, '2023-10-15 12:00:00'),
       (1005, '004001-1', 'Fiesta', 'Ford', 1, 2023, 70000.00, '2023-10-15 12:00:00');

-- Inserções para a tabela cars (carros)
INSERT INTO cars (id, model, license_plate)
VALUES (1001, 1001, 'ABC1D23'),
       (1002, 1002, 'EFG4H56'),
       (1003, 1003, 'IJK7L89'),
       (1004, 1004, 'MNO1P23'),
       (1005, 1005, 'QRS4T56');

-- Inserções para a tabela drivers (motoristas)
INSERT INTO drivers (id, document, birthdate)
VALUES (1001, '12345678901', '1980-05-15'),
       (1002, '23456789012', '1985-08-20'),
       (1003, '34567890123', '1990-03-10'),
       (1004, '45678901234', '1975-11-25'),
       (1005, '56789012345', '1995-07-30');

-- Inserções para a tabela customers (clientes)
INSERT INTO customers (id, name, driver_id)
VALUES (1001, 'João Silva', 1001),
       (1002, 'Maria Oliveira', 1002),
       (1003, 'Carlos Souza', 1003),
       (1004, 'Ana Santos', 1004),
       (1005, 'Pedro Costa', 1005);

-- Inserções para a tabela claims (sinistros)
INSERT INTO claims (id, car_id, driver_id, event_date)
VALUES (1001, 1001, 1001, '2023-01-10'),
       (1002, 1002, 1002, '2023-02-15'),
       (1003, 1003, 1003, '2023-03-20'),
       (1004, 1004, 1004, '2023-04-25'),
       (1005, 1005, 1005, '2023-05-30');