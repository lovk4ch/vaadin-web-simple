package com.haulmont.web.view.edit;

import com.haulmont.web.controller.Service;
import com.haulmont.web.model.entity.Doctor;
import com.haulmont.web.view.Consts;
import com.haulmont.web.view.DoctorView;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;

public class DoctorEdit {
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField middleName = new TextField("Middle Name");
    private TextField specialization = new TextField("Specialization");

    private Service service = Service.getInstance();
    private Binder<Doctor> binder = new Binder<>(Doctor.class);

    private Window window;
    private Button okButton, cancelButton;

    private final DoctorView mainView;

    public DoctorEdit(DoctorView mainView) {

        firstName.setMaxLength(30);
        lastName.setMaxLength(30);
        middleName.setMaxLength(30);
        specialization.setMaxLength(30);

        binder.forField(firstName)
            .withValidator(Consts.emptyValidator)
            .asRequired("First name is required")
            .bind(Doctor::getFirstName, Doctor::setFirstName);

        binder.forField(lastName)
            .withValidator(Consts.emptyValidator)
            .asRequired("Last name is required")
            .bind(Doctor::getLastName, Doctor::setLastName);

        binder.forField(specialization)
            .withValidator(Consts.emptyValidator)
            .asRequired("Specialization is required")
            .bind(Doctor::getSpecialization, Doctor::setSpecialization);

        binder.bindInstanceFields(this);
        this.mainView = mainView;
    }

    private void show(Doctor doctor, String header) {
        okButton = new Button(Consts.OK,
            clickEvent -> {
                if (binder.validate().isOk()) {
                    service.saveDoctor(doctor);
                    mainView.updateList();
                    window.close();
                    binder.setBean(null);
                } else {
                    Notification.show(binder.validate()
                        .getValidationErrors().get(0).getErrorMessage());
                }
            });
        cancelButton = new Button(Consts.CANCEL,
            clickEvent -> {
                mainView.updateList();
                window.close();
                binder.setBean(null);
            });
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(okButton, cancelButton);

        binder.setBean(doctor);

        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(firstName, lastName, middleName, specialization, buttons);

        window = new Window(header);
        window.setContent(layout);
        window.setModal(true);
        window.center();
        mainView.addWindow(window);
    }

    public void add() {
        Doctor doctor = new Doctor();
        show(doctor, Consts.ADD);
    }

    public void edit() {
        Doctor doctor = mainView.grid.asSingleSelect().getValue();
        show(doctor, Consts.UPDATE);
    }
}