/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.AñoPlan;

/**
 *
 * @author desarrollo
 */
public class ModelTableAño extends AbstractTableModel<AñoPlan> {

    public ModelTableAño(List<AñoPlan> _lista) {
        super(_lista,"Año", "Cant. Viviendas");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(_lista== null)
            return "-";
        switch(columnIndex){
            case 0:
                return _lista.get(rowIndex).getAño();
            case 1:
                return _lista.get(rowIndex).getCantViviendasAño();
            default:
                return "-";
        }
    }

}
