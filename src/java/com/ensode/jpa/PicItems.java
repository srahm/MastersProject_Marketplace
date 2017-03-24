/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "PIC_ITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PicItems.findAll", query = "SELECT p FROM PicItems p"),
    @NamedQuery(name = "PicItems.findByEmailAddress", query = "SELECT p FROM PicItems p WHERE p.emailAddress = :emailAddress"),
    @NamedQuery(name = "PicItems.findByItemDesc", query = "SELECT p FROM PicItems p WHERE p.itemDesc = :itemDesc")})
public class PicItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Size(max = 255)
    @Column(name = "ITEM_DESC")
    private String itemDesc;
    @Lob
    @Column(name = "PICTURES")
    private Serializable pictures;

    public PicItems() {
    }

    public PicItems(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Serializable getPictures() {
        return pictures;
    }

    public void setPictures(Serializable pictures) {
        this.pictures = pictures;
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
        if (!(object instanceof PicItems)) {
            return false;
        }
        PicItems other = (PicItems) object;
        if ((this.emailAddress == null && other.emailAddress != null) || (this.emailAddress != null && !this.emailAddress.equals(other.emailAddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ensode.jpa.PicItems[ emailAddress=" + emailAddress + " ]";
    }
    
}
