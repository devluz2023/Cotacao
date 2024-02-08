package br.com.cptm.sc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.File;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class RelatorioCompleto {

	public StreamedContent geraRelatorio(List<Object> lista, String relatorio) {

		StreamedContent arquivoRetorno = null;

		try {
			HashMap<String, Object> mapa = null;

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);

			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();

			String jasper = servletContext.getRealPath("") + File.separator + "resources/relatorios" + File.separator
					+ relatorio + "." + "jasper";

			JasperPrint impressao = JasperFillManager.fillReport(jasper, mapa, beanCollectionDataSource);

			JRExporter tipoArquivoExportado = new JRXlsExporter();

			String relatorioXLS = servletContext.getRealPath("") + File.separator + "resources/relatorios"
					+ File.separator + relatorio + "." + "xls";

			File arquivoGerado = new java.io.File(relatorioXLS);

			tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
			tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			tipoArquivoExportado.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
			tipoArquivoExportado.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			tipoArquivoExportado.exportReport();
			arquivoGerado.deleteOnExit();

			InputStream conteudoRelatorio = null;
			try {
				conteudoRelatorio = new FileInputStream(arquivoGerado);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/vnd.ms-excel", relatorio +".xls");

		} catch (JRException e) {
			try {
				throw new UtilException("Não foi possível gerar o relatório.", e);
			} catch (UtilException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return arquivoRetorno;

	}

}
