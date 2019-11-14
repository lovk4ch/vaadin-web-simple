package com.haulmont.web.controller.simpleJPA;

import com.haulmont.web.model.Doctor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// this class is not used and is included in the package
// for comparison with hibernate

public class DoctorDAO {
    private static Connection con;

    private final String FIRST_NAME = "FIRST_NAME";
    private final String LAST_NAME = "LAST_NAME";
    private final String MIDDLE_NAME = "MIDDLE_NAME";
    private final String SPECIALIZATION = "SPECIALIZATION";

    static {
        try {
            //Registering the HSQLDB JDBC driver
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            //Creating the connection with HSQLDB
            con = DriverManager.getConnection("jdbc:hsqldb:file:/database/src", "SA", "");
            if (con != null) {
                System.out.println("Connection Successful!");
            } else {
                System.out.println("Problem with connection");
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public synchronized List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from doctor");

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getLong("ID"));
                doctor.setFirstName(rs.getString(FIRST_NAME));
                doctor.setLastName(rs.getString(LAST_NAME));
                doctor.setMiddleName(rs.getString(MIDDLE_NAME));
                doctor.setSpecialization(rs.getString(SPECIALIZATION));
                doctors.add(doctor);
            }
            if (st != null)
                st.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return doctors;
    }

    public synchronized void add(Doctor doctor) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("insert into DOCTOR values (\'"
                    + doctor.getFirstName() + "\', \'"
                    + doctor.getLastName() + "\', \'"
                    + doctor.getMiddleName() + "\', \'"
                    + doctor.getSpecialization() + "\');"
            );
            if (st != null)
                st.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public synchronized void update(Doctor doctor) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update DOCTOR set "
                    + FIRST_NAME + " = \'" + doctor.getFirstName()
                    + "\', " + LAST_NAME + " = \'" + doctor.getLastName()
                    + "\', " + MIDDLE_NAME + " = \'" + doctor.getMiddleName()
                    + "\', " + SPECIALIZATION + " = \'" + doctor.getSpecialization()
                    + "\' where id = " + doctor.getId()
            );
            if (st != null)
                st.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}