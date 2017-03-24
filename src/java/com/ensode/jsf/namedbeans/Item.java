/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.namedbeans;

/**
 *
 * @author Sammy
 */
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "item")
@SessionScoped
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String imageID;
    private String email;
    private String itemDesc;
    private String price;
    private String date;
    private String keywords;
    private String firstname;
    private String lastname;

    OrderBean order;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;

    }

    public String getDate() {
        return date;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;

    }

    public String getKeywords() {
        return keywords;
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

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageId) {
        this.imageID = imageID;

    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    private static final ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();

    public ArrayList<OrderBean> getOrderList() {
        return orderList;
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Edited", ((OrderBean) event.getObject()).getImageID());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        orderList.remove((OrderBean) event.getObject());
    }
}
