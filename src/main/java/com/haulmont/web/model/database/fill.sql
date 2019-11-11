DELETE FROM RECIPE;
DELETE FROM DOCTOR;
DELETE FROM PATIENT;

INSERT INTO DOCTOR (FIRST_NAME, LAST_NAME, MIDDLE_NAME, SPECIALIZATION)
    values ('Calvin', 'Nickson', null, 'Neurosurgeon'),
           ('Thomas', 'Saxon', null, 'Otolaringologyst'),
           ('Alexander', 'Nikitin', null, 'Infectious disease specialist');

INSERT INTO PATIENT (FIRST_NAME, LAST_NAME, MIDDLE_NAME, PHONE_NUMBER)
    values ('Agnette', 'Mc Kinsley', null, '+5991541'),
           ('Nicole', 'Sanders', null, '+1955123'),
           ('Michael', 'Danton', null, '+1193761'),
           ('Anthony', 'Richards', null, '+3971665'),
           ('Alter', 'Abdulmalik', 'Ibn Ogly', '+7917726');

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