package com.haulmont.web.view.doctor;

import com.haulmont.web.controller.Service;
import com.haulmont.web.model.entity.Doctor;
import com.haulmont.web.view.Consts;
import com.vaadin.ui.*;

public class DoctorView extends VerticalLayout {

    private DoctorEdit doctorEdit = new DoctorEdit(this);

    private Service service = Service.getInstance();
    private Button addButton, updateButton, deleteButton, showStatButton;

    private Grid<Doctor> grid = new Grid<>(Doctor.class);

    public DoctorView() {
        grid.setColumns("firstName", "lastName", "middleName", "specialization");
        grid.setSizeFull();
        addComponents(grid);

        addButton = new Button(Consts.ADD,
            event -> doctorEdit.add(new Doctor()));

        updateButton = new Button(Consts.UPDATE,
            event -> {
                if (grid.asSingleSelect().getValue() != null) {
                    doctorEdit.edit(grid.asSingleSelect().getValue());
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
            event -> new DoctorStats(this));

        HorizontalLayout options = new HorizontalLayout(addButton, updateButton, deleteButton, showStatButton);

        addComponents(options);
        setSizeFull();

        updateList();
    }

    public void updateList() {
        grid.setItems(service.findAllDoctors());
    }
}