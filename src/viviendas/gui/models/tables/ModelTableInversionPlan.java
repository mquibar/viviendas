/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.flujo.InversionPlan;

/**
 *
 * @author desarrollo
 */
public class ModelTableInversionPlan extends AbstractTableModel<InversionPlan> {

    public ModelTableInversionPlan(List<InversionPlan> _lista) {
        super(_lista, "Ciudad", "Inversión");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(_lista==null)
            return null;
        switch(columnIndex){
            case 0:
                return _lista.get(rowIndex).getCiudad().getNombre();
            case 1:
                return _lista.get(rowIndex).getTotalInversion();
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




}
