/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cptm.sc.modelos.usuario;

import br.com.cptm.sc.config.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabiolu
 */
public class UsuarioJDBC {

    void salvar(Usuario usuario) throws SQLException {
        String sql = "insert into solicitante (nome, matricula, login, senha, telefone) "
                + "values (?,?,?,md5(?))";
        PreparedStatement stmt = null;
        Connection connection = null;
        try {
            connection = new ConnectionFactory().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getMatricula());
            stmt.setString(3, usuario.getLogin());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTelefone());

            stmt.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            connection.close();
        }
    }

    void editar(Usuario usuario) throws SQLException {
        String sql = "update solicitante set nome=?, matricula=?, login=?, senha=md5(?), telefone=?"
                + " where id =?";
        PreparedStatement stmt = null;
        Connection connection = null;
        try {
            connection = new ConnectionFactory().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getMatricula());
            stmt.setString(3, usuario.getLogin());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTelefone());
            stmt.setLong(6, usuario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            connection.close();
        }
    }

    Usuario buscarPorLogin(String login) throws SQLException {
        String sql = "select *from solicitante where login =?";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        Usuario usuario = null;
        try {
            connection = new ConnectionFactory().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setLogin(resultSet.getString("login"));
                usuario.setId(resultSet.getLong("id"));
                usuario.setTelefone(resultSet.getString("telefone"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setMatricula(resultSet.getString("matricula"));
                usuario.setStatus(resultSet.getBoolean("status"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            resultSet.close();
            connection.close();
        }
        return usuario;

    }

    Usuario buscarPorMatricula(String matricula) throws SQLException {
        String sql = "select *from solicitante where matricula  =?";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        Usuario usuario = null;
        try {
            connection = new ConnectionFactory().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, matricula);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setLogin(resultSet.getString("login"));
                usuario.setId(resultSet.getLong("id"));
                usuario.setTelefone(resultSet.getString("telefone"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setMatricula(resultSet.getString("matricula"));
                usuario.setStatus(resultSet.getBoolean("status"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            resultSet.close();
            connection.close();
        }
        return usuario;
    }

    List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "select *from solicitante WHERE MANTENEDOR = TRUE and setor='lab'"
                + "order by nome";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        try {
            connection = new ConnectionFactory().getConnection();
            stmt = connection.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin(resultSet.getString("login"));
                usuario.setId(resultSet.getLong("id"));
                usuario.setTelefone(resultSet.getString("telefone"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setMatricula(resultSet.getString("matricula"));
                usuario.setStatus(resultSet.getBoolean("status"));
                usuarios.add(usuario);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            resultSet.close();
            connection.close();
        }
        return usuarios;
    }

 
}
