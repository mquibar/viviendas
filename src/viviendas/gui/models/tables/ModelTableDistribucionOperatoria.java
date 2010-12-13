/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.DistribucionOperatoria;

/**
 *
 * @author Manuel
 */
public class ModelTableDistribucionOperatoria extends AbstractTableModel<DistribucionOperatoria> {

    public ModelTableDistribucionOperatoria(List<DistribucionOperatoria> lista) {
        super(lista, "Operatoria", "% Distribución");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (_lista == null || _lista.size() < rowIndex || rowIndex < 0) {
            return "-";
        }
        switch (columnIndex) {
            case 0:
                return _lista.get(rowIndex).getOperatoria().getNombre();
            case 1:
                return _lista.get(rowIndex).getPorcentajeDistribucion();
            default:
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
