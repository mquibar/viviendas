/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.DistribucionCiudad;

/**
 *
 * @author desarrollo
 */
public class ModelTableDistribucionCiudad extends AbstractTableModel<DistribucionCiudad> {

    public ModelTableDistribucionCiudad(List<DistribucionCiudad> _lista) {
        super(_lista, "Ciudad", "% de Distribución");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            switch (columnIndex) {
                case 0:
                    return _lista.get(rowIndex).getCuidad().getNombre();
                case 1:
                    return _lista.get(rowIndex).getPorcentajeDistribucion();
                default:
                    return "-";
            }
        } catch (Exception e) {
            return "-";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex != 1 || _lista == null || _lista.size() < rowIndex || rowIndex < 0) {
            return;
        }
        _lista.get(rowIndex).setPorcentajeDistribucion(Double.valueOf(aValue.toString()));
        fireTableDataChanged();
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
}
