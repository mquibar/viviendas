/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.utiles;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author maximiliano
 */
public class Utiles {

    public static void verificarPorcentajes(List<Double> porcentajes) throws Exception {
        double suma = 0d;

        for (int i = 0; i < porcentajes.size(); i++) {
            suma = suma + porcentajes.get(i).doubleValue();
        }

        if (suma > 100) {
            throw new Exception("la totalidad de los porcentajes, exceden el 100%");
        } else if (suma < 100) {
            throw new Exception("la totalidad de los porcentajes, son inferiores al 100%");
        }
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

    @SuppressWarnings("unchecked")
    public static void ordena(List lista, final String propiedad) {
        Collections.sort(lista, new Comparator() {

            public int compare(Object obj1, Object obj2) {

                Class clase = obj1.getClass();
                String getter = "get" + Character.toUpperCase(propiedad.charAt(0)) + propiedad.substring(1);
                try {
                    Method getPropiedad = clase.getMethod(getter);

                    Object propiedad1 = getPropiedad.invoke(obj1);
                    Object propiedad2 = getPropiedad.invoke(obj2);

                    if (propiedad1 instanceof Comparable && propiedad2 instanceof Comparable) {
                        Comparable prop1 = (Comparable) propiedad1;
                        Comparable prop2 = (Comparable) propiedad2;
                        return prop1.compareTo(prop2);
                    }//CASO DE QUE NO SEA COMPARABLE
                    else {
                        if (propiedad1.equals(propiedad2)) {
                            return 0;
                        } else {
                            return 1;
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    public static boolean validarStringNumericoDouble(String cadena) {
        return java.util.regex.Pattern.matches("[0123456789.,]*", cadena);
    }

    public static boolean validarStringNumerico(String cadena) {
        return java.util.regex.Pattern.matches("[0123456789]*", cadena);
    }
}
