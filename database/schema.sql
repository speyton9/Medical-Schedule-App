BEGIN TRANSACTION;

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS patient_office;
DROP TABLE IF EXISTS doctor_office;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS prescriptions;
DROP TABLE IF EXISTS offices;
DROP TABLE IF EXISTS doctor_accounts;
DROP TABLE IF EXISTS patient_accounts;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_comment_id;
DROP SEQUENCE IF EXISTS seq_review_id;
DROP SEQUENCE IF EXISTS seq_appt_id;
DROP SEQUENCE IF EXISTS seq_prescription_id;
DROP SEQUENCE IF EXISTS seq_office_id;
DROP SEQUENCE IF EXISTS seq_doctor_id;
DROP SEQUENCE IF EXISTS seq_patient_id;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE SEQUENCE seq_patient_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE SEQUENCE seq_doctor_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE SEQUENCE seq_office_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE SEQUENCE seq_prescription_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE SEQUENCE seq_appt_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE SEQUENCE seq_review_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE SEQUENCE seq_comment_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

--Creating the patient_accounts table  
CREATE TABLE patient_accounts (
        patient_id      serial NOT NULL,
        first_name      varchar(50) NOT NULL,
        last_name       varchar(50) NOT NULL,
        email           varchar(50) NOT NULL,
        user_id         int NOT NULL,
        CONSTRAINT pk_patient_id PRIMARY KEY(patient_id),
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)        
);

