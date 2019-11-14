package com.haulmont.web.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR", schema = "PUBLIC", catalog = "PUBLIC")
public class Doctor {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String specialization;
    private Collection<Recipe> recipesById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "MIDDLE_NAME", nullable = true, length = 30)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "SPECIALIZATION", nullable = false, length = 30)
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor that = (Doctor) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(specialization, that.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, specialization);
    }

    @OneToMany(mappedBy = "doctorByDoctor")
    public Collection<Recipe> getRecipesById() {
        return recipesById;
    }

    public void setRecipesById(Collection<Recipe> recipesById) {
        this.recipesById = recipesById;
    }
}