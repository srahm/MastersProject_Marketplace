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
@ManagedBean(name = "sgbean")
@RequestScoped

public class InputBean {

    PreparedStatement ps = null;
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

            String sql = "insert into MPP_USERS "
                    + " (LAST_NAME, FIRST_NAME,EMAIL_ADDRESS,PASSWORD)" + " values (?, ?, ?, ?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, lastname);
            ps.setString(2, firstname);
            ps.setString(3, email);
            password = passwordEncrypt(getPassword());
            ps.setString(4, password);
            System.out.println("Insert complete.");
            ps.executeUpdate();
            System.out.println("Insert complete.");

        } catch (Exception e) {
            System.out.print("Failure");

            return "registerFail";
        }

        return "confirmation.xhtml?faces-redirect=true";
    }

}
