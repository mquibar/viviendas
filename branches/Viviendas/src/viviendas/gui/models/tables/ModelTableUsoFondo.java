/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.flujo.UsoFondo;

/**
 *
 * @author Maximiliano.
 */
public class ModelTableUsoFondo extends AbstractTableModel<UsoFondo>{

    public ModelTableUsoFondo(List<UsoFondo> lista) {
        super(lista, "Nombre");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            switch (columnIndex) {
                case 0:
                    return _lista.get(rowIndex).getNombre();
                default:
                    return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;            
            default:
                return Object.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                _lista.get(rowIndex).setNombre(aValue.toString());
                break;

        }
    }
}