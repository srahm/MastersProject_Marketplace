/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.namedbeans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Sammy
 */
@ManagedBean(name = "sendAdminEmail")
@SessionScoped
public class sendAdminEmail implements Serializable {

    private String email;
    private String itemDesc;

    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;

    }

    public String sendIt() {

        final String username = "neiumarketplace@gmail.com";
        final String password = "helloworld12";
        final String website = "www.google.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(username));
            message.setSubject("Comments and concerns from MyMarketPlace user");
            message.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            System.out.println("Done");
            Transport.send(message);

            Message message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("Receipt");
            message2.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("MyMarketPlace Support Team");
            message2.setText("Hello " + email + "," + "\n\n\n Thank you for contacting us. This is an automated response confirming the receipt of your recently submitted question. "
                    + "Our team will get back to you as soon as possible.  "
                    + "\n\n\n Best," + "\n MyMarketPlace Team." + "\n" + username);

            System.out.println("Done2");
            Transport.send(message2);

        } catch (Exception e) {

            System.out.println(e);
            System.err.println(e.getMessage());
          
        }
        itemDesc = null;
        email = null;
        FacesMessage msg3 = new FacesMessage("Sent Successfully. Please check you NEIU mailbox");
        FacesContext.getCurrentInstance().addMessage(null, msg3);

        return "AboutUs.xhtml";

    }
    public String sendItreg() {

        final String username = "neiumarketplace@gmail.com";
        final String password = "helloworld12";
        final String website = "www.google.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(username));
            message.setSubject("Comments and concerns from MyMarketPlace user");
            message.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            System.out.println("Done");
            Transport.send(message);

            Message message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("Receipt");
            message2.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("MyMarketPlace Support Team");
            message2.setText("Hello " + email + "," + "\n\n\n Thank you for contacting us. This is an automated response confirming the receipt of your recently submitted question. "
                    + "Our team will get back to you as soon as possible.  "
                    + "\n\n\n Best," + "\n MyMarketPlace Team." + "\n" + username);

            System.out.println("Done2");
            Transport.send(message2);

        } catch (Exception e) {

            System.out.println(e);
            System.err.println(e.getMessage());
               FacesMessage msg3 = new FacesMessage("Failed to send. Please try again");

            FacesContext.getCurrentInstance().addMessage(null, msg3);
            
        }
        itemDesc = null;
        email = null;
        FacesMessage msg3 = new FacesMessage("Sent Successfully. Please check you NEIU mailbox");

            FacesContext.getCurrentInstance().addMessage(null, msg3);

        return "AboutUs2.xhtml";

    }
    
    public String sendItAdmin() {

        final String username = "neiumarketplace@gmail.com";
        final String password = "helloworld12";
        final String website = "www.google.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(username));
            message.setSubject("Comments and concerns from MyMarketPlace user");
            message.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            System.out.println("Done");
            Transport.send(message);

            Message message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("Receipt");
            message2.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("MyMarketPlace Support Team");
            message2.setText("Hello " + email + "," + "\n\n\n Thank you for contacting us. This is an automated response confirming the receipt of your recently submitted question. "
                    + "Our team will get back to you as soon as possible.  "
                    + "\n\n\n Best," + "\n MyMarketPlace Team." + "\n" + username);

            System.out.println("Done2");
            Transport.send(message2);

        } catch (Exception e) {

            System.out.println(e);
            System.err.println(e.getMessage());
            
        }
        itemDesc = null;
        email = null;
        FacesMessage msg3 = new FacesMessage("Sent Successfully. Please check you NEIU mailbox");

            FacesContext.getCurrentInstance().addMessage(null, msg3);

        return "AdminAboutus.xhtml";

    }
public String sendIt2() {

        final String username = "neiumarketplace@gmail.com";
        final String password = "helloworld12";
        final String website = "www.google.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(username));
            message.setSubject("Comments and concerns from MyMarketPlace user");
            message.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            System.out.println("Done");
            Transport.send(message);

            Message message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("Receipt");
            message2.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("MyMarketPlace Support Team");
            message2.setText("Hello " + email + "," + "\n\n\n Thank you for contacting us. This is an automated response confirming the receipt of your recently submitted question. "
                    + "Our team will get back to you as soon as possible.  "
                    + "\n\n\n Best," + "\n MyMarketPlace Team." + "\n" + username);

            System.out.println("Done2");
            Transport.send(message2);

        } catch (Exception e) {

            System.out.println(e);
            System.err.println(e.getMessage());
                  FacesMessage msg3 = new FacesMessage("Failed to Send. Please try again!");

            FacesContext.getCurrentInstance().addMessage(null, msg3);
        }
        itemDesc = null;
        email = null;
              FacesMessage msg3 = new FacesMessage("Sent Successfully. Please check you NEIU mailbox");

            FacesContext.getCurrentInstance().addMessage(null, msg3);

        return "AboutUs3.xhtml";

    }
    public void sendItRegister() {

        final String username = "neiumarketplace@gmail.com";
        final String password = "helloworld12";
        final String website = "http://www.neiumarketplace.com/jsf/faces/registerJsp.xhtml";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(username));
            message.setSubject("Comments and concerns from MyMarketPlace user");
            message.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            System.out.println("Done");
            Transport.send(message);

            Message message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("Receipt");
            message2.setText(itemDesc
                    + "\n\n\n Best," + "\n" + email);
            message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress("neiumarketplace@gmail.com"));
            message2.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message2.setSubject("MyMarketPlace Support Team");
            message2.setText("Hello " + email + "," + "\n\n\n Thank you for choosing My MarketPlace! "
                    + "Our team has verified your email and now you can begin the registration process. The link provided below will allow you to register and gain access to the Marketplace. \n\n" + website
                    + "\n\n\n Best," + "\n MyMarketPlace Team." + "\n" + username);

            System.out.println("Done2");
            Transport.send(message2);
            FacesMessage msg3 = new FacesMessage("Sent Succesfully! Please check you neiu mailbox");

            FacesContext.getCurrentInstance().addMessage(null, msg3);

        } catch (Exception e) {

            System.out.println(e);
            System.err.println(e.getMessage());
            FacesMessage msg3 = new FacesMessage("Failed to Send. Please try again!");

            FacesContext.getCurrentInstance().addMessage(null, msg3);
        }
        itemDesc = null;
        email = null;

    }
}
