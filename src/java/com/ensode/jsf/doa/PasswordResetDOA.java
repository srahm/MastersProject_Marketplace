/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.doa;


import com.ensode.jsf.util.DataConnect;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Sammy
 */
public class PasswordResetDOA {

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

    public static boolean validateReset(String email, String securityQuestion, String password) throws NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select EMAIL_ADDRESS,SECURITYQUESTION from MPP_USERS2 where EMAIL_ADDRESS = ? and SECURITYQUESTION = ? ");
            ps.setString(1, email);

            String encrptedQuestion = passwordEncrypt(securityQuestion);

            ps.setString(2, encrptedQuestion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement("update MPP_USERS2 set PASSWORD=? where EMAIL_ADDRESS=? ");

                System.out.print("in");
                String encrptedPassword = passwordEncrypt(password);

                ps2.setString(1, encrptedPassword);
                ps2.setString(2, email);

                ps2.executeUpdate();
                con.commit();
                System.out.print(email);
                System.out.print(encrptedPassword);

                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

}
