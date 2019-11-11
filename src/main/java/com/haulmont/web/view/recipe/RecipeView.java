package com.haulmont.web.view.recipe;

import com.haulmont.web.controller.Service;
import com.haulmont.web.view.Consts;
import com.haulmont.web.view.recipe.sub.RecipeRow;
import com.vaadin.data.HasValue;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;

import java.util.stream.Collectors;

public class RecipeView extends VerticalLayout {

    private RecipeEdit recipeEdit = new RecipeEdit(this);

    private TextField filterPatient, filterDescription;
    private ComboBox filterPriority;

    private Service service = Service.getInstance();
    private Button addButton, updateButton, deleteButton;

    private Grid<RecipeRow> grid = new Grid<>(RecipeRow.class);

    public RecipeView() {
        HasValue.ValueChangeListener changeListener = (HasValue.ValueChangeListener) valueChangeEvent -> filter(
            filterPatient.getValue(),
            filterPriority.getSelectedItem().isPresent() ?
            filterPriority.getSelectedItem().get().toString() : "",
            filterDescription.getValue());

        filterPatient = new TextField();
        filterPatient.setValueChangeMode(ValueChangeMode.EAGER);
        filterPatient.setPlaceholder("Filter by patient...");
        filterPatient.addValueChangeListener(changeListener);

        filterPriority = new ComboBox();
        filterPriority.setPlaceholder("Filter by priority...");
        filterPriority.setTextInputAllowed(false);
        filterPriority.setItems(Consts.PRIORITIES);
        filterPriority.addValueChangeListener(changeListener);

        filterDescription = new TextField();
        filterDescription.setValueChangeMode(ValueChangeMode.EAGER);
        filterDescription.setPlaceholder("Filter by description");
        filterDescription.addValueChangeListener(changeListener);

        HorizontalLayout filters = new HorizontalLayout(filterPatient, filterDescription, filterPriority);
        addComponent(filters);

        grid.setColumns("description", "doctor", "patient", "creationDate", "validity", "priority");
        grid.setSizeFull();
        addComponents(grid);

        addButton = new Button(Consts.ADD,
            event -> recipeEdit.add(new RecipeRow()));

        updateButton = new Button(Consts.UPDATE,
            event -> {
                if (grid.asSingleSelect().getValue() != null) {
                    recipeEdit.edit(grid.asSingleSelect().getValue());
                }
                else {
                    Notification.show(Consts.SELECT_WARNING);
                }
            });

        deleteButton = new Button(Consts.DELETE,
            event -> {
                if (grid.asSingleSelect().getValue() != null) {
                    service.deleteRecipe(grid.asSingleSelect().getValue().toRecipe());
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

    private void filter(String patient, String priority, String description) {
        grid.setItems(service.findAllRecipes().stream()
            .map(RecipeRow::new)
            .filter(recipeRow -> recipeRow.getPatient().toLowerCase().contains(patient))
            .filter(recipeRow -> recipeRow.getPriority().contains(priority))
            .filter(recipeRow -> recipeRow.getDescription().toLowerCase().contains(description))
            .collect(Collectors.toList()));
    }

    public void updateList() {
        grid.setItems(service.findAllRecipes().stream().map(RecipeRow::new).collect(Collectors.toList()));
    }
}