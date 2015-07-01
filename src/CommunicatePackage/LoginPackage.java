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
public class LoginPackage implements Serializable{
    public String username;
    public String password;
    
    public LoginPackage(String username, String password){
        this.username = username;
        this.password = password;
    }
}
