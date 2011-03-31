/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.flujoFondos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.gui.models.tables.ModelTableFlujoFondo;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author Maximiliano.
 */
public class CtrlFlujoFondo {
    private IUFlujoFondo _pantalla;    
    private ModelTableFlujoFondo _modelo;

    //Parte del controlador grande.
    public CtrlFlujoFondo(JDesktopPane desktop, Plan plan, DistribucionOperatoria distOp) {
        try {            
            _pantalla = new IUFlujoFondo();            
            _modelo = new ModelTableFlujoFondo(plan, distOp);
            _pantalla.getTbFlujoFondo().setModel(_modelo);
            _pantalla.getTbFlujoFondo().setDefaultRenderer(Object.class, new TableRenderFlujoFondo());
            formatearTabla();
            _pantalla.setVisible(true);
            desktop.add(_pantalla);
            _pantalla.toFront();
        } catch (BusinessOperationException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Viviendas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void formatearTabla(){
        _pantalla.getTbFlujoFondo().getColumnModel().getColumn(0).setMaxWidth(230);
        _pantalla.getTbFlujoFondo().getColumnModel().getColumn(0).setMinWidth(230);
        _pantalla.getTbFlujoFondo().getColumnModel().getColumn(0).setPreferredWidth(230);

        _pantalla.getTbFlujoFondo().getColumnModel().getColumn(1).setMaxWidth(50);
        _pantalla.getTbFlujoFondo().getColumnModel().getColumn(1).setMinWidth(50);
        _pantalla.getTbFlujoFondo().getColumnModel().getColumn(1).setPreferredWidth(50);

        _pantalla.getTbFlujoFondo().getColumnModel().getColumn(2).setMaxWidth(100);
        _pantalla.getTbFlujoFondo().getColumnModel().getColumn(2).setMinWidth(100);
        _pantalla.getTbFlujoFondo().getColumnModel().getColumn(2).setPreferredWidth(100);
    }
}

class TableRenderFlujoFondo extends DefaultTableCellRenderer{
    protected int column;


    public TableRenderFlujoFondo() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ModelTableFlujoFondo _modelo = (ModelTableFlujoFondo) table.getModel();
        this.column=column;

        JLabel lb = new JLabel();
        Color color = Color.BLACK;

        if(_modelo.getValueAt(row, 2) instanceof Double){
            Double valor = (Double) _modelo.getValueAt(row, 2);
            if(valor.doubleValue() >= 0){
                color = Color.BLUE;
            }
            else{
                color = Color.RED;
            }
        }

        if(row == 10 || row == 16 || row == 21 || row == 27 || row == 12 || row == 23 ){
            lb.setFont(new Font( "Helvetica", Font.BOLD, 12));
        }

        lb.setForeground(color);
        lb.setText(" "+_modelo.getValueAt(row,column).toString());
        return lb;
    }
}