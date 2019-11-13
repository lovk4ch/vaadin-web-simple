package com.haulmont.web.view;

import javax.servlet.annotation.WebServlet;

import com.haulmont.web.view.doctor.DoctorView;
import com.haulmont.web.view.patient.PatientView;
import com.haulmont.web.view.recipe.RecipeView;
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
public class MainView extends UI {
    @WebServlet(urlPatterns = "/*", name = "ProjectServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainView.class, productionMode = false)
    public static class ProjectServlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout main = new VerticalLayout();
        TabSheet tab = new TabSheet();
        tab.addTab(new DoctorView(), "Doctors");
        tab.addTab(new PatientView(), "Patients");
        tab.addTab(new RecipeView(), "Recipe List");
        main.addComponent(tab);
        setContent(main);
        setSizeFull();
    }
}