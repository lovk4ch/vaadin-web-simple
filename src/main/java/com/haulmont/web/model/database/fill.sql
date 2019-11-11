DELETE FROM RECIPE;
DELETE FROM DOCTOR;
DELETE FROM PATIENT;

INSERT INTO DOCTOR (FIRST_NAME, LAST_NAME, MIDDLE_NAME, SPECIALIZATION)
    values ('Calvin', 'Nickson', null, 'Neurosurgeon'),
           ('Thomas', 'Saxon', null, 'Otolaringologyst'),
           ('Alexander', 'Nikitin', null, 'Infectious disease specialist');

INSERT INTO PATIENT (FIRST_NAME, LAST_NAME, MIDDLE_NAME, PHONE_NUMBER)
    values ('Agnette', 'Mc Kinsley', null, '599-154-1999'),
           ('Nicole', 'Sanders', null, '491-926-7795'),
           ('Michael', 'Danton', null, '544-819-3673'),
           ('Anthony', 'Richards', null, '244-937-1840'),
           ('Alter-Larrd', 'Abdulmalik', 'Ibn LA''AHAD', '966-115-4673');

INSERT INTO RECIPE (DESCRIPTION, PATIENT, DOCTOR, CREATION_DATE, VALIDITY, PRIORITY)
    values ('Brainstorm',
           (SELECT ID FROM PATIENT WHERE LAST_NAME = 'Sanders'),
           (SELECT ID FROM DOCTOR WHERE LAST_NAME = 'Nickson'),
            CURRENT_DATE, CURRENT_DATE, 'Cito'),
           ('Throat cleaning',
            (SELECT ID FROM PATIENT WHERE LAST_NAME = 'Danton'),
            (SELECT ID FROM DOCTOR WHERE LAST_NAME = 'Saxon'),
            CURRENT_DATE, CURRENT_DATE, 'Statim'),
           ('Antibiotic therapy',
            (SELECT ID FROM PATIENT WHERE LAST_NAME = 'Abdulmalik'),
            (SELECT ID FROM DOCTOR WHERE LAST_NAME = 'Nikitin'),
            CURRENT_DATE, CURRENT_DATE, 'Normal')