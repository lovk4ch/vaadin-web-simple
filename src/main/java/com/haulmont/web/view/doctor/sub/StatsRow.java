package com.haulmont.web.view.doctor.sub;

public class StatsRow {
    private String firstName;
    private String lastName;
    private Integer recipes;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getRecipes() {
        return recipes;
    }

    public void setRecipes(Integer recipes) {
        this.recipes = recipes;
    }

    public StatsRow(String firstName, String lastName, Integer recipes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.recipes = recipes;
    }
}