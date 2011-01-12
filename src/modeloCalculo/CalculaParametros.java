/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloCalculo;

/**
 *
 * @author desarrollo
 */
public class CalculaParametros {

    public static double calculaInteresMensual(double Tna){
        return Tna/12;
    }

    public static double calculaInteresTrimestral(double Tna){
        return java.lang.Math.pow((1 + calculaInteresMensual(Tna)),3) - 1;
    }

    public static int calculaCuotasTrimestrales(int plazoAnios, int plazoGracia){
        return (plazoAnios - plazoGracia)*4;
    }

    public static double calculaMontoDevengado(double capitalSolicitado, double interesTrimestre, int plazoGracia){
        return capitalSolicitado *  java.lang.Math.pow( ( 1 + interesTrimestre ), (plazoGracia*4) );
    }

    public static double calculaMonto(int cuotasTrimestrales, double montoCuotaTrimestral){
        return cuotasTrimestrales * montoCuotaTrimestral;
    }

    public static double calculaInteresTotal(double capitalSolicitado, double monto){
        return monto - capitalSolicitado;
    }

    public static double calculaMontoCuotaTrimestral(double interesTrimestral, int cuotasTrimestrales, double montoDevengado){
        return (montoDevengado * (java.lang.Math.pow((1+interesTrimestral),cuotasTrimestrales)*interesTrimestral)/(java.lang.Math.pow((1+interesTrimestral),cuotasTrimestrales)-1));
    }

    public static int calculaMomentoFinGracia(int momentoOtorgamiento, int plazoGracia){
        return momentoOtorgamiento + plazoGracia * 12;
    }

    public static int calculaPrimerPago(int momentoFinGracia){
        return momentoFinGracia+3;
    }

    public static int calculaUltimoPago(int plazoGracia, int plazoAnios, int momentoFinGracia){
        return (plazoAnios - plazoGracia) * 12 + momentoFinGracia;
    }
    
    
}
