/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import EditorKits.AdvancedHTMLEditorKit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;

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
        System.err.println("Insert: " + _startPosition + "=" + _Content + "[" + (int)_Content.charAt(0) + "]");
        if ((int)_Content.charAt(0) == 10 || (int)_Content.charAt(0) == 13)
        {
            if (_startPosition > textPane.getDocument().getLength())
                _startPosition = textPane.getDocument().getLength();
     
            try {
                ((AdvancedHTMLEditorKit)textPane.getEditorKit()).insertHTML(
                        (HTMLDocument)textPane.getStyledDocument(),
                        _startPosition, "<br/>", 0, 0, HTML.Tag.BR);
            } catch (BadLocationException | IOException ex) {
                Logger.getLogger(ActionInsert.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        try {
            if (_startPosition > textPane.getDocument().getLength())
                _startPosition = textPane.getDocument().getLength();
            textPane.getDocument().insertString(_startPosition, _Content
                    , _attributeset); 
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
