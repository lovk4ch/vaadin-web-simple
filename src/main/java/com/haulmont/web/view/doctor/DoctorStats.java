package com.haulmont.web.view.doctor;

import com.haulmont.web.controller.Service;
import com.haulmont.web.view.Consts;
import com.haulmont.web.view.doctor.sub.StatsRow;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class DoctorStats {

    private Service service = Service.getInstance();
    private DoctorView mainView;
    private Button okButton;

    DoctorStats(DoctorView mainView) {
        this.mainView = mainView;
        showStatistics();
    }

    private void showStatistics() {
        List<Object[]> recipeStatistic = service.getStatistics();
        List<StatsRow> s = new ArrayList<>();
        for (Object[] o : recipeStatistic) {
            s.add(new StatsRow(
                (String)o[0], (String)o[1], (Integer)o[2]
            ));
        }

        Grid<StatsRow> grid = new Grid<>(StatsRow.class);
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