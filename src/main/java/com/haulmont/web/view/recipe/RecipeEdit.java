package com.haulmont.web.view.recipe;

import com.haulmont.web.controller.Service;
import com.haulmont.web.model.entity.Doctor;
import com.haulmont.web.model.entity.Patient;
import com.haulmont.web.view.Consts;
import com.haulmont.web.view.recipe.sub.RecipeRow;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;

public class RecipeEdit extends Window {
    private TextArea description = new TextArea("Description");
    private ComboBox<Doctor> doctorByDoctor = new ComboBox<>("Doctor");
    private ComboBox<Patient> patientByPatient = new ComboBox<>("Patient");
    private DateField creationLocalDate = new DateField("Creation Date");
    private DateField validityLocal = new DateField("Validity");
    private ComboBox<String> priority = new ComboBox<>("Priority");

    private Service service = Service.getInstance();
    private Binder<RecipeRow> binder = new Binder<>(RecipeRow.class);

    private RecipeView mainView;

    private Button okButton, cancelButton;

    // Непонятен способ маппинга отдельных полей на Vaadin ComboBox,
    // пока ограничился выводом объекта класса

    public RecipeEdit(RecipeView mainView) {
        description.setMaxLength(300);

        doctorByDoctor.setTextInputAllowed(false);
        doctorByDoctor.setItems(service.findAllDoctors());
            /*.stream()
            .map(doctor -> doctor.getFirstName() + " " + doctor.getLastName())
            .collect(Collectors.toList()));*/

        patientByPatient.setTextInputAllowed(false);
        patientByPatient.setItems(service.findAllPatients());

        creationLocalDate.setTextFieldEnabled(false);
        validityLocal.setTextFieldEnabled(false);

        priority.setTextInputAllowed(false);
        priority.setItems(Consts.PRIORITIES);

        binder.forField(description)
            .withValidator(Consts.emptyValidator)
            .asRequired("Description is required")
            .bind(RecipeRow::getDescription, RecipeRow::setDescription);

        binder.forField(doctorByDoctor)
            .asRequired("Doctor is required")
            .bind(RecipeRow::getDoctorByDoctor, RecipeRow::setDoctorByDoctor);

        binder.forField(patientByPatient)
            .asRequired("Patient is required")
            .bind(RecipeRow::getPatientByPatient, RecipeRow::setPatientByPatient);

        binder.forField(creationLocalDate)
            .withValidator(Consts.dateValidator)
            .asRequired("Creation date is required")
            .bind(RecipeRow::getCreationLocalDate, RecipeRow::setCreationLocalDate);

        binder.forField(validityLocal)
            .withValidator(Consts.dateValidator)
            .asRequired("Validity is required")
            .bind(RecipeRow::getValidityLocal, RecipeRow::setValidityLocal);

        binder.forField(priority)
            .withValidator(Consts.emptyValidator)
            .asRequired("Priority is required")
            .bind(RecipeRow::getPriority, RecipeRow::setPriority);

        binder.bindInstanceFields(this);
        this.mainView = mainView;
    }

    private void update(RecipeRow recipeRow, String header) {
        okButton = new Button(Consts.OK,
            clickEvent -> {
                if (binder.validate().isOk()) {
                    service.saveRecipe(recipeRow.toRecipe());
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

        binder.setBean(recipeRow);

        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(description, doctorByDoctor, patientByPatient, creationLocalDate, validityLocal, priority, buttons);

        setContent(layout);
        setModal(true);
        center();
        mainView.getUI().addWindow(this);
    }

    public void add(RecipeRow recipeRow) {
        update(recipeRow, Consts.ADD);
    }

    public void edit(RecipeRow recipeRow) {
        update(recipeRow, Consts.UPDATE);
    }
}