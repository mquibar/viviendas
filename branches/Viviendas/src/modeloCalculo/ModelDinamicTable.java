/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloCalculo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desarrollo
 */
public class ModelDinamicTable extends javax.swing.table.AbstractTableModel {

    public ModelDinamicTable(double capSolicitado, double TNA, double comicionOtorgamiento, double gastosAdministrativos, int momentoOtorgamiento, int plazoGracia) {
        this.capSolicitado = capSolicitado;
        this.TNA = TNA;
        this.comicionOtorgamiento = comicionOtorgamiento;
        this.gastosAdministrativos = gastosAdministrativos;
        this.momentoOtorgamiento = momentoOtorgamiento;
        this.plazoGracia = plazoGracia;
    }


    private final String ROWS[] = {"Capital del crédito", "Devolución del capital del crédito", "Intereses del crédito", "Comisión por otorgamiento",
        "Gastos administrativos", "IVA sobre intereses y comisiones del crédito", "FLUJO DEL PRÉSTAMO", "COSTO FINANCIERO SIN IVA"};
    private List<Double> capitalCredito;
    private List<Double> saldoCapital;
    private List<Double> devolucionCapital;
    private List<Double> interesPeriodo;
    private List<Double> comicionPeriodo;
    private List<Double> gastosPeriodo;
    private List<Double> ivaPeriodo;
    private List<Double> flujoPrestamo;

    //DATOS INGRESADOS...
    private double capSolicitado, TNA, comicionOtorgamiento, gastosAdministrativos;
    private int momentoOtorgamiento, plazoGracia, plazoAnuales;
    //DATOS CALCULABLES...
    private double intMensual, intTrimestral, montoDevengado, monto, interesTotal,
            montoCuotaTrimestrar;
    private int cuotasTrimestrales, momentoFinGracia, primerPago, ultimoPago;
    private int _cantAños = 0;
    private boolean inicializado = false;

    private void calcularParametros() {
        try {
            intMensual = CalculaParametros.calculaInteresMensual(TNA);
            intTrimestral = CalculaParametros.calculaInteresTrimestral(TNA);
            cuotasTrimestrales = CalculaParametros.calculaCuotasTrimestrales(_cantAños, plazoGracia);
            montoDevengado = CalculaParametros.calculaMontoDevengado(capSolicitado, intTrimestral, plazoGracia);
            montoCuotaTrimestrar = CalculaParametros.calculaMontoCuotaTrimestral(intTrimestral, cuotasTrimestrales, montoDevengado);
            monto = CalculaParametros.calculaMonto(cuotasTrimestrales, montoCuotaTrimestrar);
            interesTotal = CalculaParametros.calculaInteresTotal(capSolicitado, monto);
            
            momentoFinGracia = CalculaParametros.calculaMomentoFinGracia(momentoOtorgamiento, plazoGracia);
            primerPago = CalculaParametros.calculaPrimerPago(momentoFinGracia);
            ultimoPago = CalculaParametros.calculaUltimoPago(plazoGracia,_cantAños, momentoFinGracia);

            capitalCredito= new ArrayList<Double>();
            saldoCapital = new ArrayList<Double>();
            devolucionCapital = new ArrayList<Double>();
            interesPeriodo = new ArrayList<Double>();
            comicionPeriodo = new ArrayList<Double>();
            gastosPeriodo = new ArrayList<Double>();
            ivaPeriodo = new ArrayList<Double>();
            flujoPrestamo = new ArrayList<Double>();

            for (int columnIndex = 0; columnIndex < (_cantAños * 4)+1; columnIndex++) {
                calcularCapitalCredito(columnIndex);
                calcularSaldoCapital(columnIndex);
                calcularInteresCredito(columnIndex);
                calcularDevolucionCapital(columnIndex);
                calcularComicionOtorgamiento(columnIndex);
                calcularGastosAdministrativos(columnIndex);
                calcularIva(columnIndex);
                calcularFlujoPrestamo(columnIndex);
            }

            inicializado = true;
        } catch (Exception ex) {
            inicializado = false;
        }

    }

    public int getRowCount() {
        return ROWS.length;
    }

