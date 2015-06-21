/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;


import EditorKits.AdvancedHTMLEditorKit;
import EditorKits.AdvancedRTFEditorKit;
import java.awt.Insets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author Vin
 */
public final class StyledTextEditorOnServer extends javax.swing.JPanel {

    //undo helpers
    /**
     * Creates new form StyledTextEditor
     */
    public StyledTextEditorOnServer() {
        initComponents();
        textPane.setMargin(new Insets(50, 50, 50, 50));

        NewDocument();

    }

  synchronized public void ApplyActionChange(Actions.Action action){
        action.onDraw(textPane);
    }
  
   // Tạo một tài liệu mới
    public void NewDocument() {
        textPane.setEditorKit(new AdvancedHTMLEditorKit());
        textPane.setContentType(textPane.getEditorKit().getContentType());
        textPane.setDocument(textPane.getEditorKit().createDefaultDocument()); 
        textPane.setStyledDocument(new HTMLDocument());
        textPane.setCaretPosition(0);
    }
  
    // Lấy chuỗi HTML của tài liệu
    synchronized public String getHTMLString() {
        String strResult = "";
        try {
            AdvancedHTMLEditorKit kit = new AdvancedHTMLEditorKit();
            Document d = textPane.getStyledDocument();
            StringWriter wt = new StringWriter();
            kit.write(wt, d, d.getStartPosition().getOffset(), d.getLength());         
            strResult = wt.getBuffer().toString();
        } catch (IOException | BadLocationException ex) {
        }
        return strResult;
    }

    synchronized public void setHTMLString(String src) {
        NewDocument();
        textPane.setText(src);
    }

    public String getRTFString() {
        String strResult = "";
        try {
            RTFEditorKit kit = new RTFEditorKit();
            Document d = textPane.getStyledDocument();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            kit.write(baos, d, d.getStartPosition().getOffset(), d.getLength());
            strResult = baos.toString(StandardCharsets.UTF_8.name());
        } catch (IOException | BadLocationException ex) {
        }
        return strResult;
    }

    public void setRTFString(String src) {
        RTFEditorKit kit = new AdvancedRTFEditorKit();
        textPane.setEditorKit(kit);
        textPane.setDocument(kit.createDefaultDocument());
        textPane.setText(src);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));

        textPane.setContentType("text/html"); // NOI18N
        textPane.setAutoscrolls(false);
        textPane.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textPane.setMaximumSize(jScrollPane1.getPreferredSize());
        jScrollPane1.setViewportView(textPane);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables
}
