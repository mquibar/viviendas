/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.vivienda.Operatoria;

/**
 *
 * @author Maximiliano.
 */
public class ModeloTablaOperatoria extends AbstractTableModel<Operatoria>{

    public ModeloTablaOperatoria(List<Operatoria> lista) {
        super(lista, "Nombre");
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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        _lista.get(rowIndex).setNombre(aValue.toString());
    }

}
