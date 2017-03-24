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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "PICTURE_TABLE", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PictureTable.findAll", query = "SELECT p FROM PictureTable p"),
    @NamedQuery(name = "PictureTable.findById", query = "SELECT p FROM PictureTable p WHERE p.id = :id"),
    @NamedQuery(name = "PictureTable.findByEmailAddress", query = "SELECT p FROM PictureTable p WHERE p.emailAddress = :emailAddress"),
    @NamedQuery(name = "PictureTable.findByItemDesc", query = "SELECT p FROM PictureTable p WHERE p.itemDesc = :itemDesc"),
    @NamedQuery(name = "PictureTable.findByPrice", query = "SELECT p FROM PictureTable p WHERE p.price = :price"),
    @NamedQuery(name = "PictureTable.findByDate", query = "SELECT p FROM PictureTable p WHERE p.date = :date")})
public class PictureTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Size(max = 1024)
    @Column(name = "ITEM_DESC")
    private String itemDesc;
    @Lob
    @Column(name = "PICTURES")
    private Serializable pictures;
    @Size(max = 15)
    @Column(name = "PRICE")
    private String price;
    @Size(max = 255)
    @Column(name = "DATE")
    private String date;

    public PictureTable() {
    }

    public PictureTable(Integer id) {
        this.id = id;
    }

    public PictureTable(Integer id, String emailAddress) {
        this.id = id;
        this.emailAddress = emailAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PictureTable)) {
            return false;
        }
        PictureTable other = (PictureTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ensode.jpa.PictureTable[ id=" + id + " ]";
    }
    
}
