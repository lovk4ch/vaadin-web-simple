package com.haulmont.web.view;

import com.haulmont.web.controller.Service;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class StatisticsView {
    public class Statistics {
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

        public Statistics(String firstName, String lastName, Integer recipes) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.recipes = recipes;
        }
    }

    private Service service = Service.getInstance();
    private final DoctorView mainView;
    private Button okButton;

    public StatisticsView(DoctorView mainView) {
        this.mainView = mainView;
        showStatistics();
    }

    private void showStatistics() {
        List<Object[]> recipeStatistic = service.getStatistics();
        List<Statistics> s = new ArrayList<>();
        for (Object[] o : recipeStatistic) {
            s.add(new Statistics(
                (String)o[0], (String)o[1], (Integer)o[2]
            ));
        }

        Grid<Statistics> grid = new Grid<>(Statistics.class);
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
        mainView.addWindow(window);
    }
}