--Creating the doctor_accounts table
CREATE TABLE doctor_accounts (
        doctor_id       SERIAL NOT NULL,
        first_name      VARCHAR(50) NOT NULL,
        last_name       VARCHAR(50) NOT NULL,
        clock_in        VARCHAR(50), 
        clock_out       VARCHAR(50), 
        email           VARCHAR(50) NOT NULL,
        user_id         INTEGER NOT NULL,
        CONSTRAINT pk_doctor_id PRIMARY KEY (doctor_id),
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

--Creating the offices table
CREATE TABLE offices (
        office_id       SERIAL     NOT NULL,
        name            VARCHAR(100) NOT NULL,
        address         VARCHAR(50) NOT NULL,
        city_address    VARCHAR(50) NOT NULL,
        state_address   VARCHAR(50) NOT NULL,
        zip_address     VARCHAR(50) NOT NULL,
        phone_number    VARCHAR(50) NOT NULL,
        start_hour      VARCHAR(50) NOT NULL,
        end_hour        VARCHAR(50) NOT NULL,
        price           INTEGER     NOT NULL,
        CONSTRAINT pk_office_id         PRIMARY KEY (office_id)
);

--Creatine the prescriptions table
CREATE TABLE prescriptions (
        prescription_id         SERIAL NOT NULL,
        price                   INTEGER NOT NULL,
        patient_id              INTEGER NOT NULL,
        doctor_id               INTEGER NOT NULL,
        doctor_note             VARCHAR(200),
        CONSTRAINT fk_patient_id        FOREIGN KEY (patient_id) REFERENCES patient_accounts (patient_id),
        CONSTRAINT fk_doctor_id         FOREIGN KEY (doctor_id) REFERENCES doctor_accounts (doctor_id)  
);

--Creating the appointments table
CREATE TABLE appointments (
        appt_id                 SERIAL,
        appt_date               varchar(50) NOT NULL,
        start_time              varchar(20) NOT NULL,
        end_time                varchar(20) NOT NULL,
        appt_type               varchar(150) NOT NULL,
        office_id               INTEGER NOT NULL,
        patient_id              INTEGER NOT NULL,
        doctor_id               INTEGER NOT NULL,
        patient_note            varchar(500),
        doctor_note             varchar(500),
        date_added              timestamp default current_timestamp,
        CONSTRAINT pk_appt_id PRIMARY KEY (appt_id),
        CONSTRAINT fk_office_id FOREIGN KEY (office_id) REFERENCES offices (office_id),
        CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patient_accounts (patient_id),
        CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor_accounts (doctor_id)
);
--Cretating the doctor_office JOIN table
CREATE TABLE doctor_office (
        doctor_id       INTEGER NOT NULL,
        office_id       INTEGER NOT NULL,
        CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor_accounts (doctor_id),
        CONSTRAINT office_id    FOREIGN KEY (office_id) REFERENCES offices (office_id)
);
--Creating the patient_office JOIN table
CREATE TABLE patient_office (
        patient_id      INTEGER NOT NULL,
        office_id       INTEGER NOT NULL,
        CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patient_accounts (patient_id),
        CONSTRAINT office_id    FOREIGN KEY (office_id) REFERENCES offices (office_id)
);

--Creating the reviews table
CREATE TABLE reviews(
        review_id               SERIAL,
        patient_id              INTEGER NOT NULL,
        office_id               INTEGER NOT NULL,
        review                  varchar(500) NOT NULL,
        CONSTRAINT pk_review_id PRIMARY KEY (review_id),
        CONSTRAINT fk_patient_id        FOREIGN KEY (patient_id) REFERENCES patient_accounts (patient_id),
        CONSTRAINT fk_office_id         FOREIGN KEY (office_id) REFERENCES offices (office_id)
);
--Creating the comments table
CREATE TABLE comments(
        comment_id              SERIAL,
        doctor_id               INTEGER NOT NULL,
        review_id               INTEGER NOT NULL,
        comment_info            varchar(500),
        CONSTRAINT pk_comment_id PRIMARY KEY (comment_id),
        CONSTRAINT fk_doctor_id         FOREIGN KEY (doctor_id) REFERENCES doctor_accounts (doctor_id),
        CONSTRAINT fk_review_id         FOREIGN KEY (review_id) REFERENCES reviews (review_id)
);


-- Create Users
-- Password for below users: 'test'
INSERT INTO users (username,password_hash,role) VALUES ('testPatient1','$2a$10$JAasRbYBVvedxesTdk7hweEcr3jO4xTHKEakTZEUnYHl1a1hRUOnm','ROLE_PATIENT');
INSERT INTO users (username,password_hash,role) VALUES ('testPatient2','$2a$10$6sCQ1hRPrDm4kkfP59u7hu.0pj5JbgFs29njmQHTl9aEp2ZuqJSWq','ROLE_PATIENT');
INSERT INTO users (username,password_hash,role) VALUES ('testPatient3','$2a$10$1a7jJ0oKrqcS4ejuFJl6a.Ay.Lxy8WLDBLisM1r7UKDizTS9EjQFa','ROLE_PATIENT');
INSERT INTO users (username,password_hash,role) VALUES ('testDoctor1','$2a$10$SWRWR978X4f.g.gYKRR3kOUJ5PYDQlEg0k/iq9E15QTQRB5D65By','ROLE_DOCTOR');
INSERT INTO users (username,password_hash,role) VALUES ('testDoctor2','$2a$10$3P7KKPiqj5HUiNK12.6S7e9dCtkB3j51AQSI99en9OV.OYQge7Zae','ROLE_DOCTOR');
INSERT INTO users (username,password_hash,role) VALUES ('testDoctor3','$2a$10$gXsb3T6EKBKjfpZhQwFZlufLOcpjZtugH91SGAUlBpuY8S2zY2Ezu','ROLE_DOCTOR');

-- Create Patient Accounts
INSERT INTO patient_accounts (first_name, last_name, email, user_id) VALUES ('Grace', 'Kelly', 'gkelly@patient.com', 1);
INSERT INTO patient_accounts (first_name, last_name, email, user_id) VALUES ('Neeta', 'Maharjan', 'nmaharjan@patient.com', 2);
INSERT INTO patient_accounts (first_name, last_name, email, user_id) VALUES ('Bernie', 'Sanders', 'bsanders@patient.com', 3);

-- Create Doctor Accounts
INSERT INTO doctor_accounts (first_name, last_name, clock_in, clock_out, email, user_id) VALUES ('Batsal', 'Shrestha', '07:00 AM', '04:00 PM', 'bshrestha@doctor.gov', 4);
INSERT INTO doctor_accounts (first_name, last_name, clock_in, clock_out, email, user_id) VALUES ('Brigitte', 'Bardot', '09:00 AM', '06:00 PM', 'bbardot@doctor.gov', 5);
INSERT INTO doctor_accounts (first_name, last_name, clock_in, clock_out, email, user_id) VALUES ('Max', 'von Sydow', '08:00 AM', '05:00 PM', 'msydow@doctor.gov', 6);

-- Create Offices
INSERT INTO offices (name, address, city_address, state_address, zip_address, phone_number, start_hour, end_hour, price) VALUES ('Chestnut Hill Clinic','1234 Doctor Way', 'Philadelphia', 'PA', '12345', '123-456-7890', '10:00 AM', '05:30 PM', 40);
INSERT INTO offices (name, address, city_address, state_address, zip_address, phone_number, start_hour, end_hour, price) VALUES ('Brandywine Medicine','257 Leopard Dr', 'Brandywine', 'PA', '13872', '285-739-8573', '09:00 AM', '05:00 PM', 80);
INSERT INTO offices (name, address, city_address, state_address, zip_address, phone_number, start_hour, end_hour, price) VALUES ('Mt Airy Hospital','1644 Germantown Ave', 'Philadelphia', 'PA', '19119', '856-347-8909', '07:00 AM', '11:30 PM', 70);
INSERT INTO offices (name, address, city_address, state_address, zip_address, phone_number, start_hour, end_hour, price) VALUES ('City Line Rehab','0943 City Ave', 'Bala Cynwyd', 'PA', '09876', '789-567-3489', '09:30 AM', '06:00 PM', 60);
INSERT INTO offices (name, address, city_address, state_address, zip_address, phone_number, start_hour, end_hour, price) VALUES ('Bella Vista Day Clinic','1100 S 4th St', 'Philadelphia', 'PA', '19785', '215-987-3489', '08:30 AM', '05:30 PM', 55);

-- Connect Doctors to Offices
INSERT INTO doctor_office (doctor_id, office_id) VALUES (1, 1);
INSERT INTO doctor_office (doctor_id, office_id) VALUES (2, 2);
INSERT INTO doctor_office (doctor_id, office_id) VALUES (3, 3);
INSERT INTO doctor_office (doctor_id, office_id) VALUES (1, 4);

-- Connect Patients to Offices
INSERT INTO patient_office (patient_id, office_id) VALUES (1, 1);
INSERT INTO patient_office (patient_id, office_id) VALUES (2, 2);
INSERT INTO patient_office (patient_id, office_id) VALUES (3, 3);
INSERT INTO patient_office (patient_id, office_id) VALUES (1, 4);


-- Create Appointments
INSERT INTO appointments(appt_date, start_time, end_time, appt_type, office_id, patient_id, doctor_id, patient_note, doctor_note, date_added) VALUES ('12-01-2021', '10:00 AM', '11:00 AM', 'In-Person', 1, 1, 1, 'My eye twitches', 'Take breaks at work', (select to_timestamp('2021-12-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')));
INSERT INTO appointments(appt_date, start_time, end_time, appt_type, office_id, patient_id, doctor_id, patient_note, doctor_note, date_added) VALUES ('12-02-2021', '01:00 PM', '02:00 PM', 'Remote', 2, 2, 2, 'Bad allergies', 'Get rid of your cat', (select to_timestamp('2021-12-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')));
INSERT INTO appointments(appt_date, start_time, end_time, appt_type, office_id, patient_id, doctor_id, patient_note, doctor_note, date_added) VALUES ('12-05-2021', '02:30 PM', '03:30 PM', 'In-Person', 3, 3, 3, 'My hip aches', 'Put a cold compress on it', (select to_timestamp('2021-12-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')));
INSERT INTO appointments(appt_date, start_time, end_time, appt_type, office_id, patient_id, doctor_id, patient_note, doctor_note, date_added) VALUES ('12-05-2021', '11:00 AM', '12:00 PM', 'In-Person', 4, 1, 1, 'My stomach hurts', 'Start eating healthier', (select to_timestamp('2021-12-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')));
INSERT INTO appointments(appt_date, start_time, end_time, appt_type, office_id, patient_id, doctor_id, patient_note, doctor_note, date_added) VALUES ('12-06-2021', '02:00 PM', '03:00 PM', 'Remote', 5, 2, 2, 'Bad back pain', 'Stretch your back', (select to_timestamp('2021-12-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')));
INSERT INTO appointments(appt_date, start_time, end_time, appt_type, office_id, patient_id, doctor_id, patient_note, doctor_note, date_added) VALUES ('12-07-2021', '03:30 PM', '04:30 PM', 'In-Person', 1, 3, 3, 'My foot aches', 'Buy an insole', (select to_timestamp('2021-12-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')));
INSERT INTO appointments(appt_date, start_time, end_time, appt_type, office_id, patient_id, doctor_id, patient_note, doctor_note, date_added) VALUES ('12-09-2021', '10:00 AM', '11:00 AM', 'In-Person', 2, 1, 1, 'I cut my finger', 'Be more careful with knives', (select to_timestamp('2021-12-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')));
INSERT INTO appointments(appt_date, start_time, end_time, appt_type, office_id, patient_id, doctor_id, patient_note, doctor_note, date_added) VALUES ('12-09-2021', '01:00 PM', '02:00 PM', 'Remote', 3, 2, 2, 'My hair is falling out', 'Try to reduce stress', (select to_timestamp('2021-12-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')));
INSERT INTO appointments(appt_date, start_time, end_time, appt_type, office_id, patient_id, doctor_id, patient_note, doctor_note, date_added) VALUES ('12-10-2021', '02:30 PM', '03:30 PM', 'In-Person', 4, 3, 3, 'My knee hurts', 'Wear a knee brace', (select to_timestamp('2021-12-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')));

-- Create Reviews
INSERT INTO reviews(patient_id, office_id, review) VALUES (1, 1, 'It was great');
INSERT INTO reviews(patient_id, office_id, review) VALUES (2, 2, 'Still great, repeat customer');
INSERT INTO reviews(patient_id, office_id, review) VALUES (3, 3, 'Not as good as other places');
INSERT INTO reviews(patient_id, office_id, review) VALUES (1, 4, 'Very nice people');
INSERT INTO reviews(patient_id, office_id, review) VALUES (2, 5, 'Prices are way too high');
INSERT INTO reviews(patient_id, office_id, review) VALUES (3, 1, 'Difficult to find');
INSERT INTO reviews(patient_id, office_id, review) VALUES (1, 2, 'Loved the office cat');
INSERT INTO reviews(patient_id, office_id, review) VALUES (2, 3, 'Nice waiting room');
INSERT INTO reviews(patient_id, office_id, review) VALUES (3, 4, 'Too hot inside');
INSERT INTO reviews(patient_id, office_id, review) VALUES (3, 5, 'Nursing staff is kind');
INSERT INTO reviews(patient_id, office_id, review) VALUES (1, 1, 'Bill came late');
INSERT INTO reviews(patient_id, office_id, review) VALUES (2, 2, 'Great parking lot');
INSERT INTO reviews(patient_id, office_id, review) VALUES (3, 3, 'Service was not great');
INSERT INTO reviews(patient_id, office_id, review) VALUES (3, 4, 'Better than most places');

COMMIT TRANSACTION;

SELECT * FROM users;
SELECT * FROM doctor_accounts;
SELECT * FROM patient_accounts;
SELECT * FROM doctor_office;
SELECT * FROM offices;

SELECT * FROM reviews;

SELECT * FROM appointments a
join patient_accounts pa ON pa.patient_id = a.patient_id
join users u ON u.user_id = pa.user_id
WHERE u.user_id = 1;

