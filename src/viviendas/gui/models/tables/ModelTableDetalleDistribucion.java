package viviendas.gui.models.tables;

import viviendas.gui.financiacion.crear.DtoDetalleDistribucion;
import java.util.List;
import viviendas.gui.tool.SubscriptorTotal;

public class ModelTableDetalleDistribucion extends AbstractTableModel<DtoDetalleDistribucion> {

    public ModelTableDetalleDistribucion(List<DtoDetalleDistribucion> lista, String[] columnas) {
        super(lista, columnas);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        //TODO cambiar el @ por vacio
        DtoDetalleDistribucion dto = _lista.get(rowIndex);
        if (columnIndex < 2) {
            try {
                switch (columnIndex) {
                    case 0:
                        return dto.getEstaActivo();
                    case 1:
                        return dto.getUsoFondo().toString();

                    default:
                        return "@1";
                }
            } catch (Exception e) {
                return "@2";
            }
        } else {
            if (columnIndex  <= dto.getDetallesDistribucionesFinanciacion().size() + 2) {
                return dto.getDetallesDistribucionesFinanciacion().get(columnIndex -2).getPorcentaje();
            } else {
                return "@3";
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 1 && columnIndex != _lista.size()-1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex > 1 && _lista.size()+1>=columnIndex){
            return Double.class;
        }
        switch (columnIndex) {
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
            default:
                return Object.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        DtoDetalleDistribucion dto = _lista.get(rowIndex);
        if (columnIndex < 1) {
            switch (columnIndex) {
                case 0:
                    dto.setEstaActivo((Boolean) aValue);
                    break;
            }
        }
        if (columnIndex > 1) {
            dto.getDetallesDistribucionesFinanciacion().get(columnIndex - 2).setPorcentaje((Double) aValue);
        }
        fireTableDataChanged();
    }

    @Override
    public void fireTableDataChanged() {
        SubscriptorTotal.getInstance().notificar();
        super.fireTableDataChanged();
    }

}
