/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.flujoFondos;

import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import modeloCalculo.ModelDinamicTable;
import viviendas.entidades.flujo.InversionPlan;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.modulos.flujoFondos.GestorFlujoFondos;

/**
 *
 * @author Maximiliano.
 */
public class CtrlFlujoFondos {
    private IUFlujoFondos _pantalla;
    private GestorFlujoFondos _gestor;
    private ModelDinamicTable _modelo;

    public CtrlFlujoFondos(JDesktopPane desktop, DistribucionOperatoria distOp, Plan plan) {
        _gestor = new GestorFlujoFondos();
        _pantalla = new IUFlujoFondos();

        if(cargarTabla(distOp, plan)){
            cargarDatosPanel();
            desktop.add(_pantalla);
            _pantalla.setVisible(true);
        }
    }

    private void cargarDatosPanel(){
        
    }

    private boolean cargarTabla(DistribucionOperatoria distOp, Plan plan){
        double totalInversion = 0;
        //Buscamos todas las inversiones del plan:
        List<InversionPlan> listaInversionPlan = plan.getListaInversion();
        //De todas las inversiones del plan, buscamos cual es la inversion
        //para esta combinación y de ahi obtener el total de la inversion.
        //Para ello necesitamos saber la ciudad de la combinatoria (distribucionOperatoria)
        InversionPlan inversionPlan = null;
        Ciudad ciudad = distOp.getDistribucionSector().getDistribucionCiudad().getCuidad();
        for(InversionPlan inversion : listaInversionPlan){
            if(inversion.getCiudad().equals(ciudad)){
                inversionPlan = inversion;
                break;
            }
        }
        //totalInversion = 165000
        totalInversion = inversionPlan.getTotalInversion();

        if(distOp.getParametrosFlujoFondo() == null){
            JOptionPane.showMessageDialog(_pantalla, "No se han definido parametros de flujo de fondo.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        _modelo = new ModelDinamicTable(totalInversion, distOp.getParametrosFlujoFondo().getTna(), distOp.getParametrosFlujoFondo().getComisionOtorgamiento(), distOp.getParametrosFlujoFondo().getGastosAdministrativos(), distOp.getParametrosFlujoFondo().getMomentoOtorgamiento(), distOp.getParametrosFlujoFondo().getAnioGracia());
        _modelo.setCantAños(distOp.getAnioPlan().getPlan().getAniosPlan());
        _pantalla.getTbFlujoFondos().setModel(_modelo);
        return true;
    }

}