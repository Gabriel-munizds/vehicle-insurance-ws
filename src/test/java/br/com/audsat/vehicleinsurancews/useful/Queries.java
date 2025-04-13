package br.com.audsat.vehicleinsurancews.useful;

public interface Queries {
    String init_insurances = "INSERT INTO insurances (id, customer_id, creation_dt, updated_at, car_id, is_active, aliquot, insurance_value) \n" +
            "VALUES (5000, 1001, '2023-10-15 14:30:00', NULL, 1001, true, 3.33, 5000.00)\n";

    String init_insurances_2 = "INSERT INTO insurances (id, customer_id, creation_dt, updated_at, car_id, is_active, aliquot, insurance_value) \n" +
            "VALUES (5001, 1001,  '2023-10-15 14:30:00', NULL, 1001, true, 3.33, 5000.00)\n";
    String init_insurances_3 = "INSERT INTO insurances (id, customer_id, creation_dt, updated_at, car_id, is_active, aliquot, insurance_value) \n" +
            "VALUES (5002, 1001, '2023-10-15 14:30:00', NULL, 1001, true, 3.33, 5000.00)\n";
}
