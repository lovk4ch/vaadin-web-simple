package com.haulmont.web.view;

import javax.servlet.annotation.WebServlet;

import com.haulmont.web.controller.Service;
import com.haulmont.web.model.entity.Doctor;
import com.haulmont.web.view.edit.DoctorEdit;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class DoctorView extends UI {

    private DoctorEdit doctorEdit = new DoctorEdit(this);

    private Service service = Service.getInstance();
    private Button addButton, updateButton, deleteButton, showStatButton;

    public Grid<Doctor> grid = new Grid<>(Doctor.class);

    @WebServlet(urlPatterns = "/*", name = "ProjectServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DoctorView.class, productionMode = false)
    public static class ProjectServlet extends VaadinServlet {}

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        grid.setColumns("firstName", "lastName", "middleName", "specialization");
        grid.setSizeFull();
        layout.addComponents(grid);

        addButton = new Button(Consts.ADD,
            event -> { doctorEdit.add();
        });

        updateButton = new Button(Consts.UPDATE,
            event -> {
                if (grid.asSingleSelect().getValue() != null) {
                    doctorEdit.edit();
                }
                else {
                    Notification.show(Consts.SELECT_WARNING);
                }
            });

        deleteButton = new Button(Consts.DELETE,
            event -> {
                if (grid.asSingleSelect().getValue() != null) {
                    service.deleteDoctor(grid.asSingleSelect().getValue());
                    updateList();
                }
                else {
                    Notification.show(Consts.SELECT_WARNING);
                }
            });

        showStatButton = new Button(Consts.SHOW_STATS,
            event -> {
                new StatisticsView(this);
            });

        HorizontalLayout options = new HorizontalLayout(addButton, updateButton, deleteButton, showStatButton);

        layout.addComponents(options);
        setContent(layout);
        setSizeFull();

        updateList();
    }

    public void updateList() {
        grid.setItems(service.findAllDoctors());
    }
}