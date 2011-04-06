/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.modulos.flujoFondos;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import viviendas.entidades.flujo.ParametrosFlujoFondo;
import viviendas.entidades.vivienda.AnioPlan;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.DistribucionSector;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.gui.dto.DtoFlujoFondo;
import viviendas.gui.dto.DtoParametrosFlujoFondo;
import viviendas.gui.models.tables.ModelTableFlujoFondo;
import viviendas.modulos.parametros.GestorParametro;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.reports.DSFlujoFondo;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author Maximiliano.
 */
public class GestorFlujoFondos {

    public List<Plan> obtenerPlanes() {
        return Facade.getInstance().findAll(Plan.class);
    }

    public List<AnioPlan> obtenerAniosPlan(Plan plan) {
        Criterio criterioAños = new Criterio("plan", "=", plan);
        return Facade.getInstance().findByCriterio(AnioPlan.class, criterioAños);
    }

    public List<Ciudad> obtenerCiudades(Plan plan) {
        List<Ciudad> listaCiudades = new ArrayList<Ciudad>();
        for (AnioPlan anioPlan : plan.getListaAnioPlan()) {
            for (DistribucionCiudad distribucionCiudad : anioPlan.getDistribucionCiudad()) {
                if (!listaCiudades.contains(distribucionCiudad.getCuidad())) {
                    listaCiudades.add(distribucionCiudad.getCuidad());
                }
            }
        }
        return listaCiudades;
    }

    public List<Operatoria> obtenerOperatorias(Plan plan) {
        List<Operatoria> listaOperatoria = new ArrayList<Operatoria>();
        for (AnioPlan anioPlan : plan.getListaAnioPlan()) {
            for (DistribucionOperatoria distribucionOperatoria : anioPlan.getDistribucionOperatoria()) {
                if (!listaOperatoria.contains(distribucionOperatoria.getOperatoria())) {
                    listaOperatoria.add(distribucionOperatoria.getOperatoria());
                }
            }
        }
        return listaOperatoria;
    }

    public List<Provincia> obtenerProvincias(Plan plan) {
        List<Provincia> listaProvincia = new ArrayList<Provincia>();
        for (AnioPlan anioPlan : plan.getListaAnioPlan()) {
            for (DistribucionProvincial distribucionProvincia : anioPlan.getDistribucionProvincia()) {
                if (!listaProvincia.contains(distribucionProvincia.getProvincia())) {
                    listaProvincia.add(distribucionProvincia.getProvincia());
                }
            }
        }
        return listaProvincia;
    }

    public List<SectorEconomico> obtenerSectoresEconomicos(Plan plan) {
        List<SectorEconomico> listaSector = new ArrayList<SectorEconomico>();
        for (AnioPlan anioPlan : plan.getListaAnioPlan()) {
            for (DistribucionSector distribucionSector : anioPlan.getDistribucionSector()) {
                if (!listaSector.contains(distribucionSector.getSectorEconomico())) {
                    listaSector.add(distribucionSector.getSectorEconomico());
                }
            }
        }
        return listaSector;
    }

