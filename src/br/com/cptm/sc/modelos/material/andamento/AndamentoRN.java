package br.com.cptm.sc.modelos.material.andamento;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AndamentoRN {

	private final AndamentoJDBC andamentoJDBC;

	public AndamentoRN() {
		this.andamentoJDBC = new AndamentoJDBC();
	}

	public void salvar(Andamento andamento) {
		try {
			andamentoJDBC.salvar(andamento);
		} catch (SQLException ex) {
			Logger.getLogger(AndamentoRN.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public List<Andamento> lista(long materialId) {
		List<Andamento> andamentos = null;
		try {
			andamentos = andamentoJDBC.listar(materialId);
		} catch (SQLException ex) {
			Logger.getLogger(AndamentoRN.class.getName()).log(Level.SEVERE, null, ex);
		}
		return andamentos;
	}
}
