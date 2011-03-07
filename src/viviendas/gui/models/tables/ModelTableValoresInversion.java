/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.flujo.ValorInversion;

/**
 *
 * @author Maximiliano.
 */
public class ModelTableValoresInversion extends AbstractTableModel<ValorInversion>{

    public ModelTableValoresInversion(List<ValorInversion> lista) {
        super(lista, "Nombre", "Importe");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            switch (columnIndex) {
                case 0:
                    return _lista.get(rowIndex).getFinancio().getNombre();
                case 1:
                    return _lista.get(rowIndex).getImporte();
                    //return _lista.get(rowIndex).getFinancio().getImporte();
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
            case 1:
                return Double.class;
            default:
                return Object.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                _lista.get(rowIndex).getFinancio().setNombre(aValue.toString());
                break;
            case 1:
                //_lista.get(rowIndex).getFinancio().setImporte(Double.valueOf(aValue.toString()));
                _lista.get(rowIndex).setImporte(Double.valueOf(aValue.toString()));
                break;
        }
    }
}
