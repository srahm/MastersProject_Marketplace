/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.validator;

/**
 *
 * @author Sammy
 */
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String password = value.toString();
        System.out.print("pass" + password);
        int passLength = password.length();

        UIInput uiInputpasswordConfirm = (UIInput) component.getAttributes().get("passwordConfirm");
        String passwordConfirm = uiInputpasswordConfirm.getSubmittedValue()
                .toString();
        System.out.print(passwordConfirm);

        // Let required="true" do its job.
        if (password == null || password.isEmpty() || passwordConfirm == null
                || passwordConfirm.isEmpty()) {
            return;
        }
        if (passLength < 6) {
            uiInputpasswordConfirm.setValid(false);
            throw new ValidatorException(new FacesMessage(
                    "Password be at least 6 characters"));
        }

        if (!password.equals(passwordConfirm)) {
            uiInputpasswordConfirm.setValid(false);
            throw new ValidatorException(new FacesMessage(
                    "Passwords must match"));
        }

        System.out.print("out");

    }
}
