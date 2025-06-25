CREATE TABLE student (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    avatar_path VARCHAR(200),
    expertise VARCHAR(255),
    active TINYINT(1) DEFAULT 1,
    student_id VARCHAR(255),
    delivery_mode VARCHAR(255),
    program VARCHAR(255),
    birth_date DATE,
    cohort_id CHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE student
ADD CONSTRAINT fk_student_cohort
FOREIGN KEY (cohort_id)
REFERENCES cohort(id);