CREATE TABLE cohort (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE,
    end_date DATE,
    status VARCHAR(200),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);