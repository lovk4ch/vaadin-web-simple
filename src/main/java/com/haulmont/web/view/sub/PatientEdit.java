package com.haulmont.web.view.sub;

import com.haulmont.web.controller.Service;
import com.haulmont.web.model.entity.Patient;
import com.haulmont.web.view.Consts;
import com.haulmont.web.view.PatientView;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;

public class PatientEdit extends Window {
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField middleName = new TextField("Middle Name");
    private TextField phoneNumber = new TextField("Phone Number");

    private Service service = Service.getInstance();
    private Binder<Patient> binder = new Binder<>(Patient.class);

    private PatientView mainView;

    private Button okButton, cancelButton;

    public PatientEdit(PatientView mainView) {
        firstName.setMaxLength(30);
        lastName.setMaxLength(30);
        middleName.setMaxLength(30);
        phoneNumber.setMaxLength(15);

        binder.forField(firstName)
            .withValidator(Consts.emptyValidator)
            .asRequired("First name is required")
            .bind(Patient::getFirstName, Patient::setFirstName);

        binder.forField(lastName)
            .withValidator(Consts.emptyValidator)
            .asRequired("Last name is required")
            .bind(Patient::getLastName, Patient::setLastName);

        binder.forField(phoneNumber)
            .withValidator(Consts.emptyValidator)
            .asRequired("Phone number is required")
            .bind(Patient::getPhoneNumber, Patient::setPhoneNumber);

        binder.bindInstanceFields(this);
        this.mainView = mainView;
    }

    private void update(Patient patient, String header) {
        okButton = new Button(Consts.OK,
            clickEvent -> {
                if (binder.validate().isOk()) {
                    service.savePatient(patient);
                    mainView.updateList();
                    close();
                    binder.setBean(null);
                } else {
                    Notification.show(binder.validate()
                            .getValidationErrors().get(0).getErrorMessage());
                }
            });
        cancelButton = new Button(Consts.CANCEL,
            clickEvent -> {
                mainView.updateList();
                close();
                binder.setBean(null);
            });
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(okButton, cancelButton);

        binder.setBean(patient);

        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(firstName, lastName, middleName, phoneNumber, buttons);

        setContent(layout);
        setModal(true);
        center();
        mainView.getUI().addWindow(this);
    }

    public void add(Patient patient) {
        update(patient, Consts.ADD);
    }

    public void edit(Patient patient) {
        update(patient, Consts.UPDATE);
    }
}