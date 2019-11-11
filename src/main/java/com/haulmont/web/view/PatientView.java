package com.haulmont.web.view;

import com.haulmont.web.controller.Service;
import com.haulmont.web.model.entity.Patient;
import com.haulmont.web.view.sub.PatientEdit;
import com.vaadin.ui.*;

public class PatientView extends VerticalLayout {

    private PatientEdit patientEdit = new PatientEdit(this);

    private Service service = Service.getInstance();
    private Button addButton, updateButton, deleteButton;

    private Grid<Patient> grid = new Grid<>(Patient.class);

    PatientView() {
        grid.setColumns("firstName", "lastName", "middleName", "phoneNumber");
        grid.setSizeFull();
        addComponents(grid);

        addButton = new Button(Consts.ADD,
            event -> patientEdit.add(new Patient()));

        updateButton = new Button(Consts.UPDATE,
            event -> {
                if (grid.asSingleSelect().getValue() != null) {
                    patientEdit.edit(grid.asSingleSelect().getValue());
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

        HorizontalLayout options = new HorizontalLayout(addButton, updateButton, deleteButton);

        addComponents(options);
        setSizeFull();

        updateList();
    }

    public void updateList() {
        grid.setItems(service.findAllPatients());
    }
}