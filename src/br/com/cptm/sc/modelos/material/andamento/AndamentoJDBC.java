package br.com.cptm.sc.modelos.material.andamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cptm.sc.config.ConnectionFactory;

public class AndamentoJDBC {

	public void salvar(Andamento andamento) throws SQLException {
		String sql = "insert into cotacao_material_andamento (dataDoAndamento, andamento, materialID)"
				+ "values(?,?,?)";
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(andamento.getDataDoAndamento().getTime()));
			stmt.setString(2, andamento.getStatus());
			stmt.setLong(3, andamento.getMaterialId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			connection.rollback();

		} finally {
			connection.close();
			stmt.close();
		}

	}

	public List<Andamento> listar(long materialId) throws SQLException {
		String sql = "select *from cotacao_material_andamento where materialId = ?";
		List<Andamento> andamentos = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;

		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, materialId);
			rst = stmt.executeQuery();
			while (rst.next()) {
				Andamento andamento = new Andamento();
				andamento.setId(rst.getLong("id"));
				andamento.setDataDoAndamento(rst.getDate("dataDoAndamento"));
				andamento.setStatus(rst.getString("andamento"));
				andamento.setMaterialId(rst.getLong("materialId"));
				andamentos.add(andamento);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rst.close();
			stmt.close();
			connection.close();
		}

		return andamentos;

	}

}
