/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.flujo.ValorInversion;

/**
 *
 * @author desarrollo
 */
public class ModelTableValorInversion extends AbstractTableModel<ValorInversion>{

    public ModelTableValorInversion(List<ValorInversion> lista) {
        super(lista,"Uso de fondo" , "Valor");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(_lista==null)
            return null;
        switch(columnIndex){
            case 0:
                return _lista.get(rowIndex).getFinancio().getNombre();
            case 1:
                return _lista.get(rowIndex).getValorInversion();
            default:
                return "-";
        }
    }
      @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return Double.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex!=1)
            return false;
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex!=1 || _lista==null)
            return;
        _lista.get(rowIndex).setValorInversion((Double)aValue);
    }


}
