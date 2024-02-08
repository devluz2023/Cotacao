/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cptm.sc.modelos.usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabiolu
 */
public class UsuarioRN {

    public void salvar(Usuario usuario) {
        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
        try {
            if (usuario.getId() == 0) {
                usuarioJDBC.salvar(usuario);
            } else {
                usuarioJDBC.editar(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario buscarPorMatricula(String matricula) {
        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
        Usuario usuario = null;
        try {
            usuario = usuarioJDBC.buscarPorMatricula(matricula);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public Usuario buscarPorLogin(String login) {
        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
        Usuario usuario = null;
        try {
            usuario = usuarioJDBC.buscarPorLogin(login);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = null;
        try {
            usuarios = new UsuarioJDBC().listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuarios;

    }

  

}
