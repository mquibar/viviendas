/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.gui.tool.SubscriptorTotal;

/**
 *
 * @author Maximiliano.
 */
public class ModelTableDistribucionProvincial extends AbstractTableModel<DistribucionProvincial> {

    public ModelTableDistribucionProvincial(List<DistribucionProvincial> distribucionProvincial) {
        super(distribucionProvincial, "Provincia", "% Distribución", "Cantidad Viviendas");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DistribucionProvincial distribucion = _lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return distribucion.getProvincia().getNombre();
            case 1:
                if (distribucion.getPorcentajeDistribucion() != null) {
                    return viviendas.utiles.Utiles.round(distribucion.getPorcentajeDistribucion(), 3);
                }
                return 0.0;
            case 2:
                if (distribucion.getPorcentajeDistribucion() == null || distribucion.getAnioPlan() == null) {
                    return 0.0;
                }
                return viviendas.utiles.Utiles.round((distribucion.getAnioPlan().getCantViviendasAnio() * distribucion.getPorcentajeDistribucion()) / 100.0, 3);
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        DistribucionProvincial distribucion = _lista.get(rowIndex);
        switch (columnIndex) {
            case 1:
                //TODO verificar
                Double valor = (Double) aValue;
                if (valor.compareTo(0.0) < 0) {
                    return;
                }
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
            case 2:
                return Double.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
        SubscriptorTotal.getInstance().notificar();
    }
}
