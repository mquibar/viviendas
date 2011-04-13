/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.reports;

import java.awt.BorderLayout;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import viviendas.gui.sistema.CtrlPrincipal;

/**
 *
 * @author desarrollo
 */
public class CtrlViewerReport {

    private static CtrlViewerReport instance;

    public static CtrlViewerReport getInstance() {
        if(instance == null)
            instance = new CtrlViewerReport();
        return instance;
    }

    private CtrlViewerReport(){

    }

    public void verReporte(JasperPrint jasperPrint, String title){
        if(UIViewerReport.getInstance().isWindViewerOpen()){
            UIViewerReport.getInstance().toFront();
            return;
        }
        JRViewer jrViewer = new JRViewer(jasperPrint);
        UIViewerReport.getInstance().setWindViewerOpen(true);
        UIViewerReport.getInstance().getContentPane().add(jrViewer,BorderLayout.CENTER);
        UIViewerReport.getInstance().setTitle(title);
        CtrlPrincipal.getInstance().getDesktopPane().add(UIViewerReport.getInstance());
        UIViewerReport.getInstance().setVisible(true);
    }
}
