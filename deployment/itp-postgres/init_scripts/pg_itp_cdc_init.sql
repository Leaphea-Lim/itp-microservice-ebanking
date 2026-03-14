-- Enable logical replication (required for Debezium)
ALTER SYSTEM SET wal_level = logical;
ALTER SYSTEM SET max_replication_slots = 10;
ALTER SYSTEM SET max_wal_senders = 10;

-- Create CDC database objects
CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         code VARCHAR(50),
                         qty INT
);
