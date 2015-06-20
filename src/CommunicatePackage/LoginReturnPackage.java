/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommunicatePackage;

import Pojo.Account;
import Pojo.Document;
import Pojo.Invite;
import java.io.Serializable;

/**
 *
 * @author Thanh Tung
 */
public class LoginReturnPackage implements Serializable{
    public boolean result;
    public Account user;
    public Document[] documentList;
    public Invite[] inviteList;
    public LoginReturnPackage(boolean result, Account user, Document[] documentList, Invite[] invites){
        this.result = result;
        this.user = user;
        this.documentList = documentList;
        this.inviteList = invites;
    }
}
