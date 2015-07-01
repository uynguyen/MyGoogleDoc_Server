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
public class History {
    private int _ID;
    private String _User;
    private String _Content;
    private Date _DateTime;
    private String _Action;
    private String _ChangePosition;

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
     * @return the _User
     */
    public String getUser() {
        return _User;
    }

    /**
     * @param _User the _User to set
     */
    public void setUser(String _User) {
        this._User = _User;
    }

    /**
     * @return the _Content
     */
    public String getContent() {
        return _Content;
    }

    /**
     * @param _Content the _Content to set
     */
    public void setContent(String _Content) {
        this._Content = _Content;
    }

    /**
     * @return the _DateTime
     */
    public Date getDateTime() {
        return _DateTime;
    }

    /**
     * @param _DateTime the _DateTime to set
     */
    public void setDateTime(Date _DateTime) {
        this._DateTime = _DateTime;
    }

    /**
     * @return the _Action
     */
    public String getAction() {
        return _Action;
    }

    /**
     * @param _Action the _Action to set
     */
    public void setAction(String _Action) {
        this._Action = _Action;
    }

    /**
     * @return the _ChangePosition
     */
    public String getChangePosition() {
        return _ChangePosition;
    }

    /**
     * @param _ChangePosition the _ChangePosition to set
     */
    public void setChangePosition(String _ChangePosition) {
        this._ChangePosition = _ChangePosition;
    }
    
}
