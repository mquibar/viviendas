/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.AnioPlan;

/**
 *
 * @author desarrollo
 */
public class ModelTableAño extends AbstractTableModel<AnioPlan> {

    public ModelTableAño(List<AnioPlan> _lista) {
        super(_lista,"Año", "Cant. Viviendas");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(_lista== null)
            return "-";
        switch(columnIndex){
            case 0:
                return _lista.get(rowIndex).getAnio();
            case 1:
                return _lista.get(rowIndex).getCantViviendasAnio();
            default:
                return "-";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex==1;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex!=1)
            return;
        _lista.get(rowIndex).setCantViviendasAnio((Integer)aValue);
        fireTableDataChanged();
    }


}
