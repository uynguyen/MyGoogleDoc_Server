/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditorKits;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SizeRequirements;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.ParagraphView;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLWriter;
import javax.swing.text.html.ImageView;
import javax.swing.text.html.MinimalHTMLWriter;

/**
 *
 * @author Vin
 */
public class AdvancedHTMLEditorKit extends HTMLEditorKit {
//    PageableViewFactory factory = new PageableViewFactory();
//    protected int pageWidth = 150;
//    protected int pageHeight = 200;
//    public static int DRAW_PAGE_INSET = 15;
//    protected Insets pageMargins = new Insets(10, 10, 10, 10);

    public AdvancedHTMLEditorKit() {
        super();
    }

    @Override
    public ViewFactory getViewFactory() {
        return new HTMLFactory() {
            @Override
            public View create(Element e) {
                View v = super.create(e);
                if (v instanceof ParagraphView) {
                    return new ParagraphView(e) {
                        @Override
                        protected SizeRequirements calculateMinorAxisRequirements(int axis, SizeRequirements r) {
                            if (r == null) {
                                r = new SizeRequirements();
                            }
                            float pref = layoutPool.getPreferredSpan(axis);
                            float min = layoutPool.getMinimumSpan(axis);
                            r.minimum = (int) min;
                            r.preferred = Math.max(r.minimum, (int) pref);
                            r.maximum = Integer.MAX_VALUE;
                            r.alignment = 0.5f;
                            return r;
                        }
                    };
                } else if (v instanceof ImageView) {
                    return new Custom_ImageView(e);                   
                }
                return v;
            }
        };
    }

    @Override
    public void write(Writer out, Document doc, int pos, int len) throws IOException, BadLocationException {
        if (doc instanceof HTMLDocument) {
            HTMLWriter w = new HTMLWriter(out, (HTMLDocument) doc, pos, len);

            w.write();
        } else if (doc instanceof StyledDocument) {
            MinimalHTMLWriter w = new MinimalHTMLWriter(out, (StyledDocument) doc, pos, len);
            w.write();
        } else {
            super.write(out, doc, pos, len);
        }
    }

    public void insertImage(StyledDocument styledDocument, int caretPosition, File selectedFile) {
        // tạo chuỗi base 64 của image
        String imgString = "";
        try {
            BufferedInputStream itStrm = new BufferedInputStream(new FileInputStream(selectedFile));
            byte[] buff = Files.readAllBytes(selectedFile.toPath());
            imgString = Base64.getEncoder().encodeToString(buff);
            if (!"".equals(imgString)) {
                String imgTag = "<img src=\"data:image/png;base64," + imgString + "\" alt=\"picture\" />";

                insertHTML((HTMLDocument) styledDocument, caretPosition, imgTag,
                        0, 0, HTML.Tag.IMG);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdvancedHTMLEditorKit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | BadLocationException ex) {
            Logger.getLogger(AdvancedHTMLEditorKit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}