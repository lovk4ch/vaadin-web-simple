package com.haulmont.web.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "RECIPE", schema = "PUBLIC", catalog = "PUBLIC")
public class Recipe {
    private long id;
    private String description;
    private Date creationDate;
    private Date validity;
    private String priority;
    private Patient patientByPatient;
    private Doctor doctorByDoctor;

    @Id
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = false, length = 300)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CREATION_DATE", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "VALIDITY", nullable = false)
    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    @Basic
    @Column(name = "PRIORITY", nullable = false, length = 6)
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe that = (Recipe) o;
        return id == that.id &&
                Objects.equals(description, that.description) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(validity, that.validity) &&
                Objects.equals(priority, that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, creationDate, validity, priority);
    }

    @ManyToOne
    @JoinColumn(name = "PATIENT", referencedColumnName = "ID")
    public Patient getPatientByPatient() {
        return patientByPatient;
    }

    public void setPatientByPatient(Patient patientByPatient) {
        this.patientByPatient = patientByPatient;
    }

    @ManyToOne
    @JoinColumn(name = "DOCTOR", referencedColumnName = "ID")
    public Doctor getDoctorByDoctor() {
        return doctorByDoctor;
    }

    public void setDoctorByDoctor(Doctor doctorByDoctor) {
        this.doctorByDoctor = doctorByDoctor;
    }
}