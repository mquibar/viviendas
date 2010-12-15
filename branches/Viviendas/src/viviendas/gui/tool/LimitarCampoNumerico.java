package viviendas.gui.tool;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitarCampoNumerico extends PlainDocument {

    @Override
    public void insertString(int arg0, String texto, AttributeSet arg2) throws BadLocationException {
        if (!texto.matches("[0-9]*")) {
            return;
        }
        super.insertString(arg0, texto, arg2);
    }
}