/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.flujo.DetalleDistribucionFinanciacion;
import viviendas.entidades.flujo.DistribucionFinanciacion;
import viviendas.entidades.flujo.Financiacion;
import viviendas.entidades.flujo.InversionPlan;
import viviendas.entidades.flujo.ParametrosFlujoFondo;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.modulos.FuentesFondos.GestorFuentesFondos;
import viviendas.systemException.BusinessOperationException;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano.
 */
public class ModelTableFlujoFondo extends javax.swing.table.AbstractTableModel{
    private final String VARIABLES[] = {"Emisión títulos públicos", "Devolución de títulos públicos", "Ahorro previo de los adjudicatarios",
                                   "Devolución del ahorro previo ", "Otros aportes de los adjudicatarios", "Ahorro para el Estado por otros aportes",
                                   "Créditos del Estado", "Devolución de créditos", "Pérdida por incobrables", "Subsidios del Estado", "Saldo", " ",
                                   "Inversión del Estado", "Pago de intereses por títulos públicos", "Crédito del Estado", "Subsidios del Estado",
                                   "Total inversión del Estado", " ","Recupero de la inversión del Estado", "Pérdida por incobrables", "",
                                   "Saldo para el Estado", " ", "Aporte de los adjudicatarios", "Ahorro previo", "Otros aportes",
                                   "Intereses de los créditos", " "};
    private final String COLUMNAS[] = {"Variables", "Porcentajes", "Importes"};
    Plan plan;
    DistribucionOperatoria combinatoria;
    //monto inversion:
    private double _montoInversion;
    //Variables:
    private double _emisionTitulosPublicos;
    private double _devolucionTitulosPublicos;
    private double _ahorroPrevioAdjudicatarios;
    private double _devolucionAhorroPrevio;
    private double _otrosAportesAdjudicatarios;
    private double _ahorroEstadoOtrosAportes;
    private double _creditoEstado;
    private double _devolucionCredito;
    private double _perdidaIncobrables;
    private double _subsidioEstado;
    private double _saldo;
    private double _pagoInteresesTitulosPublicos;
    private double _totalInversionEstado;
    private double _recuperoInversionEstado;
    private double _saldoAux;
    private double _saldoParaEstado;
    //Porcentajes:
    private double _porcTitulosPublicos;//
    private double _porcAhorroPrevio;
    private double _porcOtrosAportes;
    private double _porcCreditoEstado;
    private double _porcPerdidaIncobrables;//
    private double _porcSubsidioEstado;

