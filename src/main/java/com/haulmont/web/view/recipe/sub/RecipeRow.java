package com.haulmont.web.view.recipe.sub;

import com.haulmont.web.model.entity.Recipe;

public class RecipeRow extends Recipe {
    private String doctor;
    private String patient;

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
        this.patient = patientFirstName+ " " + patientLastName;
    }

    public RecipeRow(Recipe recipe) {
        setId(recipe.getId());
        setDescription(recipe.getDescription());
        setCreationDate(recipe.getCreationDate());
        setValidity(recipe.getValidity());
        setPriority(recipe.getPriority());
        setDoctorByDoctor(recipe.getDoctorByDoctor());
        setPatientByPatient(recipe.getPatientByPatient());
        setDoctor(getDoctorByDoctor().getFirstName(), getDoctorByDoctor().getLastName());
        setPatient(getPatientByPatient().getFirstName(), getPatientByPatient().getLastName());
    }
}