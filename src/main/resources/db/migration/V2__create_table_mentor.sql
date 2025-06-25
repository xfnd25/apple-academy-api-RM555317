CREATE TABLE mentor (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    avatar_path VARCHAR(200),
    expertise VARCHAR(200),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);