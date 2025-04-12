-- Inserções para a tabela models (modelos)
INSERT INTO models (id, fipe_code, description, manufacturer, fuel_type, model_year, fipe_value, last_update_fipe_value)
VALUES (1, '001001-1', 'Corolla', 'Toyota', 1, 2023, 150000.00, '2023-10-15 12:00:00'),
       (2, '001002-1', 'Civic', 'Honda', 1, 2023, 145000.00, '2023-10-15 12:00:00'),
       (3, '002001-1', 'Onix', 'Chevrolet', 2, 2023, 80000.00, '2023-10-15 12:00:00'),
       (4, '003001-1', 'Golf', 'Volkswagen', 3, 2023, 130000.00, '2023-10-15 12:00:00'),
       (5, '004001-1', 'Fiesta', 'Ford', 1, 2023, 70000.00, '2023-10-15 12:00:00');

-- Inserções para a tabela cars (carros)
INSERT INTO cars (id, model, license_plate)
VALUES (1, 1, 'ABC1D23'),
       (2, 2, 'EFG4H56'),
       (3, 3, 'IJK7L89'),
       (4, 4, 'MNO1P23'),
       (5, 5, 'QRS4T56');

-- Inserções para a tabela drivers (motoristas)
INSERT INTO drivers (id, document, birthdate)
VALUES (1, '12345678901', '1980-05-15'),
       (2, '23456789012', '1985-08-20'),
       (3, '34567890123', '1990-03-10'),
       (4, '45678901234', '1975-11-25'),
       (5, '56789012345', '1995-07-30');

-- Inserções para a tabela customers (clientes)
INSERT INTO customers (id, name, driver_id)
VALUES (1, 'João Silva', 1),
       (2, 'Maria Oliveira', 2),
       (3, 'Carlos Souza', 3),
       (4, 'Ana Santos', 4),
       (5, 'Pedro Costa', 5);

-- Inserções para a tabela claims (sinistros)
INSERT INTO claims (id, car_id, driver_id, event_date)
VALUES (1, 1, 1, '2023-01-10'),
       (2, 2, 2, '2023-02-15'),
       (3, 3, 3, '2023-03-20'),
       (4, 4, 4, '2023-04-25'),
       (5, 5, 5, '2023-05-30');