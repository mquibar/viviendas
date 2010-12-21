package viviendas.gui.models.tables;

import viviendas.gui.financiacion.crear.DtoDetalleDistribucion;
import java.util.List;

public class ModelTableDetalleDistribucion extends AbstractTableModel<DtoDetalleDistribucion> {

    public ModelTableDetalleDistribucion(List<DtoDetalleDistribucion> lista, String[] columnas) {
        super(lista, columnas);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DtoDetalleDistribucion dto = _lista.get(rowIndex);
        if (columnIndex < 3) {
            try {
                switch (columnIndex) {
                    case 1:
                        return dto.getEstaActivo();
                    case 2:
                        return dto.getUsoFondo().toString();

                    default:
                        return "@1";
                }
            } catch (Exception e) {
                return "@2";
            }
        } else {
            if (columnIndex  == dto.getDetallesDistribucionesFinanciacion().size() + 2) {
                return dto.getDetallesDistribucionesFinanciacion().get(columnIndex -2);
            } else {
                return "@3";
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex > 2 ){
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
        if (columnIndex < 2) {
            switch (columnIndex) {
                case 1:
                    dto.setEstaActivo((Boolean) aValue);
                    break;
            }
        }
        if (columnIndex > 3) {
            dto.getDetallesDistribucionesFinanciacion().get(columnIndex - 2).setPorcentaje((Double) aValue);
        }
    }
}