    public ModelTableFlujoFondo(Plan plan, DistribucionOperatoria combinatoria) throws BusinessOperationException {
        this.plan = plan;
        this.combinatoria = combinatoria;

        //System.out.println("combinatoria.getParametrosFlujoFondo().getId(): " + combinatoria.getParametrosFlujoFondo().getId());

        calcularFlujoFondo(combinatoria, plan);

        this._porcTitulosPublicos = combinatoria.getParametrosFlujoFondo().getTnaTitulos();
        this._porcPerdidaIncobrables = combinatoria.getParametrosFlujoFondo().getPerdidaIncobrables();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: //nombres de los items.
               return VARIABLES[rowIndex];
            case 1: //porcentajes.
                switch(rowIndex){
                    case 0:
                        return getPorcTitulosPublicos();
                    case 2:
                        return getPorcAhorroPrevio() * 100;
                    case 4:
                        return getPorcOtrosAportes() * 100;
                    case 6:
                        return getPorcCreditoEstado() * 100;
                    case 8:
                        return getPorcPerdidaIncobrables() * 100;
                    case 9:
                        return getPorcSubsidioEstado() * 100;
                    default:
                        return "";
                }
            case 2: //valores.
                switch(rowIndex){
                    case 0:
                        _emisionTitulosPublicos = Utiles.round(getPorcTitulosPublicos() * _montoInversion, 0);
                        return _emisionTitulosPublicos;
                    case 1:
                        _devolucionTitulosPublicos = Utiles.round(-calcularCuotaTitulos(_emisionTitulosPublicos, combinatoria.getParametrosFlujoFondo()) * 12 * plan.getAniosPlan(), 0);
                        return _devolucionTitulosPublicos;
                    case 2:
                        _ahorroPrevioAdjudicatarios = Utiles.round(getPorcAhorroPrevio() * _montoInversion, 0);
                        return _ahorroPrevioAdjudicatarios;
                    case 3:
                        _devolucionAhorroPrevio = -_ahorroPrevioAdjudicatarios;
                        return _devolucionAhorroPrevio;
                    case 4:
                        _otrosAportesAdjudicatarios = Utiles.round(getPorcOtrosAportes() * _montoInversion, 0);
                        return _otrosAportesAdjudicatarios;
                    case 5:
                        return -_otrosAportesAdjudicatarios;
                    case 6:
                        _creditoEstado = Utiles.round(-getPorcCreditoEstado() * _montoInversion, 0);
                        return _creditoEstado;
                    case 7:
                        _devolucionCredito = Utiles.round(-calcularCuotaDevolucionCredito(_creditoEstado, plan, combinatoria.getParametrosFlujoFondo()) * 12 * plan.getAniosPlan(), 0);
                        return _devolucionCredito;
                    case 8:
                        _perdidaIncobrables = Utiles.round(-getPorcPerdidaIncobrables() * _montoInversion, 0);
                        return _perdidaIncobrables;
                    case 9:
                        _subsidioEstado = Utiles.round(-getPorcSubsidioEstado() * _montoInversion, 0);
                        return _subsidioEstado;
                    case 10:
                        //Saldo.
                        return Utiles.round(_emisionTitulosPublicos + _devolucionTitulosPublicos + _creditoEstado + _devolucionCredito + _perdidaIncobrables + _subsidioEstado, 0);
                    case 11:
                        return "";
                    case 12:
                        return "";
                    case 13:
                        return Utiles.round(_emisionTitulosPublicos + _devolucionTitulosPublicos, 0);
                    case 14:
                        return _creditoEstado;
                    case 15:
                        return _subsidioEstado;
                    case 16:
                        //Total inversion del estado:
                        return Utiles.round(_emisionTitulosPublicos + _devolucionTitulosPublicos + _creditoEstado + _subsidioEstado, 0);
                    case 17:
                        return "";
                    case 18:
                        return _devolucionCredito;
                    case 19:
                        return _perdidaIncobrables;
                    case 20:
                        return Utiles.round(_devolucionCredito + _perdidaIncobrables, 0);
                    case 21:
                        return Utiles.round(_emisionTitulosPublicos + _devolucionTitulosPublicos + _creditoEstado + _subsidioEstado + _devolucionCredito + _perdidaIncobrables, 0);
                    case 22:
                        return "";
                    case 23:
                        return "";
                    case 24:
                        return _ahorroPrevioAdjudicatarios;
                    case 25:
                        return _otrosAportesAdjudicatarios;
                    case 26:
                        return Utiles.round(_creditoEstado + _devolucionCredito + _perdidaIncobrables, 0);
                    case 27:
                        return Utiles.round(_ahorroPrevioAdjudicatarios + _otrosAportesAdjudicatarios + _creditoEstado + _devolucionCredito + _perdidaIncobrables, 0);
                }
            default:
                return "-";
        }
    }

    public int getRowCount() {
        return VARIABLES.length;
    }

    public int getColumnCount() {
        return COLUMNAS.length;
    }

    ////////////////////////////////////////////////////////////////////////////
    //Gets y Sets.
    ////////////////////////////////////////////////////////////////////////////

    public double getAhorroEstadoOtrosAportes() {
        return _ahorroEstadoOtrosAportes;
    }

    public void setAhorroEstadoOtrosAportes(double _ahorroEstadoOtrosAportes) {
        this._ahorroEstadoOtrosAportes = _ahorroEstadoOtrosAportes;
    }

    public double getAhorroPrevioAdjudicatarios() {
        return _ahorroPrevioAdjudicatarios;
    }

    public void setAhorroPrevioAdjudicatarios(double _ahorroPrevioAdjudicatarios) {
        this._ahorroPrevioAdjudicatarios = _ahorroPrevioAdjudicatarios;
    }

    public double getCreditoEstado() {
        return _creditoEstado;
    }

    public void setCreditoEstado(double _creditoEstado) {
        this._creditoEstado = _creditoEstado;
    }

    public double getDevolucionAhorroPrevio() {
        return _devolucionAhorroPrevio;
    }

    public void setDevolucionAhorroPrevio(double _devolucionAhorroPrevio) {
        this._devolucionAhorroPrevio = _devolucionAhorroPrevio;
    }

    public double getDevolucionCredito() {
        return _devolucionCredito;
    }

    public void setDevolucionCredito(double _devolucionCredito) {
        this._devolucionCredito = _devolucionCredito;
    }

    public double getDevolucionTitulosPublicos() {
        return _devolucionTitulosPublicos;
    }

    public void setDevolucionTitulosPublicos(double _devolucionTitulosPublicos) {
        this._devolucionTitulosPublicos = _devolucionTitulosPublicos;
    }

    public double getEmisionTitulosPublicos() {
        return _emisionTitulosPublicos;
    }

    public void setEmisionTitulosPublicos(double _emisionTitulosPublicos) {
        this._emisionTitulosPublicos = _emisionTitulosPublicos;
    }

    public double getMontoInversion() {
        return _montoInversion;
    }

    public void setMontoInversion(double _montoInversion) {
        this._montoInversion = _montoInversion;
    }

    public double getOtrosAportesAdjudicatarios() {
        return _otrosAportesAdjudicatarios;
    }

    public void setOtrosAportesAdjudicatarios(double _otrosAportesAdjudicatarios) {
        this._otrosAportesAdjudicatarios = _otrosAportesAdjudicatarios;
    }

    public double getPagoInteresesTitulosPublicos() {
        return _pagoInteresesTitulosPublicos;
    }

    public void setPagoInteresesTitulosPublicos(double _pagoInteresesTitulosPublicos) {
        this._pagoInteresesTitulosPublicos = _pagoInteresesTitulosPublicos;
    }

    public double getPerdidaIncobrables() {
        return _perdidaIncobrables;
    }

    public void setPerdidaIncobrables(double _perdidaIncobrables) {
        this._perdidaIncobrables = _perdidaIncobrables;
    }

    public double getPorcAhorroPrevio() {
        return _porcAhorroPrevio;
    }

    public void setPorcAhorroPrevio(double _porcAhorroPrevio) {
        this._porcAhorroPrevio = _porcAhorroPrevio;
    }

    public double getPorcCreditoEstado() {
        return _porcCreditoEstado;
    }

    public void setPorcCreditoEstado(double _porcCreditoEstado) {
        this._porcCreditoEstado = _porcCreditoEstado;
    }

    public double getPorcOtrosAportes() {
        return _porcOtrosAportes;
    }

    public void setPorcOtrosAportes(double _porcOtrosAportes) {
        this._porcOtrosAportes = _porcOtrosAportes;
    }

    public double getPorcPerdidaIncobrables() {
        return _porcPerdidaIncobrables;
    }

    public void setPorcPerdidaIncobrables(double _porcPerdidaIncobrables) {
        this._porcPerdidaIncobrables = _porcPerdidaIncobrables;
    }

    public double getPorcSubsidioEstado() {
        return _porcSubsidioEstado;
    }

    public void setPorcSubsidioEstado(double _porcSubsidioEstado) {
        this._porcSubsidioEstado = _porcSubsidioEstado;
    }

    public double getPorcTitulosPublicos() {
        return _porcTitulosPublicos;
    }

    public void setPorcTitulosPublicos(double _porcTitulosPublicos) {
        this._porcTitulosPublicos = _porcTitulosPublicos;
    }

    public double getRecuperoInversionEstado() {
        return _recuperoInversionEstado;
    }

    public void setRecuperoInversionEstado(double _recuperoInversionEstado) {
        this._recuperoInversionEstado = _recuperoInversionEstado;
    }

    public double getSaldo() {
        return _saldo;
    }

    public void setSaldo(double _saldo) {
        this._saldo = _saldo;
    }

    public double getSaldoAux() {
        return _saldoAux;
    }

    public void setSaldoAux(double _saldoAux) {
        this._saldoAux = _saldoAux;
    }

    public double getSaldoParaEstado() {
        return _saldoParaEstado;
    }

    public void setSaldoParaEstado(double _saldoParaEstado) {
        this._saldoParaEstado = _saldoParaEstado;
    }

    public double getSubsidioEstado() {
        return _subsidioEstado;
    }

    public void setSubsidioEstado(double _subsidioEstado) {
        this._subsidioEstado = _subsidioEstado;
    }

    public double getTotalInversionEstado() {
        return _totalInversionEstado;
    }

    public void setTotalInversionEstado(double _totalInversionEstado) {
        this._totalInversionEstado = _totalInversionEstado;
    }

    public DistribucionOperatoria getCombinatoria() {
        return combinatoria;
    }

    public void setCombinatoria(DistribucionOperatoria combinatoria) {
        this.combinatoria = combinatoria;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
 
    private void calcularFlujoFondo(DistribucionOperatoria distOp, Plan plan) throws BusinessOperationException{
        if(distOp.getParametrosFlujoFondo() == null){
            throw new BusinessOperationException("Falta definir los parámetros para el flujo de fondo. Menú: Parámetros/Flujo de Fondo.");
        }
        
        List<InversionPlan> listaInversionPlan = plan.getListaInversion();
        InversionPlan inversionPlan = null;
        Ciudad ciudad = distOp.getDistribucionSector().getDistribucionCiudad().getCuidad();
        for(InversionPlan inversion : listaInversionPlan){
            if(inversion.getCiudad().equals(ciudad)){
                inversionPlan = inversion;
                break;
            }
        }

        if(inversionPlan == null){
            throw new BusinessOperationException("Falta realizar las inversiones correspondientes al plan.");
        }

        _montoInversion = inversionPlan.getTotalInversion() * calcularCantViviendas(distOp);
//        _montoInversion = 165000.00;

        Financiacion financiacion = distOp.getFinanciacion();
        if(distOp.getFinanciacion() == null){
            throw new BusinessOperationException("Falta definir la financiación del plan.");
        }
        
        boolean ce, se, ap, oa;

        for(DistribucionFinanciacion distFin : financiacion.getDistribucionesFinanciacion()){
            ce=true; se=true; ap=true; oa=true;
            for(DetalleDistribucionFinanciacion detFinan : distFin.getDetallesDistribucionesFinanciacion()){
                if(ce & detFinan.getFuenteFondo().getNombre().equals(GestorFuentesFondos.CREDITO_ESTADO)){
                    System.out.println("detFinan.getPorcentaje()/100 " + detFinan.getPorcentaje()/100);
                    System.out.println("distFin.getPorcentajeFinanciacion()/100 " + distFin.getPorcentajeFinanciacion()/100);
                    System.out.println("-----------------------------------------------------------");
                    _porcCreditoEstado = _porcCreditoEstado + detFinan.getPorcentaje()/100 * distFin.getPorcentajeFinanciacion()/100;
                    ce = false;
                }
                else if(se & detFinan.getFuenteFondo().getNombre().equals(GestorFuentesFondos.SUBSIDIO_ESTADO)){
                    System.out.println("detFinan.getPorcentaje()/100 " + detFinan.getPorcentaje()/100);
                    System.out.println("distFin.getPorcentajeFinanciacion()/100 " + distFin.getPorcentajeFinanciacion()/100);
                    System.out.println("-----------------------------------------------------------");
                    _porcSubsidioEstado = _porcSubsidioEstado + detFinan.getPorcentaje()/100 * distFin.getPorcentajeFinanciacion()/100;
                    se = false;
                }
                else if(ap & detFinan.getFuenteFondo().getNombre().equals(GestorFuentesFondos.AHORRO_PREVIO)){
                    System.out.println("detFinan.getPorcentaje()/100 " + detFinan.getPorcentaje()/100);
                    System.out.println("distFin.getPorcentajeFinanciacion()/100 " + distFin.getPorcentajeFinanciacion()/100);
                    System.out.println("-----------------------------------------------------------");
                    _porcAhorroPrevio = _porcAhorroPrevio + detFinan.getPorcentaje()/100 * distFin.getPorcentajeFinanciacion()/100;
                     ap = false;
                }
                else if(oa & detFinan.getFuenteFondo().getNombre().equals(GestorFuentesFondos.OTROSAPORTES)){
                    System.out.println("detFinan.getPorcentaje()/100 " + detFinan.getPorcentaje()/100);
                    System.out.println("distFin.getPorcentajeFinanciacion()/100 " + distFin.getPorcentajeFinanciacion()/100);
                    System.out.println("-----------------------------------------------------------");
                    _porcOtrosAportes = Utiles.round(_porcOtrosAportes + detFinan.getPorcentaje()/100 * distFin.getPorcentajeFinanciacion()/100, 2);
                    oa = false;
                }
            }
        }
        System.out.println("_porcCreditoEstado = " + _porcCreditoEstado);
        System.out.println("_porcSubsidioEstado = " + _porcSubsidioEstado);
        System.out.println("_porcAhorroPrevio = " + _porcAhorroPrevio);
        System.out.println("_porcOtrosAportes = " + _porcOtrosAportes);

        //Valores de prueba:
//        _porcCreditoEstado = 0.40;
//        _porcAhorroPrevio = 0.18;
//        _porcSubsidioEstado = 0.25;
//        _porcOtrosAportes = 0.17;
    }

    //Métodos auxiliares:
    private double calcularCuotaDevolucionCredito(double capitalSolicitado, Plan plan, ParametrosFlujoFondo param){
        double porcentajeInteresTNA = param.getTna();
        double gastosOtorgamiento = param.getComisionOtorgamiento();
        double gastosAdministrativos = param.getGastosAdministrativos();

        //Paso 1: calculamos el porcentaje de interes efectivo mensual.
        double c3 = porcentajeInteresTNA * 30 / 365;

        //Paso 2: Calulamos el nro. de cuotas mensuales.
        int c5 = plan.getAniosPlan().intValue() * 12;

        //Paso 3: Calculamos los gastos de otrogamiento.
        double c7 = capitalSolicitado * gastosOtorgamiento;

        //Paso 4: Calculamos el valor de la cuota.
        double cuotaMensual = capitalSolicitado * (Math.pow((1 + c3), c5) * c3) / (Math.pow((1 + c3), c5) - 1);

        //Paso 5: Calculamos los gastos administrativos:
        double c9 = gastosAdministrativos * cuotaMensual;

        //Paso 6: Calculamos el total de la cuota:
        return cuotaMensual + c9;
    }

    private double calcularCuotaTitulos(double capitalSolicitado, ParametrosFlujoFondo param){
        int cantAniosTitulos = param.getCantAniosTitulos();

        //Paso 1: calculamos el porcentaje de interes efectivo mensual.
        double c20 = param.getTnaTitulos() * 30 / 365;

        //Paso 2: Calulamos el nro. de cuotas mensuales.
        int c22 = cantAniosTitulos * 12;

        //Calculamos la cuota de amortización mensual.
        return capitalSolicitado * (Math.pow((1 + c20), c22) * c20) / (Math.pow((1 + c20), c22) - 1);
    }

    private double calcularCantViviendas(DistribucionOperatoria combinatoria){
        double cantViviendas = 0d;

        cantViviendas = Utiles.round(combinatoria.getAnioPlan().getCantViviendasAnio() * combinatoria.getDistribucionSector().getDistribucionCiudad().getDistribucionProvincial().getPorcentajeDistribucion() / 100, 0);
        cantViviendas = Utiles.round(cantViviendas * combinatoria.getDistribucionSector().getDistribucionCiudad().getPorcentajeDistribucion() / 100, 0);
        cantViviendas = Utiles.round(cantViviendas * combinatoria.getDistribucionSector().getPorcentajeDistribucion() / 100, 0);
        cantViviendas = Utiles.round(cantViviendas * combinatoria.getPorcentajeDistribucion() / 100, 0);

        return cantViviendas;
    }
}
