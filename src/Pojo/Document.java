/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.util.Date;

/**
 *
 * @author UyNguyen.ITUS
 */
public class Document {

    public Document(int _ID, String _Name, String _Path, Date _DateCreate, int _IDOwner, int _IDPartners) {
        this._ID = _ID;
        this._Name = _Name;
        this._Path = _Path;
        this._DateCreate = _DateCreate;
        this._IDOwner = _IDOwner;
        this._IDPartners = _IDPartners;
    }
    
    
    
    
    private int _ID;
    private String _Name;
    private String _Path;
    private Date _DateCreate;
    private int _IDOwner;
    private int _IDPartners;

    /**
     * @return the _ID
     */
    public int getID() {
        return _ID;
    }

    /**
     * @param _ID the _ID to set
     */
    public void setID(int _ID) {
        this._ID = _ID;
    }

    /**
     * @return the _Name
     */
    public String getName() {
        return _Name;
    }

    /**
     * @param _Name the _Name to set
     */
    public void setName(String _Name) {
        this._Name = _Name;
    }

    /**
     * @return the _Path
     */
    public String getPath() {
        return _Path;
    }

    /**
     * @param _Path the _Path to set
     */
    public void setPath(String _Path) {
        this._Path = _Path;
    }

    /**
     * @return the _DateCreate
     */
    public Date getDateCreate() {
        return _DateCreate;
    }

    /**
     * @param _DateCreate the _DateCreate to set
     */
    public void setDateCreate(Date _DateCreate) {
        this._DateCreate = _DateCreate;
    }

    /**
     * @return the _IDOwner
     */
    public int getIDOwner() {
        return _IDOwner;
    }

    /**
     * @param _IDOwner the _IDOwner to set
     */
    public void setIDOwner(int _IDOwner) {
        this._IDOwner = _IDOwner;
    }

    /**
     * @return the _IDPartners
     */
    public int getIDPartners() {
        return _IDPartners;
    }

    /**
     * @param _IDPartners the _IDPartners to set
     */
    public void setIDPartners(int _IDPartners) {
        this._IDPartners = _IDPartners;
    }
    
}
