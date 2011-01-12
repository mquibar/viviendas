/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloCalculo;

/**
 *
 * @author desarrollo
 */
public class testModelo {

    double capSolicitado=190;
    double TNA=0.06;
    double comicionOtorgamiento=0.01;
    double gastosAdministrativos=0.01;
    int momentoOtorgamiento=0;
    int plazoGracia=2;
    
    public static void main(String[] args) {
    
        (new testModelo()).calcular();
        
    }


    public void calcular(){
        ModelDinamicTable md = new ModelDinamicTable(capSolicitado, TNA, comicionOtorgamiento, gastosAdministrativos, momentoOtorgamiento, plazoGracia);
        md.setCantAños(5);
        System.out.println("Monto Devengado = "+md.getMontoDevengado());
        System.out.println("Interes Mensual = " + md.getIntMensual()*100.0 + "%");
        System.out.println("Interes Trimestral = " +md.getIntTrimestral()*100.0 + "%");
        System.out.println("MONTO = " + md.getMonto());
        System.out.println("Iteres Total = " + md.getInteresTotal());
        System.out.println("Cuota Trimestral = " + md.getMontoCuotaTrimestrar());
        System.out.println("Momento Final = " + md.getMomentoFinGracia());
        System.out.println("Primer Pago = " + md.getPrimerPago());
        System.out.println("Ultimo Pago = " + md.getUltimoPago());
    }

    public static double round(double val, int places) {
        long factor = (long) Math.pow(10, places);
        // Shift the decimal the correct number of places
        // to the right.
        val = val * factor;
        // Round to the nearest integer.
        long tmp = Math.round(val);
        // Shift the decimal the correct number of places
        // back to the left.
        return (double) tmp / factor;
    }
}
