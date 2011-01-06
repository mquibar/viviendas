/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.gui.tool.SubscriptorTotal;

/**
 *
 * @author desarrollo
 */
public class ModelTableDistribucionCiudad extends AbstractTableModel<DistribucionCiudad> {

    public ModelTableDistribucionCiudad(List<DistribucionCiudad> _lista) {
        super(_lista, "Ciudad", "% Distribución","Cantidad Viviendas");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DistribucionCiudad distribucionCiudad = _lista.get(rowIndex);
        try {
            switch (columnIndex) {
                case 0:
                    return _lista.get(rowIndex).getCuidad().getNombre();
                case 1:
                    return viviendas.utiles.Utiles.round(_lista.get(rowIndex).getPorcentajeDistribucion(),3);
                case 2:
                    return viviendas.utiles.Utiles.round(distribucionCiudad.getPorcentajeDistribucion() /100 * (distribucionCiudad.getDistribucionProvincial().getAnioPlan().getCantViviendasAnio() * distribucionCiudad.getDistribucionProvincial().getPorcentajeDistribucion() / 100.0),3);

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

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
        SubscriptorTotal.getInstance().notificar();
    }
}
