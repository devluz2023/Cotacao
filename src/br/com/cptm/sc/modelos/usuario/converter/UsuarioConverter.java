/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.cptm.sc.modelos.usuario.converter;

import br.com.cptm.sc.modelos.usuario.Usuario;
import br.com.cptm.sc.modelos.usuario.UsuarioRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fabio julio
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            UsuarioRN usuarioRN = new UsuarioRN();
            String matricula = value.trim();
            Usuario usuario = usuarioRN.buscarPorMatricula(matricula);
            return usuario;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Usuario usuario = (Usuario) value;
        return usuario.getMatricula();
    }
}
