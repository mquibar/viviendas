/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.DistribucionProvincial;

/**
 *
 * @author Maximiliano.
 */
public class ModelTableDistribucionProvincial extends AbstractTableModel<DistribucionProvincial> {

    public ModelTableDistribucionProvincial(List<DistribucionProvincial> distribucionProvincial) {
        super(distribucionProvincial, "Provincia", "Porcentaje");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DistribucionProvincial distribucion = _lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return distribucion.getProvincia().getNombre();
            case 1:
                return distribucion.getPorcentajeDistribucion();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        DistribucionProvincial distribucion = _lista.get(rowIndex);
        switch (columnIndex) {
            case 1:
                distribucion.setPorcentajeDistribucion((Double) aValue);
        }
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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }
}
