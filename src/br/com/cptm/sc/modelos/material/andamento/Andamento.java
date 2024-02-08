package br.com.cptm.sc.modelos.material.andamento;

import java.util.Date;

public class Andamento {

	private long id;
	private Date dataDoAndamento;
	private String status;
	private long materialId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataDoAndamento() {
		return dataDoAndamento;
	}
	public void setDataDoAndamento(Date dataDoAndamento) {
		this.dataDoAndamento = dataDoAndamento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}
	
	
	
}
