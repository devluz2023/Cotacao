/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cptm.sc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author 1366
 */
public class RelatorioUtil {

    private static final int RELATORIO_PDF = 1;
    private static final int RELATORIO_EXCEL = 2;

    public StreamedContent gerarRelatorio(List<Object> objetos, String nomeRelatorioSaida,
            String nomeRelatorioJasper, int tipoRelatorio, HashMap<String, Object> mapa) throws JRException, IOException {

        JRBeanCollectionDataSource beanCollectionDataSource =
                new JRBeanCollectionDataSource(objetos);

        ServletContext servletContext =
                (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        String jasper = servletContext.getRealPath("") + File.separator + "resources/relatorio"
                + File.separator + nomeRelatorioJasper + "." + "jasper";

        String extensao = "";
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, mapa, beanCollectionDataSource);

        StreamedContent arquivoRetorno = null;
        File arquivoGerado = null;
        JRExporter tipoArquivoExportado = null;

        switch (tipoRelatorio) {

            case RELATORIO_PDF:
                tipoArquivoExportado = new JRPdfExporter();
                extensao = "pdf";
                break;
            case RELATORIO_EXCEL:
                tipoArquivoExportado = new JRXlsExporter();
                extensao = "xls";
                break;
        }

        String caminhoRelatorio = servletContext.getRealPath("") + File.separator + "resources/relatorio"
                + File.separator + nomeRelatorioSaida + "." + extensao;
        arquivoGerado = new java.io.File(caminhoRelatorio);
        tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
        tipoArquivoExportado.exportReport();
        arquivoGerado.deleteOnExit();
        InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
        arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio,
                "application/" + extensao, nomeRelatorioSaida + "." + extensao);
        return arquivoRetorno;
    }

    
    
    public void imprimir(List<Object> lista, String nomeRelatorioSaida, String nomeRelatorioJasper,
            HashMap<String, Object> mapa) {
        try {
            JRBeanCollectionDataSource beanCollectionDataSource =
                    new JRBeanCollectionDataSource(lista);
            
            ServletContext servletContext =
                    (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            
            String jasper = servletContext.getRealPath("") + File.separator + "resources/relatorios"
                    + File.separator + nomeRelatorioJasper + "." + "jasper";
            
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            JasperPrint impressao =
                    JasperFillManager.fillReport(jasper, mapa, beanCollectionDataSource);
            byte[] bytes = JasperExportManager.exportReportToPdf(impressao);
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline;" + nomeRelatorioSaida + ".pdf");
            response.getOutputStream().write(bytes);
            response.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            Logger.getLogger(RelatorioUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
