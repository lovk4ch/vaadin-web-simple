package com.haulmont.web.view.recipe.sub;

import com.haulmont.web.model.entity.Recipe;

import java.sql.Date;
import java.time.LocalDate;

public class RecipeRow extends Recipe {
    private String doctor = "";
    private String patient = "";
    private LocalDate creationLocalDate;
    private LocalDate validityLocal;

    public String getDoctor() {
        return doctor;
    }

    private void setDoctor(String doctorFirstName, String doctorLastName) {
        this.doctor = doctorFirstName + " " + doctorLastName;
    }

    public String getPatient() {
        return patient;
    }

    private void setPatient(String patientFirstName, String patientLastName) {
        this.patient = patientFirstName + " " + patientLastName;
    }

    public LocalDate getCreationLocalDate() {
        return creationLocalDate;
    }

    public void setCreationLocalDate(LocalDate creationLocalDate) {
        this.creationLocalDate = creationLocalDate;
    }

    public LocalDate getValidityLocal() {
        return validityLocal;
    }

    public void setValidityLocal(LocalDate validityLocal) {
        this.validityLocal = validityLocal;
    }

    public RecipeRow() {
    }

    public RecipeRow(Recipe recipe) {
        setId(recipe.getId());
        setDescription(recipe.getDescription());
        setCreationDate(recipe.getCreationDate());
        setValidity(recipe.getValidity());
        setPriority(recipe.getPriority());
        setDoctorByDoctor(recipe.getDoctorByDoctor());
        setPatientByPatient(recipe.getPatientByPatient());
        if (getDoctorByDoctor() != null)
            setDoctor(getDoctorByDoctor().getFirstName(), getDoctorByDoctor().getLastName());
        if (getPatientByPatient() != null)
            setPatient(getPatientByPatient().getFirstName(), getPatientByPatient().getLastName());
        if (getCreationDate() != null)
            setCreationLocalDate(recipe.getCreationDate().toLocalDate());
        if (getValidity() != null)
            setValidityLocal(recipe.getValidity().toLocalDate());
    }

    public Recipe toRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(getId());
        recipe.setDescription(getDescription());
        recipe.setCreationDate(Date.valueOf(getCreationLocalDate()));
        recipe.setValidity(Date.valueOf(getValidityLocal()));
        recipe.setPriority(getPriority());
        recipe.setDoctorByDoctor(getDoctorByDoctor());
        recipe.setPatientByPatient(getPatientByPatient());
        return recipe;
    }
}