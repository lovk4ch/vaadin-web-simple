package com.haulmont.web.view.sub;

import com.haulmont.web.controller.Service;
import com.haulmont.web.view.Consts;
import com.haulmont.web.view.DoctorView;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class DoctorStatistics {
    public class Stats {
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

        Stats(String firstName, String lastName, Integer recipes) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.recipes = recipes;
        }
    }

    private Service service = Service.getInstance();
    private DoctorView mainView;
    private Button okButton;

    public DoctorStatistics(DoctorView mainView) {
        this.mainView = mainView;
        showStatistics();
    }

    private void showStatistics() {
        List<Object[]> recipeStatistic = service.getStatistics();
        List<Stats> s = new ArrayList<>();
        for (Object[] o : recipeStatistic) {
            s.add(new Stats(
                (String)o[0], (String)o[1], (Integer)o[2]
            ));
        }

        Grid<Stats> grid = new Grid<>(Stats.class);
        grid.setColumns("firstName", "lastName", "recipes");
        grid.setItems(s);
        grid.setSizeFull();

        Window window = new Window("Statistics");

        okButton = new Button(Consts.OK,
            clickEvent -> {
                window.close();
            });

        final VerticalLayout layout = new VerticalLayout();
        layout.addComponents(grid, okButton);

        window.setContent(layout);
        window.setWidth("600");
        window.setModal(true);
        window.center();
        mainView.getUI().addWindow(window);
    }
}