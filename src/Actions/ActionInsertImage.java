/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import EditorKits.AdvancedHTMLEditorKit;
import java.io.IOException;
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
public class ActionInsertImage extends Action{

    private String _Content;

    public ActionInsertImage(AttributeSet attributeset) {
        super(attributeset);
    }
    
    
     
    @Override
    public void onDraw(JTextPane textPane) {
        System.err.println("Insert: " + _startPosition + "=" + _Content + "[" + _Content.length() + "]");
        try {           
            AdvancedHTMLEditorKit kit = (AdvancedHTMLEditorKit)textPane.getEditorKit();
            kit.insertHTML((HTMLDocument)textPane.getStyledDocument(),
                    _startPosition, _Content, 0, 0, HTML.Tag.IMG);
        } catch (BadLocationException | IOException ex) {
            Logger.getLogger(ActionInsertImage.class.getName()).log(Level.SEVERE, null, ex);
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
