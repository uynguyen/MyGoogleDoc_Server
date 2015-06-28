/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 * @author UyNguyen.ITUS
 */
public class ActionInsert extends Action implements Serializable {

     private static final long serialVersionUID = 2L;
    
    
    private String _Content;

    public ActionInsert(AttributeSet attributeset) {
        super(attributeset);
    }

    @Override
    public void onDraw(JTextPane textPane) {
        System.err.println("Insert: " + _startPosition + "=" + _Content + "[" + _Content.length() + "]");
        try {
            if (_startPosition > textPane.getStyledDocument().getEndPosition().getOffset()) {
                _startPosition = textPane.getStyledDocument().getEndPosition().getOffset();
            }
            textPane.getStyledDocument().insertString(_startPosition, getContent(), _attributeset);
        } catch (BadLocationException ex) {
            Logger.getLogger(ActionInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    
}
