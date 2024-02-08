/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.cptm.sc.modelos.usuario.util;

import br.com.cptm.sc.modelos.usuario.Usuario;
import br.com.cptm.sc.modelos.usuario.UsuarioRN;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author fabiolu
 */
public class UsuarioUtil {
    
     private Usuario usuario;

     
    public Usuario getUsuario() {
        UsuarioRN usuarioRN = new UsuarioRN();
        this.usuario = usuarioRN.buscarPorLogin(getLogin());
        return usuario;

    }

    
    
    private String getLogin() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        String login = ec.getUserPrincipal().getName();
        return login;

    }
}
