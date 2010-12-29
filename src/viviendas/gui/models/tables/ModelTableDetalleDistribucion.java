package viviendas.gui.models.tables;

import viviendas.gui.financiacion.crear.DtoDetalleDistribucion;
import java.util.List;
import viviendas.gui.tool.SubscriptorTotal;
import viviendas.modulos.FuentesFondos.GestorFuentesFondos;

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
                        return dto.getDetallesDistribucionesFinanciacion().get(0).getUsoFondo().getNombre();
                    default:
                        return "@1";
                }
            } catch (Exception e) {
                return "@2";
            }
        } else {
            if (columnIndex <= dto.getDetallesDistribucionesFinanciacion().size() + 2) {
                return dto.getDetallesDistribucionesFinanciacion().get(columnIndex - 2).getPorcentaje();
            } else {
                return "@3";
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex < 2) {
            return columnIndex != 1;
        } else {
            DtoDetalleDistribucion dtoDetalle = _lista.get(rowIndex);
            return !dtoDetalle.getDetallesDistribucionesFinanciacion().get(columnIndex - 2).getFuenteFondo().getNombre().equals(GestorFuentesFondos.OTROSAPORTES);
        }


    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex > 1 && _lista.size() + 1 >= columnIndex) {
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
            Double valor = (Double) aValue;
            if (valor < 0.0) {
                return;
            }
            dto.getDetallesDistribucionesFinanciacion().get(columnIndex - 2).setPorcentaje(valor);
        }
        fireTableDataChanged();
    }

    @Override
    public void fireTableDataChanged() {
        SubscriptorTotal.getInstance().notificar();
        super.fireTableDataChanged();
    }
}
