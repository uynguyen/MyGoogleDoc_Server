/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;

/**
 *
 * @author UyNguyen.ITUS
 */
public abstract class Action implements Serializable{
    private static final long serialVersionUID = 1L;
    protected int _startPosition;
    protected int _endPosition;
    protected int _userID;
    protected String _userName;
    private Color _color;
    protected AttributeSet _attributeset;
    public Action(AttributeSet attributeset){
        this._attributeset = attributeset;
    }
      
      
    public abstract void onDraw(JTextPane textPane);

    /**
     * @return the _startPosition
     */
    public int getStartPosition() {
        return _startPosition;
    }
    
    

    /**
     * @param _startPosition the _startPosition to set
     */
    public void setStartPosition(int _startPosition) {
        this._startPosition = _startPosition;
    }

    /**
     * @return the _endPosition
     */
    public int getEndPosition() {
        return _endPosition;
    }

    /**
     * @param _endPosition the _endPosition to set
     */
    public void setEndPosition(int _endPosition) {
        this._endPosition = _endPosition;
    }

    /**
     * @return the _attributeset
     */
    public AttributeSet getAttributeset() {
        return _attributeset;
    }

    /**
     * @param _attributeset the _attributeset to set
     */
    public void setAttributeset(AttributeSet _attributeset) {
        this._attributeset = _attributeset;
    }

    /**
     * @return the _userID
     */
    public int getUserID() {
        return _userID;
    }

    /**
     * @param _userID the _userID to set
     */
    public void setUserID(int _userID) {
        this._userID = _userID;
    }

    /**
     * @return the _userName
     */
    public String getUserName() {
        return _userName;
    }

    /**
     * @param _userName the _userName to set
     */
    public void setUserName(String _userName) {
        this._userName = _userName;
    }

    /**
     * @return the _color
     */
    public Color getColor() {
        return _color;
    }

    /**
     * @param _color the _color to set
     */
    public void setColor(Color _color) {
        this._color = _color;
    }

}
