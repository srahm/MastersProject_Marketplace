/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.namedbeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Sammy
 */
@SuppressWarnings("unused")
@ManagedBean(name = "rgbean")
@RequestScoped

public class Registeration {

    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    Connection con = null;
    Statement stmt = null;
    String url = "jdbc:derby://209.10.74.31:1527/MyMarketPlacedb";
    String user = "APP";
    String dbpassword = "helloworld";

    int i = 0;

    private String password;
    private String firstname;
    private String email;
    private String lastname;
    private String securityQuestion;
    private String passwordConfirm;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public static String passwordEncrypt(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String add() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, dbpassword);

            String sql = "insert into MPP_USERS2 "
                    + " (LAST_NAME, FIRST_NAME,EMAIL_ADDRESS,PASSWORD,SECURITYQUESTION)" + " values (?,?,?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, lastname);
            ps.setString(2, firstname);
            ps.setString(3, email);

            password = passwordEncrypt(getPassword());
            ps.setString(4, password);

            securityQuestion = passwordEncrypt(getSecurityQuestion());
            ps.setString(5, securityQuestion);

            System.out.println("Insert complete.");
            ps.executeUpdate();
            System.out.println("Insert complete.");

        } catch (Exception e) {
            System.out.print("Failure");
            System.out.print(e);

            securityQuestion = null;

            return "registerFail";
        }

        return "registerAdmin?faces-redirect=true";
    }

    public String addAdmin() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, dbpassword);

            String sql = "insert into ADMINUSERS "
                    + " (LAST_NAME, FIRST_NAME,EMAIL_ADDRESS,PASSWORD,SECURITYQUESTION)" + " values (?,?,?,?,?)";

            String sql2 = "insert into MPP_USERS2 "
                    + " (LAST_NAME, FIRST_NAME,EMAIL_ADDRESS,PASSWORD,SECURITYQUESTION)" + " values (?,?,?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, lastname);
            ps.setString(2, firstname);
            ps.setString(3, email);

            password = passwordEncrypt(getPassword());
            ps.setString(4, password);

            securityQuestion = passwordEncrypt(getSecurityQuestion());
            ps.setString(5, securityQuestion);

            ps.executeUpdate();

            ps2 = con.prepareStatement(sql2);
            ps2.setString(1, lastname);
            ps2.setString(2, firstname);
            ps2.setString(3, email);

            password = passwordEncrypt(getPassword());
            ps2.setString(4, password);

            securityQuestion = passwordEncrypt(getSecurityQuestion());
            ps2.setString(5, securityQuestion);

            ps2.executeUpdate();
            System.out.println("Insert complete.");

        } catch (Exception e) {
            System.out.print("Failure");
            System.out.print(e);

            securityQuestion = null;

            return "registerFail";
        }

        return "adminHome";
    }

}
