/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.utiles;

import java.util.List;

/**
 *
 * @author maximiliano
 */
public class Utiles {
    public static void verificarPorcentajes(List<Double> porcentajes) throws Exception{
        double suma = 0d;

        for(int i=0; i<porcentajes.size(); i++){
            suma = suma + porcentajes.get(i).doubleValue();
        }

        if(suma > 100)
            throw new Exception("la totalidad de los porcentajes, exceden el 100%");
        else if(suma < 100)
            throw  new Exception("la totalidad de los porcentajes, son inferiores al 100%");
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