package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Utils {
    public static String getSessionId() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session.getId();
    }
}
