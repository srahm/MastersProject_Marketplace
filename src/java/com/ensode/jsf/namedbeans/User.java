/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.namedbeans;

import java.sql.Statement;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.primefaces.model.UploadedFile;
import com.ensode.jsf.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "user")
@SessionScoped

public class User implements Serializable {

    private String userinfo;

    private String imageID;
    private String email;
    private String itemDesc;
    private String price;
    private String date;

    private String user;
    private String imageIDWatchlist;
    private UploadedFile file;
    private boolean editable;
    private String securityQuestion;
    private String password;
    private String passwordConfirm;
    private String firstname;
    private String lastname;

    boolean enter = true;
    User order;

    public String editAction(User order) {
        order.setEditable(true);

        return null;
    }

    Connection con = null;
    Connection con2 = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;
    Connection MySQLcon = null;
    Statement stmt = null;
    Statement stmt2 = null;
    PreparedStatement ps;
    ResultSet rs, r1;
    ResultSet rs2;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;

    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;

    }

    public String getImageLength() {
        return imageLength;
    }

    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    private String imageLength;

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getImageIDWatchlist() {
        return imageIDWatchlist;
    }

    public void setImageIDWatchlist(String imageIDWatchlist) {
        this.imageIDWatchlist = imageIDWatchlist;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
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

    //action listener event
    public void attrListener(ActionEvent event) {

        userinfo = (String) event.getComponent().getAttributes().get("email");
        firstname = (String) event.getComponent().getAttributes().get("firstname");
        lastname = (String) event.getComponent().getAttributes().get("lastname");

        System.out.print(userinfo);

        try {
            con = DataConnect.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT FIRST_NAME,LAST_NAME FROM MPP_USERS2 WHERE EMAIL_ADDRESS='" + userinfo + "'");
            
            while (rs.next()) {

                setLastname(rs.getString("LAST_NAME"));
                setFirstname(rs.getString("FIRST_NAME"));
                System.out.print("Lastname : " + lastname);

            }

        } catch (SQLException e) {
            System.out.println(e);
            System.err.println(e.getMessage());
        }

    }

    public List<User> getAllUsers() {
        List<User> imageInfo = new ArrayList<User>();

        con = DataConnect.getConnection();
        try {

            stmt = con.createStatement();
            String strSql = "SELECT EMAIL_ADDRESS,FIRST_NAME,LAST_NAME FROM MPP_USERS2";
            rs = stmt.executeQuery(strSql);

            while (rs.next()) {
                User tbl = new User();

                tbl.setEmail(rs.getString("EMAIL_ADDRESS"));
                tbl.setFirstname(rs.getString("FIRST_NAME"));
                tbl.setLastname(rs.getString("LAST_NAME"));

                imageInfo.add(tbl);

            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllUserImage::" + e.getMessage());
        }

        return imageInfo;
    }

    public List<User> getAllPosts() {
        List<User> imageInfo = new ArrayList<User>();

        con = DataConnect.getConnection();
        try {

            stmt = con.createStatement();
            String strSql = "select ID,DATE, EMAIL_ADDRESS, ITEM_DESC,PRICE from PICTURE_TABLE";
            rs = stmt.executeQuery(strSql);

            while (rs.next()) {
                User tbl = new User();
                tbl.setImageID(rs.getString("ID"));
                tbl.setDate(rs.getString("DATE"));

                tbl.setEmail(rs.getString("EMAIL_ADDRESS"));
                tbl.setItemDesc(rs.getString("ITEM_DESC"));
                tbl.setPrice(rs.getString("PRICE"));
                imageInfo.add(tbl);

            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllUserImage::" + e.getMessage());
        }

        return imageInfo;
    }

    public List<User> getAllUserImage() {
        List<User> imageInfo = new ArrayList<User>();

        con = DataConnect.getConnection();
        try {

            stmt = con.createStatement();
            String strSql = "select ID,EMAIL_ADDRESS, ITEM_DESC,PRICE from PICTURE_TABLE where EMAIL_ADDRESS= '" + userinfo + "'";
            rs = stmt.executeQuery(strSql);

            while (rs.next()) {
                User tbl = new User();
                tbl.setImageID(rs.getString("ID"));

                tbl.setEmail(rs.getString("EMAIL_ADDRESS"));
                tbl.setItemDesc(rs.getString("ITEM_DESC"));
                tbl.setPrice(rs.getString("PRICE"));
                imageInfo.add(tbl);

            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllUserImage::" + e.getMessage());
        }

        return imageInfo;
    }

    public List<User> getAllUserImageEdit() {
        List<User> imageInfo2 = new ArrayList<User>();

        con = DataConnect.getConnection();
        try {

            stmt = con.createStatement();
            String strSql = "select ID,EMAIL_ADDRESS, ITEM_DESC,PRICE from PICTURE_TABLE where EMAIL_ADDRESS= '" + userinfo + "'";
            rs = stmt.executeQuery(strSql);

            while (rs.next()) {
                User tbl = new User();
                tbl.setImageID(rs.getString("ID"));

                tbl.setEmail(rs.getString("EMAIL_ADDRESS"));
                tbl.setItemDesc(rs.getString("ITEM_DESC"));
                tbl.setPrice(rs.getString("PRICE"));
                imageInfo2.add(tbl);

            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllUserImage::" + e.getMessage());
        }

        return imageInfo2;
    }

    public List<User> getAllUserWatchlist() throws SQLException {
        List<User> imageInfo = new ArrayList<User>();

        con = DataConnect.getConnection();
        try {

            stmt = con.createStatement();
            stmt2 = con.createStatement();

            String strSql = "select WATCHLISTNUMBER from WATCHLIST where EMAIL_ADDRESS= '" + userinfo + "'";

            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                setImageIDWatchlist(rs.getString("WATCHLISTNUMBER"));
                System.out.print(imageIDWatchlist);
                String strSql2 = "select ID,EMAIL_ADDRESS, ITEM_DESC,PRICE from PICTURE_TABLE where ID= " + imageIDWatchlist;
                rs2 = stmt2.executeQuery(strSql2);
                while (rs2.next()) {
                    User tbl = new User();
                    tbl.setImageID(rs2.getString("ID"));

                    tbl.setEmail(rs2.getString("EMAIL_ADDRESS"));
                    tbl.setItemDesc(rs2.getString("ITEM_DESC"));
                    tbl.setPrice(rs2.getString("PRICE"));
                    imageInfo.add(tbl);

                }

            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllUserWatchlist::" + e);
        }

        return imageInfo;
    }

    public void onWatchlist(int imageID) {

        boolean exist = false;
        System.out.print("In get watchlist method" + userinfo + imageID);

        try {

            PreparedStatement ps = null;

            con2 = DataConnect.getConnection();

            ps = con.prepareStatement("Select EMAIL_ADDRESS,WATCHLISTNUMBER from WATCHLIST where EMAIL_ADDRESS = ? and WATCHLISTNUMBER = ?");
            ps.setString(1, userinfo);
            ps.setInt(2, imageID);
            rs = ps.executeQuery();
            if (rs.next() == true) {
                exist = true;
                System.out.print("watchlist after execute Query ");
                FacesMessage msg3 = new FacesMessage("Error: This item is already on your watchlist!");
                FacesContext.getCurrentInstance().addMessage(null, msg3);
            }
        } catch (Exception ex) {
            System.out.print("Error" + ex);

        }

        if (!exist) {
            try {
                Connection con2 = null;
                PreparedStatement ps = null;
                PreparedStatement ps2 = null;
                con2 = DataConnect.getConnection();

                ps = con.prepareStatement("Select EMAIL_ADDRESS,WATCHLISTNUMBER from WATCHLIST where EMAIL_ADDRESS = ? and WATCHLISTNUMBER = ?");
                ps.setString(1, userinfo);
                ps.setInt(2, imageID);
                rs = ps.executeQuery();

                System.out.print("watchlist after execute Query ");

                String sql = "insert into WATCHLIST "
                        + " (EMAIL_ADDRESS,WATCHLISTNUMBER)" + " values (?,?)";

                ps = con.prepareStatement(sql);
                ps.setString(1, userinfo);
                ps.setInt(2, imageID);

                ps.executeUpdate();
                System.out.println("Insert complete.");

                FacesMessage msg3 = new FacesMessage("Item Added to Watchlist");

                FacesContext.getCurrentInstance().addMessage(null, msg3);

            } catch (Exception e) {
                System.out.print("Failure");
                System.out.print(e);

            }
        }

    }

    public void onEdit(RowEditEvent event) {

        System.out.print("Price " + ((User) event.getObject()).price);

        imageID = ((User) event.getObject()).getImageID();
        price = ((User) event.getObject()).getPrice();

        System.out.print("in on edit price= " + price);

        System.out.print("in on edit image id= " + imageID);

        setPrice(((User) event.getObject()).getPrice());

        setItemDesc(((User) event.getObject()).getItemDesc());
        setEmail(((User) event.getObject()).getEmail());
        System.out.print(price);
        System.out.print(itemDesc);
        System.out.print(email);

        try {
            Connection con2 = null;
            PreparedStatement ps = null;
            PreparedStatement ps2 = null;
            con2 = DataConnect.getConnection();

            ps = con.prepareStatement("Select EMAIL_ADDRESS,ITEM_DESC,PRICE from PICTURE_TABLE where ID = ?");
            ps.setString(1, imageID);

            rs = ps.executeQuery();

            if (rs.next()) {

                ps2 = con.prepareStatement("update PICTURE_TABLE SET EMAIL_ADDRESS= ?, PRICE = ?, ITEM_DESC= ? WHERE id = " + imageID);

                ps2.setString(1, email);
                ps2.setString(2, price);
                ps2.setString(3, itemDesc);
                ps2.executeUpdate();

                FacesMessage msg = new FacesMessage("Edit Successful");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
            con2.commit();

        } catch (Exception ex) {

            System.out.println("This is an error: " + ex);

            FacesMessage msg = new FacesMessage("Edit Failed, Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public void onCancel(RowEditEvent event) throws SQLException {

        imageID = ((User) event.getObject()).getImageID();

        System.out.print(imageID);

        try {
            Connection con3 = null;
            PreparedStatement ps3 = null;
            PreparedStatement ps4 = null;
            con3 = DataConnect.getConnection();
            boolean leave = true;

            ps3 = con.prepareStatement("Select EMAIL_ADDRESS,ITEM_DESC,PRICE from PICTURE_TABLE where ID = ? ");

            ps3.setString(1, imageID);

            rs = ps3.executeQuery();

            if (rs.next()) {

                ps4 = con3.prepareStatement("DELETE from PICTURE_TABLE WHERE id = ?");

                ps4.setString(1, imageID);
                ps4.executeUpdate();

                FacesMessage msg2 = new FacesMessage("Item Deleted");

                FacesContext.getCurrentInstance().addMessage(null, msg2);

                con3.commit();
            }
            DataConnect.close(con3);

        } catch (Exception e) {
            System.out.print(e);

        }

    }

    public void onCancel3(RowEditEvent event) throws SQLException {

        imageID = ((User) event.getObject()).getEmail();

        System.out.print("Im the one being deleted" + imageID);
        System.out.print("Im the user: " + userinfo);

        try {
            Connection con3 = null;
            PreparedStatement ps3 = null;
            PreparedStatement ps4 = null;
            PreparedStatement ps5 = null;
            con3 = DataConnect.getConnection();
            boolean leave = true;

            ps3 = con.prepareStatement("Select EMAIL_ADDRESS from MPP_USERS2 where EMAIL_ADDRESS = ? ");

            ps3.setString(1, imageID);

            rs = ps3.executeQuery();

            if (imageID.equals(userinfo)) {
                FacesMessage msg2 = new FacesMessage("Error: you can't delete yourself!");

                FacesContext.getCurrentInstance().addMessage(null, msg2);

            } else {
                if (rs.next()) {

                    ps4 = con3.prepareStatement("DELETE from MPP_USERS2 WHERE EMAIL_ADDRESS = ?");

                    ps4.setString(1, imageID);
                    ps4.executeUpdate();

                   ps5 = con3.prepareStatement("DELETE from ADMINUSERS WHERE EMAIL_ADDRESS = ?");

                    ps5.setString(1, imageID);
                    ps5.executeUpdate();
                    
                    
                    FacesMessage msg3 = new FacesMessage("User Deleted");

                    FacesContext.getCurrentInstance().addMessage(null, msg3);

                }

                con3.commit();
            }
            DataConnect.close(con3);

        } catch (Exception e) {
            System.out.print(e);

        }

        try {
            Connection con3 = null;
            PreparedStatement ps3 = null;
            PreparedStatement ps4 = null;
            con3 = DataConnect.getConnection();
            boolean leave = true;

            ps3 = con.prepareStatement("Select EMAIL_ADDRESS from PICTURE_TABLE where EMAIL_ADDRESS = ? ");

            ps3.setString(1, imageID);

            rs = ps3.executeQuery();

            if (imageID.equals(userinfo)) {

            } else if (rs.next()) {

                ps4 = con3.prepareStatement("DELETE from PICTURE_TABLE WHERE EMAIL_ADDRESS = ?");

                ps4.setString(1, imageID);
                ps4.executeUpdate();

                FacesMessage msg2 = new FacesMessage("User Items Deleted");

                FacesContext.getCurrentInstance().addMessage(null, msg2);

                con3.commit();
            }
            DataConnect.close(con3);

        } catch (Exception e) {
            System.out.print(e);

        }
        try {
            Connection con3 = null;
            PreparedStatement ps3 = null;
            PreparedStatement ps4 = null;
            con3 = DataConnect.getConnection();
            boolean leave = true;

            ps3 = con.prepareStatement("Select EMAIL_ADDRESS from WATCHLIST where EMAIL_ADDRESS = ? ");

            ps3.setString(1, imageID);

            rs = ps3.executeQuery();

            if (imageID.equals(userinfo)) {

            } else {

                if (rs.next()) {

                    ps4 = con3.prepareStatement("DELETE from WATCHLIST WHERE EMAIL_ADDRESS = ?");

                    ps4.setString(1, imageID);
                    ps4.executeUpdate();

                    FacesMessage msg2 = new FacesMessage("User Watch List Deleted");

                    FacesContext.getCurrentInstance().addMessage(null, msg2);

                }

                con3.commit();
            }
            DataConnect.close(con3);

        } catch (Exception e) {
            System.out.print(e);

        }
    }

    public void onCancel2(RowEditEvent event) throws SQLException {
        List<User> imageInfo = new ArrayList<User>();

        imageInfo.remove((User) event.getObject());
        System.out.print("in cancel 2 ==" + imageInfo);

        imageID = ((User) event.getObject()).getImageID();

        System.out.print("in cancel 2 image id: " + imageID);
        System.out.print("in cancel 2 image id: " + userinfo);

        try {
            Connection con3 = null;
            PreparedStatement ps3 = null;
            PreparedStatement ps4 = null;
            con3 = DataConnect.getConnection();
            boolean leave = true;

            ps3 = con.prepareStatement("Select EMAIL_ADDRESS,WATCHLISTNUMBER from WATCHLIST where EMAIL_ADDRESS= ? and WATCHLISTNUMBER=? ");

            ps3.setString(1, userinfo);
            ps3.setString(2, imageID);

            rs = ps3.executeQuery();

            if (rs.next()) {

                ps4 = con3.prepareStatement("delete FROM WATCHLIST where EMAIL_ADDRESS= ? and WATCHLISTNUMBER=? ");
                ps4.setString(1, userinfo);
                ps4.setString(2, imageID);
                ps4.executeUpdate();

                FacesMessage msg2 = new FacesMessage("Item Deleted");

                FacesContext.getCurrentInstance().addMessage(null, msg2);

                con3.commit();
            }
            DataConnect.close(con3);

        } catch (Exception e) {
            System.out.print(e);

        }

    }

    public String sendEmailUpdate() {

        System.out.print(email);
        System.out.print(userinfo);

        try {
            Connection con = null;

            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;

            con = DataConnect.getConnection();

            ps = con.prepareStatement("Select EMAIL_ADDRESS from MPP_USERS2 where EMAIL_ADDRESS = ?");

            ps.setString(1, email);

            rs = ps.executeQuery();

            System.out.print("IN1");

            if (rs.next()==false) {

                ps2 = con.prepareStatement("update MPP_USERS2 SET EMAIL_ADDRESS= ? where EMAIL_ADDRESS = ?");

                ps2.setString(1, email);
                ps2.setString(2, userinfo);

                ps2.executeUpdate();

                System.out.print("IN3");

                FacesMessage msg = new FacesMessage("Edit Successful");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                
                con.commit();
            
                con.close();

            }
            else{
                FacesMessage msg = new FacesMessage("Failure: This Email already exists");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                
                return "editEmail.xhtml";
            
            }
            

        } catch (Exception ex) {

            System.out.println("This is an error: " + ex);

        }
        
        

        try {

            Connection con2 = null;
            con2 = DataConnect.getConnection();
            PreparedStatement ps4 = null;
            PreparedStatement ps5 = null;
            ps4 = con2.prepareStatement("Select EMAIL_ADDRESS from WATCHLIST where EMAIL_ADDRESS = ?");
            ps4.setString(1, userinfo);

            rs2 = ps4.executeQuery();

            if (rs2.next()) {

                ps5 = con.prepareStatement("update WATCHLIST SET EMAIL_ADDRESS= ? where EMAIL_ADDRESS = ?");

                ps5.setString(1, email);
                ps5.setString(2, userinfo);

                ps5.executeUpdate();

                System.out.print("IN666");

                

            }
            con2.commit();

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Edit Failed");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

        try {
            Connection con3 = null;
            ResultSet rs3;

            con3 = DataConnect.getConnection();

            PreparedStatement ps1 = null;

            ps1 = con3.prepareStatement("Select EMAIL_ADDRESS from PICTURE_TABLE where EMAIL_ADDRESS = ?");
            ps1.setString(1, userinfo);

            PreparedStatement ps5 = null;

            rs3 = ps1.executeQuery();

            if (rs3.next()) {

                ps5 = con.prepareStatement("update PICTURE_TABLE SET EMAIL_ADDRESS= ? where EMAIL_ADDRESS = ?");

                ps5.setString(1, email);
                ps5.setString(2, userinfo);

                ps5.executeUpdate();

                System.out.print("IN666");


            }
            con2.commit();

        } catch (Exception e) {

        }

        return "editConfirmation.xhtml";

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

    public String updatePassword() {

        System.out.print(email);
        System.out.print(userinfo);

        try {
            Connection con6 = null;

            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;

            con6 = DataConnect.getConnection();

            ps = con6.prepareStatement("Select EMAIL_ADDRESS from MPP_USERS2 where EMAIL_ADDRESS = ?");

            ps.setString(1, userinfo);

            rs = ps.executeQuery();

            System.out.print("IN1");

            String hashpass = passwordEncrypt(password);

            String hashpassConfirm = passwordEncrypt(passwordConfirm);
            System.out.print(hashpass);
            System.out.print(hashpassConfirm);

            if (hashpass.equals(hashpassConfirm)) {

                if (rs.next()) {

                    ps2 = con6.prepareStatement("update MPP_USERS2 SET PASSWORD= ? where EMAIL_ADDRESS = ?");

                    ps2.setString(1, hashpass);
                    ps2.setString(2, userinfo);

                    ps2.executeUpdate();

                    System.out.print("IN3");

                    FacesMessage msg = new FacesMessage("Edit Successful");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                }
                con6.commit();
                con6.close();

            } else {
                FacesMessage msg = new FacesMessage("Edit Failed Password Needs to match");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "editPasswordFail.xhtml";

            }

        } catch (Exception ex) {

            System.out.println("This is an error: " + ex);

        }

        return "admin.xhtml";

    }

    public String updateSecQuestion() {
        System.out.print(email);
        System.out.print(userinfo);

        try {
            Connection con7 = null;

            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;

            con7 = DataConnect.getConnection();

            ps = con7.prepareStatement("Select EMAIL_ADDRESS from MPP_USERS2 where EMAIL_ADDRESS = ?");

            ps.setString(1, userinfo);

            rs = ps.executeQuery();

            System.out.print("IN1");

            String hashQuestion = passwordEncrypt(securityQuestion);

            if (rs.next()) {

                ps2 = con7.prepareStatement("update MPP_USERS2 SET SECURITYQUESTION= ? where EMAIL_ADDRESS = ?");

                ps2.setString(1, hashQuestion);
                ps2.setString(2, userinfo);

                ps2.executeUpdate();

                System.out.print("IN3");

                FacesMessage msg = new FacesMessage("Edit Successful");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
            con7.commit();
            con7.close();

        } catch (SQLException | NoSuchAlgorithmException ex) {

            System.out.println("This is an error: " + ex);
            FacesMessage msg = new FacesMessage("Edit Failed Password Needs to match");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

        return "admin.xhtml";
    }

    public String sendEmailUpdateAdmin() {

        System.out.print(email);
        System.out.print(userinfo);

        try {
            Connection con = null;

           
            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;
            PreparedStatement ps4 = null;

            con = DataConnect.getConnection();

            ps = con.prepareStatement("Select EMAIL_ADDRESS from MPP_USERS2 where EMAIL_ADDRESS = ?");
            ps4 = con.prepareStatement("Select EMAIL_ADDRESS from ADMINUSERS where EMAIL_ADDRESS = ?");

            ps.setString(1, userinfo);
            ps4.setString(1, userinfo);

            rs = ps4.executeQuery();
            r1 = ps.executeQuery();
            
            System.out.print("Print statment rs: " + rs.next());
            System.out.print("Print statment r1: " + r1.next());


            System.out.print("IN1");

            if (rs.next()==false && r1.next()==false) {

                ps2 = con.prepareStatement("update MPP_USERS2 SET EMAIL_ADDRESS= ? where EMAIL_ADDRESS = ?");
                ps3 = con.prepareStatement("update ADMINUSERS SET EMAIL_ADDRESS= ? where EMAIL_ADDRESS = ?");

                ps2.setString(1, email);
                ps2.setString(2, userinfo);

                ps3.setString(1, email);
                ps3.setString(2, userinfo);

                ps2.executeUpdate();
                ps3.executeUpdate();

                System.out.print("IN3");

                FacesMessage msg = new FacesMessage("Edit Successful");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                
                con.commit();
           
                con.close();

            }
            else{
                                
                FacesMessage msg = new FacesMessage("Failure: This email already exists.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                
                return "adminEditEmail.xhtml";
            
            }
            
            
            
           
           

        } catch (Exception ex) {

            System.out.println("This is an error: " + ex);
                     FacesMessage msg = new FacesMessage("Failure: This email already exists.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
             return "adminEditEmail.xhtml";

        }

        try {

            Connection con2 = null;
            con2 = DataConnect.getConnection();
            PreparedStatement ps4 = null;
            PreparedStatement ps5 = null;
            ps4 = con2.prepareStatement("Select EMAIL_ADDRESS from WATCHLIST where EMAIL_ADDRESS = ?");
            ps4.setString(1, userinfo);

            rs2 = ps4.executeQuery();

            if (rs2.next()) {

                ps5 = con.prepareStatement("update WATCHLIST SET EMAIL_ADDRESS= ? where EMAIL_ADDRESS = ?");

                ps5.setString(1, email);
                ps5.setString(2, userinfo);

                ps5.executeUpdate();

                System.out.print("IN666");

            

            }
            con2.commit();

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Edit Failed");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

        try {
            Connection con3 = null;
            ResultSet rs3;

            con3 = DataConnect.getConnection();

            PreparedStatement ps1 = null;

            ps1 = con3.prepareStatement("Select EMAIL_ADDRESS from PICTURE_TABLE where EMAIL_ADDRESS = ?");
            ps1.setString(1, userinfo);

            PreparedStatement ps5 = null;

            rs3 = ps1.executeQuery();

            if (rs3.next()) {

                ps5 = con.prepareStatement("update PICTURE_TABLE SET EMAIL_ADDRESS= ? where EMAIL_ADDRESS = ?");

                ps5.setString(1, email);
                ps5.setString(2, userinfo);

                ps5.executeUpdate();

                System.out.print("IN666");

             

            }
            con2.commit();

        } catch (Exception e) {

        }

        return "editConfirmation.xhtml";

    }

    public String updatePasswordAdmin() {

        System.out.print(email);
        System.out.print(userinfo);

        try {
            Connection con6 = null;

            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;

            con6 = DataConnect.getConnection();

            ps = con6.prepareStatement("Select EMAIL_ADDRESS from MPP_USERS2 where EMAIL_ADDRESS = ?");
            ps1 = con6.prepareStatement("Select EMAIL_ADDRESS from ADMINUSERS where EMAIL_ADDRESS = ?");

            ps.setString(1, userinfo);
            ps1.setString(1, userinfo);

            rs = ps.executeQuery();
            rs = ps1.executeQuery();

            System.out.print("IN1");

            String hashpass = passwordEncrypt(password);

            String hashpassConfirm = passwordEncrypt(passwordConfirm);
            System.out.print(hashpass);
            System.out.print(hashpassConfirm);

            if (hashpass.equals(hashpassConfirm)) {

                if (rs.next()) {

                    ps2 = con6.prepareStatement("update MPP_USERS2 SET PASSWORD= ? where EMAIL_ADDRESS = ?");
                    ps3 = con6.prepareStatement("update ADMINUSERS SET PASSWORD= ? where EMAIL_ADDRESS = ?");

                    ps2.setString(1, hashpass);
                    ps2.setString(2, userinfo);

                    ps3.setString(1, hashpass);
                    ps3.setString(2, userinfo);

                    ps2.executeUpdate();
                    ps3.executeUpdate();

                    System.out.print("IN3");

                    FacesMessage msg = new FacesMessage("Edit Successful");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                }
                con6.commit();
                con6.close();

            } else {
                FacesMessage msg = new FacesMessage("Edit Failed Password Needs to match");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "editPasswordFailAdmin.xhtml";

            }

        } catch (Exception ex) {

            System.out.println("This is an error: " + ex);

        }

        return "adminHome.xhtml";

    }

    public String updateSecQuestionAdmin() {
        System.out.print(email);
        System.out.print(userinfo);

        try {
            Connection con7 = null;

            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;

            con7 = DataConnect.getConnection();

            ps = con7.prepareStatement("Select EMAIL_ADDRESS from MPP_USERS2 where EMAIL_ADDRESS = ?");
            ps1 = con7.prepareStatement("Select EMAIL_ADDRESS from ADMINUSERS where EMAIL_ADDRESS = ?");

            ps.setString(1, userinfo);
            ps1.setString(1, userinfo);

            rs = ps.executeQuery();
            rs = ps1.executeQuery();

            System.out.print("IN1");

            String hashQuestion = passwordEncrypt(securityQuestion);

            if (rs.next()) {

                ps2 = con7.prepareStatement("update MPP_USERS2 SET SECURITYQUESTION= ? where EMAIL_ADDRESS = ?");
                ps3 = con7.prepareStatement("update ADMINUSERS SET SECURITYQUESTION= ? where EMAIL_ADDRESS = ?");

                ps2.setString(1, hashQuestion);
                ps2.setString(2, userinfo);

                ps3.setString(1, hashQuestion);
                ps3.setString(2, userinfo);

                ps2.executeUpdate();
                ps3.executeUpdate();

                System.out.print("IN3");

                FacesMessage msg = new FacesMessage("Edit Successful");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
            con7.commit();
            con7.close();

        } catch (SQLException | NoSuchAlgorithmException ex) {

            System.out.println("This is an error: " + ex);
            FacesMessage msg = new FacesMessage("Edit Failed Password Needs to match");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

        return "adminHome.xhtml";
    }

}
