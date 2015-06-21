/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommunicatePackage;

import java.io.Serializable;

/**
 *
 * @author Thanh Tung
 */
public class ReplyInvitePackage implements Serializable{    
    public boolean _reply;
    public int _idInvite;
    public String _docCode;
    public int _idClient;
    
    public ReplyInvitePackage(boolean reply, int idInvite, String docCode, int idClient){
        this._reply = reply;
        this._idInvite = idInvite;
        this._docCode = docCode;
        this._idClient = idClient;
    }
}
