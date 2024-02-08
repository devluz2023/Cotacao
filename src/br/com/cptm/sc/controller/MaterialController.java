/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cptm.sc.controller;

import br.com.cptm.sc.modelos.material.Material;
import br.com.cptm.sc.modelos.material.MaterialRN;
import br.com.cptm.sc.modelos.material.andamento.Andamento;
import br.com.cptm.sc.modelos.usuario.util.UsuarioUtil;
import br.com.cptm.sc.util.RelatorioCompleto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class MaterialController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Material material;
	private List<Material> materiaisSelecionados;
	private List<Material> materiaisFiltrados;
	private List<Material> materiais;
	private String status;
	private StreamedContent arquivoRetorno;
	private int relatorio;

	@PostConstruct
	public void init() {
		this.material = new Material();
		this.material.setDataSolicitacao(new Date());
		this.material.setStatus("Solicitada");
		this.material.setUsuario(new UsuarioUtil().getUsuario());

	}

	public String salvar() {
		MaterialRN materialRN = new MaterialRN();
		materialRN.salvar(this.material);
		this.material = new Material();
		return "home.xhtml";
	}

	public StreamedContent getArquivoRetorno() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (!materiaisSelecionados.isEmpty()) {
			RelatorioCompleto relatorioCompleto = new RelatorioCompleto();
			String arquivo = "";
					
			if (relatorio == 1) {
				arquivo = "cotacao";
			} else {
				arquivo = "listagem";
			}

			this.arquivoRetorno = relatorioCompleto.geraRelatorio(new ArrayList<Object>(materiaisSelecionados),
					arquivo);

		} else {
			context.addMessage(null, new FacesMessage("Nada Selecionado"));
		}

		return this.arquivoRetorno;

	}

	public void editar(RowEditEvent event) {
		Material m = ((Material) event.getObject());
		MaterialRN materialRN = new MaterialRN();
		materialRN.salvar(m);
	}

	public void buscar(long id) {
		this.material = new MaterialRN().buscar(id);
	}

	public void mudarStatus() {
		if (!materiaisSelecionados.isEmpty()) {
			MaterialRN materialRN = new MaterialRN();
			for (Material m : materiaisSelecionados) {

				Andamento andamento = new Andamento();
				andamento.setDataDoAndamento(m.getDataSolicitacao());
				andamento.setMaterialId(m.getId());
				andamento.setStatus(m.getStatus());
				m.adicionarAndamento(andamento);
				m.setStatus(status);
				m.setDataSolicitacao(new Date());
				materialRN.salvar(m);
			}
		}
	}

	public List<Material> getMateriais() {

		if (materiais == null) {
			this.materiais = new MaterialRN().listar();
		}
		return materiais;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public List<Material> getMateriaisSelecionados() {
		return materiaisSelecionados;
	}

	public void setMateriaisSelecionados(List<Material> materiaisSelecionados) {
		this.materiaisSelecionados = materiaisSelecionados;
	}

	public List<Material> getMateriaisFiltrados() {
		return materiaisFiltrados;
	}

	public void setMateriaisFiltrados(List<Material> materiaisFiltrados) {
		this.materiaisFiltrados = materiaisFiltrados;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setArquivoRetorno(StreamedContent arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}

	public int getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(int relatorio) {
		this.relatorio = relatorio;
	}

}
