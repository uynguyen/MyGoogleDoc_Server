/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author UyNguyen.ITUS
 */
public class Account {

    public Account(){}
    public Account(String _Username, String _Avatar, String _EMail) {
      
        this._Username = _Username;
        this._Avatar = _Avatar;
        this._EMail = _EMail;
    }
  
    private String _Username;
    private String _Avatar;
    private String _EMail;

    
    
    
  

    /**
     * @return the _Username
     */
    public String getUsername() {
        return _Username;
    }

    /**
     * @param _Username the _Username to set
     */
    public void setUsername(String _Username) {
        this._Username = _Username;
    }

    /**
     * @return the _Avatar
     */
    public String getAvatar() {
        return _Avatar;
    }

    /**
     * @param _Avatar the _Avatar to set
     */
    public void setAvatar(String _Avatar) {
        this._Avatar = _Avatar;
    }

    /**
     * @return the _EMail
     */
    public String getEMail() {
        return _EMail;
    }

    /**
     * @param _EMail the _EMail to set
     */
    public void setEMail(String _EMail) {
        this._EMail = _EMail;
    }
           
}