    public void guardar(DtoParametrosFlujoFondo _dto) throws BusinessOperationException {
        List<DistribucionOperatoria> listaDistribucionOperatoria;

        listaDistribucionOperatoria = GestorParametro.obtenerDistribucionOperatoria(_dto.getPlan(), _dto.getAnioPlan(), _dto.getProvincia(), _dto.getCiudad(), _dto.getSectorEconomico(), _dto.getOperatoria());

        if (!listaDistribucionOperatoria.isEmpty()) {
            ParametrosFlujoFondo parametroFlujoFondo;

            try {
                Facade.getInstance().beginTx();
                for (DistribucionOperatoria distribucionOperatoria : listaDistribucionOperatoria) {
                    parametroFlujoFondo = new ParametrosFlujoFondo();
                    parametroFlujoFondo.setTna(_dto.getTna());
                    parametroFlujoFondo.setGastosAdministrativos(_dto.getGastosAdministrativos());
                    parametroFlujoFondo.setComisionOtorgamiento(_dto.getComisionOtorgamiento());
                    parametroFlujoFondo.setMomentoOtorgamiento(_dto.getMomentoOtorgamiento());
                    parametroFlujoFondo.setAnioGracia(_dto.getPlazoGracia());
                    parametroFlujoFondo.setPerdidaIncobrables(_dto.getPerdidaIncobrables());
                    parametroFlujoFondo.setCantAniosTitulos(_dto.getCantAniosTitulos());
                    parametroFlujoFondo.setTnaTitulos(_dto.getTnaTitulos());

                    Facade.getInstance().guardar(parametroFlujoFondo);

                    distribucionOperatoria.setParametrosFlujoFondo(parametroFlujoFondo);
                    Facade.getInstance().actualizar(distribucionOperatoria);

                    parametroFlujoFondo = null;
                }
                Facade.getInstance().commitTx();
            } catch (PersistException ex) {
                throw new BusinessOperationException("Error al guardar.");
            }
        }
    }

    public JasperPrint imprimir(ModelTableFlujoFondo modelo){
        List<DtoFlujoFondo> listDto = new ArrayList<DtoFlujoFondo>();
        DtoFlujoFondo dto;
        JRDataSource datos;
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(new Date());

        Map parametros = new HashMap();

//        parametros.put("provincia", modelo.getCombinatoria().getDistribucionSector().getDistribucionCiudad().getDistribucionProvincial().getProvincia().getNombre());
//        parametros.put("ciudad", modelo.getCombinatoria().getDistribucionSector().getDistribucionCiudad().getCuidad().getNombre());
//        parametros.put("segmentoEconomico", modelo.getCombinatoria().getDistribucionSector().getSectorEconomico().getNombre());
//        parametros.put("operatoria", modelo.getCombinatoria().getOperatoria().getNombre());
//        parametros.put("fecha", calendario.get(Calendar.DAY_OF_MONTH) + "/" + (calendario.get(Calendar.MONTH) + 1) + "/" + calendario.get(Calendar.YEAR));
//        parametros.put("inversion", String.valueOf(modelo.getMontoInversion()));


        parametros.put("provincia", "");
        parametros.put("ciudad", "");
        parametros.put("segmentoEconomico", "");
        parametros.put("operatoria", "");
        parametros.put("fecha", "");
        parametros.put("inversion", "");


        for(int i=0; i<modelo.getRowCount(); i++){
            dto = new DtoFlujoFondo();

            dto.setNombre(modelo.getValueAt(i, 0).toString());
            dto.setPorcentaje(modelo.getValueAt(i, 1).toString());
            dto.setValor(modelo.getValueAt(i, 2).toString());

            listDto.add(dto);
            dto = null;
        }

        datos = new DSFlujoFondo(listDto);
        return printReport(parametros, "/viviendas/reports/ReporteFlujoFondos", datos);
    }

    JasperPrint printReport(Map parametros, String xmlFile, JRDataSource datos) {
        InputStream lis = getStream(xmlFile);
        System.out.println(lis);
        JasperPrint jp = null;
        try {
            JasperDesign jd = JRXmlLoader.load(lis);

            JasperReport jr = JasperCompileManager.compileReport(jd);            

            jp = JasperFillManager.fillReport(jr, parametros, datos);

            lis.close();
            jd = null;
            jr = null;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error creando jasperprint: " + ex.toString());
        }
        return jp;
    }


    InputStream getStream(String xmlFile) {
        InputStream lis = null;

        try {
            String fileName =  xmlFile + ".jrxml";
            lis = getClass().getResourceAsStream(fileName);
        } catch (Exception ex) {
            System.out.print("Error creando stream: " + ex.toString());
        }

        return lis;
    }
}
