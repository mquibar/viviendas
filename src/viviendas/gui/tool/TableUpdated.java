/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.tool;

import javax.swing.JTable;

/**
 *
 * @author desarrollo
 */
public class TableUpdated extends JTable {

    public TableUpdated() {
        this.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    @Override
    public void changeSelection(final int row, final int column, boolean toggle, boolean extend) {
        super.changeSelection(row, column, toggle, extend);
        if (editCellAt(row, column)) {
            ((javax.swing.JTextField) getEditorComponent()).selectAll();
            getEditorComponent().requestFocusInWindow();
        }
    }
}
