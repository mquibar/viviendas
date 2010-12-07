/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.Plan;

/**
 *
 * @author desarrollo
 */
public class ModelTablePlan extends AbstractTableModel<Plan> {

    public ModelTablePlan(List<Plan> lista) {
        super(lista,"Nombre");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            switch(columnIndex){
                case 0:
                    return _lista.get(rowIndex).getNombre();
                default:
                    return "-";
            }

        }catch(Exception e){
            return "-";
        }
    }



}
