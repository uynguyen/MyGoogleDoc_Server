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
    
    private String doc_Code;
    
    private int id_sender;
    private String username_Sender;
    
    private Date date_Invite;

    public Invite(String doc_Code, int id_sender, String username_Sender, Date date_Invite) {
        this.doc_Code = doc_Code;
        this.id_sender = id_sender;
        this.username_Sender = username_Sender;
        this.date_Invite = date_Invite;
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
    
    
}
