/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.Ciudad;


/**
 *
 * @author Maximiliano.
 */
public class ModeloTableCiudad extends AbstractTableModel<Ciudad>{

    public ModeloTableCiudad(List<Ciudad> lista) {
        super(lista, "Nombre", "Porcentaje Distrib.");
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
                _lista.get(rowIndex).setNombre(aValue.toString().toUpperCase());
                break;
            case 1:
                _lista.get(rowIndex).getParametro().setPorcenteaje(Double.valueOf(aValue.toString()));
                break;
        }
    }   
}
