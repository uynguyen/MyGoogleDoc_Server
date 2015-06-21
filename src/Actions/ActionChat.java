/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;

/**
 *
 * @author UyNguyen.ITUS
 */
public class ActionChat extends Action{

    private String _username;
    private String _content;
    
    
    public ActionChat(AttributeSet attributeset) {
        super(attributeset);
    }

    @Override
    public void onDraw(JTextPane textPane) {
        
    }

    /**
     * @return the _username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @param _username the _username to set
     */
    public void setUsername(String _username) {
        this._username = _username;
    }

    /**
     * @return the _content
     */
    public String getContent() {
        return _content;
    }

    /**
     * @param _content the _content to set
     */
    public void setContent(String _content) {
        this._content = _content;
    }
    
    
}