    public int getColumnCount() {
        if (_cantAños > 0) {
            return _cantAños + 1;
        }
        return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ROWS[rowIndex];
            default:
                int newColumnIndex = ((columnIndex-1) * 4)+1;
                switch(rowIndex){
                    case 0:
                        return testModelo1.round(
                                capitalCredito.get(newColumnIndex) + capitalCredito.get(newColumnIndex+1) + capitalCredito.get(newColumnIndex+2) + capitalCredito.get(newColumnIndex+3)
                                ,2);
                    case 1:
                        return testModelo1.round(
                                devolucionCapital.get(newColumnIndex) + devolucionCapital.get(newColumnIndex+1) + devolucionCapital.get(newColumnIndex+2)+ devolucionCapital.get(newColumnIndex+3) ,2);
                    case 2:
                        return testModelo1.round(
                                interesPeriodo.get(newColumnIndex)+interesPeriodo.get(newColumnIndex+1)+interesPeriodo.get(newColumnIndex+2)+interesPeriodo.get(newColumnIndex+3)
                                ,2);
                    case 3:
                        return testModelo1.round(
                                comicionPeriodo.get(newColumnIndex)+comicionPeriodo.get(newColumnIndex+1)+comicionPeriodo.get(newColumnIndex+2)+comicionPeriodo.get(newColumnIndex+3)
                                ,2);
                    case 4:
                        return testModelo1.round(
                                gastosPeriodo.get(newColumnIndex)+gastosPeriodo.get(newColumnIndex+1)+gastosPeriodo.get(newColumnIndex+2)+gastosPeriodo.get(newColumnIndex+3)
                                ,2);
                    case 5:
                        return testModelo1.round(
                                ivaPeriodo.get(newColumnIndex)+ivaPeriodo.get(newColumnIndex+1)+ivaPeriodo.get(newColumnIndex+2)+ivaPeriodo.get(newColumnIndex+3)
                                ,2);
                    case 6:
                        return testModelo1.round(
                                flujoPrestamo.get(newColumnIndex)+flujoPrestamo.get(newColumnIndex+1)+flujoPrestamo.get(newColumnIndex+2)+flujoPrestamo.get(newColumnIndex+3)
                                ,2);
                    case 7:
                        return testModelo1.round(
                                calcularCostoSinIva(newColumnIndex)+calcularCostoSinIva(newColumnIndex+1)+calcularCostoSinIva(newColumnIndex+2)+calcularCostoSinIva(newColumnIndex+3)
                                ,2);
                    case 8:
                    default:
                        return "-";

                }
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Detalles";
        }
        return "Año " + column;
    }

   

    private void calcularCapitalCredito(int columIndex) {
        if (columIndex == momentoOtorgamiento) {
            capitalCredito.add(columIndex,capSolicitado);
        } else {
            capitalCredito.add(columIndex,0.0);
        }
    }

    private void calcularSaldoCapital(int columnIndex){
        if(columnIndex<momentoOtorgamiento){
            saldoCapital.add(columnIndex, 0.0);
            return;
        }
        if(columnIndex==momentoOtorgamiento){
            saldoCapital.add(columnIndex, capSolicitado);
            return;
        }
        if(columnIndex<momentoFinGracia){
            double saldo = 0.0;
            //saldo = capSolicitado*(1+intTrimestral);
            saldo = capSolicitado * java.lang.Math.pow((1+intTrimestral), ((columnIndex*4)-momentoOtorgamiento)/4);
            saldoCapital.add(columnIndex, saldo);
            return;
        }
        if(columnIndex-1 < momentoOtorgamiento){
            saldoCapital.add(columnIndex, 0.0);
            return;
        }
        if(columnIndex-1 < momentoFinGracia){
            saldoCapital.add(columnIndex, saldoCapital.get(columnIndex-1)*(1+intTrimestral));
            return;
        }
        saldoCapital.add(columnIndex, saldoCapital.get(columnIndex-1)+devolucionCapital.get(columnIndex-1));

    }

    private void calcularDevolucionCapital(int columnIndex) {
        double cuotaPagar = 0;
        if (columnIndex >= momentoFinGracia && columnIndex < ultimoPago) {
            cuotaPagar = -montoCuotaTrimestrar;
            if (interesPeriodo.get(columnIndex) == null) {
                interesPeriodo.add(columnIndex, (-saldoCapital.get(columnIndex)) * intTrimestral);
            }
            devolucionCapital.add(columnIndex, cuotaPagar - interesPeriodo.get(columnIndex));
        } else {
            devolucionCapital.add(columnIndex, 0.0);
        }

    }

    private void calcularInteresCredito(int columnIndex) {
        if (columnIndex >= momentoFinGracia && columnIndex < ultimoPago) {
            interesPeriodo.add(columnIndex, (-saldoCapital.get(columnIndex)) * intTrimestral);
        } else {
            interesPeriodo.add(columnIndex, 0.0);
        }
    }

    private void calcularComicionOtorgamiento(int columnIndex) {
        if (columnIndex == momentoOtorgamiento) {
            comicionPeriodo.add(columnIndex, (-capSolicitado * comicionOtorgamiento));
        } else {
            comicionPeriodo.add(columnIndex, 0.0);
        }
    }

    private void calcularGastosAdministrativos(int columnIndex){
        if(devolucionCapital.get(columnIndex)<0){
            gastosPeriodo.add(columnIndex, -montoCuotaTrimestrar*gastosAdministrativos);
        }else
            gastosPeriodo.add(columnIndex, 0.0);
    }

    private void calcularIva(int columnIndex){
        ivaPeriodo.add(columnIndex, ((interesPeriodo.get(columnIndex)+comicionPeriodo.get(columnIndex)+gastosPeriodo.get(columnIndex))* 0.21));
    }

    private void calcularFlujoPrestamo(int columnIndex){
        flujoPrestamo.add(columnIndex, capitalCredito.get(columnIndex) + devolucionCapital.get(columnIndex) +
                                       interesPeriodo.get(columnIndex) + comicionPeriodo.get(columnIndex) +
                                       gastosPeriodo.get(columnIndex) + ivaPeriodo.get(columnIndex));
    }

    public double calcularCostoSinIva(int columnIndex){
        return interesPeriodo.get(columnIndex)+comicionPeriodo.get(columnIndex)+gastosPeriodo.get(columnIndex);
    }

    public double getTNA() {
        return TNA;
    }

    public void setTNA(double TNA) {
        this.TNA = TNA;
    }

    public int getCantAños() {
        return _cantAños;
    }

    public void setCantAños(int _cantAños) {
        this._cantAños = _cantAños;
        calcularParametros();
    }

    public double getCapSolicitado() {
        return capSolicitado;
    }

    public void setCapSolicitado(double capSolicitado) {
        this.capSolicitado = capSolicitado;
    }

    public double getComicionOtorgamiento() {
        return comicionOtorgamiento;
    }

    public void setComicionOtorgamiento(double comicionOtorgamiento) {
        this.comicionOtorgamiento = comicionOtorgamiento;
    }

    public double getGastosAdministrativos() {
        return gastosAdministrativos;
    }

    public void setGastosAdministrativos(double gastosAdministrativos) {
        this.gastosAdministrativos = gastosAdministrativos;
    }

    public int getMomentoOtorgamiento() {
        return momentoOtorgamiento;
    }

    public void setMomentoOtorgamiento(int momentoOtorgamiento) {
        this.momentoOtorgamiento = momentoOtorgamiento;
    }

    public int getPlazoAnuales() {
        return plazoAnuales;
    }

    public void setPlazoAnuales(int plazoAnuales) {
        this.plazoAnuales = plazoAnuales;
    }

    public int getPlazoGracia() {
        return plazoGracia;
    }

    public void setPlazoGracia(int plazoGracia) {
        this.plazoGracia = plazoGracia;
    }

    public int getCuotasTrimestrales() {
        return cuotasTrimestrales;
    }

    public double getIntMensual() {
        return intMensual;
    }

    public double getIntTrimestral() {
        return intTrimestral;
    }

    public double getInteresTotal() {
        return interesTotal;
    }

    public int getMomentoFinGracia() {
        return momentoFinGracia;
    }

    public double getMonto() {
        return monto;
    }

    public double getMontoCuotaTrimestrar() {
        return montoCuotaTrimestrar;
    }

    public double getMontoDevengado() {
        return montoDevengado;
    }

    public int getPrimerPago() {
        return primerPago;
    }

    public int getUltimoPago() {
        return ultimoPago;
    }
}
