package com.haulmont.web.view;

import com.vaadin.data.Validator;

import java.time.LocalDate;
import java.util.Objects;

public class Consts {

    public static final String FILL_WARNING = "Please complete all the required fields";
    public static final String SELECT_WARNING = "Please select an entry in the table";
    public static final String DOCTOR_DELETE_ERROR = "This doctor cannot be removed because he is writing a prescription for one or more patients.";
    public static final String PATIENT_DELETE_ERROR = "This patient cannot be deleted because one or more recipes have been registered for him.";

    public static final String ADD = "Добавить";
    public static final String UPDATE = "Изменить";
    public static final String DELETE = "Удалить";
    public static final String OK = "OK";
    public static final String CANCEL = "Отменить";
    public static final String SHOW_STATS = "Показать статистику";

    public static final String[] PRIORITIES = { "Normal", "Cito", "Statim" };

    public static Validator<LocalDate> dateValidator = Validator.from(Objects::nonNull, FILL_WARNING);
    public static Validator<String> emptyValidator = Validator.from(str -> str.length() > 0, FILL_WARNING);

    /*public static Validator<String> lengthValidatorMax(String caption, int maxLength) {
        return new StringLengthValidator(String.format(LENGTH_WARNING_MAX, caption, maxLength), 0, maxLength);
    }

    public static Validator<String> lengthValidator(String caption, int minLength, int maxLength) {
        return new StringLengthValidator(String.format(LENGTH_WARNING_MINMAX, caption, minLength, maxLength), minLength, maxLength);
    }*/
}