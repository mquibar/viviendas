/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.DistribucionSector;
import viviendas.gui.tool.SubscriptorTotal;

/**
 *
 * @author desarrollo
 */
public class ModelTableDistribucionSectorEconomico extends AbstractTableModel<DistribucionSector>{

    public ModelTableDistribucionSectorEconomico(List<DistribucionSector> lista) {
        super(lista,"Sector","% Distribución","Cantidad Viviendas");
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        DistribucionSector distribucion = _lista.get(rowIndex);
        if(_lista == null)
            return "-";
        switch(columnIndex){
            case 0:
                return _lista.get(rowIndex).getSectorEconomico().getNombre();
            case 1:
                return viviendas.utiles.Utiles.round(_lista.get(rowIndex).getPorcentajeDistribucion(),3);
            case 2:
                return viviendas.utiles.Utiles.round(distribucion.getPorcentajeDistribucion()/100 * (distribucion.getDistribucionCiudad().getPorcentajeDistribucion()/100) * (distribucion.getDistribucionCiudad().getDistribucionProvincial().getAnioPlan().getCantViviendasAnio() * distribucion.getDistribucionCiudad().getDistribucionProvincial().getPorcentajeDistribucion() / 100.0) ,3 );


            default:
                return "-";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex==1)
            return true;
        return false;
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
        if(_lista == null || columnIndex!=1)
            return;
        _lista.get(rowIndex).setPorcentajeDistribucion(Double.valueOf(aValue.toString()));
         fireTableDataChanged();
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
        SubscriptorTotal.getInstance().notificar();
    }

}
