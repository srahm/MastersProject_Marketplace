/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jpa;

import java.io.Serializable;
import java.util.Collections;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sammy
 */
@Entity
@Table(name = "MPP_USERS")
@XmlRootElement
@NamedQueries({
    
    @NamedQuery(name = "MppUsers.findAll", query = "SELECT m FROM MppUsers m"),
    @NamedQuery(name = "MppUsers.findByEmailAddress", query = "SELECT m FROM MppUsers m WHERE m.emailAddress = :emailAddress"),
    @NamedQuery(name = "MppUsers.findByFirstName", query = "SELECT m FROM MppUsers m WHERE m.firstName = :firstName"),
    @NamedQuery(name = "MppUsers.findByLastName", query = "SELECT m FROM MppUsers m WHERE m.lastName = :lastName"),
    @NamedQuery(name = "MppUsers.findByPassword", query = "SELECT m FROM MppUsers m WHERE m.password = :password")})
public class MppUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Size(max = 255)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 255)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;

    public MppUsers() {
    }

    public MppUsers(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailAddress != null ? emailAddress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MppUsers)) {
            return false;
        }
        MppUsers other = (MppUsers) object;
        if ((this.emailAddress == null && other.emailAddress != null) || (this.emailAddress != null && !this.emailAddress.equals(other.emailAddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ensode.jpa.MppUsers[ emailAddress=" + emailAddress + " ]";
    }
    
}
