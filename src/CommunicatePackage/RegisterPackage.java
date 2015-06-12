/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommunicatePackage;

import java.io.Serializable;

/**
 *
 * @author ThanhTung
 */
public class RegisterPackage implements Serializable {

    public String username;
    public String password;
    public String email;

    public RegisterPackage(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
