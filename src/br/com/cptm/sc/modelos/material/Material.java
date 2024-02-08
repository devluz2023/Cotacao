/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cptm.sc.modelos.material;

import br.com.cptm.sc.modelos.material.andamento.Andamento;
import br.com.cptm.sc.modelos.material.andamento.AndamentoRN;
import br.com.cptm.sc.modelos.usuario.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fabiolu
 */
public class Material implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1844986646900332111L;
	private long id;
	private String codigoCPTM;
	private String descricao;
	private String osm;
	private double quantidade;
	private String tipo;
	private String status;
	private Usuario usuario;
	private Date dataSolicitacao;
	private String unidade;
	private String sff;
	private List<Andamento> listaAndamento = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigoCPTM() {
		return codigoCPTM;
	}

	public void setCodigoCPTM(String codigoCPTM) {
		this.codigoCPTM = codigoCPTM;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOsm() {
		return osm;
	}

	public void setOsm(String osm) {
		this.osm = osm;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getSff() {
		return sff;
	}

	public void setSff(String sff) {
		this.sff = sff;
	}

	public void adicionarAndamento(Andamento andamento) {
		this.listaAndamento.add(andamento);
		AndamentoRN andamentoRN = new AndamentoRN();
		andamentoRN.salvar(andamento);
	}
	
	

	public List<Andamento> getListaAndamento() {
		return listaAndamento;
	}

	public void setListaAndamento(List<Andamento> listaAndamento) {
		this.listaAndamento = listaAndamento;
	}

}
