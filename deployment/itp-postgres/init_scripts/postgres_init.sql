-- allow Debezium replication
ALTER ROLE itpusr WITH REPLICATION;

-- example table
CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         code VARCHAR(255),
                         qty INT
);