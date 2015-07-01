/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author UyNguyen.ITUS
 */
public class Invite implements Serializable{
    private int id;
    private String doc_Code;
    
    private int id_sender;
    private String username_Sender;
    
    private Date date_Invite;
    private String doc_title;

    public Invite(int id,String doc_Code, int id_sender, String username_Sender, Date date_Invite, String doc_title) {
        this.id = id;
        this.doc_Code = doc_Code;
        this.id_sender = id_sender;
        this.username_Sender = username_Sender;
        this.date_Invite = date_Invite;
        this.doc_title = doc_title;
    }

    
    
    
    /**
     * @return the doc_Code
     */
    public String getDoc_Code() {
        return doc_Code;
    }

    /**
     * @param doc_Code the doc_Code to set
     */
    public void setDoc_Code(String doc_Code) {
        this.doc_Code = doc_Code;
    }

    /**
     * @return the id_sender
     */
    public int getId_sender() {
        return id_sender;
    }

    /**
     * @param id_sender the id_sender to set
     */
    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
    }

    /**
     * @return the username_Sender
     */
    public String getUsername_Sender() {
        return username_Sender;
    }

    /**
     * @param username_Sender the username_Sender to set
     */
    public void setUsername_Sender(String username_Sender) {
        this.username_Sender = username_Sender;
    }

    /**
     * @return the date_Invite
     */
    public Date getDate_Invite() {
        return date_Invite;
    }

    /**
     * @param date_Invite the date_Invite to set
     */
    public void setDate_Invite(Date date_Invite) {
        this.date_Invite = date_Invite;
    }

    /**
     * @return the doc_title
     */
    public String getDoc_title() {
        return doc_title;
    }

    /**
     * @param doc_title the doc_title to set
     */
    public void setDoc_title(String doc_title) {
        this.doc_title = doc_title;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
