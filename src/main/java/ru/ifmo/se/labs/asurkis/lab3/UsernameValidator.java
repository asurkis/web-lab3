package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ru.ifmo.se.labs.asurkis.lab3.UsernameValidator")
public class UsernameValidator implements Validator<String> {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, String s) throws ValidatorException {
        FacesMessage msg = new FacesMessage("Введите новое имя пользователя");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (s.isEmpty()) {
            throw new ValidatorException(msg);
        }
    }
}
