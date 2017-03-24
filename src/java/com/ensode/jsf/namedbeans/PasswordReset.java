/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.namedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.ensode.jsf.doa.PasswordResetDOA;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Sammy
 */
@ManagedBean(name = "prbean")
@SessionScoped
/**
 *
 * @author Sammy
 */
public class PasswordReset implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String securityQuestion;
    private String email;
    private String password;
    private String passwordConfirm;

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String validateEmailQuestion() throws NoSuchAlgorithmException {
        boolean valid = PasswordResetDOA.validateReset(email, securityQuestion, password);
        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("email", email);

            return "passRestSuccess?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Email and/or Security Question",
                            "Please enter correct Email and Question"));
            return "passResetFail";
        }
    }

}
