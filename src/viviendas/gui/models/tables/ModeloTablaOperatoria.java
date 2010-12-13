/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.Operatoria;

/**
 *
 * @author Maximiliano.
 */
public class ModeloTablaOperatoria extends AbstractTableModel<Operatoria>{

    public ModeloTablaOperatoria(List<Operatoria> lista) {
        super(lista, "Nombre", "Porcentaje Distr.");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            switch(columnIndex){
                case 0:
                    return _lista.get(rowIndex).getNombre();
                case 1:
                    return _lista.get(rowIndex).getParametro().getPorcenteaje();
                default:
                    return "0";
            }
        }catch(Exception e){
            return "0";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                _lista.get(rowIndex).setNombre(aValue.toString().toUpperCase());
                break;
            case 1:
                try {
                    _lista.get(rowIndex).getParametro().setPorcenteaje(Double.valueOf(aValue.toString()));
                } catch (NumberFormatException ex) {
                    _lista.get(rowIndex).getParametro().setPorcenteaje(0d);                
                }
                break;
        }
    }

}