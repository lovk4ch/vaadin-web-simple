package com.haulmont.web.view.patient;

import com.haulmont.web.controller.Service;
import com.haulmont.web.model.Patient;
import com.haulmont.web.view.Consts;
import com.vaadin.ui.*;

import javax.persistence.PersistenceException;

public class PatientView extends VerticalLayout {

    private PatientEdit patientEdit = new PatientEdit(this);

    private Service service = Service.getInstance();
    private Button addButton, updateButton, deleteButton;

    private Grid<Patient> grid = new Grid<>(Patient.class);

    public PatientView() {
        grid.setColumns("firstName", "lastName", "middleName", "phoneNumber");
        grid.setSizeFull();
        addComponents(grid);

        addButton = new Button(Consts.ADD,
                event -> patientEdit.add(new Patient()));

        updateButton = new Button(Consts.UPDATE,
                event -> {
                    if (grid.asSingleSelect().getValue() != null) {
                        patientEdit.edit(grid.asSingleSelect().getValue());
                    } else {
                        Notification.show(Consts.SELECT_WARNING);
                    }
                });

        deleteButton = new Button(Consts.DELETE,
                event -> {
                    if (grid.asSingleSelect().getValue() != null) {
                        try {
                            service.deletePatient(grid.asSingleSelect().getValue());
                            updateList();
                        } catch (PersistenceException e) {
                            Notification.show(Consts.PATIENT_DELETE_ERROR);
                        }
                    } else {
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